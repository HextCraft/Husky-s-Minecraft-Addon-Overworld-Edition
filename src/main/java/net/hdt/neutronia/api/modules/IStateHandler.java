package net.hdt.neutronia.api.modules;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.*;

@SuppressWarnings("unused")
public interface IStateHandler {

    default void constructed(FMLConstructionEvent event) {
    }

    default void preInit(FMLPreInitializationEvent event) {
    }

    default void postPreInit(FMLPreInitializationEvent event) {
    }

    default void init(FMLInitializationEvent event) {
    }

    default void postInit(FMLPostInitializationEvent event) {
    }

    default void finalInit(FMLPostInitializationEvent event) {
    }

    default void serverStarted(FMLServerStartedEvent event) {
    }

    default void serverStarting(FMLServerStartingEvent event) {
    }

    default void serverStopped(FMLServerStoppedEvent event) {
    }

    default void preInitClient(FMLPreInitializationEvent event) {
    }

    default void initClient(FMLInitializationEvent event) {
    }

    default void postInitClient(FMLPostInitializationEvent event) {
    }

    default void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    }

    String getName();

    String getType();

    boolean isEnabled();
}
