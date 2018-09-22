package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class FloatArrayProperty extends ConfigProperty<float[]> {

    public FloatArrayProperty(Configuration config, String property, String category, float[] defaultValue) {
        super(config, property, category, defaultValue);
    }

    @Override
    public ConfigProperty<float[]> setDefault(float[] defaultValue) {
        property.setDefaultValues(from(defaultValue));
        return this;
    }

    @Override
    public Property createProperty(String category, String property) {
        return config.get(category, property, from(defaultValue));
    }

    @Override
    public float[] get() {
        return from(property.getDoubleList());
    }


    private double[] from(float[] a) {
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    private float[] from(double[] a) {
        float[] b = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (float) a[i];
        }
        return b;
    }
}
