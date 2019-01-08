package team.abnormal.neutronia.blocks;

import net.minecraft.block.material.Material;
import team.abnormal.abnormalib.block.BlockFacing;

public class BlockSmithingTable extends BlockFacing implements INeutroniaBlock {

    public BlockSmithingTable() {
        super("smithing_table", Material.ROCK);
    }

}