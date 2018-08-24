package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.init.NEBiomes;
import net.hdt.neutronia.groups.dimensions.world.biomes.end.NEBiomeManager;
import net.minecraftforge.fml.common.event.*;

public class RevampedEnd extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NEBiomeManager.preInit();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        NEBiomes.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        NEBiomes.postInit();
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        NEBiomeManager.setupDefaultBiomes();
        NEBiomeManager.setupCompatibleBiomes(event.getServer());
        NEBiomeManager.setupCustomBiomes();
    }

    @Override
    public void serverStopped(FMLServerStoppedEvent event) {
        NEBiomeManager.resetBiomes();
    }

}