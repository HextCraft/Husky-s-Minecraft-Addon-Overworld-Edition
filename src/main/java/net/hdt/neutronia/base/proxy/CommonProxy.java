package net.hdt.neutronia.base.proxy;

import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.groups.NGroups;
import net.hdt.neutronia.tileentity.TileEntityPot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NGroups.registerGroups();
        GroupLoader.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerTileEntity(TileEntityPot.class, new ResourceLocation(MOD_ID, "pot"));
        GroupLoader.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        GroupLoader.postInit(event);
    }

    @Override
    public void finalInit(FMLPostInitializationEvent event) {
        GroupLoader.finalInit(event);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        GroupLoader.serverStarting(event);
    }

    @Override
    public void addVanillaResourceOverride(String path, String file) {

    }

    @Override
    public void addNeutroniaResourceOverride(String path, String file) {

    }

}