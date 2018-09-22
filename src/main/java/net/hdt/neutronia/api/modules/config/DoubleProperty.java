package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class DoubleProperty extends ConfigProperty<Double> {

    public DoubleProperty(Configuration config, String property, String category, Double defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<Double> setDefault(Double defaultValue) {
        property.setDefaultValue(defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public Double get() {
        return property.getDouble();
    }

}
