package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class BooleanProperty extends ConfigProperty<Boolean> {

    public BooleanProperty(Configuration config, String property, String category, Boolean defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<Boolean> setDefault( Boolean defaultValue) {
        property.setDefaultValue(defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public Boolean get() {
        return property.getBoolean();
    }
}
