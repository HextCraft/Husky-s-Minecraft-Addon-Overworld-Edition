package net.hdt.neutronia.groups.dimensions.world.biomes.end;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeVoidFalls extends BiomeEnd {
    public BiomeVoidFalls() {
        super(new BiomeProperties("Void Falls").setTemperature(0.9F).setRainfall(0.5F).setRainDisabled(), "void_falls");
//        topBlock = EndExBlocks.MOSSY_END_STONE.getDefaultState();
        topBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        fillerBlock = Blocks.END_STONE.getDefaultState();
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
    }
}