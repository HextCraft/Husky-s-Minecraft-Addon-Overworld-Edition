package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

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