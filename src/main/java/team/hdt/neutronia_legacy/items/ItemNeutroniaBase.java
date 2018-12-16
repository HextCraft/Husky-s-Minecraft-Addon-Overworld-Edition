package team.hdt.neutronia_legacy.items;

import team.hdt.huskylib.item.ItemMod;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.items.INeutroniaItem;

public class ItemNeutroniaBase extends ItemMod implements INeutroniaItem {

    public ItemNeutroniaBase(String name, String... variants) {
        super(name, variants);
        setCreativeTab(CreativeTabs.MISC);
    }

}
