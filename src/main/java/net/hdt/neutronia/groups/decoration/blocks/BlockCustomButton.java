package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.neutronia.base.blocks.BlockNeutroniaButton;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCustomButton extends BlockNeutroniaButton {

    public BlockCustomButton(String variant) {
        super(variant + "_button", true);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

    public BlockCustomButton(String variant, boolean wooden) {
        super(variant + "_button", wooden);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

}
