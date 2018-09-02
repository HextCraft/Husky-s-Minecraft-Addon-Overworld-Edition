package net.hdt.neutronia.base.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public interface IProxy {

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    void finalInit(FMLPostInitializationEvent event);

    void serverStarting(FMLServerStartingEvent event);

    void addResourceOverride(String path, String file);

    default float getPartialTicks() {
        return 0.0F;
    }

}
