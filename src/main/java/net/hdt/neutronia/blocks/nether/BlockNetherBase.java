package net.hdt.neutronia.blocks.nether;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.material.Material;

public class BlockNetherBase extends BlockMod implements INeutroniaBlock {

    public BlockNetherBase(Material material, String name) {
        super(name, material);
        setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
    }

}
