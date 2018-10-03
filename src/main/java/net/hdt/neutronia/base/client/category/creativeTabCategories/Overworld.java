package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Overworld extends AbstractCreativeCategory {

    public Overworld() {
        super("neutronia.category.overworld", new ItemStack(Blocks.GRASS));
    }

    @Override
    public void init() {

    }

}
