package net.hdt.neutronia.init;

import net.hdt.neutronia.base.ModCreativeTab;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class NCreativeTabs {

    public static final CreativeTabs NEUTRONIA_MAIN = new ModCreativeTab(I18n.format("itemGroup.main"), new ItemStack(Blocks.BOOKSHELF));

}
