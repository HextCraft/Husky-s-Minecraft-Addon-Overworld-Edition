package team.hdt.neutronia_legacy.groups.experimental.features;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.experimental.world.BigCaveGenerator;

public class BiggerCaves extends Component {

    public static float overallCaveSizeVariance, overallCaveSizeBase;

    public static float bigCaveSizeVariance, bigCaveSizeBase;

    public static boolean generateHugeCaves;
    public static int hugeCaveMaxY, hugeCaveChance;
    public static float hugeCaveSizeVariance, hugeCaveSizeBase;

    @Override
    public void setupConfig() {
        overallCaveSizeVariance = (float) loadPropDouble("Overall Cave Size Variance", "Vanilla value is 3", 7);
        overallCaveSizeBase = (float) loadPropDouble("Overall Cave Size Minimum", "Vanilla value is 0", 0);

        bigCaveSizeVariance = (float) loadPropDouble("Big Cave Size Variance", "Vanilla value is 3", 8);
        bigCaveSizeBase = (float) loadPropDouble("Big Cave Size Minimum", "Vanilla value is 1", 1);

        generateHugeCaves = loadPropBool("Huge Caves Enabled", "", true);
        hugeCaveMaxY = loadPropInt("Huge Cave Maximum Y Level", "", 32);
        hugeCaveChance = loadPropInt("Huge Cave Chance", "Given the value of this config as X, in average, 1 in X caves will be a huge cave", 1800);
        hugeCaveSizeVariance = (float) loadPropDouble("Huge Cave Size Variance", "", 6);
        hugeCaveSizeBase = (float) loadPropDouble("Huge Cave Size Minimum", "", 14);
    }

    @SubscribeEvent
    public void getCaveGenerator(InitMapGenEvent event) {
        if (event.getType() == EventType.CAVE)
            event.setNewGen(new BigCaveGenerator());
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }

}
