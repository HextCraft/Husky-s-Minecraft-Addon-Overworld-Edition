package net.hdt.neutronia.groups.world.features.overworld.changers;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;

public class TableChanger implements IChanger {
    @Override
    public boolean canChange(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState state) {
        if (state.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            IBlockState below = world.getBlockState(pos.down());
            return below.getBlock() instanceof BlockFence;
        }
        return false;
    }

    @Override
    public IBlockState change(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState state) {
//        world.setBlockState(pos.down(), BWMBlocks.WOOD_TABLE.getDefaultState());
        return Blocks.AIR.getDefaultState();
    }
}
