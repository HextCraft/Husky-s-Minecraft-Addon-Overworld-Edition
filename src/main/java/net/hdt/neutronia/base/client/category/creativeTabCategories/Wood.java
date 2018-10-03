package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.hdt.neutronia.groups.building.features.LogBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Wood extends AbstractCreativeCategory {

    public Wood() {
        super("neutronia.category.wood", new ItemStack(Blocks.LOG));
    }

    @Override
    public void init() {
        add(LogBlocks.strippedLogPoles);
        add(LogBlocks.logPoles);
        add(LogBlocks.strippedLogs);
        add(LogBlocks.plankPoles);
//        add(LogBlocks.logDowels);
//        add(LogBlocks.strippedLogDowels);
    }

}
