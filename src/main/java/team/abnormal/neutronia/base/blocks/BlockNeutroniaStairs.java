package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNeutroniaStairs extends BlockStairs {

    public BlockNeutroniaStairs(IBlockState state) {
        super(state);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

}
