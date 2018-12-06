package team.hdt.neutronia.events.handlers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import team.hdt.neutronia.commands.CommandFindBiome;
import team.hdt.neutronia.commands.CommandStructureCapture;
import team.hdt.neutronia.commands.CommandTeleportToDimension;
import team.hdt.neutronia.events.ILifeCycleHandler;
import team.hdt.neutronia.init.NBlocks;
import team.hdt.neutronia.init.NItems;

public class EventHandlers implements ILifeCycleHandler {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(NBlocks.class);
        MinecraftForge.EVENT_BUS.register(NItems.class);
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {

    }

    @Override
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFindBiome());
        event.registerServerCommand(new CommandTeleportToDimension());
        event.registerServerCommand(new CommandStructureCapture());
    }

}
