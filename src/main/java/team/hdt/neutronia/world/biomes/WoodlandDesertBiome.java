package team.hdt.neutronia.world.biomes;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenFossils;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class WoodlandDesertBiome extends NBiome {

    public WoodlandDesertBiome(BiomeProperties properties) {
        super(properties);
        decorator.deadBushPerChunk = 8;
        decorator.cactiPerChunk = 10;
        spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 5, 2, 3));
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 6, 3, 3));
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);
        addDoublePlants(worldIn, rand, pos, (rand.nextInt(10) - 3));

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DESERT_WELL))
            if (rand.nextInt(1000) == 0)
            {
                int i = rand.nextInt(16) + 8;
                int j = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
                (new WorldGenDesertWells()).generate(worldIn, rand, blockpos);
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
            if (rand.nextInt(64) == 0)
            {
                (new WorldGenFossils()).generate(worldIn, rand, pos);
            }
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(5) < 1) {
            return new WorldGenShrub(OAK_LOG, OAK_LEAVES);
        }
        if (rand.nextInt(6) < 2) {
            if (rand.nextInt(3) == 3) {
                return BIG_TREE_FEATURE;
            }
            return TREE_FEATURE;
        }
        else {
            return SAVANNA_TREE;
        }
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal < 0.1D) {
            topBlock = Blocks.SAND.getDefaultState();
            fillerBlock = Blocks.SAND.getDefaultState();
            decorator.treesPerChunk = -999;
            this.decorator.reedsPerChunk = 50;
        }
        if (noiseVal >= 0.1D) {
            fillerBlock = Blocks.DIRT.getDefaultState();
            decorator.treesPerChunk = 12;
            decorator.grassPerChunk = 8;
            if (rand.nextInt(10) > 7) {
                topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
            }
            else {
                topBlock = Blocks.GRASS.getDefaultState();
            }
        }
        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    public void addDoublePlants(World world, Random random, BlockPos pos, int p_185378_4_)
    {
        for (int i = 0; i < p_185378_4_; ++i)
        {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

            for (int k = 0; k < 5; ++k)
            {
                int l = random.nextInt(16) + 8;
                int i1 = random.nextInt(16) + 8;
                int j1 = random.nextInt(world.getHeight(pos.add(l, 0, i1)).getY() + 32);

                if (DOUBLE_PLANT_GENERATOR.generate(world, random, new BlockPos(pos.getX() + l, j1, pos.getZ() + i1)))
                {
                    break;
                }
            }

            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);

            for (int k = 0; k < 3; ++k)
            {
                int l = random.nextInt(16) + 8;
                int i1 = random.nextInt(16) + 8;
                int j1 = random.nextInt(world.getHeight(pos.add(l, 0, i1)).getY() + 32);

                if (DOUBLE_PLANT_GENERATOR.generate(world, random, new BlockPos(pos.getX() + l, j1, pos.getZ() + i1)))
                {
                    break;
                }
            }
        }
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return BlockFlower.EnumFlowerType.HOUSTONIA;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x726B2C;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x726B2C;
    }
}