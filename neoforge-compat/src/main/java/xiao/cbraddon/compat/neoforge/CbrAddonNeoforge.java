package xiao.cbraddon.compat.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import xiao.battleroyale.api.common.McSide;
import xiao.cbraddon.CbrAddon;

@Mod(CbrAddon.MOD_ID)
public class CbrAddonNeoforge {

    public CbrAddonNeoforge() {
        Dist dist = FMLLoader.getCurrent().getDist();
        McSide mcSide = dist.isClient() ? McSide.CLIENT : McSide.DEDICATED_SERVER;

        CbrAddon.init(mcSide);
    }
}
