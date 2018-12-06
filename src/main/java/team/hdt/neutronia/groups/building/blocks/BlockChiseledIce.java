package team.hdt.neutronia.groups.building.blocks;

import net.minecraft.block.material.Material;
import team.hdt.huskylib.block.BlockFacing;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockChiseledIce extends BlockFacing implements INeutroniaBlock {

    public BlockChiseledIce() {
        super("chiseled_ice", Material.PACKED_ICE);
    }

}