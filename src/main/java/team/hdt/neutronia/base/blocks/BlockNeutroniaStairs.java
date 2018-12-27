package team.hdt.neutronia.base.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.huskylib.block.BlockModStairs;
import team.hdt.neutronia.blocks.INeutroniaBlock;

public class BlockNeutroniaStairs extends BlockModStairs implements INeutroniaBlock {

    public BlockNeutroniaStairs(String name, IBlockState state) {
        super(name, state);
        setCreativeTabs(CreativeTabs.BUILDING_BLOCKS);
    }

}
