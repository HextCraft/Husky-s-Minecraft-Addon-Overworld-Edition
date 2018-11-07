package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockLoom extends BlockMod implements INeutroniaBlock {
    public BlockLoom(String name, Material materialIn, String... variants) {
        super(name, materialIn, variants);
    }
}
