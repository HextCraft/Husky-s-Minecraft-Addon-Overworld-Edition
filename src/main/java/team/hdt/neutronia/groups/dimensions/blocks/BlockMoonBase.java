package team.hdt.neutronia.groups.dimensions.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockMoonBase extends BlockMod implements INeutroniaBlock {

    public BlockMoonBase(String name, Material materialIn, CreativeTabs creativeTabs) {
        super(name, materialIn);
        setCreativeTab(creativeTabs);
    }

}
