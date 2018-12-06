package team.hdt.neutronia.groups.world.blocks.corals;

import net.minecraft.block.material.Material;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.properties.EnumCoralColor;

public class BlockDeadCoralBlock extends BlockNeutroniaBase {

    public BlockDeadCoralBlock(EnumCoralColor colorIn) {
        super(Material.GRASS, "dead_" + colorIn.getName() + "_coral_block", false);
    }

}