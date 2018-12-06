package team.hdt.neutronia.groups.musicRevamp.blocks;

import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.material.Material;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockComposer extends BlockFacing implements INeutroniaBlock {

    public BlockComposer() {
        super("composer", Material.WOOD);
    }

}