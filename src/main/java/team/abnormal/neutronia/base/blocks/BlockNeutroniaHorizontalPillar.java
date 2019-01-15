package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.neutronia.blocks.INeutroniaBlock;

public class BlockNeutroniaHorizontalPillar extends BlockModHorizontal implements INeutroniaBlock {

    public BlockNeutroniaHorizontalPillar(Material materialIn, String name, String... variants) {
        super(name, materialIn);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

}
