package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.world.gen.WorldProviderSurface;
import net.hdt.neutronia.groups.world.world.gen.structure.*;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ImprovedWorldGen extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MapGenStructureIO.registerStructure(StructureMineshaftStart.class, "Mineshaft");
        MapGenStructureIO.registerStructure(MapGenVillage.Start.class, "Village");
        MapGenStructureIO.registerStructure(MapGenStronghold.Start.class, "Stronghold");
        MapGenStructureIO.registerStructure(StructureOceanMonument.StartMonument.class, "Ocean_Monument");
        StructureVillagePieces.registerVillagePieces();
        StructureMineshaftPieces.registerStructurePieces();
        StructureStrongholdPieces.prepareStructurePieces();
        StructureStrongholdPieces.registerStrongholdPieces();
        StructureOceanMonumentPieces.registerOceanMonumentPieces();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        DimensionManager.unregisterDimension(0);
        DimensionType overworld = DimensionType.register("Overworld", "_overworld", 0, WorldProviderSurface.class, false);
        DimensionManager.registerDimension(0, overworld);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
