package team.hdt.neutronia_legacy.groups.world.features.overworld;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.util.Reference;
import team.hdt.neutronia_legacy.groups.world.biomes.*;

import static net.minecraft.world.biome.Biome.BiomeProperties;

public class MoreBiomes extends Component {

    private static final Biome CHAPARRAL = new BiomeChaparral();

    //WARM
    private static final Biome HILLS = new BiomeNHills(new BiomeProperties("Hills").setBaseHeight(1.15F).setHeightVariation(0.558F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome MOUNTAINS = new BiomeNHills(new BiomeProperties("Mountains").setBaseHeight(1.5F).setHeightVariation(0.69F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));

    //COOL
    private static final Biome CLIFFS = new BiomeCliffs(new BiomeProperties("Cliffs").setBaseHeight(3.4F).setHeightVariation(0.4F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome PINE_LAND = new BiomePineland(new BiomeProperties("Pineland").setBaseHeight(0.98F).setHeightVariation(0.54F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
    private static final Biome PINE_FOREST = new BiomePineForest(new BiomeProperties("Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));

    //DESERT
    private static final Biome SCRUB_LAND = new BiomeScrubland(new BiomeProperties("Scrubland").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    private static final Biome SANDY_SCRUB_LAND = new BiomeSandyScrubland(new BiomeProperties("Sandy Scrubland").setBaseHeight(0.123F).setHeightVariation(0.044F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(0.9F));
    private static final Biome LUSH_DESERT = new BiomeLushDesert(new BiomeProperties("Lush Desert").setBaseHeight(0.123F).setHeightVariation(0.044F).setTemperature(Biomes.JUNGLE.getDefaultTemperature()).setRainfall(0.9F));
    private static final Biome SAHEL = new BiomeSahel(new BiomeProperties("Sahel").setBaseHeight(0.125F).setHeightVariation(0.046F).setTemperature(0.95F).setRainfall(0.9F));
    private static final Biome ROCK_LAND = new BiomeRockland(new BiomeProperties("Rockland").setBaseHeight(0.82F).setHeightVariation(0.48F).setTemperature(0.8F).setRainfall(0.4F));
    private static final Biome SERENGETI = new BiomeSerengeti(new BiomeProperties("Serengeti").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    private static final Biome GOLDEN_SAVANNA = new BiomeGoldenSavanna(new BiomeProperties("Golden Savanna").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    private static final Biome GOLDEN_SAVANNA_PLATEAU = new BiomeGoldenSavanna(new BiomeProperties("Golden Savanna Plateau").setBaseHeight(Biomes.SAVANNA_PLATEAU.getBaseHeight()).setHeightVariation(Biomes.SAVANNA_PLATEAU.getHeightVariation()).setTemperature(Biomes.SAVANNA_PLATEAU.getDefaultTemperature()).setRainfall(Biomes.SAVANNA_PLATEAU.getRainfall()));
    private static final Biome SAND_DUNE = new BiomeSandDune(new BiomeProperties("Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
    private static final Biome RED_SAND_DUNE = new BiomeRedSandDune(new BiomeProperties("Red Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());

    //ICY
    private static final Biome ALPS = new BiomeAlps(new BiomeProperties("Alps").setBaseHeight(5F).setHeightVariation(0.8F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome COLD_FOREST = new BiomeColdForest(BiomeForest.Type.NORMAL, "Cold Forest");
    private static final Biome COLD_BIRCH_FOREST = new BiomeColdForest(BiomeForest.Type.BIRCH, "Cold Birch Forest");
    private static final Biome COLD_ROOFED_FOREST = new BiomeColdForest(BiomeForest.Type.ROOFED, "Cold Roofed Forest");
    private static final Biome COLD_FLOWER_FOREST = new BiomeColdForest(BiomeForest.Type.FLOWER, "Cold Flower Forest");
    private static final Biome COLD_MEGA_TAIGA = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA, "Cold Mega Taiga");
    private static final Biome COLD_MEGA_SPRUCE_TAIGA = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga");
    private static final Biome COLD_FOREST_HILLS = new BiomeColdForestHills(BiomeForest.Type.NORMAL, "Cold Forest Hills");
    private static final Biome COLD_BIRCH_FOREST_HILLS = new BiomeColdForestHills(BiomeForest.Type.BIRCH, "Cold Birch Forest Hills");
    private static final Biome COLD_ROOFED_FOREST_HILLS = new BiomeColdForestHills(BiomeForest.Type.ROOFED, "Cold Roofed Forest Hills");
    private static final Biome COLD_FLOWER_FOREST_HILLS = new BiomeColdForestHills(BiomeForest.Type.FLOWER, "Cold Flower Forest Hills");
    private static final Biome COLD_MEGA_TAIGA_HILLS = new BiomeColdMegaTaigaHills(BiomeTaiga.Type.MEGA, "Cold Mega Taiga Hills");
    private static final Biome COLD_MEGA_SPRUCE_TAIGA_HILLS = new BiomeColdMegaTaigaHills(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga Hills");
    private static final Biome ICY_TUNDRA = new BiomeIcyTundra(new BiomeProperties("Icy Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(-1).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()).setSnowEnabled());
    private static final Biome TUNDRA = new BiomeTundra(new BiomeProperties("Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(0.20F).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));
    private static final Biome SNOWY_PINE_FOREST = new BiomeSnowyPineForest(new BiomeProperties("Snowy Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
    private static final Biome SNOW_DUNE = new BiomeSnowDune(new BiomeProperties("Snow Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.FROZEN_OCEAN.getDefaultTemperature()).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));

    private static void addBiome(Biome biome, String name, int weight, boolean hasVillage, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {
        biome.setRegistryName(Reference.MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println(String.format("Biome: %s is now registered", biome.getRegistryName()));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addStrongholdBiome(biome);
        BiomeManager.addVillageBiome(biome, hasVillage);

        System.out.println(String.format("Biome: %s is now added to the spawn biome's", biome.getRegistryName()));
        System.out.println(String.format("Biome: %s has a %d percent chance to spawn", biome.getRegistryName(), weight));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        //WARM
        addBiome(MOUNTAINS, "mountains", 4, true, BiomeType.WARM, Type.HILLS);
        addBiome(HILLS, "hills", 6, true, BiomeType.WARM, Type.HILLS);

        //COOL
        addBiome(CLIFFS, "cliffs", 7, false, BiomeType.COOL, Type.MOUNTAIN, Type.CONIFEROUS);
        addBiome(PINE_LAND, "pine_land", 6, false, BiomeType.COOL, Type.HILLS, Type.CONIFEROUS);
        addBiome(PINE_FOREST, "pine_forest", 5, false, BiomeType.COOL, Type.CONIFEROUS);

        //DESERT
        addBiome(SCRUB_LAND, "scrub_land", 5, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(SERENGETI, "serengeti", 5, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(SANDY_SCRUB_LAND, "sandy_scrub_land", 3, true, BiomeType.DESERT, Type.SANDY);
        addBiome(LUSH_DESERT, "lush_desert", 4, true, BiomeType.DESERT, Type.SAVANNA, Type.LUSH);
        addBiome(SAHEL, "sahel", 6, true, BiomeType.DESERT, Type.SANDY);
        addBiome(ROCK_LAND, "rock_land", 5, false, BiomeType.DESERT, Type.SAVANNA);
        addBiome(GOLDEN_SAVANNA, "golden_savanna", 5, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(GOLDEN_SAVANNA_PLATEAU, "golden_savanna_plateau", 1, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(SAND_DUNE, "sand_dune", 5, false, BiomeType.DESERT, Type.SANDY);
        addBiome(RED_SAND_DUNE, "red_sand_dune", 3, false, BiomeType.DESERT, Type.SANDY);
        addBiome(CHAPARRAL, "chaparral", 5, false, BiomeType.DESERT, Type.SANDY);

        //ICY
        addBiome(ALPS, "alps", 6, false, BiomeType.ICY, Type.MOUNTAIN, Type.CONIFEROUS, Type.SNOWY);
        addBiome(COLD_FOREST, "cold_forest", 7, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_BIRCH_FOREST, "cold_birch_forest", 7, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_ROOFED_FOREST, "cold_roofed_forest", 6, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_FLOWER_FOREST, "cold_flower_forest", 1, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_MEGA_TAIGA, "cold_mega_taiga", 5, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(COLD_MEGA_SPRUCE_TAIGA, "cold_mega_spruce_taiga", 4, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(COLD_FOREST_HILLS, "cold_forest_hills", 7, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_BIRCH_FOREST_HILLS, "cold_birch_forest_hills", 2, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_ROOFED_FOREST_HILLS, "cold_roofed_forest_hills", 2, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_FLOWER_FOREST_HILLS, "cold_flower_forest_hills", 1, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(COLD_MEGA_TAIGA_HILLS, "cold_mega_taiga_hills", 2, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(COLD_MEGA_SPRUCE_TAIGA_HILLS, "cold_mega_spruce_taiga_hills", 2, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(ICY_TUNDRA, "icy_tundra", 5, false, BiomeType.ICY, Type.COLD, Type.SNOWY);
        addBiome(TUNDRA, "tundra", 6, false, BiomeType.ICY, Type.COLD);
        addBiome(SNOWY_PINE_FOREST, "snowy_pine_forest", 6, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(SNOW_DUNE, "snowdune", 2, false, BiomeType.ICY, Type.COLD, Type.SNOWY);

        BiomeManager.addVillageBiome(Biomes.JUNGLE_EDGE, true);
        BiomeManager.addVillageBiome(Biomes.MESA, true);
        BiomeManager.addVillageBiome(Biomes.EXTREME_HILLS, true);
    }

}
