package team.hdt.neutronia_legacy.groups.world.blocks.corals;

import net.minecraft.block.material.Material;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_legacy.properties.EnumCoralColor;

public class BlockDeadCoralBlock extends BlockNeutroniaBase {

    public BlockDeadCoralBlock(EnumCoralColor colorIn) {
        super(Material.GRASS, "dead_" + colorIn.getName() + "_coral_block", false);
    }

}