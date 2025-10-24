package xiao.cbraddon.event.game;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import xiao.battleroyale.api.event.game.zone.EntityEvent;
import xiao.battleroyale.api.event.CustomEventType;
import xiao.battleroyale.api.event.ICustomEvent;
import xiao.battleroyale.api.event.ICustomEventHandler;
import xiao.battleroyale.api.game.IGameManager;
import xiao.battleroyale.api.game.zone.gamezone.IGameZone;
import xiao.battleroyale.common.game.zone.ZoneManager;
import xiao.battleroyale.util.Vec3Utils;
import xiao.cbraddon.CbrAddon;
import xiao.cbraddon.config.common.game.zone.EntityFuncProtocol;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EntityFuncHandler implements ICustomEventHandler {

    private static class EntityFuncHandlerHolder {
        private static final EntityFuncHandler INSTANCE = new EntityFuncHandler();
    }

    public static EntityFuncHandler get() {
        return EntityFuncHandlerHolder.INSTANCE;
    }

    private EntityFuncHandler() {}

    @Override
    public String getEventHandlerName() {
        return "cbra:EntityFuncHandler";
    }

    @Override
    public void handleEvent(CustomEventType customEventType, ICustomEvent customEvent) {
        if (customEventType == CustomEventType.ENTITY_EVENT) {
            // 检查协议是否指向该模组
            EntityEvent entityEvent = (EntityEvent) customEvent;
            EntityFuncProtocol entityFuncProtocol = EntityFuncProtocol.getConfigFromProtocol(entityEvent.getProtocol(), entityEvent.getJsonTag());
            if (entityFuncProtocol == null) {
                return;
            }

            // 基本API
            IGameManager gameManager = entityEvent.getGameManager();
            ZoneManager.ZoneTickContext zoneTickContext = entityEvent.getZoneTickContext();
            ServerLevel serverLevel = gameManager.getServerLevel();

            // GameZone信息
            int zoneId = zoneTickContext.zoneId;
            @Nullable IGameZone gameZone = zoneTickContext.gameZones.get(zoneId);
            if (gameZone == null) {
                return;
            } else if (!gameZone.isDetermined()) {
                return;
            }
            double shapeProgress = gameZone.getShapeProgress(zoneTickContext.gameTime);
            Vec3 zoneCenter = gameZone.getCenterPos(shapeProgress);
            Vec3 zoneDimension = gameZone.getDimension(shapeProgress);
            assert zoneCenter != null;
            assert zoneDimension != null;

            // 生成模拟点位
            List<Entity> lootEntities = entityEvent.getLootEntities();
            int minSimulation = (int) (lootEntities.size() * entityFuncProtocol.lootFactor + entityFuncProtocol.fixedSimulation);
            List<Vec3> pendingPos = entityFuncProtocol.distribution.distributed(zoneCenter, zoneDimension,
                    minSimulation,
                    entityFuncProtocol.allowOnBorder,
                    entityFuncProtocol.globalShrinkRatio);
            // 打乱点位
            if (entityFuncProtocol.needShuffle) {
                Collections.shuffle(pendingPos, CbrAddon.COMMON_RANDOM);
            }

            // 随机偏移
            if (entityFuncProtocol.randomRange != Vec3.ZERO) {
                pendingPos.replaceAll(v -> Vec3Utils.randomAdjustXYZ(v, entityFuncProtocol.randomRange, zoneTickContext.random));
            }

            // 从区域顶面往下射线检测找地面
            if (entityFuncProtocol.findGround) {
                Double zoneTopHeight = gameZone.getTopCenterPos(shapeProgress);
                Double zoneBottomHeight = gameZone.getBottomCenterPos(shapeProgress);
                assert zoneTopHeight != null;
                assert zoneBottomHeight != null;
                for (int i = 0; i < pendingPos.size(); i++) {
                    // 用TeleportSpawner的方式
                    Vec3 basePos = pendingPos.get(i);
                    BlockPos lookupPos = new BlockPos((int) basePos.x, (int) (double) zoneTopHeight, (int) basePos.z);
                    int groundY = serverLevel.getHeight(Heightmap.Types.MOTION_BLOCKING, lookupPos.getX(), lookupPos.getZ());
                    double targetY = groundY + 1;
                    // 下限
                    if (targetY < serverLevel.getMinBuildHeight() + 2) {
                        CbrAddon.LOGGER.debug("EntityFuncHandler attempt to use invalid targetY {}", targetY);
                    }
                    if (targetY < zoneBottomHeight && entityFuncProtocol.limitToBottom) {
                        targetY = zoneBottomHeight;
                    }
                    // 上限
                    if (targetY > zoneTopHeight && entityFuncProtocol.limitToTop) {
                        targetY = zoneTopHeight;
                    }
                    pendingPos.set(i, new Vec3(basePos.x, targetY, basePos.z));
                }
            }

            // 额外偏移 (比如TNT需要在找到地面之后往上偏移固定距离使得在落地时爆炸)
            if (entityFuncProtocol.additionalOffset != Vec3.ZERO) {
                pendingPos.replaceAll(v -> v.add(entityFuncProtocol.additionalOffset));
            }

            // 排除不在圈里的
            if (entityFuncProtocol.ignoreOutside) {
                List<Vec3> inside = new ArrayList<>(pendingPos.size());
                for (Vec3 pos : pendingPos) {
                    if (gameZone.isWithinZone(pos, shapeProgress)) {
                        inside.add(pos);
                    }
                }
                if (pendingPos.size() != inside.size()) {
                    pendingPos.clear();
                    pendingPos = inside;
                }
            }
            int pendingSize = pendingPos.size();
            if (pendingSize == 0) {
                CbrAddon.LOGGER.warn("EntityFuncHandler: pendingSize == 0, add Vec3.ZERO");
                pendingPos.add(Vec3.ZERO);
                pendingSize = 1;
            }

            // 逐个写入NBT并应用偏移
            boolean doRelativeMovement = entityFuncProtocol.relativeMovementRange != Vec3.ZERO;
            for (int i = 0; i < lootEntities.size(); i++) {
                Entity entity = lootEntities.get(i);

                // 写入NBT
                CompoundTag entityNbt = entity.serializeNBT(zoneTickContext.serverLevel.registryAccess());
                CompoundTag nbt = entityEvent.getNbt().copy(); // 只读
                for (String key : nbt.getAllKeys()) {
                    entityNbt.put(key, Objects.requireNonNull(nbt.get(key)));
                }
                entity.load(entityNbt);

                entity.setPos(pendingPos.get(i % pendingSize));
                if (doRelativeMovement) {
                    Vec3 baseMovement = entity.getDeltaMovement();
                    entity.setDeltaMovement(Vec3Utils.randomAdjustXYZ(baseMovement, entityFuncProtocol.relativeMovementRange, zoneTickContext.random));
                }
            }
        } else {
            CbrAddon.LOGGER.warn("{} received wrong custom event type: {}", getEventHandlerName(), customEventType);
        }
    }
}
