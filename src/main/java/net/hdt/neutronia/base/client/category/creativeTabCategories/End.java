package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class End extends AbstractCreativeCategory {

    public End() {
        super("neutronia.category.end", new ItemStack(Blocks.END_STONE));
    }

    @Override
    public void init() {

    }

}
