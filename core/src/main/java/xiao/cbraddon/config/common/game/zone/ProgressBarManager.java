package xiao.cbraddon.config.common.game.zone;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xiao.battleroyale.BattleRoyale;
import xiao.battleroyale.api.common.McSide;
import xiao.battleroyale.api.event.CustomEventType;
import xiao.battleroyale.api.event.ICustomEvent;
import xiao.battleroyale.api.event.ICustomEventHandler;
import xiao.battleroyale.api.event.ICustomEventRegister;
import xiao.battleroyale.api.event.game.finish.GameStopFinishEvent;
import xiao.battleroyale.api.event.game.starter.GameStartFinishEvent;
import xiao.battleroyale.api.event.game.tick.ZoneTickFinishEvent;
import xiao.battleroyale.api.event.game.zone.ZoneCompleteEvent;
import xiao.battleroyale.api.event.game.zone.ZoneCreatedEvent;
import xiao.battleroyale.api.game.IGameManager;
import xiao.battleroyale.api.game.zone.gamezone.IGameZone;
import xiao.battleroyale.config.common.game.zone.zonefunc.ZoneFuncType;
import xiao.battleroyale.data.io.TempDataManager;
import xiao.battleroyale.util.WorldUtils;
import xiao.cbraddon.CbrAddon;
import xiao.cbraddon.api.data.CbraTempDataTag;

import java.util.UUID;

/**
 * 放 zone 而不放 progress 的原因：
 * <ol>
 *     <li>progress 维护 zone 信息职责越界</li>
 *     <li>更新挂在 zoneTick 事件上</li>
 * </ol>
 */
public class ProgressBarManager implements ICustomEventHandler {

    private static class ProgressBarManagerHolder {
        private static final ProgressBarManager INSTANCE = new ProgressBarManager();
    }

    public static ProgressBarManager get() {
        return ProgressBarManagerHolder.INSTANCE;
    }

    protected ProgressBarManager() {
    }

    public static void init(McSide mcSide) {
        TempDataManager tempDataManager = TempDataManager.get();
        Boolean enable = tempDataManager.getBool(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.ENABLE_PROGRESS_BAR);
        if (enable != null && enable) {
            get().enable();
        }
    }

    private volatile ProgressBarProtocol progressBarProtocol;
    public final UUID progressBarUUID = UUID.nameUUIDFromBytes("cbraddon:progress_bar_protocol".getBytes());
    public ProgressBarProtocol getProgressBarProtocol() {
        return this.progressBarProtocol;
    }

    protected final Context context = new Context();

    @ApiStatus.Internal
    private boolean registerGameEventHandler() {
        ICustomEventRegister customEventRegister = BattleRoyale.getEventRegister();
        var _this = get();
        customEventRegister.register(_this, CustomEventType.GAME_START_FINISH_EVENT);
        customEventRegister.register(_this, CustomEventType.ZONE_TICK_FINISH_EVENT);
        customEventRegister.register(_this, CustomEventType.ZONE_CREATED_EVENT);
        customEventRegister.register(_this, CustomEventType.ZONE_COMPLETE_EVENT);
        customEventRegister.register(_this, CustomEventType.GAME_STOP_FINISH_EVENT);
        return true;
    }

    @ApiStatus.Internal
    private boolean unregisterGameEventHandler() {
        ICustomEventRegister customEventRegister = BattleRoyale.getEventRegister();
        var _this = get();
        customEventRegister.unregister(_this, CustomEventType.GAME_START_FINISH_EVENT);
        customEventRegister.unregister(_this, CustomEventType.ZONE_TICK_FINISH_EVENT);
        customEventRegister.unregister(_this, CustomEventType.ZONE_CREATED_EVENT);
        customEventRegister.unregister(_this, CustomEventType.ZONE_COMPLETE_EVENT);
        customEventRegister.unregister(_this, CustomEventType.GAME_STOP_FINISH_EVENT);
        return true;
    }

    public static final String _MANAGER_NAME = String.format("%s:ProgressBarManager", CbrAddon.MOD_ID);
    @Override
    public String getEventHandlerName() {
        return _MANAGER_NAME;
    }

