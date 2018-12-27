package team.hdt.neutronia.base.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import team.hdt.neutronia.base.modules.ModuleLoader;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ModuleLoader.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ModuleLoader.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ModuleLoader.postInit(event);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        ModuleLoader.serverStarting(event);
    }

}