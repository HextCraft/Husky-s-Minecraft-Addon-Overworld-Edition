package net.hdt.neutronia.groups.building.blocks.stair;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.BlockNeutroniaStairs;
import net.minecraft.block.state.IBlockState;

public class BlockVanillaStairs extends BlockNeutroniaStairs {

    public BlockVanillaStairs(String name, IBlockState state) {
        super(name, state);
        setCreativeTab(Neutronia.CREATIVE_TAB);
    }

}
