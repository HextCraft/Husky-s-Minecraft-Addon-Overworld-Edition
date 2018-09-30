package net.hdt.neutronia.base.blocks;

import net.hdt.neutronia.blocks.base.BlockModColoredSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;

public class BlockNeutroniaColoredSlab extends BlockModColoredSlab implements INeutroniaBlock {

    public BlockNeutroniaColoredSlab(String name, EnumDyeColor color, Material materialIn, boolean doubleSlab) {
        super(name + "_slab", color, materialIn, doubleSlab);
    }

}
