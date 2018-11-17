package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;

public class BlockBarrel extends BlockFacing implements INeutroniaBlock {

    public BlockBarrel(BlockPlanks.EnumType type) {
        super(String.format("%s_barrel", type.getName()), Material.WOOD);
    }

    /*@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBarrel();
    }*/

}