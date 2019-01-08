package team.abnormal.neutronia.items;

import net.minecraft.creativetab.CreativeTabs;
import team.abnormal.abnormalib.item.ItemMod;

public class ItemNeutroniaBase extends ItemMod implements INeutroniaItem {

    public ItemNeutroniaBase(String name, String... variants) {
        super(name, variants);
        setCreativeTab(CreativeTabs.MISC);
    }

}
