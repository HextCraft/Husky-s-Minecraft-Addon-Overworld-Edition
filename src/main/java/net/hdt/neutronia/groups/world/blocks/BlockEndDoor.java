package net.hdt.neutronia.groups.world.blocks;

import net.hdt.neutronia.base.blocks.BlockModDoor;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockEndDoor extends BlockModDoor implements INeutroniaBlock {

    public BlockEndDoor(Material materialIn, String name) {
        super(materialIn, name);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

}
