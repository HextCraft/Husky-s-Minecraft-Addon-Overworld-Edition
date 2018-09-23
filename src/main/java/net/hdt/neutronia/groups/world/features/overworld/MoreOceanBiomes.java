package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.biomes.BiomeColdOcean;
import net.hdt.neutronia.groups.world.biomes.BiomeLukewarmOcean;
import net.hdt.neutronia.groups.world.biomes.BiomeWarmOcean;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

public class MoreOceanBiomes extends Component {

    public static final Biome COLD_OCEAN = new BiomeColdOcean((new Biome.BiomeProperties("Cold Ocean")).setBaseHeight(-1.0F).setHeightVariation(0.1F).setBaseBiome("ocean").setWaterColor(0x3d57d6));
    public static final Biome LUKEWARM_OCEAN = new BiomeLukewarmOcean((new Biome.BiomeProperties("Lukewarm Ocean")).setBaseHeight(-1.0F).setHeightVariation(0.1F).setBaseBiome("ocean").setWaterColor(0x45adf2));
    public static final Biome WARM_OCEAN = new BiomeWarmOcean((new Biome.BiomeProperties("Warm Ocean")).setBaseHeight(-1.0F).setHeightVariation(0.1F).setBaseBiome("ocean").setWaterColor(0x43d5ee));
    public static final Biome DEEP_COLD_OCEAN = new BiomeColdOcean((new Biome.BiomeProperties("Deep Cold Ocean")).setBaseHeight(-1.8F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x3d57d6));
    public static final Biome DEEP_LUKEWARM_OCEAN = new BiomeLukewarmOcean((new Biome.BiomeProperties("Deep Lukewarm Ocean")).setBaseHeight(-1.8F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x45adf2));
    public static final Biome DEEP_WARM_OCEAN = new BiomeWarmOcean((new Biome.BiomeProperties("Deep Warm Ocean")).setBaseHeight(-1.8F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x43d5ee));
//    public static final Biome SUPER_DEEP_COLD_OCEAN = new BiomeColdOcean((new Biome.BiomeProperties("Super Deep Cold Ocean")).setBaseHeight(-2.7F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x3d57d6));
//    public static final Biome SUPER_DEEP_LUKEWARM_OCEAN = new BiomeLukewarmOcean((new Biome.BiomeProperties("Super Deep Lukewarm Ocean")).setBaseHeight(-2.7F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x45adf2));
//    public static final Biome SUPER_DEEP_WARM_OCEAN = new BiomeWarmOcean((new Biome.BiomeProperties("Super Deep Warm Ocean")).setBaseHeight(-2.7F).setHeightVariation(0.1F).setBaseBiome("deep_ocean").setWaterColor(0x3d57d6));

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        addOceanBiome(COLD_OCEAN, "cold_ocean", 15, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(LUKEWARM_OCEAN, "lukewarm_ocean", 10, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(WARM_OCEAN, "warm_ocean", 80, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);

        addOceanBiome(DEEP_COLD_OCEAN, "deep_cold_ocean", 20, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(DEEP_LUKEWARM_OCEAN, "deep_lukewarm_ocean", 4, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        addOceanBiome(DEEP_WARM_OCEAN, "deep_warm_ocean", 80, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
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