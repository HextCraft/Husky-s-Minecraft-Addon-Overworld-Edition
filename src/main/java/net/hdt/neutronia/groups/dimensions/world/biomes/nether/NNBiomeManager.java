package net.hdt.neutronia.groups.dimensions.world.biomes.nether;

import com.google.common.collect.ImmutableList;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.util.Config;
import net.hdt.neutronia.base.util.FileHelper;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.base.world.biome.BiomeWrapper;
import net.hdt.neutronia.groups.dimensions.features.RevampedNether;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class NNBiomeManager {
    private static final List<BiomeManager.BiomeEntry> BIOMES = new ArrayList<>();
    private static final Map<Biome, BiomeWrapper> BIOME_WRAPPERS = new HashMap<>();
    private static final Map<Biome, Config> BIOME_CONFIGS = new HashMap<>();

    public static void preInit() {
        FileHelper.copyDirectoryToDirectory(Neutronia.class.getResource("/assets/neutronia/modules/dimensions/revamped_nether/biome_configs"), new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/dimensions/revamped_nether/biomes"));
    }

    public static void setupDefaultBiomes() {
        Neutronia.LOGGER.info("Setting up default biomes.");
        parseBiomeConfigs(new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/dimensions/revamped_nether/biomes/vanilla"));
        parseBiomeConfigs(new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/dimensions/revamped_nether/biomes/nether"));
    }

    public static void setupCompatibleBiomes(MinecraftServer server) {
        World world = server.getEntityWorld();

        if (Loader.isModLoaded("biomesoplenty") && RevampedNether.enableBOPCompat) {
            WorldType worldType = world.getWorldType();

            if (worldType.getName().equalsIgnoreCase("BIOMESOP") || worldType.getName().equalsIgnoreCase("lostcities_bop")) {
                Neutronia.LOGGER.info("Setting up Biomes O' Plenty biomes.");
                parseBiomeConfigs(new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/dimensions/revamped_nether/biomes/biomesoplenty"));
            }
        }
    }

    public static void setupCustomBiomes() {
        Neutronia.LOGGER.info("Setting up custom biomes.");
        parseBiomeConfigs(new File(Reference.CONFIG_DIRECTORY, "Neutronia/modules/dimensions/revamped_nether/biomes/custom"));
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
        BiomeWrapper wrapper = new NetherBiomeWrapper(config);
        Biome biome = wrapper.getBiome();

        if (biome != null && wrapper.isEnabled()) {
            BIOMES.add(new BiomeManager.BiomeEntry(biome, config.getInt("weight", 10)));
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

    public static List<BiomeManager.BiomeEntry> getBiomes() {
        return ImmutableList.copyOf(BIOMES);
    }

    public static BiomeWrapper getBiomeWrapper(Biome key) {
        return BIOME_WRAPPERS.get(key);
    }

    public static Config getBiomeConfig(Biome key) {
        return BIOME_CONFIGS.get(key);
    }
}