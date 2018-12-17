package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.material.Material;
import team.hdt.huskylib.block.BlockFacing;

public class BlockChiseledIce extends BlockFacing implements INeutroniaBlock {

    public BlockChiseledIce() {
        super("chiseled_ice", Material.PACKED_ICE);
    }

}