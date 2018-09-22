package net.hdt.neutronia.api.modules.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public abstract class ConfigProperty<T> {

    protected T defaultValue;
    protected Property property;

    protected Configuration config;
    private String category;

    public ConfigProperty(Configuration config, String property, String category, T defaultValue) {
        this.config = config;
        this.defaultValue = defaultValue;
        this.category = category;
        this.property = createProperty(category, property);
    }

    public ConfigProperty<T> setMax(int max) {
        this.property.setMaxValue(max);
        return this;
    }

    public ConfigProperty<T> setMin(int min) {
        this.property.setMinValue(min);
        return this;
    }

    public ConfigProperty<T> setMax(double max) {
        this.property.setMaxValue(max);
        return this;
    }

    public ConfigProperty<T> setMin(double min) {
        this.property.setMinValue(min);
        return this;
    }


    public ConfigProperty<T> setComment(String comment) {
        this.property.setComment(comment);
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public ConfigProperty<T> setCategory(String category) {
        config.getCategory(category).put(property.getName(), property);
        this.category = category;
        return this;
    }

    public ConfigProperty<T> subCategory(String child) {
        return this.setCategory(String.format("%s.%s", getCategory(), child));
    }

    public ConfigProperty<T> setCategoryComment(String comment) {
        config.setCategoryComment(getCategory(), comment);
        return this;
    }

    public abstract ConfigProperty<T> setDefault(T defaultValue);


    public abstract Property createProperty(String category, String property);

    public abstract T get();

}
