package net.hdt.neutronia.init;

import net.hdt.neutronia.groups.world.biomes.BiomeBlackDesert;
import net.hdt.neutronia.groups.world.biomes.BiomeRedDesert;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

public class NBiomes {

    public static final Biome RED_DESERT = new BiomeRedDesert();
    public static final Biome BLACK_DESERT = new BiomeBlackDesert();

    public static void registerBiomes() {
        addBiome(RED_DESERT, "red_desert", 10, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        addBiome(BLACK_DESERT, "black_desert", 20, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
    }

    private static void addBiome(Biome biome, String name, int weight, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {
        biome.setRegistryName(MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println(String.format("Biome: %s is now registered", name));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addStrongholdBiome(biome);
        BiomeManager.addVillageBiome(biome, true);
        System.out.println(String.format("Biome: %s is now added to the spawn biome's", name));
        System.out.println(String.format("Biome: %s has a %d percent chance to spawn", name, new BiomeManager.BiomeEntry(biome, weight).itemWeight));
    }

}