package net.hdt.neutronia.api.modules.impl;

import net.hdt.neutronia.api.modules.IStateHandler;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class ListStateHandler<T extends IStateHandler> extends ArrayList<T> implements IStateHandler {

    private boolean enabled;

    protected void forEachEnabled(Consumer<T> consumer) {
        forEach(i -> {
            if (i.isEnabled()) consumer.accept(i);
        });
    }

    @Override
    public void constructed(FMLConstructionEvent event) {
        forEachEnabled(i -> {
            getLogger().info("[Construction] {}: {}", i.getType(), i.getName());
            i.constructed(event);
        });
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        forEachEnabled(i -> {
            getLogger().info(" {}: {} is enabled", i.getType(), i.getName());
            i.preInit(event);
        });
    }

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        forEachEnabled(i -> i.preInitClient(event));
    }

    @Override
    public void postPreInit(FMLPreInitializationEvent event) {
        forEachEnabled(i -> i.postPreInit(event));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        forEachEnabled(i -> i.init(event));
    }

    @Override
    public void initClient(FMLInitializationEvent event) {
        forEachEnabled(i -> i.initClient(event));
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        forEachEnabled(i -> i.postInit(event));
    }

    @Override
    public void finalInit(FMLPostInitializationEvent event) {
        forEachEnabled(i -> i.finalInit(event));
    }

    @Override
    public void postInitClient(FMLPostInitializationEvent event) {
        forEachEnabled(i -> i.postInitClient(event));
    }

    @Override
    public void serverStarted(FMLServerStartedEvent event) {
        forEachEnabled(i -> i.serverStarted(event));
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        forEachEnabled(i -> i.serverStarting(event));
    }

    @Override
    public void serverStopped(FMLServerStoppedEvent event) {
        forEachEnabled(i -> i.serverStopped(event));
    }

    @Override
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        forEachEnabled(i -> i.registerRecipes(event));
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract Logger getLogger();
}
