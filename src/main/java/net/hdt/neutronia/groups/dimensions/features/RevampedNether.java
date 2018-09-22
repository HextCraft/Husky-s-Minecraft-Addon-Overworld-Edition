package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.init.NNBiomes;
import net.hdt.neutronia.groups.dimensions.world.biomes.nether.NNBiomeManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

public class RevampedNether extends Component {

    public static boolean enableBOPCompat;
    public static boolean generateSoulSand;
    public static boolean generateGravel;
    public static boolean disableNetherFog;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NNBiomeManager.preInit();
    }

    @Override
    public void setupConfig() {
        enableBOPCompat = loadProperty("Enable BiomesOPlenty Compatibility", true).setComment("This will enable compatibility with Biomes O' Plenty nether biomes").get();
        generateSoulSand = loadProperty("Generate Soul Sand", false).setComment("Do Soul Sand platforms generate in the Nether").get();
        generateGravel = loadProperty("Generate Gravel", false).setComment("Do Gravel platforms generate in the Nether").get();
        disableNetherFog = loadProperty("Disable Nether Fog", true).setComment("Does Fog render in the Nether").get();

        NNBiomes.init();
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        NNBiomeManager.setupDefaultBiomes();
        NNBiomeManager.setupCompatibleBiomes(event.getServer());
        NNBiomeManager.setupCustomBiomes();
    }

    @Override
    public void serverStopped(FMLServerStoppedEvent event) {
        NNBiomeManager.resetBiomes();
    }

    @Override
    public String getDescription() {
        return "This revamps the nether";
    }
}