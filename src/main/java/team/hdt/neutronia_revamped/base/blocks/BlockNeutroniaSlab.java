package team.hdt.neutronia_revamped.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.huskylib.block.BlockModSlab;
import team.hdt.neutronia_revamped.blocks.INeutroniaBlock;

public class BlockNeutroniaSlab extends BlockModSlab implements INeutroniaBlock {

    public BlockNeutroniaSlab(String name, Material materialIn, boolean doubleSlab) {
        super(name + "_slab", materialIn, doubleSlab);
        setCreativeTab(doubleSlab ? CreativeTabs.SEARCH : CreativeTabs.BUILDING_BLOCKS);
    }

}
