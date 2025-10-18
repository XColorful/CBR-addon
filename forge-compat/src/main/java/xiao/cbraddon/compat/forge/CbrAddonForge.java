package xiao.cbraddon.compat.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLLoader;
import xiao.cbraddon.CbrAddon;
import net.minecraftforge.fml.common.Mod;
import xiao.battleroyale.api.common.McSide;

@Mod(CbrAddon.MOD_ID)
public class CbrAddonForge {

    public CbrAddonForge() {
        Dist dist = FMLLoader.getDist();
        McSide mcSide = dist.isClient() ? McSide.CLIENT : McSide.DEDICATED_SERVER;

        CbrAddon.init(mcSide);
    }
}
