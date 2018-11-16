package net.hdt.neutronia.groups.craftingExtension.blocks;

import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLantern extends BlockFacing implements INeutroniaBlock {

    public BlockLantern() {
        super("lantern", Material.IRON);
        setSoundType(SoundType.ANVIL);
    }

}