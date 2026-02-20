package xiao.cbraddon.config.common.game.zone.zonefunc.custom;

import com.google.gson.JsonObject;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import xiao.battleroyale.BattleRoyale;
import xiao.battleroyale.api.algorithm.IAlgorithmApi;
import xiao.battleroyale.api.algorithm.IDistribution;
import xiao.battleroyale.util.JsonUtils;
import xiao.cbraddon.CbrAddon;

// cbra:0.4.4
public class EntityFuncProtocol044 {

    private static final IAlgorithmApi algorithmApi = BattleRoyale.getAlgorithmApi();

    public static final String DISTRIBUTION_TYPE = "distributionType";
    public static final String LOOT_FACTOR_CONTRIBUTION = "lootFactorContribution";
    public static final String FIXED_SIMULATION = "fixedSimulation";
    public static final String ALLOW_ON_BORDER = "allowOnBorder";
    public static final String GLOBAL_SHRINK_RATIO = "globalShrinkRatio";
    public static final String NEED_SHUFFLE = "needShuffle";
    public static final String RANDOM_RANGE = "randomRange";
    public static final String FIND_GROUND = "findGround";
    public static final String LIMIT_TO_BOTTOM = "limitToBottom";
    public static final String LIMIT_TO_TOP = "limitToTop";
    public static final String ADDITIONAL_OFFSET = "additionalOffset";
    public static final String IGNORE_OUTSIDE = "ignoreOutside";
    public static final String RELATIVE_MOVEMENT_RANDOM_RANGE = "relativeMovementRandomRange";

    protected static EntityFuncProtocol fromTag(@NotNull JsonObject jsonTag) {
        try {
            IDistribution distribution = switch (JsonUtils.getJsonString(jsonTag, DISTRIBUTION_TYPE, "")) {
                case "rectangleGrid" -> algorithmApi.rectangleGrid();
                case "goldenSpiral" -> algorithmApi.golderSpiral();
                case "circleGrid" -> algorithmApi.circleGrid();
                default -> algorithmApi.circleGrid();
            };
            double lootFactor = JsonUtils.getJsonDouble(jsonTag, LOOT_FACTOR_CONTRIBUTION, 1);
            int fixedSimulation = JsonUtils.getJsonInt(jsonTag, FIXED_SIMULATION, 0);

            boolean allowOnBorder = JsonUtils.getJsonBool(jsonTag, ALLOW_ON_BORDER, false);
            double globalShrinkRatio = JsonUtils.getJsonDouble(jsonTag, GLOBAL_SHRINK_RATIO, 1);
            boolean needShuffle = JsonUtils.getJsonBool(jsonTag, NEED_SHUFFLE, false);

            Vec3 randomRange = JsonUtils.getJsonVec(jsonTag, RANDOM_RANGE, Vec3.ZERO);
            boolean findGround = JsonUtils.getJsonBool(jsonTag, FIND_GROUND, false);
            boolean limitToBottom = JsonUtils.getJsonBool(jsonTag, LIMIT_TO_BOTTOM, true);
            boolean limitToTop = JsonUtils.getJsonBool(jsonTag, LIMIT_TO_TOP, false);
            Vec3 additionalOffset = JsonUtils.getJsonVec(jsonTag, ADDITIONAL_OFFSET, Vec3.ZERO);
            boolean ignoreOutside = JsonUtils.getJsonBool(jsonTag, IGNORE_OUTSIDE, false);
            Vec3 relativeMovementRandomRange = JsonUtils.getJsonVec(jsonTag, RELATIVE_MOVEMENT_RANDOM_RANGE, Vec3.ZERO);

            return new EntityFuncProtocol(distribution, lootFactor, fixedSimulation,
                    allowOnBorder, globalShrinkRatio, needShuffle,
                    randomRange, findGround, limitToBottom, limitToTop, additionalOffset, ignoreOutside, relativeMovementRandomRange);
        } catch (Exception e) {
            CbrAddon.LOGGER.debug("EntityFuncProtocol044: Failed to parse by cbra:0.4.4 protocol from jsonTag: {}", jsonTag, e);
            return null;
        }
    }
}
