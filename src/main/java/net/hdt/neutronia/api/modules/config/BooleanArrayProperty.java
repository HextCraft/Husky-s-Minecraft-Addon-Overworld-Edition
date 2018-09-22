package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class BooleanArrayProperty extends ConfigProperty<boolean[]> {

    public BooleanArrayProperty(Configuration config, String property, String category, boolean[] defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<boolean[]> setDefault(boolean[] defaultValue) {
        property.setDefaultValues(defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public boolean[] get() {
        return property.getBooleanList();
    }
}
