package team.hdt.neutronia.groups.dimensions.world.biomes.end;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeOvergrownIslands extends BiomeEnd {
    public BiomeOvergrownIslands() {
        super(new BiomeProperties("Overgrown Islands").setTemperature(0.65F).setRainfall(0.3F).setRainDisabled(), "overgrown_islands");
//        topBlock = EndExBlocks.MOSSY_END_STONE.getDefaultState();
        topBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        fillerBlock = Blocks.END_STONE.getDefaultState();
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
    }
}