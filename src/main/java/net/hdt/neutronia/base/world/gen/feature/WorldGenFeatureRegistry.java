package net.hdt.neutronia.base.world.gen.feature;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.util.Config;
import net.hdt.neutronia.base.util.Reference;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class WorldGenFeatureRegistry {
    private static final Map<ResourceLocation, Class<? extends WorldGenFeature>> FEATURES = new HashMap<>();

    static {
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":scatter"), WorldGenFeatureScatter.class);
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":cluster"), WorldGenFeatureCluster.class);
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":fluid"), WorldGenFeatureFluid.class);
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":ore"), WorldGenFeatureOre.class);
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":pool"), WorldGenFeaturePool.class);
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":big_mushroom"), WorldGenFeatureBigMushroom.class);
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":oak_tree"), WorldGenFeatureOakTree.class);
        registerFeature(new ResourceLocation(Reference.MOD_ID + ":structure"), WorldGenFeatureStructure.class);
    }

    public static void registerFeature(ResourceLocation name, Class<? extends WorldGenFeature> cls) {
        if (!FEATURES.containsKey(name)) {
            FEATURES.put(name, cls);
        } else {
            Neutronia.LOGGER.warn("A feature with the name, {}, is already registered!", name.toString());
        }
    }

    public static WorldGenFeature createFeature(ResourceLocation name, Config config) {
        if (FEATURES.containsKey(name)) {
            try {
                return FEATURES.get(name).getConstructor(Config.class).newInstance(config);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}