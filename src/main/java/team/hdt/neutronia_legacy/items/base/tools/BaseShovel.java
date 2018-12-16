package team.hdt.neutronia_legacy.items.base.tools;

import team.hdt.huskylib.item.ItemModSpade;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.items.INeutroniaItem;

public class BaseShovel extends ItemModSpade implements INeutroniaItem {

    public BaseShovel(String name, ToolMaterial material) {
        super(material, name);
        setCreativeTab(CreativeTabs.TOOLS);
    }

}
