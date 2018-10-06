package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.hdt.neutronia.groups.decoration.features.DecorativeAquamarine;
import net.hdt.neutronia.groups.decoration.features.DecorativeCorals;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Ocean extends AbstractCreativeCategory {

    public Ocean() {
        super("neutronia.category.ocean", new ItemStack(Blocks.PRISMARINE));
    }

    @Override
    public void init() {
        add(DecorativeAquamarine.decorativeAquamarine);
        add(DecorativeCorals.decorativeCoralBlock);
        add(DecorativeCorals.decorativeCoral);
        add(DecorativeCorals.decorativeDeadCoralBlock);
        add(DecorativeCorals.decorativeDeadCoral);
        add(DecorativeCorals.decorativeCoralFan);
        add(DecorativeCorals.decorativeDeadCoralFan);
    }

}
