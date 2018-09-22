package net.hdt.neutronia.base.groups;

import net.hdt.neutronia.api.modules.impl.ListStateHandler;
import net.hdt.neutronia.base.Neutronia;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Group extends ListStateHandler<Component> {

    public final Map<String, Component> components = new HashMap<>();
    final List<Component> enabledComponents = new ArrayList<>();
    public String name, desc;
    public boolean enabled;
    public Property prop;
    private ItemStack iconStack;
    private ConfigHelper config;
    private Logger logger;

    public Group() {
        name = getClass().getSimpleName().replaceAll("Neutronia", "").toLowerCase();
        desc = "This is a missing description text since this component does not have a description defined";
    }

    public Group(Builder builder) {
        name = builder.name;
        desc = builder.desc;
        enabled = builder.enabled;
        iconStack = builder.icon;
        for (Component component : builder.components) {
            addFeature(component);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public ItemStack getIconStack() {
        if (iconStack != null) {
            return iconStack;
        } else {
            return new ItemStack(Blocks.BARRIER);
        }
    }

    private void setIconStack(ItemStack stack) {
        this.iconStack = stack;
    }

    public void addFeatures() {
    }

    /**
     * @param helper supplies a {@link ConfigHelper} for creating configurable code ,
     * @param logger Mod log instance
     * @return list of {@link Component}s that are enabled, used for {@link GroupLoader#isComponentEnabled(Class)}
     */
    public List<Component> setup(ConfigHelper helper, Logger logger) {
        this.setLogger(logger);
        this.setConfig(helper);
        this.addFeatures();
        this.enabled = canEnable();
        if (isEnabled()) {
            forEach(Component::setup);
        }
        config.save();
        return stream().filter(Component::isEnabled).collect(Collectors.toList());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        config.save();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        config.save();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        config.save();
    }

    protected void addFeatures(Component... features) {
        for (Component feature : features)
            addFeature(feature);
    }

    protected void addFeature(Component feature) {
        feature.parent = this;
        this.add(feature);
    }

    protected void addFeature(Class<? extends Component> clazz, String... dependencies) {
        config().setCategoryComment(ConfigHelper.joinCategory(getName(), clazz.getSimpleName()), "Requires:" + String.join(",", dependencies));
        if (Arrays.stream(dependencies).allMatch(Loader::isModLoaded)) {
            try {
                this.addFeature(clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public void setConfig(ConfigHelper config) {
        this.config = config;
    }

    public ConfigHelper config() {
        return config;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    protected boolean canEnable() {
        return config().load(getName(), "Enabled", isEnabledByDefault()).setComment("Enable this module").get();
    }

    protected boolean isEnabledByDefault() {
        return true;
    }

    @Override
    public String getType() {
        return "Group";
    }

    public static class Builder {

        private String name, desc;
        private ItemStack icon;
        private Group group;
        private boolean enabled;
        private List<Component> components = new ArrayList<>();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder addComponent(Component component) {
            components.add(component);
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder iconStack(ItemStack icon) {
            if (!icon.isEmpty()) {
                this.icon = icon;
            } else {
                this.icon = new ItemStack(Blocks.AIR);
            }
            return this;
        }

        public Group register() {
            group = new Group(this);
            group.setIconStack(icon);
            Neutronia.MODULE_LOADER.addModule(group);
            return group;
        }

    }

}
