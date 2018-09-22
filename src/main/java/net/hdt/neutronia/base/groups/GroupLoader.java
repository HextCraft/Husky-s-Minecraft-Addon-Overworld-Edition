package net.hdt.neutronia.base.groups;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.hdt.neutronia.api.modules.impl.ListStateHandler;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public final class GroupLoader extends ListStateHandler<Group> {

    static final HashMap<String, Boolean> JSON_CONDITIONS = Maps.newHashMap();

    private static Set<Class<? extends Component>> enabledFeatures = Sets.newHashSet();
    private Logger logger;
    public static Configuration config;
    private File relativeConfigDir;

    public GroupLoader(File relativeConfigDir) {
        this.relativeConfigDir = relativeConfigDir;
    }

    public GroupLoader addModules(Group... modules) {
        for (Group module : modules)
            addModule(module);
        return this;
    }

    public GroupLoader addModule(Group module) {
        add(module);
        return this;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
//        File file = event.getSuggestedConfigurationFile();
//        ConfigHelper helper = new ConfigHelper(file.getParent(), new Configuration(file));
        config = new Configuration(event.getSuggestedConfigurationFile());
        forEach(module -> {
            //FIXME Currently have a config for each module, not entirely sure about this
            File file = new File(event.getModConfigurationDirectory(), relativeConfigDir.getPath());
            ConfigHelper helper = new ConfigHelper(file.getPath(), new Configuration(new File(file, module.getName() + ".cfg")));

            List<Component> feature = module.setup(helper, getLogger());
            //Add all feature classes to the set;
            enabledFeatures.addAll(feature.stream().map(Component::getClass).collect(Collectors.toSet()));
        });
        super.preInit(event);
    }

    public static boolean isComponentEnabled(Class<? extends Component> clazz) {
        return enabledFeatures.contains(clazz);
    }

    @Override
    public String getName() {
        return "GroupLoader";
    }

    @Override
    public String getType() {
        return "GroupLoader";
    }

    @SuppressWarnings("unused")
    public static class ConditionConfig implements IConditionFactory {
        @Override
        public BooleanSupplier parse(JsonContext context, JsonObject json) {
            String enabled = JsonUtils.getString(json, "enabled");
            return () -> JSON_CONDITIONS.getOrDefault(enabled, false);
        }
    }

}
