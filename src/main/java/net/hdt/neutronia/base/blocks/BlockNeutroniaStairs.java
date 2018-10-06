package net.hdt.neutronia.base.blocks;

import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.state.IBlockState;

public class BlockNeutroniaStairs extends BlockModStairs implements INeutroniaBlock {

    public BlockNeutroniaStairs(String name, IBlockState state) {
        super(name, state);
        setCreativeTabs(NCreativeTabs.NEUTRONIA_MAIN);
    }

}
