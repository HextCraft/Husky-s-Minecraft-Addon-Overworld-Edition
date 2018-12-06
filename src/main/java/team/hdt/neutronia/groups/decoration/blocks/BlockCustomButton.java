package team.hdt.neutronia.groups.decoration.blocks;

import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.blocks.BlockNeutroniaButton;

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
