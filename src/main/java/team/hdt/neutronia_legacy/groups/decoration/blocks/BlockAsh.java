package team.hdt.neutronia_legacy.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockModFalling;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockAsh extends BlockModFalling implements INeutroniaBlock {

    public BlockAsh() {
        super("ash");
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

}