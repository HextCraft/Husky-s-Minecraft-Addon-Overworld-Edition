package team.hdt.neutronia_legacy.groups.decoration.blocks;

import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaButton;

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
