package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.huskylib2.block.BlockModFalling;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAsh extends BlockModFalling implements INeutroniaBlock {

    public BlockAsh() {
        super("ash");
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

}