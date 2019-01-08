package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.abnormalib.block.BlockModSlab;
import team.abnormal.neutronia.blocks.INeutroniaBlock;

public class BlockNeutroniaSlab extends BlockModSlab implements INeutroniaBlock {

    public BlockNeutroniaSlab(String name, Material materialIn, boolean doubleSlab) {
        super(name + "_slab", materialIn, doubleSlab);
        setCreativeTab(doubleSlab ? CreativeTabs.SEARCH : CreativeTabs.BUILDING_BLOCKS);
    }

}
