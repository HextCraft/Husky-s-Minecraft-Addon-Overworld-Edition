package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import net.minecraft.property.Properties;
import net.minecraft.fluid.Fluid;
import net.minecraft.state.StateContainer;
import net.minecraft.world.IWorldReadable;
import net.minecraft.fluid.Fluids;
import net.minecraft.client.render.block.BlockRenderLayer;
import javax.annotation.Nullable;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Facing;
import net.minecraft.world.IBlockView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.property.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.property.PropertyBoolean;

public class BlockCoral extends Block implements IFluidStateSupport, IFluidStateSupportWritable
{
    public static final PropertyBoolean WATERLOGGED;
    private static final VoxelShape shape;
    
    protected BlockCoral(final Builder builder) {
        super(builder);
        this.setDefaultState(((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockCoral.WATERLOGGED, true));
    }
    
    protected void a(final BlockState aBlockState1, final IWorld aIWorld2, final BlockPos aBlockPos3) {
        if (!b_(aBlockState1, aIWorld2, aBlockPos3)) {
            aIWorld2.getBlockTickScheduler().scheduleTick(aBlockPos3, this, 60 + aIWorld2.getRandom().nextInt(40));
        }
    }
    
    protected static boolean b_(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockCoral.WATERLOGGED)) {
            return true;
        }
        for (final Facing vFacing7 : Facing.values()) {
            if (aIBlockView2.getFluidState(aBlockPos3.offset(vFacing7)).hasTag(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        final IFluidState vIFluidState2 = aBlockPlaceItemInteraction.getWorld().getFluidState(aBlockPlaceItemInteraction.getPosition());
        return ((StateHolder<O, BlockState>)this.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockCoral.WATERLOGGED, vIFluidState2.hasTag(FluidTags.WATER) && vIFluidState2.getLevel() == 8);
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return BlockCoral.shape;
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
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockCoral.WATERLOGGED)) {
            aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        }
        if (aFacing2 == Facing.DOWN && !this.canBePlaced(aBlockState1, aIWorld4, aBlockPos5)) {
            return Blocks.a.getDefaultState();
        }
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public boolean canBePlaced(final BlockState aBlockState1, final IWorldReadable aIWorldReadable2, final BlockPos aBlockPos3) {
        return aIWorldReadable2.h(aBlockPos3.down()).isTopSolid();
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockCoral.WATERLOGGED);
    }
    
    @Override
    public IFluidState getFluidState(final BlockState aBlockState) {
        if (aBlockState.<Boolean>get((IProperty<Boolean>)BlockCoral.WATERLOGGED)) {
            return Fluids.WATER.a(false);
        }
        return super.getFluidState(aBlockState);
    }
    
    @Override
    public Fluid getContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3) {
        if (aBlockState3.<Boolean>get((IProperty<Boolean>)BlockCoral.WATERLOGGED)) {
            aIWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)aBlockState3).<Comparable, Boolean>with((IProperty<Comparable>)BlockCoral.WATERLOGGED, false), 3);
            return Fluids.WATER;
        }
        return Fluids.a;
    }
    
    @Override
    public boolean canSetFluid(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final Fluid aFluid4) {
        return !aBlockState3.<Boolean>get((IProperty<Boolean>)BlockCoral.WATERLOGGED) && aFluid4 == Fluids.WATER;
    }
    
    @Override
    public boolean setContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3, final IFluidState aIFluidState4) {
        if (!aBlockState3.<Boolean>get((IProperty<Boolean>)BlockCoral.WATERLOGGED) && aIFluidState4.getFluid() == Fluids.WATER) {
            if (!aIWorld1.isClient()) {
                aIWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)aBlockState3).<Comparable, Boolean>with((IProperty<Comparable>)BlockCoral.WATERLOGGED, true), 3);
                aIWorld1.getFluidTickScheduler().scheduleTick(aBlockPos2, aIFluidState4.getFluid(), aIFluidState4.getFluid().a(aIWorld1));
            }
            return true;
        }
        return false;
    }
    
    static {
        WATERLOGGED = Properties.WATERLOGGED;
        shape = Block.a(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
    }
}
