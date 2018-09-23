package net.hdt.neutronia.groups.world.features.overworld.changers;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;

public class ChestLootChanger implements IChanger {
    @Override
    public boolean canChange(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
        return original instanceof BlockChest;
    }

    @Override
    public IBlockState change(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileEntityChest) {
            ((TileEntityChest) tile).setLootTable(null, world.rand.nextLong());
        }
        return null;
    }
}
