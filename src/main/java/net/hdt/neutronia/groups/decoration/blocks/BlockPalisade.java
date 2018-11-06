package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.neutronia.base.blocks.BlockNeutroniaFence;
import net.minecraft.block.state.IBlockState;

public class BlockPalisade extends BlockNeutroniaFence {

    public BlockPalisade(String name, IBlockState state) {
        super(name, state);
        setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
    }

}