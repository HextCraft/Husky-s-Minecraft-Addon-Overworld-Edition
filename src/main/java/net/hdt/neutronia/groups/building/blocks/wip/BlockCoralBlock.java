package net.hdt.neutronia.groups.building.blocks.wip;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Facing;
import net.minecraft.world.IBlockView;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.state.BlockState;

public class BlockCoralBlock extends Block
{
    private final Block a;
    
    public BlockCoralBlock(final Block aBlock1, final Builder aBlockBuilder2) {
        super(aBlockBuilder2);
        this.a = aBlock1;
    }
    
    @Override
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        if (!this.a(aWorld2, aBlockPos3)) {
            aWorld2.setBlockState(aBlockPos3, this.a.getDefaultState(), 2);
        }
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (!this.a(aIWorld4, aBlockPos5)) {
            aIWorld4.getBlockTickScheduler().scheduleTick(aBlockPos5, this, 60 + aIWorld4.getRandom().nextInt(40));
        }
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    protected boolean a(final IBlockView aIBlockView1, final BlockPos aBlockPos2) {
        for (final Facing vFacing6 : Facing.values()) {
            final IFluidState vIFluidState7 = aIBlockView1.getFluidState(aBlockPos2.offset(vFacing6));
            if (vIFluidState7.hasTag(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        if (!this.a(aBlockPlaceItemInteraction.getWorld(), aBlockPlaceItemInteraction.getPosition())) {
            aBlockPlaceItemInteraction.getWorld().getBlockTickScheduler().scheduleTick(aBlockPlaceItemInteraction.getPosition(), this, 60 + aBlockPlaceItemInteraction.getWorld().getRandom().nextInt(40));
        }
        return this.getDefaultState();
    }
}
