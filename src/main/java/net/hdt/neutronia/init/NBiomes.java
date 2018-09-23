package net.hdt.neutronia.init;

import net.hdt.neutronia.groups.world.biomes.BiomeColdOcean;
import net.hdt.neutronia.groups.world.biomes.BiomeLukewarmOcean;
import net.hdt.neutronia.groups.world.biomes.BiomeWarmOcean;
import net.hdt.neutronia.world.biome.overworld.BiomeBasaltOverworld;
import net.hdt.neutronia.world.biome.overworld.BiomeBlackDesert;
import net.hdt.neutronia.world.biome.overworld.BiomePath;
import net.hdt.neutronia.world.biome.overworld.BiomeRedDesert;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

public class NBiomes {

    public static final Biome BASALT = new BiomeBasaltOverworld();
    public static final Biome RED_DESERT = new BiomeRedDesert();
    public static final Biome BLACK_DESERT = new BiomeBlackDesert();

    public static final Biome PATH = new BiomePath((new Biome.BiomeProperties("Path")).setBaseHeight(-0.2F).setHeightVariation(0.0F));

    public static final Biome COLD_OCEAN = new BiomeColdOcean((new Biome.BiomeProperties("Cold Ocean")).setBaseHeight(-1.0F).setHeightVariation(0.1F).setBaseBiome("ocean").setWaterColor(0x3d57d6));
    public static final Biome LUKEWARM_OCEAN = new BiomeLukewarmOcean((new Biome.BiomeProperties("Lukewarm Ocean")).setBaseHeight(-1.0F).setHeightVariation(0.1F).setBaseBiome("ocean").setWaterColor(0x45adf2));
    public static final Biome WARM_OCEAN = new BiomeWarmOcean((new Biome.BiomeProperties("Warm Ocean")).setBaseHeight(-1.0F).setHeightVariation(0.1F).setBaseBiome("ocean").setWaterColor(0x43d5ee));
    public static final Biome DEEP_COLD_OCEAN = new BiomeColdOcean((new Biome.BiomeProperties("Deep Cold Ocean")).setBaseHeight(-1.8F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x3d57d6));
    public static final Biome DEEP_LUKEWARM_OCEAN = new BiomeLukewarmOcean((new Biome.BiomeProperties("Deep Lukewarm Ocean")).setBaseHeight(-1.8F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x45adf2));
    public static final Biome DEEP_WARM_OCEAN = new BiomeWarmOcean((new Biome.BiomeProperties("Deep Warm Ocean")).setBaseHeight(-1.8F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x43d5ee));
    public static final Biome SUPER_DEEP_COLD_OCEAN = new BiomeColdOcean((new Biome.BiomeProperties("Super Deep Cold Ocean")).setBaseHeight(-2.7F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x3d57d6));
    public static final Biome SUPER_DEEP_LUKEWARM_OCEAN = new BiomeLukewarmOcean((new Biome.BiomeProperties("Super Deep Lukewarm Ocean")).setBaseHeight(-2.7F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x45adf2));
    public static final Biome SUPER_DEEP_WARM_OCEAN = new BiomeWarmOcean((new Biome.BiomeProperties("Super Deep Warm Ocean")).setBaseHeight(-2.7F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x3d57d6));
//    public static final Biome MESA = new BiomeFrozenMesa(false, false, (new Biome.BiomeProperties("Frozen Mesa")).setTemperature(0.0F).setRainfall(0.0F).setRainDisabled());
//    public static final Biome MESA_ROCK = new BiomeFrozenMesa(false, true, (new Biome.BiomeProperties("Frozen Mesa Plateau F")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(0.0F).setRainfall(0.0F).setRainDisabled());
//    public static final Biome MESA_CLEAR_ROCK = new BiomeFrozenMesa(false, false, (new Biome.BiomeProperties("Frozen Mesa Plateau")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(0.0F).setRainfall(0.0F).setRainDisabled());
//    public static final Biome MUTATED_MESA = new BiomeFrozenMesa(false, true, (new Biome.BiomeProperties("Frozen Mesa (Bryce)")).setBaseBiome("frozen_mesa").setTemperature(0.0F).setRainfall(0.0F).setRainDisabled());
//    public static final Biome MUTATED_MESA_ROCK = new BiomeFrozenMesa(false, true, (new Biome.BiomeProperties("Frozen Mesa Plateau F M")).setBaseBiome("frozen_mesa_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.0F).setRainfall(0.0F).setRainDisabled());
//    public static final Biome MUTATED_MESA_CLEAR_ROCK = new BiomeFrozenMesa(false, false, (new Biome.BiomeProperties("Frozen Mesa Plateau F M")).setBaseBiome("frozen_mesa_clear_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.0F).setRainfall(0.0F).setRainDisabled());

    public static void registerBiomes() {
        addBiome(BASALT, "basalt", 3, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);
        addBiome(RED_DESERT, "red_desert", 10, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        addBiome(BLACK_DESERT, "black_desert", 20, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);

        addOceanBiome(COLD_OCEAN, "cold_ocean", 15, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(LUKEWARM_OCEAN, "lukewarm_ocean", 10, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(WARM_OCEAN, "warm_ocean", 80, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);

        addOceanBiome(DEEP_COLD_OCEAN, "deep_cold_ocean", 20, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(DEEP_LUKEWARM_OCEAN, "deep_lukewarm_ocean", 4, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(DEEP_WARM_OCEAN, "deep_warm_ocean", 80, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);

        addBiome(PATH, "path", 20, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.WATER, BiomeDictionary.Type.WATER);

//        addOceanBiome(SUPER_DEEP_COLD_OCEAN, "super_deep_cold_ocean", 20, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
//        addOceanBiome(SUPER_DEEP_LUKEWARM_OCEAN, "super_deep_lukewarm_ocean", 4, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
//        addOceanBiome(SUPER_DEEP_WARM_OCEAN, "super_deep_warm_ocean", 6, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);

//        addBiome(MESA, "frozen_mesa", 10, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
//        addBiome(MESA_ROCK, "frozen_mesa_rock", 10, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
//        addBiome(MESA_CLEAR_ROCK, "frozen_mesa_clear_rock", 10, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
//        addBiome(MUTATED_MESA, "frozen_mutated_mesa", 2, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
//        addBiome(MUTATED_MESA_ROCK, "frozen_mutated_mesa_rock", 5, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
//        addBiome(MUTATED_MESA_CLEAR_ROCK, "frozen_mutated_mesa_clear_rock", 3, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
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

    private static void addOceanBiome(Biome biome, String name, int weight, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {
        biome.setRegistryName(MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println(String.format("Biome: %s is now registered", name));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        System.out.println(String.format("Biome: %s is now added to the spawn biome's", name));
        System.out.println(String.format("Biome: %s has a %d percent chance to spawn", name, new BiomeManager.BiomeEntry(biome, weight).itemWeight));
        BiomeManager.oceanBiomes.add(biome);
    }

}