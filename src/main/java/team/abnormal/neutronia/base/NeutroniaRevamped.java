package team.abnormal.neutronia.base;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.abnormal.neutronia.commands.CommandNewLocate;
import team.abnormal.neutronia.commands.CommandTeleportToDimension;
import team.abnormal.neutronia.init.NBiomes;
import team.abnormal.neutronia.init.NBlocks;
import team.abnormal.neutronia.init.NItems;
import team.abnormal.neutronia.tileentities.TileEntityNeutroniaFurnace;
import team.abnormal.neutronia.base.modules.ModuleLoader;
import team.abnormal.neutronia.base.proxy.CommonProxy;
import team.abnormal.neutronia.tileentities.TileCustomChest;
import team.abnormal.neutronia.tileentities.TileEntityBlastFurnace;
import team.abnormal.neutronia.tileentities.TileEntitySmoker;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY)
public class NeutroniaRevamped {

    @Mod.Instance
    public static NeutroniaRevamped instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        NBlocks.init();
        NItems.init();
        NBiomes.init();
        GameRegistry.registerTileEntity(TileEntitySmoker.class, new ResourceLocation(Reference.MOD_ID, "smoker"));
        GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, new ResourceLocation(Reference.MOD_ID, "blast_furnace"));
        GameRegistry.registerTileEntity(TileCustomChest.class, new ResourceLocation(Reference.MOD_ID, "wooden_chest"));
        GameRegistry.registerTileEntity(TileEntityNeutroniaFurnace.class, new ResourceLocation(Reference.MOD_ID, "furnace"));
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
