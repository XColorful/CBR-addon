package xiao.cbraddon.config.common.game.zone;

import com.google.gson.JsonObject;
import net.minecraft.world.BossEvent;
import org.jetbrains.annotations.NotNull;
import xiao.battleroyale.util.JsonUtils;
import xiao.cbraddon.CbrAddon;

import java.util.regex.Pattern;

// cbra:0.5.7
public class ProgressBarProtocol057 {

    // zoneId过滤
    public static final String ZONE_ID_REGEX = "zoneId_regex";

    // moveDelay
    public static final String MOVE_DELAY_COLOR = "moveDelay_color";
    public static final String MOVE_DELAY_OVERLAY = "moveDelay_overlay";
    // moveTime
    public static final String MOVE_TIME_COLOR = "moveTime_color";
    public static final String MOVE_TIME_OVERLAY = "moveTime_overlay";

    protected static ProgressBarProtocol fromTag(@NotNull JsonObject jsonTag) {
        try {
            String zoneId_regex = JsonUtils.getJsonString(jsonTag, ZONE_ID_REGEX, ".*");
            Pattern pattern = Pattern.compile(zoneId_regex);

            BossEvent.BossBarColor moveDelay_color = JsonUtils.getBossBarColor(jsonTag, MOVE_DELAY_COLOR);
            BossEvent.BossBarOverlay moveDelay_overlay = JsonUtils.getBossBarOverlay(jsonTag, MOVE_DELAY_OVERLAY);

            BossEvent.BossBarColor moveTime_color = JsonUtils.getBossBarColor(jsonTag, MOVE_TIME_COLOR);
            BossEvent.BossBarOverlay moveTime_overlay = JsonUtils.getBossBarOverlay(jsonTag, MOVE_TIME_OVERLAY);

            return new ProgressBarProtocol(pattern,
                    moveDelay_color, moveDelay_overlay,
                    moveTime_color, moveTime_overlay);
        } catch (Exception e) {
            CbrAddon.LOGGER.warn("ProgressBarProtocol057: Failed to parse by cbra:0.5.7 protocol from jsonTag: {}", jsonTag, e);
            return null;
        }
    }
}
