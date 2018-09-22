package net.hdt.neutronia.base.groups;

import net.hdt.neutronia.api.modules.IStateHandler;
import net.hdt.neutronia.api.modules.config.ConfigProperty;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;

public abstract class Component implements IStateHandler {

    public boolean enabled, recipeCondition;
    protected static Group parent;
    private String name, category;

    public Component recipes() {
        recipeCondition = true;
        GroupLoader.JSON_CONDITIONS.put(getName(), recipeCondition);
        return this;
    }

    public void setup() {
        this.name = getClass().getSimpleName().toLowerCase();
        this.category = ConfigHelper.joinCategory(parent.getName(), getName());
        this.enabled = canEnable();
    }

    public void setupConfig() {

    }

    public static Group getParent() {
        return parent;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public static ConfigHelper config() {
        return parent.config();
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return name;
    }

    public <T> ConfigProperty<T> loadProperty(String property, T defaultValue) {
        return config().load(this.category, property, defaultValue);
    }

    public abstract String getDescription();

    public String[] getIncompatibleMods() {
        return new String[0];
    }


    protected boolean isEnabledByDefault() {
        return true;
    }

    public boolean hasSubscriptions() {
        return false;
    }

    public boolean hasTerrainSubscriptions() {
        return false;
    }

    public boolean hasOreGenSubscriptions() {
        return false;
    }

    public boolean requiresMinecraftRestartToEnable() {
        return hasSubscriptions() || hasOreGenSubscriptions() || hasTerrainSubscriptions();
    }

    public final boolean isClient() {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    /**
     * @return boolean that dictates whether a feature will be enabled on load. Default implementation is based on a config entry
     */
    protected boolean canEnable() {
        for (String modid : getIncompatibleMods()) {
            if (Loader.isModLoaded(modid)) {
                parent.getLogger().info("Feature: {} was not loaded due to an incompatible mod being installed - {}", getName(), modid);
                return false;
            }
        }
        return config().load(this.category, "Enabled", isEnabledByDefault()).setCategoryComment(getDescription()).get();
    }


    @Override
    public String getType() {
        return "Feature";
    }


}