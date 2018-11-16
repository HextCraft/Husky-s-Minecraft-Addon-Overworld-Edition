package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;

public class BlockFletchingTable extends BlockFacing implements INeutroniaBlock {

    public BlockFletchingTable(BlockPlanks.EnumType woodType) {
        super(String.format("%s_fletching_table", woodType.getName()), Material.WOOD);
    }

}