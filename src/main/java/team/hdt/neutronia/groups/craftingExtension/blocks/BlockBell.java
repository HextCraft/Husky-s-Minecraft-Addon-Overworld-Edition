package team.hdt.neutronia.groups.craftingExtension.blocks;

import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockBell extends BlockFacing implements INeutroniaBlock {

    public BlockBell() {
        super("bell", Material.IRON);
        setSoundType(SoundType.ANVIL);
    }

}