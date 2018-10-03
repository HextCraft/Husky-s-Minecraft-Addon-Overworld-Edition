package net.hdt.neutronia.base;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ModCreativeTab extends CreativeTabs {

    private boolean displayRandom;
    private int tempIndex = 0;
    private ItemStack tempDisplayStack = ItemStack.EMPTY;
    private ItemStack icon = getIcon();
    private String label;

    public ModCreativeTab(String label, boolean displayRandom) {
        super(label);
        this.label = label;
        this.displayRandom = displayRandom;
    }

    public ModCreativeTab(String label, ItemStack stack) {
        super(label);
        this.label = label;
        this.icon = stack;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public ItemStack getIcon() {
        if (this.displayRandom) {
            if (Minecraft.getSystemTime() % 120 == 0) {
                this.updateDisplayStack();
            }
            return this.tempDisplayStack;
        } else return this.icon;
    }

    @Override
    public String getTranslationKey() {
        return getTabLabel();
    }

    private void updateDisplayStack() {
        if (this.displayRandom) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();
            this.displayAllRelevantItems(itemStacks);
            this.tempDisplayStack = !itemStacks.isEmpty() ? itemStacks.get(tempIndex) : ItemStack.EMPTY;
            if (++tempIndex >= itemStacks.size()) tempIndex = 0;
        } else {
            if (this.icon.isEmpty()) {
                this.tempDisplayStack = new ItemStack(Items.DIAMOND);
            }
            this.tempDisplayStack = this.icon;
        }
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    @Override
    public ItemStack createIcon() {
        return this.getIcon();
    }

}
