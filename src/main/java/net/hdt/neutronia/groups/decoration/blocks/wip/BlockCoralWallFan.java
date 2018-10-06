package net.hdt.neutronia.groups.decoration.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCoralWallFan extends BlockCoralWallFanDead {
   private final Block deadBlock;

   protected BlockCoralWallFan(Block p_i49774_1_, Block.Builder builder) {
      super(builder);
      this.deadBlock = p_i49774_1_;
   }

   public void onBlockAdded(IBlockState state, World worldIn, BlockPos pos, IBlockState oldState) {
      this.checkForDeath(state, worldIn, pos);
   }

   public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
      if (!canSurvive(state, worldIn, pos)) {
         worldIn.setBlockState(pos, (IBlockState)((IBlockState)this.deadBlock.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false))).with(FACING, state.get(FACING)), 2);
      }

   }

   public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (facing.getOpposite() == stateIn.get(FACING) && !stateIn.isValidPosition(worldIn, currentPos)) {
         return Blocks.AIR.getDefaultState();
      } else {
         if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
         }

         this.checkForDeath(stateIn, worldIn, currentPos);
         return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
      }
   }
}
