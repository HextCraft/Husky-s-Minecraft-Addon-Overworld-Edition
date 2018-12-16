package team.hdt.neutronia_legacy.items.base.tools;

import team.hdt.huskylib.item.ItemModSword;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.items.INeutroniaItem;

public class BaseSword extends ItemModSword implements INeutroniaItem {

    public BaseSword(String name, ToolMaterial material) {
        super(name, material);
        setCreativeTab(CreativeTabs.COMBAT);
    }

}
