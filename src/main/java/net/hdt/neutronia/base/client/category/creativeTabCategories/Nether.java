package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Nether extends AbstractCreativeCategory {

    public Nether() {
        super("neutronia.category.nether", new ItemStack(Blocks.NETHERRACK));
    }

    @Override
    public void init() {

    }

}
