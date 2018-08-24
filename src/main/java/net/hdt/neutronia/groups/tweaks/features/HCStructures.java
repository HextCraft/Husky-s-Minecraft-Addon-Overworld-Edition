package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.world.BWComponentScatteredFeaturePieces;
import net.hdt.neutronia.world.BWMapGenScatteredFeature;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HCStructures extends Component {

    private static int HARDCORE_STRUCTURE_RADIUS;

    public static boolean isInRadius(World world, int x, int z) {
        BlockPos center = world.getSpawnPoint();
        return Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(z - center.getZ(), 2)) < HARDCORE_STRUCTURE_RADIUS;
    }

    @Override
    public String getFeatureDescription() {
        return "Makes it so structures are looted within a radius of spawn and unlooted outside of that radius. \nEncourages exploration.\nHC" +
                "Also makes unlooted structures the only source of Enchanting Tables and Brewing Stands.";
    }

    @Override
    public void setupConfig() {
        HARDCORE_STRUCTURE_RADIUS = loadPropInt("Hardcore Structure Radius", "Radius from original spawn which structures will be abandoned in", 2000);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MapGenStructureIO.registerStructure(BWMapGenScatteredFeature.Start.class, "BWTemple");
        MapGenStructureIO.registerStructureComponent(BWComponentScatteredFeaturePieces.DesertPyramid.class, "BWTeDP");
        MapGenStructureIO.registerStructureComponent(BWComponentScatteredFeaturePieces.JunglePyramid.class, "BWTeJP");
        MapGenStructureIO.registerStructureComponent(BWComponentScatteredFeaturePieces.SwampHut.class, "BWTeSH");
    }

    @SubscribeEvent
    public void overrideScatteredFeature(InitMapGenEvent event) {
        if (event.getType().equals(InitMapGenEvent.EventType.SCATTERED_FEATURE))
            event.setNewGen(new BWMapGenScatteredFeature());
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }
}
