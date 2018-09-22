package net.hdt.neutronia.base.groups;

import com.google.common.collect.Maps;
import net.hdt.neutronia.api.modules.config.*;
import net.hdt.neutronia.base.util.StackIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigHelper {

    public static Configuration config = null;
    public final String path;

    public ConfigHelper(String path, Configuration configuration) {
        this.path = path;
        config = configuration;
    }

    public static String joinCategory(String... cat) {
        return String.join(".", cat);
    }

    private ResourceLocation rlFromString(String loc) {
        String[] split = loc.split(":");
        if (split.length > 1) {
            return new ResourceLocation(split[0], split[1]);
        }
        return null;
    }

    public static Configuration getConfig() {
        return config;
    }

    private ItemStack stackFromString(String name) {
        String[] split = name.split(":");
        if (split.length > 1) {
            int meta = 0;
            if (split.length > 2) {
                if (split[2].equalsIgnoreCase("*"))
                    meta = OreDictionary.WILDCARD_VALUE;
                else
                    meta = Integer.parseInt(split[2]);
            }
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(split[0], split[1]));
            if (item != null) {
                return new ItemStack(item, 1, meta);
            }
        }
        return ItemStack.EMPTY;
    }

    private Ingredient ingredientfromString(String name) {
        if (name.startsWith("ore:"))
            return new OreIngredient(name.substring(4));
        String[] split = name.split(":");
        if (split.length > 1) {
            int meta = 0;
            if (split.length > 2) {
                if (split[2].equalsIgnoreCase("*"))
                    meta = OreDictionary.WILDCARD_VALUE;
                else
                    meta = Integer.parseInt(split[2]);
            }
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(split[0], split[1]));
            if (item != null) {
                return StackIngredient.fromStacks(new ItemStack(item, 1, meta));
            }
        }
        return Ingredient.EMPTY;
    }

    private String fromStack(ItemStack stack) {
        if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
            return String.format("%s:*", stack.getItem().getRegistryName());
        } else if (stack.getMetadata() == 0) {
            return stack.getItem().getRegistryName().toString();
        } else {
            return String.format("%s:%s", stack.getItem().getRegistryName(), stack.getMetadata());
        }
    }

    private HashMap<Ingredient, Integer> parseIngredientValueMap(String[] entries) {
        HashMap<Ingredient, Integer> map = Maps.newHashMap();
        for (String entry : entries) {
            String[] keyValue = entry.split("=");
            if (keyValue.length == 2) {
                map.put(this.ingredientfromString(keyValue[0]), Integer.parseInt(keyValue[1]));
            }
        }
        return map;
    }

    public void setDescription(String category, String comment) {
        config.setCategoryComment(category, comment);
    }

    public void setCategoryComment(String category, String comment) {
        config.setCategoryComment(category, comment);
    }

    public Set<ResourceLocation> loadResourceLocations(String property, String category, String comment, String[] default_) {
        return Arrays.stream(load(category, property, default_).setComment(comment).get()).map(this::rlFromString).collect(Collectors.toSet());
    }

    public boolean loadRecipeCondition(String jsonString, String property, String category, String comment, boolean default_) {
        boolean value = load(category, property, default_).setComment(comment).get();
        GroupLoader.JSON_CONDITIONS.put(jsonString, value);
        return value;
    }

    public List<ResourceLocation> loadResouceLocations(String property, String category, String comment, String[] default_) {
        return Arrays.stream(load(category, property, default_).setComment(comment).get()).map(this::rlFromString).collect(Collectors.toList());
    }

    public List<ItemStack> loadItemStackList(String property, String category, String comment, String[] default_) {
        return Arrays.stream(load(category, property, default_).setComment(comment).get()).map(this::stackFromString).collect(Collectors.toList());
    }

    public List<ItemStack> loadItemStackList(String property, String category, String comment, ItemStack[] default_) {
        String[] strings_ = new String[default_.length];
        Arrays.stream(default_).map(this::fromStack).collect(Collectors.toList()).toArray(strings_);
        return loadItemStackList(property, category, comment, strings_);
    }

    public ItemStack[] loadItemStackArray(String property, String category, String comment, String[] default_) {
        return Arrays.stream(load(category, property, default_).setComment(comment).get()).map(this::stackFromString).toArray(ItemStack[]::new);
    }

    public ItemStack[] loadItemStackArray(String property, String category, String comment, ItemStack[] default_) {
        String[] strings_ = new String[default_.length];
        Arrays.stream(default_).map(this::fromStack).collect(Collectors.toList()).toArray(strings_);
        return loadItemStackArray(property, category, comment, strings_);
    }

    public HashMap<Ingredient, Integer> loadItemStackIntMap(String property, String category, String comment, String[] default_) {
        return parseIngredientValueMap(load(category, property, default_).setComment(comment).get());
    }

    //
    public void save() {
        if (config.hasChanged())
            config.save();
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigProperty<T> load(String category, String property, T value) {
        ConfigProperty<T> prop = null;
        if (Boolean.class.isInstance(value)) {
            prop = (ConfigProperty<T>) new BooleanProperty(config, property, category, (Boolean) value);
        } else if (Integer.class.isInstance(value)) {
            prop = (ConfigProperty<T>) new IntProperty(config, property, category, (Integer) value);
        } else if (value instanceof String) {
            prop = (ConfigProperty<T>) new StringProperty(config, property, category, (String) value);
        } else if (Double.class.isInstance(value)) {
            prop = (ConfigProperty<T>) new DoubleProperty(config, property, category, (Double) value);
        } else if (Float.class.isInstance(value)) {
            prop = (ConfigProperty<T>) new FloatProperty(config, property, category, (Float) value);
        } else if (value instanceof String[]) {
            prop = (ConfigProperty<T>) new StringArrayProperty(config, property, category, (String[]) value);
        } else if (value instanceof int[]) {
            prop = (ConfigProperty<T>) new IntArrayProperty(config, property, category, (int[]) value);
        } else if (value instanceof double[]) {
            prop = (ConfigProperty<T>) new DoubleArrayProperty(config, property, category, (double[]) value);
        } else if (value instanceof boolean[]) {
            prop = (ConfigProperty<T>) new BooleanArrayProperty(config, property, category, (boolean[]) value);
        }
        return prop;
    }

}
