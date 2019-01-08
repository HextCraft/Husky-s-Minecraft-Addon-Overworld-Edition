package team.abnormal.neutronia.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import team.abnormal.abnormalib.block.BlockFacing;

public class BlockLoom extends BlockFacing implements INeutroniaBlock {

    public BlockLoom(BlockPlanks.EnumType woodType) {
        super(String.format("%s_loom", woodType.getName()), Material.WOOD);
    }

}