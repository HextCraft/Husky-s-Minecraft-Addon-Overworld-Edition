package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Ocean extends AbstractCreativeCategory {

    public Ocean() {
        super("neutronia.category.ocean", new ItemStack(Blocks.PRISMARINE));
    }

    @Override
    public void init() {

    }

}
