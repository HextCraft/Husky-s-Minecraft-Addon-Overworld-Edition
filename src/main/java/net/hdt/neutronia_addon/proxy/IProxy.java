package net.hdt.neutronia_addon.proxy;

import net.minecraftforge.fml.common.event.*;

public interface IProxy {

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    void finalInit(FMLPostInitializationEvent event);

    void serverStarting(FMLServerStartingEvent event);

}
