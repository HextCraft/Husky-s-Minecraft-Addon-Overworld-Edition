package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class DoubleArrayProperty extends ConfigProperty<double[]> {

    public DoubleArrayProperty(Configuration config, String property, String category, double[] defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<double[]> setDefault(double[] defaultValue) {
        property.setDefaultValues(defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public double[] get() {
        return property.getDoubleList();
    }
}
