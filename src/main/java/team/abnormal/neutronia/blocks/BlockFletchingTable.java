package team.abnormal.neutronia.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import team.abnormal.abnormalib.block.BlockFacing;

public class BlockFletchingTable extends BlockFacing implements INeutroniaBlock {

    public BlockFletchingTable(BlockPlanks.EnumType woodType) {
        super(String.format("%s_fletching_table", woodType.getName()), Material.WOOD);
    }

}