package net.hdt.neutronia.groups.dimensions.world.biomes.end;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeChorusForest extends BiomeEnd {
    public BiomeChorusForest() {
        super(new Biome.BiomeProperties("Chorus Forest").setTemperature(0.35F).setRainfall(0.2F).setRainDisabled(), "chorus_forest");
//        topBlock = EndExBlocks.MOSSY_END_STONE.getDefaultState();
        topBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        fillerBlock = Blocks.END_STONE.getDefaultState();
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
    }
}