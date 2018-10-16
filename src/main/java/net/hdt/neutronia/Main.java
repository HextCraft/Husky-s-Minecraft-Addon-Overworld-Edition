package net.hdt.neutronia;

import net.hdt.neutronia.events.ILifeCycleHandler;
import net.hdt.neutronia.events.handlers.EventHandlers;
import net.hdt.neutronia.events.handlers.RecipeHandlers;
import net.hdt.neutronia.proxy.CommonProxy;
import net.hdt.neutronia.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static net.hdt.neutronia.util.Reference.*;

@Mod(modid = MOD_ID, name = NAME, version = VERSION, dependencies = DEPENDENCIES, updateJSON = UPDATE_JSON, guiFactory = GUI_FACTORY)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    private List<ILifeCycleHandler> handlers = new ArrayList<ILifeCycleHandler>(){{
        add(new EventHandlers());
        add(new RecipeHandlers());
    }};

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        handlers.forEach(handler -> handler.preInit(event));
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        handlers.forEach(handler -> handler.init(event));
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        handlers.forEach(handler -> handler.postInit(event));
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event){
        handlers.forEach(handler -> handler.loadComplete(event));
    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        handlers.forEach(handler -> handler.serverInit(event));
    }

}