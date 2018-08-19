package net.hdt.neutronia.groups.world.blocks.corals;

import net.hdt.neutronia.base.states.BlockStateProperties;
import net.hdt.neutronia.blocks.base.BlockModBush;
import net.hdt.neutronia.groups.world.features.overworld.SeaPickles;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSeaPickle extends BlockModBush implements IGrowable {
    public static final PropertyInteger field_204902_a = BlockStateProperties.field_208135_aj;
    private static final PropertyBool field_204903_b = BlockStateProperties.field_208198_y;

    public BlockSeaPickle() {
        super("sea_pickle", Material.CORAL);
        this.setDefaultState((this.blockState.getBaseState()).withProperty(field_204902_a, 1).withProperty(field_204903_b, Boolean.TRUE));
    }

    private boolean func_204901_j(IBlockState p_204901_1_) {
        return !p_204901_1_.getValue(field_204903_b);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, field_204902_a, field_204903_b);
    }

    /**
     * Whether this IGrowable can grow
     */
    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (!this.func_204901_j(state) && worldIn.getBlockState(pos.down()).getBlock() instanceof BlockCoralBlock) {
            int j = 1;
            int l = 0;
            int i1 = pos.getX() - 2;
            int j1 = 0;

            for (int k1 = 0; k1 < 5; ++k1) {
                for (int l1 = 0; l1 < j; ++l1) {
                    int i2 = 2 + pos.getY() - 1;

                    for (int j2 = i2 - 2; j2 < i2; ++j2) {
                        BlockPos blockpos = new BlockPos(i1 + k1, j2, pos.getZ() - j1 + l1);

                        if (blockpos != pos && rand.nextInt(6) == 0 && worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) {
                            IBlockState iblockstate = worldIn.getBlockState(blockpos.down());

                            if (iblockstate.getBlock() instanceof BlockCoralBlock) {
                                worldIn.setBlockState(blockpos, SeaPickles.SEA_PICKLE.getDefaultState().withProperty(field_204902_a, rand.nextInt(4) + 1), 3);
                            }
                        }
                    }
                }

                if (l < 2) {
                    j += 2;
                    ++j1;
                } else {
                    j -= 2;
                    --j1;
                }

                ++l;
            }

            worldIn.setBlockState(pos, state.withProperty(field_204902_a, 4), 2);
        }
    }
}
