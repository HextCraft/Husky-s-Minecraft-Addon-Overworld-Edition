package net.hdt.neutronia.base.blocks;

import net.hdt.huskylib2.block.BlockModSlab;
import net.hdt.neutronia.base.Neutronia;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNeutroniaSlab extends BlockModSlab implements INeutroniaBlock {

    public BlockNeutroniaSlab(String name, Material materialIn, boolean doubleSlab) {
        super(name + "_slab", materialIn, doubleSlab);
        setCreativeTab(doubleSlab ? CreativeTabs.SEARCH : Neutronia.CREATIVE_TAB);
    }

    @Override
    public String getTranslationKey(int meta) {
        return getTranslationKey();
    }

}
