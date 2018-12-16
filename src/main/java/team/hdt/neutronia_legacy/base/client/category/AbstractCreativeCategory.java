package team.hdt.neutronia_legacy.base.client.category;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.List;

/**
 * Author: MrCrayfish
 */
public abstract class AbstractCreativeCategory {

    private String titleKey;
    private ItemStack icon;
    private boolean enabled = true;
    private List<Item> items = Lists.newArrayList();

    public AbstractCreativeCategory(String titleKey, ItemStack icon) {
        this.titleKey = titleKey;
        this.icon = icon;
        this.init();
    }

    public abstract void init();

    public String getTitleKey() {
        return titleKey;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    protected void add(Item item) {
        items.add(item);
    }

    protected void add(Item... items2) {
        Collections.addAll(items, items2);
    }

    protected void add(Block block) {
        items.add(Item.getItemFromBlock(block));
    }

    protected void add(Block... blocks) {
        for (Block block : blocks) {
            items.add(Item.getItemFromBlock(block));
        }
    }

    public List<Item> getItems() {
        return items;
    }

}