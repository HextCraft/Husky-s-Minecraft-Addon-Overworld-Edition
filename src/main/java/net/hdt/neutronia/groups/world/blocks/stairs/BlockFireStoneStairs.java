package net.hdt.neutronia.groups.world.blocks.stairs;

import net.hdt.neutronia.base.blocks.BlockNeutroniaStairs;
import net.hdt.neutronia.groups.world.features.overworld.CaveBiomes;

public class BlockFireStoneStairs extends BlockNeutroniaStairs {

    public BlockFireStoneStairs() {
        super("fire_stone_stairs", CaveBiomes.biome_cobblestone.getStateFromMeta(0));
    }

}