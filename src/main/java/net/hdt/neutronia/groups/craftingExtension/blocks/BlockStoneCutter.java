package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;

public class BlockStoneCutter extends BlockMod implements INeutroniaBlock {
    public BlockStoneCutter(String name, Material materialIn, String... variants) {
        super(name, materialIn, variants);
    }
}
