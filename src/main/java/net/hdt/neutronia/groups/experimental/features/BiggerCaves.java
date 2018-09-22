package net.hdt.neutronia.groups.experimental.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.experimental.world.BigCaveGenerator;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class BiggerCaves extends Component {

    public static float overallCaveSizeVariance, overallCaveSizeBase;

    public static float bigCaveSizeVariance, bigCaveSizeBase;

    public static boolean generateHugeCaves;
    public static int hugeCaveMaxY, hugeCaveChance;
    public static float hugeCaveSizeVariance, hugeCaveSizeBase;

    @Override
    public void setupConfig() {
        overallCaveSizeVariance = (float) loadProperty("Overall Cave Size Variance", 7).setComment("Vanilla value is 3").get();
        overallCaveSizeBase = (float) loadProperty("Overall Cave Size Minimum", 0).setComment("Vanilla value is 0").get();

        bigCaveSizeVariance = (float) loadProperty("Big Cave Size Variance", 8).setComment("Vanilla value is 3").get();
        bigCaveSizeBase = (float) loadProperty("Big Cave Size Minimum", 1).setComment("Vanilla value is 1").get();

        generateHugeCaves = loadProperty("Huge Caves Enabled", true).get();
        hugeCaveMaxY = loadProperty("Huge Cave Maximum Y Level", 32).get();
        hugeCaveChance = loadProperty("Huge Cave Chance", 1800).setComment("Given the value of this config as X, in average, 1 in X caves will be a huge cave").get();
        hugeCaveSizeVariance = (float) loadProperty("Huge Cave Size Variance", 6).get();
        hugeCaveSizeBase = (float) loadProperty("Huge Cave Size Minimum", 14).get();
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

    @Override
    public String getDescription() {
        return "Adds bigger caves";
    }
}
