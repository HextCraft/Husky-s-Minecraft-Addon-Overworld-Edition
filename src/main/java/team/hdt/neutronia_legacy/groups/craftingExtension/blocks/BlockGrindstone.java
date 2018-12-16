package team.hdt.neutronia_legacy.groups.craftingExtension.blocks;

import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.material.Material;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockGrindstone extends BlockFacing implements INeutroniaBlock {

    public BlockGrindstone() {
        super("grindstone", Material.ROCK);
    }

}