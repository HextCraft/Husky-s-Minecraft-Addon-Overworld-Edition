package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockBlastFurnace extends BlockFacing implements INeutroniaBlock {

    public BlockBlastFurnace() {
        super("blast_furnace", Material.ROCK);
    }

}