package team.hdt.neutronia.blocks;

import net.minecraft.block.material.Material;
import team.hdt.huskylib.block.BlockFacing;

public class BlockGrindstone extends BlockFacing implements INeutroniaBlock {

    public BlockGrindstone() {
        super("grindstone", Material.ROCK);
    }

}