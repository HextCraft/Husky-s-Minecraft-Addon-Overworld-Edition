package net.hdt.neutronia.groups.decoration.blocks.corals;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.material.Material;

public class BlockDecorativeCoralBlockBase extends BlockMod implements INeutroniaBlock {

    public BlockDecorativeCoralBlockBase(String name) {
        super(name, Material.CORAL);
        this.setCreativeTab(NCreativeTabs.OCEAN_EXPANSION_TAB);
    }

}
