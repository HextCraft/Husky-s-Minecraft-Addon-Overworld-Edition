package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import team.hdt.huskylib.block.BlockFacing;

public class BlockLectern extends BlockFacing implements INeutroniaBlock {

    public BlockLectern(BlockPlanks.EnumType woodType) {
        super(String.format("%s_lectern", woodType.getName()), Material.WOOD);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

}