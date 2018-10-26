package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockView;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;

public class BlockDeadCoral extends BlockCoral
{
    protected static final VoxelShape shape;
    
    protected BlockDeadCoral(final Builder builder) {
        super(builder);
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return BlockDeadCoral.shape;
    }
    
    static {
        shape = Block.a(2.0, 0.0, 2.0, 14.0, 15.0, 14.0);
    }
}
