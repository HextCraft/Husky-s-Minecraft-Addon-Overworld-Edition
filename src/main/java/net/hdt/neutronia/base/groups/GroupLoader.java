package net.hdt.neutronia.base.groups;

import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.base.util.Reference;
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
    public static Map<Class<? extends Component>, Component> componentInstances = new HashMap<>();

    static {
        groups = new ArrayList<>();
    }

    public static void preInit(FMLPreInitializationEvent event) {
        NGroups.registerGroups();

        setupConfig(event);

        forEachGroup(module -> LibMisc.LOGGER.info("Group " + module.name + " is " + (module.enabled ? "enabled" : "disabled")));

        forEachEnabledGroup(module -> module.preInit(event));
        forEachEnabledGroup(module -> module.postPreInit(event));
    }

    public static void init(FMLInitializationEvent event) {
        forEachEnabledGroup(module -> module.init(event));
    }

    public static void postInit(FMLPostInitializationEvent event) {
        forEachEnabledGroup(module -> module.postInit(event));
    }

    public static void finalInit(FMLPostInitializationEvent event) {
        forEachEnabledGroup(module -> module.finalInit(event));
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient(FMLPreInitializationEvent event) {
        forEachEnabledGroup(module -> module.preInitClient(event));
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(FMLInitializationEvent event) {
        forEachEnabledGroup(module -> module.initClient(event));
    }

    @SideOnly(Side.CLIENT)
    public static void postInitClient(FMLPostInitializationEvent event) {
        forEachEnabledGroup(module -> module.postInitClient(event));
    }

    public static void serverStarting(FMLServerStartingEvent event) {
        forEachEnabledGroup(module -> module.serverStarting(event));
    }

    public static void setupConfig(FMLPreInitializationEvent event) {
        File configFile = event.getSuggestedConfigurationFile();
        if(!configFile.exists())
            firstLoad = true;

        config = new Configuration(configFile);
        config.load();

        loadConfig();

        forEachEnabledGroup(group -> new ConfigFileGenerator(new File(Reference.CONFIG_DIRECTORY + "/Neutronia/groups/" + group.name.toLowerCase().replace(" ", "_"), "main.json"), group));

        MinecraftForge.EVENT_BUS.register(new ChangeListener());
    }

    public static void loadConfig() {
        GlobalConfig.initGlobalConfig();

        forEachGroup(group -> {
            if (group.canBeDisabled()) {
                ConfigHelper.needsRestart = true;
                group.enabled = ConfigHelper.loadPropBool(group.name, "_groups", group.getModuleDescription(), group.isEnabledByDefault());
                group.prop = ConfigHelper.lastProp;
            }
        });

        enabledGroups = new ArrayList<>(groups);
        enabledGroups.removeIf(group -> !group.enabled);

        loadGroupConfigs();

        if (config.hasChanged())
            config.save();
    }

    private static void loadGroupConfigs() {
        forEachGroup(Group::setupConfig);
    }

    public static boolean isFeatureEnabled(Class<? extends Component> clazz) {
        return componentInstances.get(clazz).enabled;
    }

    private static void forEachGroup(Consumer<Group> consumer) {
        groups.forEach(consumer);
    }

    private static void forEachEnabledGroup(Consumer<Group> consumer) {
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
