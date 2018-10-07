package net.hdt.neutronia.base.client.category;

import com.google.common.collect.Lists;
import net.hdt.neutronia.base.groups.Group;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Author: MrCrayfish
 */
public abstract class AbstractConfigGroupCategory {

    private String titleKey;
    private ItemStack icon;
    private boolean enabled = true;
    private List<Group> groups = Lists.newArrayList();

    public AbstractConfigGroupCategory(String titleKey, ItemStack icon) {
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

    protected void add(Group group) {
        groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }

}