    @Override
    public void handleEvent(CustomEventType customEventType, ICustomEvent customEvent) {
        switch (customEventType) {
            case GAME_START_FINISH_EVENT -> this.onGameStartFinish((GameStartFinishEvent) customEvent);
            case ZONE_TICK_FINISH_EVENT -> this.onZoneTickFinish((ZoneTickFinishEvent) customEvent);
            case ZONE_CREATED_EVENT -> this.onZoneCreated((ZoneCreatedEvent) customEvent);
            case ZONE_COMPLETE_EVENT -> this.onZoneComplete((ZoneCompleteEvent) customEvent);
            case GAME_STOP_FINISH_EVENT -> this.onGameStopFinish((GameStopFinishEvent) customEvent);
            default -> onReceiveWrongEvent(customEventType);
        }
    }

    public void enable() {
        JsonObject jsonTag = TempDataManager.get().getJsonObject(CbraTempDataTag.CBR_ADDON, CbraTempDataTag.PROGRESS_BAR_PROTOCOL);

        this.progressBarProtocol = ProgressBarProtocol.getConfigFromProtocol("", jsonTag != null ? jsonTag : new JsonObject());
        this.registerGameEventHandler();

        // 不需要清理context，已启用的时候再次enable用来重新读jsonTag
    }

    public void disable() {
        this.progressBarProtocol = null;
        this.unregisterGameEventHandler();

        this.context.clear();
        this._removeBossBarToAllPlayers(null);
    }

    @ApiStatus.Internal
    protected void onGameStartFinish(GameStartFinishEvent event) {
        this.context.clear();
    }

    @ApiStatus.Internal
    protected void onZoneTickFinish(ZoneTickFinishEvent event) {
        IGameManager gameManager = BattleRoyale.getGameManager();
        if (!gameManager.isInGame()) return;

        // ----tick----
        int zoneId = this.context.currentZoneId;
        if (zoneId < 0) return;

        // ----更新信息----
        boolean needSend = this.updateProgressBarCache(gameManager);

        // ----发boss栏----
        if (needSend && this.progressBarProtocol != null) {
            this.sendProgressBarToGamePlayers(gameManager);
        }
    }
    /**
     * @return 是否需要更新信息
     */
    private boolean updateProgressBarCache(IGameManager gameManager) {
        IGameZone gameZone = gameManager.getGameZoneReadApi().getGameZone(this.context.currentZoneId);
        if (gameZone != null) {
            int currentGameTime = gameManager.getGameTime();
            int currentZoneTime = currentGameTime - gameZone.getZoneDelay();
            int moveDelay = gameZone.getShapeMoveDelay();
            this.context.moveStarted = moveDelay <= currentZoneTime;

            int secondsTotal = this.calculateSecondsTotal(gameZone, currentZoneTime, moveDelay);

            // 更新Component cache
            if (secondsTotal != this.context.secondsTotal || Math.abs(currentGameTime - this.context.lastUpdateGameTime) >= 20) {
                int minutes = secondsTotal / 60;
                int seconds = secondsTotal % 60;
                this.context.barProgress = this.calculateBarProgress(gameZone, currentZoneTime, moveDelay);
                //   ZoneName | 00:01
                // --------------------
                this.context.progressBarComponentCache = Component.literal(String.format("%s | %02d:%02d", gameZone.getZoneName(), minutes, seconds));
                this.context.lastUpdateGameTime = currentGameTime;
                this.context.secondsTotal = secondsTotal;
                return true;
            }
        }
        return false;
    }
    /**
     * 计算进度条总秒数
     * @param currentZoneTime 当前区域时长 (tick)
     * @param moveDelay 当前区域移动延迟 (tick)
     */
    private int calculateSecondsTotal(IGameZone gameZone, int currentZoneTime, int moveDelay) {
        int secondsTotal;
        if (this.context.moveStarted) {
            /* 缩圈移动阶段
            进度条为倒计时, 逐渐减少
             */
            int ticksElapsed = currentZoneTime - moveDelay; // 已经移动了多少 Tick
            int ticksLeft = gameZone.getShapeMoveTime() - ticksElapsed; // 还剩多少 Tick

            secondsTotal = Math.max(0, ticksLeft / 20); // 倒计时秒数
        } else {
            /* 缩圈延迟阶段
            进度条为缩圈进度, 逐渐增加
             */
            int ticksLeft = moveDelay - currentZoneTime; // 还剩多少 Tick

            secondsTotal = Math.max(0, ticksLeft / 20); // 倒计时秒数
        }
        return secondsTotal;
    }
    /**
     * 计算进度条比例
     * @param gameZone 当前区域对象
     * @param currentZoneTime 当前区域时长 (tick)
     * @param moveDelay 当前区域移动延迟 (tick)
     */
    private float calculateBarProgress(IGameZone gameZone, int currentZoneTime, int moveDelay) {
        float barProgress;
        int barLength;
        if (this.context.moveStarted) { // 缩圈移动阶段
            barLength = gameZone.getShapeMoveTime();
            int ticksElapsed = currentZoneTime - moveDelay;
            barProgress = barLength != 0 ? (float) ticksElapsed / barLength : 1f;
        } else { // 缩圈延迟阶段
            barLength = moveDelay;
            barProgress = barLength != 0 ? 1 - (float) currentZoneTime / barLength : 1f;
        }
        return Mth.clamp(barProgress, 0f, 1f);
    }
    private void sendProgressBarToGamePlayers(IGameManager gameManager) {
        ServerLevel serverLevel = gameManager.getServerLevel();
        if (serverLevel == null) return;

        // 缩圈移动阶段
        if (this.context.moveStarted) WorldUtils.sendBossBar(serverLevel, gameManager.getTeamManager().getGamePlayers(), this.progressBarUUID, this.context.progressBarComponentCache,
                this.context.barProgress, this.progressBarProtocol.moveTime_color, this.progressBarProtocol.moveTime_overlay);
            // 缩圈延迟阶段
        else WorldUtils.sendBossBar(serverLevel, gameManager.getTeamManager().getGamePlayers(), this.progressBarUUID, this.context.progressBarComponentCache,
                this.context.barProgress, this.progressBarProtocol.moveDelay_color, this.progressBarProtocol.moveDelay_overlay);
    }

