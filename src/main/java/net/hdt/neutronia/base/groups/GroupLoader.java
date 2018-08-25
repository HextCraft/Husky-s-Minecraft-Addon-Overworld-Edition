package net.hdt.neutronia.base.groups;

import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.groups.NGroups;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class GroupLoader {

    public static Map<String, Component> componentClassNames = new HashMap<>();
    public static Configuration config;
    public static List<Group> groups;
    public static List<Group> enabledGroups;
    public static boolean firstLoad;
    static Map<Class<? extends Component>, Component> componentInstances = new HashMap<>();

    static {
        groups = new ArrayList<>();
    }

    public static void preInit(FMLPreInitializationEvent event) {
        NGroups.registerGroups();

        setupConfig(event);

        forEachModule(group -> {
            if (group.enabled) {
                LibMisc.LOGGER.info("Enabling Group " + group.name);
            } else {
                LibMisc.LOGGER.error("Could not enable " + group.name);
            }
        });

        forEachEnabled(module -> module.preInit(event));
        forEachEnabled(module -> module.postPreInit(event));
        forEachModule(module -> {
            for (Component component : componentInstances.values()) {
                LibMisc.LOGGER.info("Module " + module.name + " have these features enabled: " + component.configName);
            }
        });
    }

    public static void init(FMLInitializationEvent event) {
        forEachEnabled(module -> module.init(event));
    }

    public static void postInit(FMLPostInitializationEvent event) {
        forEachEnabled(module -> module.postInit(event));
    }

    public static void finalInit(FMLPostInitializationEvent event) {
        forEachEnabled(module -> module.finalInit(event));
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient(FMLPreInitializationEvent event) {
        forEachEnabled(module -> module.preInitClient(event));
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(FMLInitializationEvent event) {
        forEachEnabled(module -> module.initClient(event));
    }

    @SideOnly(Side.CLIENT)
    public static void postInitClient(FMLPostInitializationEvent event) {
        forEachEnabled(module -> module.postInitClient(event));
    }

    public static void serverStarting(FMLServerStartingEvent event) {
        forEachEnabled(module -> module.serverStarting(event));
    }

    public static void setupConfig(FMLPreInitializationEvent event) {
        File configFile = event.getSuggestedConfigurationFile();
        if(!configFile.exists())
            firstLoad = true;

        config = new Configuration(configFile);
        config.load();

        loadConfig();

        MinecraftForge.EVENT_BUS.register(new ChangeListener());
    }

    public static void loadConfig() {
        GlobalConfig.initGlobalConfig();

        forEachModule(module -> {
            module.enabled = true;
            if (module.canBeDisabled()) {
                ConfigHelper.needsRestart = true;
                module.enabled = ConfigHelper.loadPropBool(module.name, "_groups", module.getModuleDescription(), module.isEnabledByDefault());
                module.prop = ConfigHelper.lastProp;
            }
        });

        enabledGroups = new ArrayList<>(groups);
        enabledGroups.removeIf(module -> !module.enabled);

        loadModuleConfigs();

        if (config.hasChanged())
            config.save();
    }

    private static void loadModuleConfigs() {
        forEachModule(Group::setupConfig);
    }

    public static boolean isFeatureEnabled(Class<? extends Component> clazz) {
        return componentInstances.get(clazz).enabled;
    }

    static void forEachModule(Consumer<Group> consumer) {
        groups.forEach(consumer);
    }

    private static void forEachEnabled(Consumer<Group> consumer) {
        enabledGroups.forEach(consumer);
    }

    static void registerGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            if (!group.name.isEmpty())
                LibMisc.LOGGER.info("Registering Group " + group.name);
        }
    }

    public static class ChangeListener {

        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
            if (eventArgs.getModID().equals(LibMisc.MOD_ID))
                loadConfig();
        }

    }

}
