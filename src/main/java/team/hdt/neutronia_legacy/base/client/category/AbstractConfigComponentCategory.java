package team.hdt.neutronia_legacy.base.client.category;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.groups.Group;

import java.util.List;

/**
 * Author: MrCrayfish
 */
public abstract class AbstractConfigComponentCategory {

    private String titleKey;
    private ItemStack icon;
    private boolean enabled = true;
    private List<Component> components = Lists.newArrayList();

    public AbstractConfigComponentCategory(String titleKey, ItemStack icon) {
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

    protected void add(Component component) {
        components.add(component);
    }

    protected void add(Group group) {
        components.addAll(group.components.values());
    }

    public List<Component> getComponents() {
        return components;
    }

}