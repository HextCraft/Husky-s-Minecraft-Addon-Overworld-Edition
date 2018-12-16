package team.hdt.neutronia_rewrite;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_rewrite.init.NBlocks;

import static team.hdt.neutronia_rewrite.Referece.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION)
public class NeutroniaRewrite {

    @Mod.Instance
    public static NeutroniaRewrite instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        NBlocks.init();
    }

}
