package team.hdt.neutronia.base.modules;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.base.Reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Module implements Comparable<Module> {

    public final Map<String, Feature> components = new HashMap<>();
    private final List<Feature> enabledFeatures = new ArrayList<>();
    public String name, desc;
    public boolean enabled, enabledByDefault;
    public Property prop;
    private ItemStack iconStack;

    public Module(Builder builder) {
        this.name = builder.name;
        this.desc = builder.desc;
        this.enabled = builder.enabled;
        this.enabledByDefault = builder.enabledByDefault;
        for (Feature component : builder.components) {
            registerFeature(component, component.stateManager.enabledByDefault);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public void registerFeature(Feature component, boolean enabledByDefault) {
        registerFeature(component, convertName(component.getClass().getSimpleName()), enabledByDefault);
    }

    private String convertName(String origName) {
        String withSpaces = origName.replaceAll("(?<=.)([A-Z])", " $1").toLowerCase();
        return Character.toUpperCase(withSpaces.charAt(0)) + withSpaces.substring(1);
    }

    private void registerFeature(Feature component, String name, boolean enabledByDefault) {
        Class<? extends Feature> clazz = component.getClass();
        if (ModuleLoader.componentInstances.containsKey(clazz))
            throw new IllegalArgumentException("Feature " + clazz + " is already registered!");

        ModuleLoader.componentInstances.put(clazz, component);
        components.put(name, component);

        component.stateManager.enabledByDefault = enabledByDefault;
        component.prevEnabled = false;

        component.module = this;
        component.configName = name;
        component.configCategory = this.name + "." + name;
    }

    public void setupConfig() {
        forEachFeature(component -> {
            ConfigHelper.needsRestart = component.requiresMinecraftRestartToEnable();
            component.stateManager.enabled = loadPropBool(component.configName, component.getFeatureDescription(), component.stateManager.enabledByDefault) && enabled;
            component.prop = ConfigHelper.lastProp;

            component.setupConstantConfig();

            if (!component.forceLoad && GlobalConfig.enableAntiOverlap) {
                String[] incompatibilities = component.getIncompatibleMods();
                if (incompatibilities != null) {
                    List<String> failiures = new ArrayList<>();

                    for (String s : incompatibilities)
                        if (Loader.isModLoaded(s)) {
                            component.stateManager.enabled = false;
                            failiures.add(s);
                        }

                    if (!failiures.isEmpty())
                        Reference.LOGGER.info("'" + component.configName + "' is forcefully disabled as it's incompatible with the following loaded mods: " + failiures);
                }
            }

            if (!component.loadtimeDone) {
                component.enabledAtLoadtime = component.stateManager.enabled;
                component.loadtimeDone = true;
            }

            if (component.stateManager.enabled && !enabledFeatures.contains(component))
                enabledFeatures.add(component);
            else if (!component.stateManager.enabled)
                enabledFeatures.remove(component);

            component.setupConfig();

            if (!component.stateManager.enabled && component.prevEnabled) {
                if (component.hasSubscriptions())
                    MinecraftForge.EVENT_BUS.unregister(component);
                if (component.hasTerrainSubscriptions())
                    MinecraftForge.TERRAIN_GEN_BUS.unregister(component);
                if (component.hasOreGenSubscriptions())
                    MinecraftForge.ORE_GEN_BUS.unregister(component);
                component.onDisabled();
            } else if (component.stateManager.enabled && (component.enabledAtLoadtime || !component.requiresMinecraftRestartToEnable()) && !component.prevEnabled) {
                if (component.hasSubscriptions())
                    MinecraftForge.EVENT_BUS.register(component);
                if (component.hasTerrainSubscriptions())
                    MinecraftForge.TERRAIN_GEN_BUS.register(component);
                if (component.hasOreGenSubscriptions())
                    MinecraftForge.ORE_GEN_BUS.register(component);
                component.onEnabled();
            }

            component.prevEnabled = component.stateManager.enabled;
        });
    }

    public void preInit(FMLPreInitializationEvent event) {
        forEachEnabledFeature(component -> component.preInit(event));
    }

    void postPreInit(FMLPreInitializationEvent event) {
        forEachEnabledFeature(component -> component.postPreInit(event));
    }

    public void init(FMLInitializationEvent event) {
        forEachEnabledFeature(component -> component.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        forEachEnabledFeature(component -> component.postInit(event));
    }

    void finalInit(FMLPostInitializationEvent event) {
        forEachEnabledFeature(component -> component.finalInit(event));
    }

    @SideOnly(Side.CLIENT)
    void preInitClient(FMLPreInitializationEvent event) {
        forEachEnabledFeature(component -> component.preInitClient(event));
    }

    @SideOnly(Side.CLIENT)
    void initClient(FMLInitializationEvent event) {
        forEachEnabledFeature(component -> component.initClient(event));
    }

    @SideOnly(Side.CLIENT)
    void postInitClient(FMLPostInitializationEvent event) {
        forEachEnabledFeature(component -> component.postInitClient(event));
    }

    void serverStarting(FMLServerStartingEvent event) {
        forEachEnabledFeature(component -> component.serverStarting(event));
    }

    boolean canBeDisabled() {
        return true;
    }

    boolean isEnabledByDefault() {
        return enabledByDefault;
    }

    String getModuleDescription() {
        return desc;
    }

    public ItemStack getIconStack() {
        if (iconStack != null) {
            return iconStack;
        } else {
            return new ItemStack(Blocks.BARRIER);
        }
    }

    public void forEachFeature(Consumer<Feature> consumer) {
        components.values().forEach(consumer);
    }

    private void forEachEnabledFeature(Consumer<Feature> consumer) {
        enabledFeatures.forEach(consumer);
    }

    public final int loadPropInt(String propName, String desc, int default_) {
        return ConfigHelper.loadPropInt(propName, name, desc, default_);
    }

    public final double loadPropDouble(String propName, String desc, double default_) {
        return ConfigHelper.loadPropDouble(propName, name, desc, default_);
    }

    private boolean loadPropBool(String propName, String desc, boolean default_) {
        return ConfigHelper.loadPropBool(propName, name, desc, default_);
    }

    public final String loadPropString(String propName, String desc, String default_) {
        return ConfigHelper.loadPropString(propName, name, desc, default_);
    }

    @Override
    public int compareTo(Module module) {
        return name.compareTo(module.name);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {

        private String name, desc;
        private ItemStack icon;
        private Module module;
        private boolean enabled, enabledByDefault;
        private List<Feature> components = new ArrayList<>();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder addFeature(Feature component) {
            components.add(component);
            return this;
        }

        public Builder addFeature(Feature component, boolean enabled) {
            component.stateManager.enabled = enabled;
            components.add(component);
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder enabledByDefault(boolean enabledByDefault) {
            this.enabledByDefault = enabledByDefault;
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

        public Module register() {
            module = new Module(this);
            module.iconStack = icon;
            module.enabled = enabled;
            module.enabledByDefault = enabledByDefault;
            ModuleLoader.registerModule(module);
            return module;
        }

    }
    
}
