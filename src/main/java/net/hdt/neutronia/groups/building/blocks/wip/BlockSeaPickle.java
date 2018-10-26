package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.state.StateHolder;
import net.minecraft.property.Properties;
import net.minecraft.tag.BlockTags;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.state.StateContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReadable;
import net.minecraft.util.math.Facing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockView;
import javax.annotation.Nullable;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.property.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.property.PropertyBoolean;
import net.minecraft.property.PropertyInteger;

public class BlockSeaPickle extends BlockBush implements IFertilizable, IFluidStateSupport, IFluidStateSupportWritable
{
    public static final PropertyInteger a;
    public static final PropertyBoolean b;
    protected static final VoxelShape c;
    protected static final VoxelShape d;
    protected static final VoxelShape e;
    protected static final VoxelShape f;
    
    protected BlockSeaPickle(final Builder builder) {
        super(builder);
        this.setDefaultState(((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockSeaPickle.a, 1)).<Comparable, Boolean>with((IProperty<Comparable>)BlockSeaPickle.b, true));
    }
    
    @Override
    public int getLightValue(final BlockState aBlockState) {
        return this.k(aBlockState) ? 0 : (super.getLightValue(aBlockState) + 3 * aBlockState.<Integer>get((IProperty<Integer>)BlockSeaPickle.a));
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        final BlockState vBlockState2 = aBlockPlaceItemInteraction.getWorld().h(aBlockPlaceItemInteraction.getPosition());
        if (vBlockState2.getBlock() == this) {
            return ((StateHolder<O, BlockState>)vBlockState2).<Comparable, Integer>with((IProperty<Comparable>)BlockSeaPickle.a, Math.min(4, vBlockState2.<Integer>get((IProperty<Integer>)BlockSeaPickle.a) + 1));
        }
        final IFluidState vIFluidState3 = aBlockPlaceItemInteraction.getWorld().getFluidState(aBlockPlaceItemInteraction.getPosition());
        final boolean vBoolean4 = vIFluidState3.hasTag(FluidTags.WATER) && vIFluidState3.getLevel() == 8;
        return ((StateHolder<O, BlockState>)super.getDefaultState(aBlockPlaceItemInteraction)).<Comparable, Boolean>with((IProperty<Comparable>)BlockSeaPickle.b, vBoolean4);
    }
    
    private boolean k(final BlockState aBlockState) {
        return !aBlockState.<Boolean>get((IProperty<Boolean>)BlockSeaPickle.b);
    }
    
    @Override
    protected boolean canBePlacedOn(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return !aBlockState1.h(aIBlockView2, aBlockPos3).a(Facing.UP).b();
    }
    
