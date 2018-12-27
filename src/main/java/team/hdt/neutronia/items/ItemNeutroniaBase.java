package team.hdt.neutronia.items;

import net.minecraft.creativetab.CreativeTabs;
import team.hdt.huskylib.item.ItemMod;

public class ItemNeutroniaBase extends ItemMod implements INeutroniaItem {

    public ItemNeutroniaBase(String name, String... variants) {
        super(name, variants);
        setCreativeTab(CreativeTabs.MISC);
    }

}
