package net.hdt.neutronia.base.proxy;

import net.minecraftforge.fml.common.event.*;

public interface IProxy {

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    void finalInit(FMLPostInitializationEvent event);

    void serverStarting(FMLServerStartingEvent event);

    void addVanillaResourceOverride(String path, String file);

    void addNeutroniaResourceOverride(String path, String file);

    default float getPartialTicks() {
        return 0.0F;
    }

}
