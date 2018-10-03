package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class General extends AbstractCreativeCategory {

    public General() {
        super("neutronia.category.general", new ItemStack(Blocks.BRICK_BLOCK));
    }

    @Override
    public void init() {

    }

}
