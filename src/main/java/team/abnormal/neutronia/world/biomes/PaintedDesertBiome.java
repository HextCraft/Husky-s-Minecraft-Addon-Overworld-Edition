package team.abnormal.neutronia.world.biomes;

import net.minecraft.block.BlockStainedHardenedClay;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenFossils;

import java.util.Random;

public class PaintedDesertBiome extends NBiome {

    public PaintedDesertBiome(BiomeProperties properties) {
        super(properties);
        //topBlock = RED_SAND;
        //fillerBlock = RED_SAND;
        decorator.treesPerChunk = -999;
        decorator.deadBushPerChunk = 2;
        decorator.reedsPerChunk = 50;
        decorator.cactiPerChunk = 10;
        spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 6, 3, 3));
        spawnableCreatureList.add(new SpawnListEntry(EntityHusk.class, 6, 3, 3));
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
            if (rand.nextInt(64) == 0)
            {
                (new WorldGenFossils()).generate(worldIn, rand, pos);
            }
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal <= 0.25D) {
            topBlock = STAINED_HARDENED_CLAY.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.RED);
            fillerBlock = STAINED_HARDENED_CLAY.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.ORANGE);
        }
        if (noiseVal <= 0.125D) {
            topBlock = STAINED_HARDENED_CLAY.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.PURPLE);
            fillerBlock = STAINED_HARDENED_CLAY.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.MAGENTA);
        }
        else if (noiseVal <= 0.05D) {
            topBlock = RED_SAND;
            fillerBlock = RED_SAND;
        }
        generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
}