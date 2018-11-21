package net.hdt.neutronia.groups.building.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockChiseledIce extends BlockFacing implements INeutroniaBlock {

    public BlockChiseledIce() {
        super("chiseled_ice", Material.PACKED_ICE);
    }

}