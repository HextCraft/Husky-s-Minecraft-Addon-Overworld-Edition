package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorldReadable;
import net.minecraft.world.IWorld;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.util.math.Facing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockView;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.block.state.BlockState;

public class BlockKelpPlant extends Block implements IFluidStateSupportWritable
{
    final BlockKelp a;
    
    protected BlockKelpPlant(final BlockKelp aBlockKelp1, final Builder aBlockBuilder2) {
        super(aBlockBuilder2);
        this.a = aBlockKelp1;
    }
    
    @Override
    public boolean isFullBlock(final BlockState aBlockState) {
        return false;
    }
    
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(final IBlockView aIBlockView1, final BlockState aBlockState2, final BlockPos aBlockPos3, final Facing aFacing4) {
        return BlockFaceShape.UNDEFINED;
    }
    
    @Override
    public IFluidState getFluidState(final BlockState aBlockState) {
        return Fluids.WATER.a(false);
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (!aBlockState1.a(aIWorld4, aBlockPos5)) {
            return Blocks.a.getDefaultState();
        }
        if (aFacing2 == Facing.UP) {
            final Block vBlock7 = aBlockState3.getBlock();
            if (vBlock7 != this && vBlock7 != this.a) {
                return this.a.a(aIWorld4);
            }
        }
        aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public boolean canBePlaced(final BlockState aBlockState1, final IWorldReadable aIWorldReadable2, final BlockPos aBlockPos3) {
        final BlockPos vBlockPos4 = aBlockPos3.down();
        final BlockState vBlockState5 = aIWorldReadable2.h(vBlockPos4);
        final Block vBlock6 = vBlockState5.getBlock();
        return vBlock6 != Blocks.iz && (vBlock6 == this || Block.a(vBlockState5.h(aIWorldReadable2, vBlockPos4), Facing.UP));
    }
    
    @Sided(Side.CLIENT)
    @Override
    public ItemStack getPickItemStack(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3) {
        return new ItemStack(Blocks.jS);
    }
    
    @Override
    public boolean canSetFluid(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final Fluid aFluid4) {
        return false;
    }
    
    @Override
    public boolean setContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3, final IFluidState aIFluidState4) {
        return false;
    }
}
