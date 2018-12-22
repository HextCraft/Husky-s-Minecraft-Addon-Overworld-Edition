package team.hdt.neutronia_revamped;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia_revamped.init.NBlocks;
import team.hdt.neutronia_revamped.init.NItems;
import team.hdt.neutronia_revamped.tileentities.TileEntityBlastFurnace;
import team.hdt.neutronia_revamped.tileentities.TileEntitySmoker;

import static team.hdt.neutronia_revamped.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
public class NeutroniaRevamped {

    @Mod.Instance
    public static NeutroniaRevamped instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        NBlocks.init();
        NItems.init();
        GameRegistry.registerTileEntity(TileEntitySmoker.class, "neutronia_revamped:smoker");
        GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, "neutronia_revamped:blast_furnace");
    }

}
