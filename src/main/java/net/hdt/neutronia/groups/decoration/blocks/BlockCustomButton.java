package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.BlockNeutroniaButton;

public class BlockCustomButton extends BlockNeutroniaButton {

    public BlockCustomButton(String variant) {
        super(variant + "_button", true);
        setCreativeTab(Neutronia.NEUTRONIA_MAIN);
    }

    public BlockCustomButton(String variant, boolean wooden) {
        super(variant + "_button", wooden);
    }

}
