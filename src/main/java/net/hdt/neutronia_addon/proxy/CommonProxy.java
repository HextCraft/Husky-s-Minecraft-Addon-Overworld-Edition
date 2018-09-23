package net.hdt.neutronia_addon.proxy;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia_addon.NeutroniaAddon;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy implements IProxy {

    @Override
    public void construction(FMLConstructionEvent event) {
        Neutronia.MODULE_LOADER.constructed(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NeutroniaAddon.MODULE_LOADER.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        NeutroniaAddon.MODULE_LOADER.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        NeutroniaAddon.MODULE_LOADER.postInit(event);
    }

    @Override
    public void finalInit(FMLPostInitializationEvent event) {
        NeutroniaAddon.MODULE_LOADER.finalInit(event);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        NeutroniaAddon.MODULE_LOADER.serverStarting(event);
    }

    @Override
    public void serverStarted(FMLServerStartedEvent event) {
        NeutroniaAddon.MODULE_LOADER.serverStarted(event);
    }

    @Override
    public void serverStopped(FMLServerStoppedEvent event) {
        NeutroniaAddon.MODULE_LOADER.serverStopped(event);
    }

}