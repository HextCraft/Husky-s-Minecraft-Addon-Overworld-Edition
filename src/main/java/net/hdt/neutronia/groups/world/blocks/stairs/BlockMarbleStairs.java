package net.hdt.neutronia.groups.world.blocks.stairs;

import net.hdt.neutronia.base.blocks.BlockNeutroniaStairs;
import net.hdt.neutronia.groups.world.features.overworld.BetterStoneGeneration;

public class BlockMarbleStairs extends BlockNeutroniaStairs {

    public BlockMarbleStairs() {
        super("stone_marble_stairs", BetterStoneGeneration.marble.getDefaultState());
    }

}
