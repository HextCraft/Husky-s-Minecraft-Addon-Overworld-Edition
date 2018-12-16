package team.hdt.neutronia_legacy.groups.craftingExtension.blocks;

import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockFletchingTable extends BlockFacing implements INeutroniaBlock {

    public BlockFletchingTable(BlockPlanks.EnumType woodType) {
        super(String.format("%s_fletching_table", woodType.getName()), Material.WOOD);
    }

}