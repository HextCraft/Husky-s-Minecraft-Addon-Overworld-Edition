package team.hdt.neutronia.base;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia.base.modules.ModuleLoader;
import team.hdt.neutronia.base.proxy.CommonProxy;
import team.hdt.neutronia.commands.CommandNewLocate;
import team.hdt.neutronia.commands.CommandTeleportToDimension;
import team.hdt.neutronia.init.NBiomes;
import team.hdt.neutronia.init.NBlocks;
import team.hdt.neutronia.init.NItems;
import team.hdt.neutronia.tileentities.TileCustomChest;
import team.hdt.neutronia.tileentities.TileEntityBlastFurnace;
import team.hdt.neutronia.tileentities.TileEntityNeutroniaFurnace;
import team.hdt.neutronia.tileentities.TileEntitySmoker;

import static team.hdt.neutronia.base.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES, guiFactory = GUI_FACTORY)
public class NeutroniaRevamped {

    @Mod.Instance
    public static NeutroniaRevamped instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        NBlocks.init();
        NItems.init();
        NBiomes.init();
        GameRegistry.registerTileEntity(TileEntitySmoker.class, new ResourceLocation(MOD_ID, "smoker"));
        GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, new ResourceLocation(MOD_ID, "blast_furnace"));
        GameRegistry.registerTileEntity(TileCustomChest.class, new ResourceLocation(MOD_ID, "wooden_chest"));
        GameRegistry.registerTileEntity(TileEntityNeutroniaFurnace.class, new ResourceLocation(MOD_ID, "furnace"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        ModuleLoader.serverStarting(event);
        event.registerServerCommand(new CommandNewLocate());
        event.registerServerCommand(new CommandTeleportToDimension());
    }

}
