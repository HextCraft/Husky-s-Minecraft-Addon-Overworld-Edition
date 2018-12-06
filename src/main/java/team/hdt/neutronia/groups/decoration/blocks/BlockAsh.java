package team.hdt.neutronia.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockModFalling;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockAsh extends BlockModFalling implements INeutroniaBlock {

    public BlockAsh() {
        super("ash");
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

}