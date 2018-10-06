package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.neutronia.base.blocks.BlockNeutroniaButton;
import net.hdt.neutronia.init.NCreativeTabs;

public class BlockCustomButton extends BlockNeutroniaButton {

    public BlockCustomButton(String variant) {
        super(variant + "_button", true);
        setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
    }

    public BlockCustomButton(String variant, boolean wooden) {
        super(variant + "_button", wooden);
    }

}
