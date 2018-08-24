package net.hdt.neutronia.groups.dimensions.world.biomes.nether;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hdt.neutronia.base.util.Config;
import net.hdt.neutronia.base.world.biome.BiomeWrapper;
import net.hdt.neutronia.base.world.gen.WorldGenerationStages;
import net.hdt.neutronia.base.world.gen.feature.WorldGenFeature;
import net.hdt.neutronia.base.world.gen.feature.WorldGenFeatureRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NetherBiomeWrapper extends BiomeWrapper {
    public NetherBiomeWrapper(Config configIn) {
        super(configIn);
    }

    @Override
    protected void parse() {
        biome = ForgeRegistries.BIOMES.getValue(config.getResource("biome"));

        if (biome != null) {
            ResourceLocation biomeName = biome.getRegistryName();
            weight = config.getInt("weight", 10);

            if (!biomeName.getNamespace().equalsIgnoreCase("biomesoplenty")) {
                Config blockConfig = config.getDataBranch("blocks", new JsonObject());
                blockConfig.getBlock("topBlock", biome.topBlock);
                blockConfig.getBlock("fillerBlock", biome.fillerBlock);

                for (Map.Entry<String, JsonElement> entry : blockConfig.getAllData().entrySet()) {
                    if (blockConfig.getBlock(entry.getKey()) != null) {
                        blocks.put(entry.getKey(), blockConfig.getBlock(entry.getKey()));
                    }
                }
            }

            List<Config> entityConfigs = config.getDataBranches("entities", new ArrayList<>());
            List<JsonObject> entityObjects = new ArrayList<>();

            for (EnumCreatureType creatureType : EnumCreatureType.values()) {
                entryLoop:
                for (Biome.SpawnListEntry entry : biome.getSpawnableList(creatureType)) {
                    ResourceLocation entityName = ForgeRegistries.ENTITIES.getKey(EntityRegistry.getEntry(entry.entityClass));
                    boolean containsEntry = false;

                    Iterator<Config> configIter = entityConfigs.iterator();

                    while (configIter.hasNext()) {
                        Config entityConfig = configIter.next();

                        if (entityName != null && entityConfig.getString("entity").equals(entityName.toString())) {
                            containsEntry = true;
                        }

                        entityObjects.add(entityConfig.serialize().getAsJsonObject());
                        configIter.remove();

                        if (containsEntry) {
                            continue entryLoop;
                        }
                    }

                    JsonObject entityObject = new JsonObject();
                    entityObject.addProperty("entity", ForgeRegistries.ENTITIES.getKey(EntityRegistry.getEntry(entry.entityClass)).toString());
                    entityObject.addProperty("creatureType", creatureType.toString().toLowerCase());
                    entityObject.addProperty("weight", entry.itemWeight);
                    entityObject.addProperty("minGroupCount", entry.minGroupCount);
                    entityObject.addProperty("maxGroupCount", entry.maxGroupCount);
                    entityObject.addProperty("spawn", true);
                    entityObjects.add(entityObject);
                }
            }

            config.removeData("entities");
            entityConfigs = config.getDataBranches("entities", entityObjects);

            for (Config entityConfig : entityConfigs) {
                EntityEntry entry = ForgeRegistries.ENTITIES.getValue(entityConfig.getResource("entity"));

                if (entry != null && entityConfig.getBoolean("spawn", true)) {
                    Class<? extends Entity> entityCls = entry.getEntityClass();
                    EnumCreatureType creatureType = entityConfig.getEnum("creatureType", EnumCreatureType.class);

                    if (EntityLiving.class.isAssignableFrom(entityCls)) {
                        spawnableMobs.computeIfAbsent(creatureType, k -> new ArrayList<>()).add(new Biome.SpawnListEntry((Class<? extends EntityLiving>) entityCls, entityConfig.getInt("weight", 10), entityConfig.getInt("minGroupCount", 1), entityConfig.getInt("maxGroupCount", 4)));
                    }
                }
            }

            List<Config> featureConfigs = config.getDataBranches("features", new ArrayList<>());
            List<JsonObject> featureObjects = new ArrayList<>();

            for (Config featureConfig : featureConfigs) {
                WorldGenFeature worldGenFeature = WorldGenFeatureRegistry.createFeature(featureConfig.getResource("worldGenFeature"), featureConfig);

                if (worldGenFeature != null && featureConfig.getBoolean("generate", true)) {
                    WorldGenerationStages generationStage = featureConfig.getEnum("genStage", WorldGenerationStages.class, WorldGenerationStages.POST_DECORATE);
                    generationStageFeatures.computeIfAbsent(generationStage, k -> new ArrayList<>()).add(worldGenFeature);
                }

                featureObjects.add(featureConfig.serialize().getAsJsonObject());
            }

            config.removeData("features");
            config.getDataBranches("features", featureObjects);
            enabled = config.getBoolean("enabled", true);
            genDefaultFeatures = config.getBoolean("genDefaultFeatures", true);
        }
    }
}