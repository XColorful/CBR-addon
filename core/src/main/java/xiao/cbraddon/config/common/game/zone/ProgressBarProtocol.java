package xiao.cbraddon.config.common.game.zone;

import com.google.gson.JsonObject;
import net.minecraft.world.BossEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

public class ProgressBarProtocol {

    // zoneId过滤
    public final @NotNull Pattern zoneId_regex;

    // moveDelay
    public final @NotNull BossEvent.BossBarColor moveDelay_color;
    public final @NotNull BossEvent.BossBarOverlay moveDelay_overlay;

    // moveTime
    public final @NotNull BossEvent.BossBarColor moveTime_color;
    public final @NotNull BossEvent.BossBarOverlay moveTime_overlay;

    protected ProgressBarProtocol(@NotNull Pattern zoneId_regex,
                                  @NotNull BossEvent.BossBarColor moveDelay_color, @NotNull BossEvent.BossBarOverlay moveDelay_overlay,
                                  @NotNull BossEvent.BossBarColor moveTime_color, @NotNull BossEvent.BossBarOverlay moveTime_overlay) {
        this.zoneId_regex = zoneId_regex;
        this.moveDelay_color = moveDelay_color;
        this.moveDelay_overlay = moveDelay_overlay;
        this.moveTime_color = moveTime_color;
        this.moveTime_overlay = moveTime_overlay;
    }

    public static @Nullable ProgressBarProtocol getConfigFromProtocol(String protocol, @NotNull JsonObject jsonTag) {
        return ProgressBarProtocol057.fromTag(jsonTag);
    }
}
