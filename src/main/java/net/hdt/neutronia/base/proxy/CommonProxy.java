package net.hdt.neutronia.base.proxy;

import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.util.Localization;
import net.hdt.neutronia.groups.NGroups;
import net.hdt.neutronia.groups.world.world.gen.OreGeneration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Localization.initialize(Side.SERVER);
        NGroups.registerGroups();
        GroupLoader.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        GroupLoader.init(event);
        new OreGeneration();
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