    @Override
    public boolean canBePlaced(final BlockState aBlockState1, final IWorldReadable aIWorldReadable2, final BlockPos aBlockPos3) {
        final BlockPos vBlockPos4 = aBlockPos3.down();
        return this.canBePlacedOn(aIWorldReadable2.h(vBlockPos4), aIWorldReadable2, vBlockPos4);
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (!aBlockState1.a(aIWorld4, aBlockPos5)) {
            return Blocks.a.getDefaultState();
        }
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockSeaPickle.b)) {
            aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        }
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public boolean a(final BlockState aBlockState1, final BlockPlaceItemInteraction aBlockPlaceItemInteraction2) {
        return (aBlockPlaceItemInteraction2.getHeldItem().getItem() == this.getItem() && aBlockState1.<Integer>get((IProperty<Integer>)BlockSeaPickle.a) < 4) || super.a(aBlockState1, aBlockPlaceItemInteraction2);
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        switch (aBlockState1.<Integer>get((IProperty<Integer>)BlockSeaPickle.a)) {
            default: {
                return BlockSeaPickle.c;
            }
            case 2: {
                return BlockSeaPickle.d;
            }
            case 3: {
                return BlockSeaPickle.e;
            }
            case 4: {
                return BlockSeaPickle.f;
            }
        }
    }
    
    @Override
    public Fluid getContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3) {
        if (aBlockState3.<Boolean>get((IProperty<Boolean>)BlockSeaPickle.b)) {
            aIWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)aBlockState3).<Comparable, Boolean>with((IProperty<Comparable>)BlockSeaPickle.b, false), 3);
            return Fluids.WATER;
        }
        return Fluids.a;
    }
    
    @Override
    public IFluidState getFluidState(final BlockState aBlockState) {
        if (aBlockState.<Boolean>get((IProperty<Boolean>)BlockSeaPickle.b)) {
            return Fluids.WATER.a(false);
        }
        return super.getFluidState(aBlockState);
    }
    
    @Override
    public boolean canSetFluid(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final Fluid aFluid4) {
        return !aBlockState3.<Boolean>get((IProperty<Boolean>)BlockSeaPickle.b) && aFluid4 == Fluids.WATER;
    }
    
    @Override
    public boolean setContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3, final IFluidState aIFluidState4) {
        if (!aBlockState3.<Boolean>get((IProperty<Boolean>)BlockSeaPickle.b) && aIFluidState4.getFluid() == Fluids.WATER) {
            if (!aIWorld1.isClient()) {
                aIWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)aBlockState3).<Comparable, Boolean>with((IProperty<Comparable>)BlockSeaPickle.b, true), 3);
                aIWorld1.getFluidTickScheduler().scheduleTick(aBlockPos2, aIFluidState4.getFluid(), aIFluidState4.getFluid().a(aIWorld1));
            }
            return true;
        }
        return false;
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockSeaPickle.a, BlockSeaPickle.b);
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
    public void grow(final World aWorld1, final Random aRandom2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        if (!this.k(aBlockState4) && aWorld1.h(aBlockPos3.down()).a(BlockTags.CORAL_BLOCKS)) {
            final int vInteger5 = 5;
            int vInteger6 = 1;
            final int vInteger7 = 2;
            int vInteger8 = 0;
            final int vInteger9 = aBlockPos3.getX() - 2;
            int vInteger10 = 0;
            for (int vInteger11 = 0; vInteger11 < 5; ++vInteger11) {
                for (int vInteger12 = 0; vInteger12 < vInteger6; ++vInteger12) {
                    for (int vInteger13 = 2 + aBlockPos3.getY() - 1, vInteger14 = vInteger13 - 2; vInteger14 < vInteger13; ++vInteger14) {
                        final BlockPos vBlockPos15 = new BlockPos(vInteger9 + vInteger11, vInteger14, aBlockPos3.getZ() - vInteger10 + vInteger12);
                        if (vBlockPos15 != aBlockPos3) {
                            if (aRandom2.nextInt(6) == 0 && aWorld1.h(vBlockPos15).getBlock() == Blocks.A) {
                                final BlockState vBlockState16 = aWorld1.h(vBlockPos15.down());
                                if (vBlockState16.a(BlockTags.CORAL_BLOCKS)) {
                                    aWorld1.setBlockState(vBlockPos15, ((StateHolder<O, BlockState>)Blocks.kK.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockSeaPickle.a, aRandom2.nextInt(4) + 1), 3);
                                }
                            }
                        }
                    }
                }
                if (vInteger8 < 2) {
                    vInteger6 += 2;
                    ++vInteger10;
                }
                else {
                    vInteger6 -= 2;
                    --vInteger10;
                }
                ++vInteger8;
            }
            aWorld1.setBlockState(aBlockPos3, ((StateHolder<O, BlockState>)aBlockState4).<Comparable, Integer>with((IProperty<Comparable>)BlockSeaPickle.a, 4), 2);
        }
    }
    
    static {
        a = Properties.PICKLES;
        b = Properties.WATERLOGGED;
        c = Block.a(6.0, 0.0, 6.0, 10.0, 6.0, 10.0);
        d = Block.a(3.0, 0.0, 3.0, 13.0, 6.0, 13.0);
        e = Block.a(2.0, 0.0, 2.0, 14.0, 6.0, 14.0);
        f = Block.a(2.0, 0.0, 2.0, 14.0, 7.0, 14.0);
    }
}
