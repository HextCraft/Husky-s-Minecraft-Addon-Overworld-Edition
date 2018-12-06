package team.hdt.neutronia.groups.craftingExtension.blocks;

import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.material.Material;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockStoneCutter extends BlockFacing implements INeutroniaBlock {

    public BlockStoneCutter() {
        super("stone_cutter", Material.WOOD);
    }

}