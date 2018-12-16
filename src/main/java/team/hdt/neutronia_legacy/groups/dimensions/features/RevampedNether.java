package team.hdt.neutronia_legacy.groups.dimensions.features;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.dimensions.init.NNBiomes;
import team.hdt.neutronia_legacy.groups.dimensions.world.biomes.nether.NNBiomeManager;

public class RevampedNether extends Component {

    public static boolean enableBOPCompat;
    public static boolean generateSoulSand;
    public static boolean generateGravel;
    public static boolean disableNetherFog;

    @Override
    public void setupConfig() {
        enableBOPCompat = loadPropBool("Enable BiomesOPlenty Compatibility", "This will enable compatibility with Biomes O' Plenty nether biomes", true);
        generateSoulSand = loadPropBool("Generate Soul Sand", "Do Soul Sand platforms generate in the Nether", false);
        generateGravel = loadPropBool("Generate Gravel", "Do Gravel platforms generate in the Nether", false);
        disableNetherFog = loadPropBool("Disable Nether Fog", "Does Fog render in the Nether", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NNBiomeManager.preInit();
    }

    @Override
    public void init(FMLInitializationEvent event) {
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
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}