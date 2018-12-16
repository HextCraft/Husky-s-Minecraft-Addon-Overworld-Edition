package team.hdt.neutronia_legacy.groups.dimensions.world.biomes.end;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeStarlands extends BiomeEnd {
    public BiomeStarlands() {
        super(new BiomeProperties("Starlands").setTemperature(0.35F).setRainfall(0.2F).setRainDisabled(), "starlands");
//        topBlock = EndExBlocks.END_STONE.getDefaultState().withProperty(BlockEndStone.TYPE, BlockEndStone.EnumType.STARRY);
//        fillerBlock = EndExBlocks.END_STONE.getDefaultState().withProperty(BlockEndStone.TYPE, BlockEndStone.EnumType.STARRY);
        topBlock = Blocks.PURPUR_BLOCK.getDefaultState();
        fillerBlock = Blocks.END_BRICKS.getDefaultState();
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
    }
}