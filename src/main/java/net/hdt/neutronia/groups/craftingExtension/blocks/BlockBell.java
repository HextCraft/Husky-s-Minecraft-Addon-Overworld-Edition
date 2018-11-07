package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockBell extends BlockMod implements INeutroniaBlock {
    public BlockBell(String name, Material materialIn, String... variants) {
        super(name, materialIn, variants);
    }
}
