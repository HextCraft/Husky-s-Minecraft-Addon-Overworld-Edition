package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class FloatProperty extends ConfigProperty<Float> {
    public FloatProperty(Configuration config, String property, String category, Float defaultValue) {
        super(config, property, category, (float) defaultValue.doubleValue());
    }

    @Override
    public ConfigProperty<Float> setDefault(Float defaultValue) {
        property.setDefaultValue((double) defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public Float get() {

        return (float) property.getDouble();
    }
}
