package team.hdt.neutronia_revamped;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_revamped.init.NBlocks;

import static team.hdt.neutronia_revamped.Referece.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION)
public class NeutroniaRevamped {

    @Mod.Instance
    public static NeutroniaRevamped instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        NBlocks.init();
    }

}
