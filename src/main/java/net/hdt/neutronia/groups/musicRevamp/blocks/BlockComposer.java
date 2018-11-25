package net.hdt.neutronia.groups.musicRevamp.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockComposer extends BlockFacing implements INeutroniaBlock {

    public BlockComposer() {
        super("composer", Material.WOOD);
    }

}