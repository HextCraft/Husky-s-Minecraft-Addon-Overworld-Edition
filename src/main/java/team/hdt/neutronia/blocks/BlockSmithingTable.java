package team.hdt.neutronia.blocks;

import net.minecraft.block.material.Material;
import team.hdt.huskylib.block.BlockFacing;

public class BlockSmithingTable extends BlockFacing implements INeutroniaBlock {

    public BlockSmithingTable() {
        super("smithing_table", Material.ROCK);
    }

}