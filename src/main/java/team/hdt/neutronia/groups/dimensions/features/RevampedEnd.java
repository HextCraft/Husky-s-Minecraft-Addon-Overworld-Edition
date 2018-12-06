package team.hdt.neutronia.groups.dimensions.features;

import net.minecraftforge.fml.common.event.*;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.dimensions.init.NEBiomes;
import team.hdt.neutronia.groups.dimensions.world.biomes.end.NEBiomeManager;

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

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}