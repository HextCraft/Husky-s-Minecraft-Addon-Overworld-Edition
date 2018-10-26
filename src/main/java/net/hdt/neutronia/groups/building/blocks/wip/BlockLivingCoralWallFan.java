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

public class BlockLivingCoralWallFan extends BlockCoralWallFan
{
    private final Block deadBlock;
    
    protected BlockLivingCoralWallFan(final Block aBlock1, final Builder aBlockBuilder2) {
        super(aBlockBuilder2);
        this.deadBlock = aBlock1;
    }
    
    @Override
    public void onBlockAdded(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        this.a(aBlockState1, (IWorld)aWorld2, aBlockPos3);
    }
    
    @Override
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        if (!BlockCoral.b_(aBlockState1, aWorld2, aBlockPos3)) {
            aWorld2.setBlockState(aBlockPos3, ((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)this.deadBlock.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockLivingCoralWallFan.WATERLOGGED, false)).<Comparable, Comparable>with((IProperty<Comparable>)BlockLivingCoralWallFan.FACING, (Comparable)aBlockState1.<V>get((IProperty<V>)BlockLivingCoralWallFan.FACING)), 2);
        }
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (aFacing2.getOpposite() == aBlockState1.<Facing>get((IProperty<Facing>)BlockLivingCoralWallFan.FACING) && !aBlockState1.a(aIWorld4, aBlockPos5)) {
            return Blocks.a.getDefaultState();
        }
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockLivingCoralWallFan.WATERLOGGED)) {
            aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        }
        this.a(aBlockState1, aIWorld4, aBlockPos5);
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
}
