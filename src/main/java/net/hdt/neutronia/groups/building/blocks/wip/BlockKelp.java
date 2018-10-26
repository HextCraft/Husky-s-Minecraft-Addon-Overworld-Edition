package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import net.minecraft.property.Properties;
import net.minecraft.fluid.Fluid;
import net.minecraft.state.StateContainer;
import net.minecraft.world.IWorldReadable;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.Facing;
import net.minecraft.client.render.block.BlockRenderLayer;
import javax.annotation.Nullable;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.world.IWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockView;
import net.minecraft.property.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.property.PropertyInteger;

public class BlockKelp extends Block implements IFluidStateSupportWritable
{
    public static final PropertyInteger a;
    protected static final VoxelShape b;
    
    protected BlockKelp(final Builder builder) {
        super(builder);
        this.setDefaultState(((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockKelp.a, 0));
    }
    
    @Override
    public boolean isFullBlock(final BlockState aBlockState) {
        return false;
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return BlockKelp.b;
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        final IFluidState vIFluidState2 = aBlockPlaceItemInteraction.getWorld().getFluidState(aBlockPlaceItemInteraction.getPosition());
        if (vIFluidState2.hasTag(FluidTags.WATER) && vIFluidState2.getLevel() == 8) {
            return this.a(aBlockPlaceItemInteraction.getWorld());
        }
        return null;
    }
    
    public BlockState a(final IWorld aIWorld) {
        return ((StateHolder<O, BlockState>)this.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockKelp.a, aIWorld.getRandom().nextInt(25));
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
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        if (!aBlockState1.a((IWorldReadable)aWorld2, aBlockPos3)) {
            aWorld2.breakBlock(aBlockPos3, true);
            return;
        }
        final BlockPos vBlockPos5 = aBlockPos3.up();
        final BlockState vBlockState6 = aWorld2.h(vBlockPos5);
        if (vBlockState6.getBlock() == Blocks.A && aBlockState1.<Integer>get((IProperty<Integer>)BlockKelp.a) < 25 && aRandom4.nextDouble() < 0.14) {
            aWorld2.setBlockState(vBlockPos5, ((StateHolder<O, BlockState>)aBlockState1).<Comparable>a((IProperty<Comparable>)BlockKelp.a));
        }
    }
    
    @Override
    public boolean canBePlaced(final BlockState aBlockState1, final IWorldReadable aIWorldReadable2, final BlockPos aBlockPos3) {
        final BlockPos vBlockPos4 = aBlockPos3.down();
        final BlockState vBlockState5 = aIWorldReadable2.h(vBlockPos4);
        final Block vBlock6 = vBlockState5.getBlock();
        return vBlock6 != Blocks.iz && (vBlock6 == this || vBlock6 == Blocks.jT || Block.a(vBlockState5.h(aIWorldReadable2, vBlockPos4), Facing.UP));
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (!aBlockState1.a(aIWorld4, aBlockPos5)) {
            if (aFacing2 == Facing.DOWN) {
                return Blocks.a.getDefaultState();
            }
            aIWorld4.getBlockTickScheduler().scheduleTick(aBlockPos5, this, 1);
        }
        if (aFacing2 == Facing.UP && aBlockState3.getBlock() == this) {
            return Blocks.jT.getDefaultState();
        }
        aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockKelp.a);
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
        a = Properties.AGE_6;
        b = Block.a(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);
    }
}
