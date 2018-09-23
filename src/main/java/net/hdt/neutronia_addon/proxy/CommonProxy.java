package net.hdt.neutronia_addon.proxy;

import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia_addon.modules.NAGroups;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NAGroups.registerGroups();
        GroupLoader.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
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

}