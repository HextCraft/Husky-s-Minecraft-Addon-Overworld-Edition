package net.hdt.neutronia.groups.decoration.blocks.wip;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCoral extends BlockMod implements INeutroniaBlock {
   private final Block deadBlock;

   public BlockCoral(String name, Block p_i48893_1_) {
      super(name, Material.ROCK);
      this.deadBlock = p_i48893_1_;
   }

   public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
      if (!this.canLive(worldIn, pos)) {
         worldIn.setBlockState(pos, this.deadBlock.getDefaultState(), 2);
      }

   }

   public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, World worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (!this.canLive(worldIn, currentPos)) {
         worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 60 + worldIn.getRandom().nextInt(40));
      }

      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   protected boolean canLive(World world, BlockPos itsPosition) {
      for (EnumFacing facing : EnumFacing.values()) {
         IBlockState sidestate = world.getBlockState(itsPosition.offset(facing));
         if (sidestate.getBlock() == Blocks.WATER || sidestate.getBlock() == Blocks.FLOWING_WATER) {
            return true;
         }
      }
      return false;
   }

   @Override
   public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
      if (!this.canLive(context.getWorld(), context.getPos())) {
         context.getWorld().getPendingBlockTicks().scheduleTick(context.getPos(), this, 60 + context.getWorld().getRandom().nextInt(40));
      }

      return this.getDefaultState();
   }

   @Nullable
   public IBlockState getStateForPlacement(BlockItemUseContext context) {
      if (!this.canLive(context.getWorld(), context.getPos())) {
         context.getWorld().getPendingBlockTicks().scheduleTick(context.getPos(), this, 60 + context.getWorld().getRandom().nextInt(40));
      }

      return this.getDefaultState();
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
      return this.deadBlock;
   }
}
