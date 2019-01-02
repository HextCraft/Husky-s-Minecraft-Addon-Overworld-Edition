package team.hdt.neutronia.world.biomes;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class NBiome extends Biome {

    protected boolean genDoubleGrass = false;
    protected int doubleGrassPerChunk;

    protected static final IBlockState SAND = Blocks.SAND.getDefaultState();
    protected static final IBlockState RED_SAND = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
    protected static final IBlockState HARDENED_CLAY = Blocks.HARDENED_CLAY.getDefaultState();
    protected static final IBlockState STAINED_HARDENED_CLAY = Blocks.STAINED_HARDENED_CLAY.getDefaultState();
    protected static final IBlockState GRASS = Blocks.GRASS.getDefaultState();
    protected static final IBlockState DIRT = Blocks.DIRT.getDefaultState();
    protected static final IBlockState COARSE_DIRT = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
    protected static final IBlockState PODZOL = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
    protected static final IBlockState CLAY = Blocks.CLAY.getDefaultState();
    protected static final IBlockState OAK_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, false);
    protected static final IBlockState JUNGLE_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, false);
    protected static final IBlockState PACKED_ICE = Blocks.PACKED_ICE.getDefaultState();
    protected static final IBlockState SNOW = Blocks.SNOW.getDefaultState();
    protected static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();
    protected static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    protected static final IBlockState OAK_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);

    protected static final WorldGenTaiga1 PINE_TREE = new WorldGenTaiga1();
    protected static final WorldGenTaiga2 SPRUCE_TREE = new WorldGenTaiga2(false);
    protected static final WorldGenBirchTree SUPER_BIRCH_TREE = new WorldGenBirchTree(false, true);
    protected static final WorldGenBirchTree BIRCH_TREE = new WorldGenBirchTree(false, false);
    protected static final WorldGenCanopyTree ROOF_TREE = new WorldGenCanopyTree(false);
    protected static final WorldGenSavannaTree SAVANNA_TREE = new WorldGenSavannaTree(false);

    public NBiome(BiomeProperties properties) {
        super(properties);
    }

    public void addDoubleGrass(World world, Random random, BlockPos pos, int p_185378_4_)
    {
        for (int i = 0; i < p_185378_4_; ++i)
        {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

            for (int k = 0; k < doubleGrassPerChunk; k++)
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
}