package net.hdt.neutronia.groups.dimensions.world.biomes.end;

import com.google.common.collect.ImmutableList;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.util.Config;
import net.hdt.neutronia.base.util.FileHelper;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.base.world.biome.BiomeWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class NEBiomeManager {
    private static final Map<BiomeType, List<BiomeManager.BiomeEntry>> BIOMES = new HashMap<>();
    private static final Map<Biome, BiomeWrapper> BIOME_WRAPPERS = new HashMap<>();
    private static final Map<Biome, Config> BIOME_CONFIGS = new HashMap<>();

    public static void preInit() {
        FileHelper.copyDirectoryToDirectory(Neutronia.class.getResource("/assets/neutronia/modules/dimensions/revamped_end/biome_configs"), new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/biomes/revamped_end/biomes"));
    }

    public static void setupDefaultBiomes() {
        Neutronia.LOGGER.info("Setting up default biomes.");
        parseBiomeConfigs(new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/biomes/revamped_end/biomes/vanilla"));
        parseBiomeConfigs(new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/biomes/revamped_end/biomes/end"));
    }

    public static void setupCompatibleBiomes(MinecraftServer server) {
        World world = server.getEntityWorld();
    }

    public static void setupCustomBiomes() {
        Neutronia.LOGGER.info("Setting up custom biomes.");
        parseBiomeConfigs(new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/biomes/revamped_end/biomes/custom"));
    }

    private static void parseBiomeConfigs(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            Iterator<Path> pathIter = Files.walk(directory.toPath()).iterator();

            while (pathIter.hasNext()) {
                Path configPath = pathIter.next();
                File configFile = configPath.toFile();

                if (FileHelper.getFileExtension(configFile).equals("json")) {
                    wrapBiome(new Config(configFile, true), configFile);
                } else if (!configFile.isDirectory()) {
                    Neutronia.LOGGER.warn("Skipping file located at, {}, as it is not a json file.", configPath.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void wrapBiome(Config config, File configFile) {
        BiomeWrapper wrapper = new BiomeWrapper(config);
        Biome biome = wrapper.getBiome();
        BiomeType type = config.getEnum("region", BiomeType.class, BiomeType.ORBIT);

        if (biome != null && wrapper.isEnabled()) {
            BIOMES.put(type, BIOMES.computeIfAbsent(type, k -> new ArrayList<>())).add(new BiomeManager.BiomeEntry(biome, config.getInt("weight", 10)));
            BIOME_WRAPPERS.put(biome, wrapper);
            BIOME_CONFIGS.put(biome, config);
            config.save(configFile);
        }
    }

    public static void resetBiomes() {
        BIOMES.clear();
        BIOME_WRAPPERS.clear();
        BIOME_CONFIGS.clear();
    }

    public static List<BiomeManager.BiomeEntry> getBiomes(BiomeType type) {
        return ImmutableList.copyOf(BIOMES.get(type));
    }

    public static BiomeWrapper getBiomeWrapper(Biome key) {
        return BIOME_WRAPPERS.get(key);
    }

    public static Config getBiomeConfig(Biome key) {
        return BIOME_CONFIGS.get(key);
    }

    public enum BiomeType {
        HEART,
        ORBIT,
        DIVIDE,
        EXPANSE
    }
}