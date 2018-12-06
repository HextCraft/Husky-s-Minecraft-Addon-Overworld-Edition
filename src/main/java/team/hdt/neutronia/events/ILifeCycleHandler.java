package team.hdt.neutronia.events;

import net.minecraftforge.fml.common.event.*;

public interface ILifeCycleHandler {

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    void loadComplete(FMLLoadCompleteEvent event);

    void serverInit(FMLServerStartingEvent event);

}
