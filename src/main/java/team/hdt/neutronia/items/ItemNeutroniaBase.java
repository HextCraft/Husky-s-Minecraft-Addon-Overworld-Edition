package team.hdt.neutronia.items;

import team.hdt.huskylib.item.ItemMod;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia.base.items.INeutroniaItem;

public class ItemNeutroniaBase extends ItemMod implements INeutroniaItem {

    public ItemNeutroniaBase(String name, String... variants) {
        super(name, variants);
        setCreativeTab(CreativeTabs.MISC);
    }

}
