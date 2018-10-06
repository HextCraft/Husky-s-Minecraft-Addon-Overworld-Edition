package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.hdt.neutronia.groups.world.features.end.Acidian;
import net.hdt.neutronia.groups.world.features.end.PurhoganyWood;
import net.hdt.neutronia.groups.world.features.overworld.Stalactite;
import net.hdt.neutronia.groups.world.features.overworld.Stalagmite;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class End extends AbstractCreativeCategory {

    public End() {
        super("neutronia.category.end", new ItemStack(Blocks.END_STONE));
    }

    @Override
    public void init() {
        add(Acidian.acidianBricks);
        add(Acidian.chiseledAcidian);
        add(Acidian.naturalAcidian);
        add(Acidian.acidianPillar);
        add(Acidian.acidianBars);
        add(PurhoganyWood.purhoganyLog);
        add(PurhoganyWood.purhoganyPlanks);
        add(PurhoganyWood.purhoganyDoor);
        add(PurhoganyWood.purhoganyTrapdoor);
        add(Block.getBlockFromName("neutronia:purhogany_planks_stairs"));
        add(Block.getBlockFromName("neutronia:purhogany_log_stairs"));
        add(Block.getBlockFromName("neutronia:purhogany_planks_slab"));
        add(Block.getBlockFromName("neutronia:purhogany_log_slab"));
        add(Stalagmite.end_stalagmite);
        add(Stalactite.end_stalactite);
    }

}
