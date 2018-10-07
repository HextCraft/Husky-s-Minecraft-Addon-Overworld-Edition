package net.hdt.neutronia.groups.world.blocks.corals;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.material.Material;

public class BlockDeadCoralBlock extends BlockNeutroniaBase {

    public BlockDeadCoralBlock(EnumCoralColor colorIn) {
        super(Material.GRASS, "dead_" + colorIn.getName() + "_coral_block", false);
    }

}