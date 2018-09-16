package net.hdt.neutronia.groups.world.blocks.corals;

import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.material.Material;

public class BlockDeadCoralBlock extends BlockOverworldBase {

    public BlockDeadCoralBlock(EnumCoralColor colorIn) {
        super(Material.GRASS, colorIn.getName() + "_coral_block", false);
    }

}
