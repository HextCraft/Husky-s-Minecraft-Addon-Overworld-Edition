package team.hdt.neutronia.groups.world.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

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
