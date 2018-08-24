package net.hdt.neutronia.groups.dimensions.world.biomes.end;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeEndIslands extends BiomeEnd {
    public BiomeEndIslands() {
        super(new BiomeProperties("End Islands").setTemperature(0.5F).setRainfall(0.0F).setRainDisabled(), "end_islands");
        topBlock = Blocks.END_STONE.getDefaultState();
        fillerBlock = Blocks.END_STONE.getDefaultState();
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
    }
}