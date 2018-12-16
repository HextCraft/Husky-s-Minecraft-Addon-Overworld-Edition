package team.hdt.neutronia_legacy.groups.building.blocks;

import net.minecraft.block.material.Material;
import team.hdt.huskylib.block.BlockFacing;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockChiseledIce extends BlockFacing implements INeutroniaBlock {

    public BlockChiseledIce() {
        super("chiseled_ice", Material.PACKED_ICE);
    }

}