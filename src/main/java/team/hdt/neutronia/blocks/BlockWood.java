package team.hdt.neutronia.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.huskylib.block.BlockMod;

public class BlockWood extends BlockMod implements INeutroniaBlock {

    public BlockWood(String name) {
        super(name, Material.WOOD);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

}