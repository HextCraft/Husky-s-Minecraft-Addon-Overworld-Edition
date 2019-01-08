package team.abnormal.neutronia.world.biomes;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class TropicalDesertBiome extends NBiome {

    public TropicalDesertBiome(BiomeProperties properties) {
        super(properties);
        decorator.flowersPerChunk = 0;
        if (topBlock == Blocks.GRASS.getDefaultState()) {
            spawnableCreatureList.add(new SpawnListEntry(EntityParrot.class, 40, 1, 2));
            spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
            spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 1, 1, 1));
            spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 10, 1, 4));
            spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        }
        if (topBlock == SAND) {
            spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 10, 1, 4));
            spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        }
    }

    public BiomeDecorator createBiomeDecorator()
    {
        return new Decorator();
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(10) == 0)
        {
            return BIG_TREE_FEATURE;
        }
        else if (rand.nextInt(2) == 0) {
            return new WorldGenShrub(JUNGLE_LOG, OAK_LEAVES);
        }
        else
        {
            return (rand.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAVES) : new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAVES, true));
        }
    }

    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);
        int i = rand.nextInt(16) + 8;
        int j = rand.nextInt(16) + 8;
        int height = worldIn.getHeight(pos.add(i, 0, j)).getY() * 2; // could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int k = rand.nextInt(height);
        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN))
            (new WorldGenMelon()).generate(worldIn, rand, pos.add(i, k, j));
        WorldGenVines worldgenvines = new WorldGenVines();

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
            for (int j1 = 0; j1 < 50; ++j1)
            {
                k = rand.nextInt(16) + 8;
                int l = 128;
                int i1 = rand.nextInt(16) + 8;
                worldgenvines.generate(worldIn, rand, pos.add(k, 128, i1));
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DESERT_WELL))
            if (rand.nextInt(1000) == 0)
            {
                BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
                (new WorldGenDesertWells()).generate(worldIn, rand, blockpos);
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
            if (rand.nextInt(64) == 0)
            {
                (new WorldGenFossils()).generate(worldIn, rand, pos);
            }
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal < 0.1D) {
            topBlock = Blocks.SAND.getDefaultState();
            fillerBlock = Blocks.SAND.getDefaultState();
            decorator.treesPerChunk = -999;
            decorator.reedsPerChunk = 50;
            decorator.deadBushPerChunk = 8;
            decorator.cactiPerChunk = 10;
        }
        if (noiseVal >= 0.1D) {
            fillerBlock = Blocks.DIRT.getDefaultState();
            topBlock = Blocks.GRASS.getDefaultState();
            decorator.treesPerChunk = 4;
            decorator.grassPerChunk = 8;
        }
        generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 2211330;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 2211330;
    }

    class Decorator extends BiomeDecorator
    {
        private Decorator()
        {
        }

        protected void generateOres(World worldIn, Random random)
        {
            super.generateOres(worldIn, random);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, lapisGen, chunkPos, OreGenEvent.GenerateMinable.EventType.LAPIS))
                genStandardOre1(worldIn, random, 10, lapisGen, 32, 80);
        }
    }
}