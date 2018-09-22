package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class IntProperty extends ConfigProperty<Integer> {

    public IntProperty(Configuration config, String property, String category, Integer defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<Integer> setDefault(Integer defaultValue) {
        property.setDefaultValue(defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public Integer get() {
        return property.getInt();
    }
}
