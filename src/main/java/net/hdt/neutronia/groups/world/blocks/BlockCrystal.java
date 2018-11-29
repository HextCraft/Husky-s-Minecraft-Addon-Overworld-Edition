package net.hdt.neutronia.groups.world.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;

public class BlockCrystal extends BlockMod implements INeutroniaBlock {

    public BlockCrystal(Material material, String name, String... variants) {
        super(name, material, variants);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

}
