package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.neutronia.blocks.INeutroniaBlock;
import team.abnormal.abnormalib.block.BlockModStairs;

public class BlockNeutroniaStairs extends BlockModStairs implements INeutroniaBlock {

    public BlockNeutroniaStairs(String name, IBlockState state) {
        super(name, state);
        setCreativeTabs(CreativeTabs.BUILDING_BLOCKS);
    }

}
