package team.hdt.neutronia_legacy.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import team.hdt.neutronia_legacy.blocks.base.BlockModColoredSlab;

public class BlockNeutroniaColoredSlab extends BlockModColoredSlab implements INeutroniaBlock {

    public BlockNeutroniaColoredSlab(String name, EnumDyeColor color, Material materialIn, boolean doubleSlab) {
        super(name + "_slab", color, materialIn, doubleSlab);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

}
