package team.abnormal.neutronia.world.biomes;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class MarshBiome extends NBiome {

    public MarshBiome(BiomeProperties properties) {
        super(properties);
        decorator.flowersPerChunk = 1;
        decorator.mushroomsPerChunk = 8;
        decorator.reedsPerChunk = 10;
        decorator.clayPerChunk = 1;
        decorator.waterlilyPerChunk = 4;
        decorator.sandPatchesPerChunk = 0;
        decorator.grassPerChunk = 5;
        spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(2) > 1) {
            return ROOF_TREE;
        }
        return TREE_FEATURE;
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

        if (d0 > 0.0D)
        {
            int i = x & 15;
            int j = z & 15;

            for (int k = 255; k >= 0; --k)
            {
                if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR)
                {
                    if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER)
                    {
                        chunkPrimerIn.setBlockState(j, k, i, Blocks.WATER.getDefaultState());

                        if (d0 < 0.12D)
                        {
                            chunkPrimerIn.setBlockState(j, k + 1, i, WATER_LILY);
                        }
                    }

                    break;
                }
            }
        }
        generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);
        addMushrooms(worldIn, rand, pos);
        addDoublePlants(worldIn, rand, pos, (rand.nextInt(25) - 3) + 8);
    }

    protected void addMushrooms(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                int k = i * 4 + 1 + 8 + random.nextInt(3);
                int l = j * 4 + 1 + 8 + random.nextInt(3);
                BlockPos blockpos = world.getHeight(pos.add(k, 0, l));

                if (random.nextInt(20) == 0)
                {
                    WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
                    worldgenbigmushroom.generate(world, random, blockpos);
                }
                else
                {
                    WorldGenAbstractTree worldgenabstracttree = this.getRandomTreeFeature(random);
                    worldgenabstracttree.setDecorationDefaults();

                    if (worldgenabstracttree.generate(world, random, blockpos))
                    {
                        worldgenabstracttree.generateSaplings(world, random, blockpos);
                    }
                }
            }
        }
    }

    public void addDoublePlants(World world, Random random, BlockPos pos, int p_185378_4_)
    {
        for (int i = 0; i < p_185378_4_; ++i)
        {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

            for (int k = 0; k < 20; ++k)
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

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x416023;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x416023;
    }
}