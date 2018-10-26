package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nullable;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.world.IWorldReadable;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.IWorld;
import net.minecraft.state.StateContainer;
import net.minecraft.util.MirrorDirection;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockView;
import net.minecraft.property.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.Facing;
import java.util.Map;
import net.minecraft.property.PropertyFacing;

public class BlockCoralWallFan extends bdn
{
    public static final PropertyFacing FACING;
    private static final Map<Facing, VoxelShape> c;
    
    protected BlockCoralWallFan(final Builder builder) {
        super(builder);
        this.setDefaultState(((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Facing>with((IProperty<Comparable>)BlockCoralWallFan.FACING, Facing.NORTH)).<Comparable, Boolean>with((IProperty<Comparable>)BlockCoralWallFan.WATERLOGGED, true));
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return BlockCoralWallFan.c.get(aBlockState1.<Object>get((IProperty<Object>)BlockCoralWallFan.FACING));
    }
    
    @Override
    public BlockState withRotation(final BlockState aBlockState1, final Rotation aRotation2) {
        return ((StateHolder<O, BlockState>)aBlockState1).<Comparable, Facing>with((IProperty<Comparable>)BlockCoralWallFan.FACING, aRotation2.rotate(aBlockState1.<Facing>get((IProperty<Facing>)BlockCoralWallFan.FACING)));
    }
    
    @Override
    public BlockState withMirror(final BlockState aBlockState1, final MirrorDirection aMirrorDirection2) {
        return aBlockState1.withRotation(aMirrorDirection2.a(aBlockState1.<Facing>get((IProperty<Facing>)BlockCoralWallFan.FACING)));
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockCoralWallFan.FACING, BlockCoralWallFan.WATERLOGGED);
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockCoralWallFan.WATERLOGGED)) {
            aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        }
        if (aFacing2.getOpposite() == aBlockState1.<Facing>get((IProperty<Facing>)BlockCoralWallFan.FACING) && !aBlockState1.a(aIWorld4, aBlockPos5)) {
            return Blocks.a.getDefaultState();
        }
        return aBlockState1;
    }
    
    @Override
    public boolean canBePlaced(final BlockState aBlockState1, final IWorldReadable aIWorldReadable2, final BlockPos aBlockPos3) {
        final Facing vFacing4 = aBlockState1.<Facing>get((IProperty<Facing>)BlockCoralWallFan.FACING);
        final BlockPos vBlockPos5 = aBlockPos3.offset(vFacing4.getOpposite());
        final BlockState vBlockState6 = aIWorldReadable2.h(vBlockPos5);
        return vBlockState6.c(aIWorldReadable2, vBlockPos5, vFacing4) == BlockFaceShape.SOLID && !Block.disallowsPistonAttach(vBlockState6.getBlock());
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        BlockState vBlockState2 = super.getDefaultState(aBlockPlaceItemInteraction);
        final IWorldReadable vIWorldReadable3 = aBlockPlaceItemInteraction.getWorld();
        final BlockPos vBlockPos4 = aBlockPlaceItemInteraction.getPosition();
        final Facing[] e;
        final Facing[] vArr5 = e = aBlockPlaceItemInteraction.e();
        for (final Facing vFacing9 : e) {
            if (vFacing9.getAxis().isHorizontal()) {
                vBlockState2 = ((StateHolder<O, BlockState>)vBlockState2).<Comparable, Facing>with((IProperty<Comparable>)BlockCoralWallFan.FACING, vFacing9.getOpposite());
                if (vBlockState2.a(vIWorldReadable3, vBlockPos4)) {
                    return vBlockState2;
                }
            }
        }
        return null;
    }
    
    static {
        FACING = BlockHorizontalRotation.HORIZONTAL_FACING;
        c = Maps.<Facing, Object>newEnumMap((Map<Facing, ?>)ImmutableMap.<Facing, VoxelShape>of(Facing.NORTH, Block.a(0.0, 4.0, 5.0, 16.0, 12.0, 16.0), Facing.SOUTH, Block.a(0.0, 4.0, 0.0, 16.0, 12.0, 11.0), Facing.WEST, Block.a(5.0, 4.0, 0.0, 16.0, 12.0, 16.0), Facing.EAST, Block.a(0.0, 4.0, 0.0, 11.0, 12.0, 16.0)));
    }
}
