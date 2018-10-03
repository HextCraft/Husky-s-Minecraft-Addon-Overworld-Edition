package net.hdt.neutronia.blocks.end;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.material.Material;

public class BlockEndBase extends BlockMod implements INeutroniaBlock {

    public BlockEndBase(Material material, String name) {
        super(name, material);
        setCreativeTabs(NCreativeTabs.NEUTRONIA_MAIN);
        setHardness(3.0F);
        setResistance(15.0F);
    }

}
