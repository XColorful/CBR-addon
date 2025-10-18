package xiao.cbraddon.config.common.game.zone;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import xiao.battleroyale.BattleRoyale;
import xiao.battleroyale.api.algorithm.IAlgorithmApi;
import xiao.battleroyale.api.algorithm.IDistribution;
import xiao.battleroyale.util.StringUtils;
import xiao.cbraddon.CbrAddon;

// cbra:0.4.4
public class EntityFuncProtocol044 {

    private static final IAlgorithmApi algorithmApi = BattleRoyale.getAlgorithmApi();

    public static final String DISTRIBUTION_TYPE = "distributionType";
    public static final String LOOT_FACTOR_CONTRIBUTION = "lootFactorContribution";
    public static final String FIXED_SIMULATION = "fixedSimulation";
    public static final String ALLOW_ON_BORDER = "allowOnBorder";
    public static final String GLOBAL_SHRINK_RATIO = "globalShrinkRatio";
    public static final String RANDOM_RANGE = "randomRange";
    public static final String FIND_GROUND = "findGround";
    public static final String LIMIT_TO_BOTTOM = "limitToBottom";
    public static final String LIMIT_TO_TOP = "limitToTop";
    public static final String ADDITIONAL_OFFSET = "additionalOffset";
    public static final String IGNORE_OUTSIDE = "ignoreOutside";
    public static final String RELATIVE_MOVEMENT_RANDOM_RANGE = "relativeMovementRandomRange";

    protected static EntityFuncProtocol fromTag(@NotNull CompoundTag tag) {
        try {
            IDistribution distribution = switch (tag.contains(DISTRIBUTION_TYPE) ? tag.getString(DISTRIBUTION_TYPE) : "") {
                case "rectangleGrid" -> algorithmApi.rectangleGrid();
                case "goldenSpiral" -> algorithmApi.golderSpiral();
                case "circleGrid" -> algorithmApi.circleGrid();
                default -> algorithmApi.circleGrid();
            };
            double lootFactor = tag.contains(LOOT_FACTOR_CONTRIBUTION) ? tag.getDouble(LOOT_FACTOR_CONTRIBUTION) : 1;
            int fixedSimulation = tag.contains(FIXED_SIMULATION) ? tag.getInt(FIXED_SIMULATION) : 0;

            boolean allowOnBorder = tag.contains(ALLOW_ON_BORDER) ? tag.getBoolean(ALLOW_ON_BORDER) : false;
            double globalShrinkRatio = tag.contains(GLOBAL_SHRINK_RATIO) ? tag.getDouble(GLOBAL_SHRINK_RATIO) : 1;

            Vec3 randomRange = tag.contains(RANDOM_RANGE) ? StringUtils.parseVectorString(tag.getString(RANDOM_RANGE)) : Vec3.ZERO;
            boolean findGround = tag.contains(FIND_GROUND) ? tag.getBoolean(FIND_GROUND) : false;
            boolean limitToBottom = tag.contains(LIMIT_TO_BOTTOM) ? tag.getBoolean(LIMIT_TO_BOTTOM) : true;
            boolean limitToTop = tag.contains(LIMIT_TO_TOP) ? tag.getBoolean(LIMIT_TO_TOP) : false;
            Vec3 additionalOffset = tag.contains(ADDITIONAL_OFFSET) ? StringUtils.parseVectorString(tag.getString(ADDITIONAL_OFFSET)) : Vec3.ZERO;
            boolean ignoreOutside = tag.contains(IGNORE_OUTSIDE) ? tag.getBoolean(IGNORE_OUTSIDE) : false;
            Vec3 relativeMovementRandomRange = tag.contains(RELATIVE_MOVEMENT_RANDOM_RANGE) ? StringUtils.parseVectorString(tag.getString(RELATIVE_MOVEMENT_RANDOM_RANGE)) : Vec3.ZERO;

            return new EntityFuncProtocol(distribution, lootFactor, fixedSimulation,
                    allowOnBorder, globalShrinkRatio,
                    randomRange, findGround, limitToBottom, limitToTop, additionalOffset, ignoreOutside, relativeMovementRandomRange);
        } catch (Exception e) {
            CbrAddon.LOGGER.debug("EntityFuncProtocol044: Failed to parse by cbra:0.4.4 protocol from tag: {}", tag, e);
            return null;
        }
    }
}
