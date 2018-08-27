package net.hdt.neutronia.groups.dimensions.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMoonBase extends BlockMod implements INeutroniaBlock {

    public BlockMoonBase(String name, Material materialIn, CreativeTabs creativeTabs) {
        super(name, materialIn);
        setCreativeTab(creativeTabs);
    }

}
