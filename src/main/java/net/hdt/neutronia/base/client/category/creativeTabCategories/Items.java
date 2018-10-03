package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.minecraft.item.ItemStack;

public class Items extends AbstractCreativeCategory {

    public Items() {
        super("neutronia.category.items", new ItemStack(net.minecraft.init.Items.IRON_AXE));
    }

    @Override
    public void init() {

    }

}
