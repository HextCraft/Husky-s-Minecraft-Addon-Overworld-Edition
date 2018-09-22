package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class StringArrayProperty extends ConfigProperty<String[]> {

    public StringArrayProperty(Configuration config, String property, String category,  String[] defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<String[]> setDefault(String[] defaultValue) {
        property.setDefaultValues(this.defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public String[] get() {
        return property.getStringList();
    }
}
