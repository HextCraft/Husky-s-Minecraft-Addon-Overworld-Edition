package team.abnormal.neutronia.world.biomes;

import net.minecraft.block.*;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class WoodlandBiome extends NBiome {

    public boolean isHilly;
    public EnumType type;

    public WoodlandBiome(boolean isHillyIn, EnumType typeIn, BiomeProperties properties) {
        super(properties);
        isHilly = isHillyIn;
        type = typeIn;
        fillerBlock = Blocks.DIRT.getDefaultState();
        decorator.treesPerChunk = 12;
        decorator.grassPerChunk = 8;
        if (type == EnumType.NORMAL) {
            spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 5, 2, 3));
            spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 6, 3, 3));
        }
        if (type == EnumType.COLD) {
            spawnableCreatureList.add(new SpawnListEntry(EntityStray.class, 10, 3, 4));
            getEnableSnow();
            properties.setSnowEnabled();
        }
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);
        addDoublePlants(worldIn, rand, pos, (rand.nextInt(10) - 3));
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(5) == 0) {
            return new WorldGenShrub(OAK_LOG, OAK_LEAVES);
        }
        if (type == EnumType.NORMAL) {
            if (rand.nextInt(6) < 5) {
                if (rand.nextInt(3) == 3) {
                    return BIG_TREE_FEATURE;
                }
                return TREE_FEATURE;
            }
            else if (rand.nextInt(6) > 4) {
                return BIRCH_TREE;
            }
            else {
                return SPRUCE_TREE;
            }
        }
        if (type == EnumType.COLD) {
            if (rand.nextInt(6) < 4) {
                return SPRUCE_TREE;
            }
            else if (rand.nextInt(8) > 5) {
                return BIRCH_TREE;
            }
            else {
                if (rand.nextInt(4) == 3) {
                    return BIG_TREE_FEATURE;
                }
                return TREE_FEATURE;
            }
        }
        return null;
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal < 0.1D && rand.nextInt(10) > 7);{
            topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
            decorator.treesPerChunk = 5;
        }
        if (noiseVal >= 0.1D) {
            topBlock = Blocks.GRASS.getDefaultState();
        }
        if (type == EnumType.COLD && rand.nextInt(10) > 7) {
            if (isHilly && noiseVal > 1.0D) {
                topBlock = Blocks.SNOW.getDefaultState();
            }
            else if (!isHilly && noiseVal > 1.5D) {
                topBlock = Blocks.SNOW.getDefaultState();
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
        }
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return super.pickRandomFlower(rand, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x739134;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x8A9630;
    }

    public enum EnumType {
        NORMAL,
        COLD
    }
}