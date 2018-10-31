package net.hdt.neutronia.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNeutroniaHorizontalPillar extends BlockModHorizontal {

    public BlockNeutroniaHorizontalPillar(Material materialIn, String name, String... variants) {
        super(name, materialIn);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

}
