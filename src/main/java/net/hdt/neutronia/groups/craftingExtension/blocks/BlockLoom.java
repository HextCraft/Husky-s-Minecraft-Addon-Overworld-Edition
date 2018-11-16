package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;

public class BlockLoom extends BlockFacing implements INeutroniaBlock {

    public BlockLoom(BlockPlanks.EnumType woodType) {
        super(String.format("%s_loom", woodType.getName()), Material.WOOD);
    }

}