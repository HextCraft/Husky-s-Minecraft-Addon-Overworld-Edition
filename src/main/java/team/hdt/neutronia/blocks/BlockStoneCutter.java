package team.hdt.neutronia.blocks;

import net.minecraft.block.material.Material;
import team.hdt.huskylib.block.BlockFacing;

public class BlockStoneCutter extends BlockFacing implements INeutroniaBlock {

    public BlockStoneCutter() {
        super("stone_cutter", Material.WOOD);
    }

}