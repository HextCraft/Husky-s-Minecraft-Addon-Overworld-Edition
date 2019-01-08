package team.abnormal.neutronia.base.modules;

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
import org.apache.commons.lang3.text.WordUtils;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.base.handlers.RecipeProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModuleLoader {

    public static Configuration config;
    public static List<Module> groups;
    public static List<Module> enabledModules;
    public static boolean firstLoad;
    public static Map<Class<? extends Feature>, Feature> componentInstances = new HashMap<>();

    static {
        groups = new ArrayList<>();
    }

    public static void preInit(FMLPreInitializationEvent event) {
        setupConfig(event);

        forEachModule(module -> Reference.LOGGER.info(WordUtils.capitalizeFully(module.name) + " is " + (module.enabled ? "enabled" : "disabled")));

        forEachEnabledModule(module -> module.preInit(event));
        forEachEnabledModule(module -> module.postPreInit(event));

        RecipeProcessor.runConsumers();
    }

    public static void init(FMLInitializationEvent event) {
        forEachEnabledModule(module -> module.init(event));
    }

    public static void postInit(FMLPostInitializationEvent event) {
        forEachEnabledModule(module -> module.postInit(event));
    }

    public static void finalInit(FMLPostInitializationEvent event) {
        forEachEnabledModule(module -> module.finalInit(event));
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient(FMLPreInitializationEvent event) {
        forEachEnabledModule(module -> module.preInitClient(event));
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(FMLInitializationEvent event) {
        forEachEnabledModule(module -> module.initClient(event));
    }

    @SideOnly(Side.CLIENT)
    public static void postInitClient(FMLPostInitializationEvent event) {
        forEachEnabledModule(module -> module.postInitClient(event));
    }

    public static void serverStarting(FMLServerStartingEvent event) {
        forEachEnabledModule(module -> module.serverStarting(event));
    }

    public static void setupConfig(FMLPreInitializationEvent event) {
        File configFile = event.getSuggestedConfigurationFile();
        if (!configFile.exists())
            firstLoad = true;

        config = new Configuration(configFile);
        config.load();

        loadConfig();

        MinecraftForge.EVENT_BUS.register(new ChangeListener());
    }

    public static void loadConfig() {
        GlobalConfig.initGlobalConfig();
        forEachModule(group -> {
            if (group.canBeDisabled()) {
                ConfigHelper.needsRestart = true;
                group.enabled = ConfigHelper.loadPropBool(group.name, "_groups", group.getModuleDescription(), group.isEnabledByDefault());
                group.prop = ConfigHelper.lastProp;
            }
        });

        enabledModules = new ArrayList<>(groups);
        enabledModules.removeIf(group -> !group.enabled);

        loadModuleConfigs();

        if (config.hasChanged())
            config.save();
    }

    private static void loadModuleConfigs() {
        forEachModule(Module::setupConfig);
    }

    public static boolean isFeatureEnabled(Class<? extends Feature> clazz) {
        return componentInstances.get(clazz).stateManager.enabled;
    }

    private static void forEachModule(Consumer<Module> consumer) {
        groups.forEach(consumer);
    }

    private static void forEachEnabledModule(Consumer<Module> consumer) {
        if(!enabledModules.isEmpty()) {
            enabledModules.forEach(consumer);
        }
    }

    public static void registerModule(Module group) {
        if (!groups.contains(group)) {
            groups.add(group);
            if (!group.name.isEmpty())
                Reference.LOGGER.info("Registering Module " + group.name);
        }
    }

    public static class ChangeListener {

        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
            if (eventArgs.getModID().equals(Reference.MOD_ID))
                loadConfig();
        }

    }
    
}
