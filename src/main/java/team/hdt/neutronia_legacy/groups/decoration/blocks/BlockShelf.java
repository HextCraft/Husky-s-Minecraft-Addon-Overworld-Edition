package team.hdt.neutronia_legacy.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockShelf extends BlockFacing implements INeutroniaBlock {

    public BlockShelf(BlockPlanks.EnumType type) {
        super(String.format("%s_shelf", type.getName()), Material.WOOD);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
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