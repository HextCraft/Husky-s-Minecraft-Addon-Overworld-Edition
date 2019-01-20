package team.abnormal.neutronia.base.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.base.modules.ModuleLoader;
import team.abnormal.neutronia.blocks.ComposterBlock;
import team.abnormal.neutronia.init.NBiomes;
import team.abnormal.neutronia.init.NBlocks;
import team.abnormal.neutronia.init.NItems;
import team.abnormal.neutronia.tileentities.TileCustomChest;
import team.abnormal.neutronia.tileentities.TileEntityBlastFurnace;
import team.abnormal.neutronia.tileentities.TileEntityNeutroniaFurnace;
import team.abnormal.neutronia.tileentities.TileEntitySmoker;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ModuleLoader.preInit(event);
        NBlocks.init();
        NItems.init();
        ComposterBlock.registerDefaultCompostableItems();
        NBiomes.init();
        GameRegistry.registerTileEntity(TileEntitySmoker.class, new ResourceLocation(Reference.MOD_ID, "smoker"));
        GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, new ResourceLocation(Reference.MOD_ID, "blast_furnace"));
        GameRegistry.registerTileEntity(TileCustomChest.class, new ResourceLocation(Reference.MOD_ID, "wooden_chest"));
        GameRegistry.registerTileEntity(TileEntityNeutroniaFurnace.class, new ResourceLocation(Reference.MOD_ID, "furnace"));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ModuleLoader.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ModuleLoader.postInit(event);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        ModuleLoader.serverStarting(event);
    }

}