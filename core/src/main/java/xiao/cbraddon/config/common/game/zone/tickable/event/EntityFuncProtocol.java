package xiao.cbraddon.config.common.game.zone.tickable.event;

import com.google.gson.JsonObject;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xiao.battleroyale.api.algorithm.IDistribution;
import xiao.cbraddon.CbrAddon;

import java.util.HashSet;
import java.util.Set;

public class EntityFuncProtocol {

    public final IDistribution distribution;
    public final double lootFactor;
    public final int fixedSimulation;

    public final boolean allowOnBorder;
    public final double globalShrinkRatio;
    public final boolean needShuffle;

    public final @NotNull Vec3 randomRange;
    public final boolean findGround;
    public final boolean limitToBottom;
    public final boolean limitToTop;
    public final @NotNull Vec3 additionalOffset;
    public final boolean ignoreOutside;
    public final @NotNull Vec3 relativeMovementRange;

    protected EntityFuncProtocol(IDistribution distribution, double lootFactor, int fixedSimulation,
                                 boolean allowOnBorder, double globalShrinkRatio, boolean needShuffle,
                                 Vec3 randomRange, boolean findGround, boolean limitToBottom, boolean limitToTop, Vec3 additionalOffset, boolean ignoreOutside, Vec3 relativeMovementRange) {
        this.distribution = distribution;
        this.lootFactor = lootFactor;
        this.fixedSimulation = fixedSimulation;

        this.allowOnBorder = allowOnBorder;
        this.globalShrinkRatio = globalShrinkRatio;
        this.needShuffle = needShuffle;

        this.randomRange = randomRange != null ? randomRange : Vec3.ZERO;
        this.findGround = findGround;
        this.limitToBottom = limitToBottom;
        this.limitToTop = limitToTop;
        this.additionalOffset = additionalOffset != null ? additionalOffset : Vec3.ZERO;
        this.ignoreOutside = ignoreOutside;
        this.relativeMovementRange = relativeMovementRange != null ? relativeMovementRange : Vec3.ZERO;
    }

    public static @Nullable EntityFuncProtocol getConfigFromProtocol(String protocol, @NotNull JsonObject jsonTag) {
        if (protocol == null || protocol.isEmpty()) {
            return null;
        }
        String[] parts = protocol.split(":", 2);
        if (parts.length != 2) {
            return null;
        }
        String namespace = parts[0];
        String version = parts[1];
        if (namespace.equals(CbrAddon.MOD_ID) || namespace.equals(CbrAddon.MOD_NAME_SHORT)) {
            switch (version) {
                case "0.4.4" -> {
                    return EntityFuncProtocol044.fromTag(jsonTag);
                }
                default -> {
                    if (!unknownVersion.contains(version)) {
                        CbrAddon.LOGGER.info("EntityFuncProtocol: unknown version {}", version);
                        unknownVersion.add(version);
                    }
                    return EntityFuncProtocol044.fromTag(jsonTag);
                }
            }
        }
        return null;
    }

    private static final Set<String> unknownVersion = new HashSet<>();
}
