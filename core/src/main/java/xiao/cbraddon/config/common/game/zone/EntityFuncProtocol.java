package xiao.cbraddon.config.common.game.zone;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xiao.battleroyale.api.algorithm.IDistribution;

public class EntityFuncProtocol {

    public final IDistribution distribution;
    public final boolean allowOnBorder;
    public final double globalShrinkRatio;
    public final Vec3 randomRange;
    public final boolean findGround;
    public final boolean limitToBottom;
    public final boolean limitToTop;
    public final Vec3 additionalOffset;
    public final double lootFactor;
    public final int fixedSimulation;
    public final boolean ignoreOutside;
    public final Vec3 relativeMovementRange;

    protected EntityFuncProtocol(IDistribution distribution, double lootFactor, int fixedSimulation,
                                 boolean allowOnBorder, double globalShrinkRatio,
                                 Vec3 randomRange, boolean findGround, boolean limitToBottom, boolean limitToTop, Vec3 additionalOffset, boolean ignoreOutside, Vec3 relativeMovementRange) {
        this.distribution = distribution;
        this.lootFactor = lootFactor;
        this.fixedSimulation = fixedSimulation;

        this.allowOnBorder = allowOnBorder;
        this.globalShrinkRatio = globalShrinkRatio;

        this.randomRange = randomRange;
        this.findGround = findGround;
        this.limitToBottom = limitToBottom;
        this.limitToTop = limitToTop;
        this.additionalOffset = additionalOffset;
        this.ignoreOutside = ignoreOutside;
        this.relativeMovementRange = relativeMovementRange;
    }

    public static @Nullable EntityFuncProtocol getConfigFromProtocol(String protocol, @NotNull CompoundTag tag) {
        // 目前只有为cbra:0.4.4
        if (protocol.equals("cbra:0.4.4")) {
            return EntityFuncProtocol044.fromTag(tag);
        }

        return null;
    }
}
