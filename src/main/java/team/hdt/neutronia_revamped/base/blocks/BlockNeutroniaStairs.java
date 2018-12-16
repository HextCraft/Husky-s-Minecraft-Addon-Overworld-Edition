package team.hdt.neutronia_legacy.base.blocks;

import team.hdt.huskylib.block.BlockModStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNeutroniaStairs extends BlockModStairs implements INeutroniaBlock {

    public BlockNeutroniaStairs(String name, IBlockState state) {
        super(name, state);
        setCreativeTabs(CreativeTabs.BUILDING_BLOCKS);
    }

}
