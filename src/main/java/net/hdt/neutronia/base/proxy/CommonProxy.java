package net.hdt.neutronia.base.proxy;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.tileentity.TileEntityPot;
import net.hdt.neutronia.world.gen.WorldGenCustomStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

public class CommonProxy implements IProxy {

    @Override
    public void construction(FMLConstructionEvent event) {
        Neutronia.MODULE_LOADER.constructed(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Neutronia.MODULE_LOADER.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 1);
        GameRegistry.registerTileEntity(TileEntityPot.class, new ResourceLocation(MOD_ID, "pot"));
        Neutronia.MODULE_LOADER.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        Neutronia.MODULE_LOADER.postInit(event);
    }

    @Override
    public void finalInit(FMLPostInitializationEvent event) {
        Neutronia.MODULE_LOADER.finalInit(event);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        Neutronia.MODULE_LOADER.serverStarting(event);
    }

    @Override
    public void addVanillaResourceOverride(String path, String file) {

    }

    @Override
    public void addNeutroniaResourceOverride(String path, String file) {

    }

}