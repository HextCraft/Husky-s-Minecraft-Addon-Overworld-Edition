package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockGrindstone extends BlockFacing implements INeutroniaBlock {

    public BlockGrindstone() {
        super("grindstone", Material.ROCK);
    }

}