    @ApiStatus.Internal
    protected void onZoneCreated(ZoneCreatedEvent event) {
        if (!event.isSuccess()) return;

        IGameZone gameZone = event.getGameZone();
        if (gameZone.getFuncType() != ZoneFuncType.SAFE) { // 这个可以去掉
            return;
        }

        int zoneId = gameZone.getZoneId();
        if (this.progressBarProtocol != null) {
            if (!this.progressBarProtocol.zoneId_regex.matcher(Integer.toString(zoneId)).matches()) {
                return;
            }
        }

        this.context.currentZoneId = zoneId;
        CbrAddon.LOGGER.debug("{}: Set progressBar zoneId to {}", _MANAGER_NAME, zoneId);
    }

    @ApiStatus.Internal
    protected void onZoneComplete(ZoneCompleteEvent event) {
        IGameManager gameManager = BattleRoyale.getGameManager();
        if (!gameManager.isInGame()) return;

        if (event.getGameZone().getZoneId() == this.context.currentZoneId) {
            this.context.currentZoneId = -1;
            this._removeBossBarToAllPlayers(gameManager.getServerLevel());
        }
    }

    @ApiStatus.Internal
    protected void onGameStopFinish(GameStopFinishEvent event) {
        this.context.clear();
        this._removeBossBarToAllPlayers(event.getGameManager().getServerLevel());
    }


    private void _removeBossBarToAllPlayers(ServerLevel serverLevel) {
        if (serverLevel != null) {
            serverLevel.players().forEach(player -> WorldUtils.removeBossBar(player, this.progressBarUUID));
        } else { // 游戏维度没了，玩家大概都到其他维度了
            BattleRoyale.getMinecraftServer().getPlayerList().getPlayers().forEach(player -> WorldUtils.removeBossBar(player, this.progressBarUUID));
        }
    }

    public static class Context {

        public int lastUpdateGameTime = -1;
        public int currentZoneId = -1;
        public int secondsTotal = 0;
        public float barProgress = 0f;
        public @Nullable Component progressBarComponentCache;
        public boolean moveStarted = false;

        public Context() {
        }

        public void clear() {
            this.lastUpdateGameTime = -1;
            this.currentZoneId = -1;
            this.secondsTotal = 0;
            this.barProgress = 0f;
            this.progressBarComponentCache = null;
            this.moveStarted = false;
        }
    }
}
