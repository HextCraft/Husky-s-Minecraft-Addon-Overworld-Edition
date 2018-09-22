package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class IntArrayProperty extends ConfigProperty<int[]> {

    public IntArrayProperty(Configuration config, String property, String category, int[] defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<int[]> setDefault(int[] defaultValue) {
        property.setDefaultValues(defaultValue);
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category,property,defaultValue);
    }

    @Override
    public int[] get() {

        return property.getIntList();
    }
}
