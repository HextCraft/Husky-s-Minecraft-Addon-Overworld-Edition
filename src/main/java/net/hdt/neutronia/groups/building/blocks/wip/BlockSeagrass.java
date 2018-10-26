package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.state.StateHolder;
import net.minecraft.fluid.Fluid;
import net.minecraft.property.IProperty;
import net.minecraft.property.enums.DoorHalf;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReadable;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.IWorld;
import javax.annotation.Nullable;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.util.math.Facing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockView;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;

public class BlockSeagrass extends BlockBush implements IFertilizable, IFluidStateSupportWritable
{
    protected static final VoxelShape a;
    
    protected BlockSeagrass(final Builder builder) {
        super(builder);
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return BlockSeagrass.a;
    }
    
    @Override
    protected boolean canBePlacedOn(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return Block.a(aBlockState1.h(aIBlockView2, aBlockPos3), Facing.UP) && aBlockState1.getBlock() != Blocks.iz;
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        final IFluidState vIFluidState2 = aBlockPlaceItemInteraction.getWorld().getFluidState(aBlockPlaceItemInteraction.getPosition());
        if (vIFluidState2.hasTag(FluidTags.WATER) && vIFluidState2.getLevel() == 8) {
            return super.getDefaultState(aBlockPlaceItemInteraction);
        }
        return null;
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        final BlockState vBlockState7 = super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
        if (!vBlockState7.isAir()) {
            aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        }
        return vBlockState7;
    }
    
    @Override
    public boolean isFertilizable(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final boolean aBoolean4) {
        return true;
    }
    
    @Override
    public boolean canGrow(final World aWorld1, final Random aRandom2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        return true;
    }
    
    @Override
    public IFluidState getFluidState(final BlockState aBlockState) {
        return Fluids.WATER.a(false);
    }
    
    @Override
    public void grow(final World aWorld1, final Random aRandom2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        final BlockState vBlockState5 = Blocks.aU.getDefaultState();
        final BlockState vBlockState6 = ((StateHolder<O, BlockState>)vBlockState5).<DoorHalf, DoorHalf>with(BlockTallSeagrass.c, DoorHalf.UPPER);
        final BlockPos vBlockPos7 = aBlockPos3.up();
        if (aWorld1.h(vBlockPos7).getBlock() == Blocks.A) {
            aWorld1.setBlockState(aBlockPos3, vBlockState5, 2);
            aWorld1.setBlockState(vBlockPos7, vBlockState6, 2);
        }
    }
    
    @Override
    public boolean canSetFluid(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final Fluid aFluid4) {
        return false;
    }
    
    @Override
    public boolean setContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3, final IFluidState aIFluidState4) {
        return false;
    }
    
    static {
        a = Block.a(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);
    }
}
