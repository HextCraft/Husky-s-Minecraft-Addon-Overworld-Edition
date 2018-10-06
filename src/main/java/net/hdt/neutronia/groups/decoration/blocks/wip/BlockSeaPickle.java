package net.hdt.neutronia.groups.decoration.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ShapeUtils;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSeaPickle extends BlockBush implements IGrowable, IBucketPickupHandler, ILiquidContainer {
   public static final IntegerProperty PICKLES = BlockStateProperties.PICKLES_1_4;
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
   protected static final VoxelShape field_204904_c = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
   protected static final VoxelShape field_204905_t = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
   protected static final VoxelShape field_204906_u = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
   protected static final VoxelShape field_204907_v = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);

   protected BlockSeaPickle(Block.Builder builder) {
      super(builder);
      this.setDefaultState((IBlockState)((IBlockState)(this.stateContainer.getBaseState()).with(PICKLES, Integer.valueOf(1))).with(WATERLOGGED, Boolean.valueOf(true)));
   }

   public int getLightValue(IBlockState state) {
      return this.isInBadEnvironment(state) ? 0 : super.getLightValue(state) + 3 * state.get(PICKLES);
   }

   @Nullable
   public IBlockState getStateForPlacement(BlockItemUseContext context) {
      IBlockState iblockstate = context.getWorld().getBlockState(context.getPos());
      if (iblockstate.getBlock() == this) {
         return (IBlockState)iblockstate.with(PICKLES, Integer.valueOf(Math.min(4, iblockstate.get(PICKLES) + 1)));
      } else {
         IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
         boolean flag = ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
         return (IBlockState)super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(flag));
      }
   }

   private boolean isInBadEnvironment(IBlockState p_204901_1_) {
      return !p_204901_1_.get(WATERLOGGED);
   }

   protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
      return !ShapeUtils.makeShapeFromSide(state.getCollisionShape(worldIn, pos), EnumFacing.UP).isEmpty();
   }

   public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
      BlockPos blockpos = pos.down();
      return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
   }

   public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (!stateIn.isValidPosition(worldIn, currentPos)) {
         return Blocks.AIR.getDefaultState();
      } else {
         if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
         }

         return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
      }
   }

   public boolean isReplaceable(IBlockState state, BlockItemUseContext useContext) {
      return useContext.getItem().getItem() == this.asItem() && state.get(PICKLES) < 4 ? true : super.isReplaceable(state, useContext);
   }

   public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
      switch(state.get(PICKLES)) {
      case 1:
      default:
         return field_204904_c;
      case 2:
         return field_204905_t;
      case 3:
         return field_204906_u;
      case 4:
         return field_204907_v;
      }
   }

   public Fluid pickupFluid(IWorld worldIn, BlockPos pos, IBlockState state) {
      if (state.get(WATERLOGGED)) {
         worldIn.setBlockState(pos, (IBlockState)state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
         return Fluids.WATER;
      } else {
         return Fluids.EMPTY;
      }
   }

   public IFluidState getFluidState(IBlockState state) {
      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
   }

   public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, IBlockState state, Fluid fluidIn) {
      return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
   }

   public boolean receiveFluid(IWorld worldIn, BlockPos pos, IBlockState state, IFluidState fluidStateIn) {
      if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
         if (!worldIn.isRemote()) {
            worldIn.setBlockState(pos, (IBlockState)state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
         }

         return true;
      } else {
         return false;
      }
   }

   protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
      builder.add(PICKLES, WATERLOGGED);
   }

   public int quantityDropped(IBlockState state, Random random) {
      return state.get(PICKLES);
   }

   public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
      return true;
   }

   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }

   public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      if (!this.isInBadEnvironment(state) && worldIn.getBlockState(pos.down()).isIn(BlockTags.CORAL_BLOCKS)) {
         int i = 5;
         int j = 1;
         int k = 2;
         int l = 0;
         int i1 = pos.getX() - 2;
         int j1 = 0;

         for(int k1 = 0; k1 < 5; ++k1) {
            for(int l1 = 0; l1 < j; ++l1) {
               int i2 = 2 + pos.getY() - 1;

               for(int j2 = i2 - 2; j2 < i2; ++j2) {
                  BlockPos blockpos = new BlockPos(i1 + k1, j2, pos.getZ() - j1 + l1);
                  if (blockpos != pos && rand.nextInt(6) == 0 && worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) {
                     IBlockState iblockstate = worldIn.getBlockState(blockpos.down());
                     if (iblockstate.isIn(BlockTags.CORAL_BLOCKS)) {
                        worldIn.setBlockState(blockpos, (IBlockState) Blocks.SEA_PICKLE.getDefaultState().with(PICKLES, Integer.valueOf(rand.nextInt(4) + 1)), 3);
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

         worldIn.setBlockState(pos, (IBlockState)state.with(PICKLES, Integer.valueOf(4)), 2);
      }

   }
}
