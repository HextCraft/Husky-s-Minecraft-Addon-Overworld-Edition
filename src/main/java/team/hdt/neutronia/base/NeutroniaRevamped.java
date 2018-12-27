package team.hdt.neutronia.base;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia.base.proxy.CommonProxy;
import team.hdt.neutronia.init.NBlocks;
import team.hdt.neutronia.init.NItems;
import team.hdt.neutronia.tileentities.TileEntityBlastFurnace;
import team.hdt.neutronia.tileentities.TileEntitySmoker;

import static team.hdt.neutronia.base.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
public class NeutroniaRevamped {

    @Mod.Instance
    public static NeutroniaRevamped instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        NBlocks.init();
        NItems.init();
        GameRegistry.registerTileEntity(TileEntitySmoker.class, "neutronia_revamped:smoker");
        GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, "neutronia_revamped:blast_furnace");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
