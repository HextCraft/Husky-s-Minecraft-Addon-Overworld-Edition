package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import net.minecraft.property.Properties;
import net.minecraft.fluid.Fluid;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.block.entity.BlockEntityBeacon;
import net.minecraft.item.ItemStack;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReadable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Facing;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.block.entity.BlockEntityConduit;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.IBlockView;
import net.minecraft.state.StateContainer;
import net.minecraft.property.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.property.PropertyBoolean;

public class BlockConduit extends BlockWithEntity implements IFluidStateSupport, IFluidStateSupportWritable
{
    public static final PropertyBoolean a;
    protected static final VoxelShape b;
    
    public BlockConduit(final Builder builder) {
        super(builder);
        this.setDefaultState(((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockConduit.a, true));
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockConduit.a);
    }
    
    @Override
    public BlockEntity createBlockEntity(final IBlockView aIBlockView) {
        return new BlockEntityConduit();
    }
    
    @Override
    public boolean isFullBlock(final BlockState aBlockState) {
        return false;
    }
    
    @Override
    public BlockRenderType getRenderType(final BlockState aBlockState) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    
    @Override
    public IFluidState getFluidState(final BlockState aBlockState) {
        if (aBlockState.<Boolean>get((IProperty<Boolean>)BlockConduit.a)) {
            return Fluids.WATER.a(false);
        }
        return super.getFluidState(aBlockState);
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockConduit.a)) {
            aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        }
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return BlockConduit.b;
    }
    
    @Override
    public void onPlaced(final World aWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3, @Nullable final EntityLiving aEntityLiving4, final ItemStack aItemStack5) {
        if (aItemStack5.hasDisplayName()) {
            final BlockEntity vBlockEntity6 = aWorld1.getBlockEntity(aBlockPos2);
            if (vBlockEntity6 instanceof BlockEntityBeacon) {
                ((BlockEntityBeacon)vBlockEntity6).a(aItemStack5.r());
            }
        }
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        final IFluidState vIFluidState2 = aBlockPlaceItemInteraction.getWorld().getFluidState(aBlockPlaceItemInteraction.getPosition());
        return ((StateHolder<O, BlockState>)this.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockConduit.a, vIFluidState2.hasTag(FluidTags.WATER) && vIFluidState2.getLevel() == 8);
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
    public boolean isNotSolid(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3, final ccm aCcm4) {
        return false;
    }
    
    @Override
    public Fluid getContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3) {
        if (aBlockState3.<Boolean>get((IProperty<Boolean>)BlockConduit.a)) {
            aIWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)aBlockState3).<Comparable, Boolean>with((IProperty<Comparable>)BlockConduit.a, false), 3);
            return Fluids.WATER;
        }
        return Fluids.a;
    }
    
    @Override
    public boolean canSetFluid(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final Fluid aFluid4) {
        return !aBlockState3.<Boolean>get((IProperty<Boolean>)BlockConduit.a) && aFluid4 == Fluids.WATER;
    }
    
    @Override
    public boolean setContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3, final IFluidState aIFluidState4) {
        if (!aBlockState3.<Boolean>get((IProperty<Boolean>)BlockConduit.a) && aIFluidState4.getFluid() == Fluids.WATER) {
            if (!aIWorld1.isClient()) {
                aIWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)aBlockState3).<Comparable, Boolean>with((IProperty<Comparable>)BlockConduit.a, true), 3);
                aIWorld1.getFluidTickScheduler().scheduleTick(aBlockPos2, aIFluidState4.getFluid(), aIFluidState4.getFluid().a(aIWorld1));
            }
            return true;
        }
        return false;
    }
    
    static {
        a = Properties.WATERLOGGED;
        b = Block.a(5.0, 5.0, 5.0, 11.0, 11.0, 11.0);
    }
}
