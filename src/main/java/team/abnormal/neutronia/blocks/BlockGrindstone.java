package team.abnormal.neutronia.blocks;

import net.minecraft.block.material.Material;
import team.abnormal.abnormalib.block.BlockFacing;

public class BlockGrindstone extends BlockFacing implements INeutroniaBlock {

    public BlockGrindstone() {
        super("grindstone", Material.ROCK);
    }

}