package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.IWorldReadable;
import net.minecraft.util.math.Facing;
import net.minecraft.property.IProperty;
import net.minecraft.world.IBlockView;
import java.util.Random;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;

public class BlockLivingCoral extends BlockCoral
{
    private final Block c;
    protected static final VoxelShape shape;
    
    protected BlockLivingCoral(final Block aBlock1, final Builder aBlockBuilder2) {
        super(aBlockBuilder2);
        this.c = aBlock1;
    }
    
    @Override
    public void onBlockAdded(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        this.a(aBlockState1, (IWorld)aWorld2, aBlockPos3);
    }
    
    @Override
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        if (!BlockCoral.b_(aBlockState1, aWorld2, aBlockPos3)) {
            aWorld2.setBlockState(aBlockPos3, ((StateHolder<O, BlockState>)this.c.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockLivingCoral.WATERLOGGED, false), 2);
        }
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (aFacing2 == Facing.DOWN && !aBlockState1.a(aIWorld4, aBlockPos5)) {
            return Blocks.a.getDefaultState();
        }
        this.a(aBlockState1, aIWorld4, aBlockPos5);
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockLivingCoral.WATERLOGGED)) {
            aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        }
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return BlockLivingCoral.shape;
    }
    
    static {
        shape = Block.a(2.0, 0.0, 2.0, 14.0, 15.0, 14.0);
    }
}
