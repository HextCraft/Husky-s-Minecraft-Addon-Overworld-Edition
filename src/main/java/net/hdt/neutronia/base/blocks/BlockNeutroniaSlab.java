package net.hdt.neutronia.base.blocks;

import net.hdt.huskylib2.block.BlockModSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNeutroniaSlab extends BlockModSlab implements INeutroniaBlock {

    public BlockNeutroniaSlab(String name, Material materialIn, boolean doubleSlab) {
        super(name + "_slab", materialIn, doubleSlab);
        setCreativeTab(doubleSlab ? CreativeTabs.SEARCH : CreativeTabs.BUILDING_BLOCKS);
    }

}
