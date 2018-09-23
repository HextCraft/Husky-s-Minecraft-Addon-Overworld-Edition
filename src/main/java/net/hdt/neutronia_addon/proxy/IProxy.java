package net.hdt.neutronia_addon.proxy;

import net.minecraftforge.fml.common.event.*;

public interface IProxy {

    void construction(FMLConstructionEvent event);

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    void finalInit(FMLPostInitializationEvent event);

    void serverStarting(FMLServerStartingEvent event);

    void serverStarted(FMLServerStartedEvent event);

    void serverStopped(FMLServerStoppedEvent event);

}
