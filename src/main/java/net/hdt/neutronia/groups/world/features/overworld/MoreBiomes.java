package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.groups.world.biomes.*;
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

import static net.minecraft.world.biome.Biome.BiomeProperties;

public class MoreBiomes extends Component {

    private static final Biome GOLDEN_SAVANNA = new BiomeGoldenSavanna(new BiomeProperties("Golden Savanna").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    private static final Biome GOLDEN_SAVANNA_PLATEAU = new BiomeGoldenSavanna(new BiomeProperties("Golden Savanna Plateau").setBaseHeight(Biomes.SAVANNA_PLATEAU.getBaseHeight()).setHeightVariation(Biomes.SAVANNA_PLATEAU.getHeightVariation()).setTemperature(Biomes.SAVANNA_PLATEAU.getDefaultTemperature()).setRainfall(Biomes.SAVANNA_PLATEAU.getRainfall()));
    private static final Biome SAND_DUNE = new BiomeSandDune(new BiomeProperties("Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
    private static final Biome RED_SAND_DUNE = new BiomeRedSandDune(new BiomeProperties("Red Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
    private static final Biome CHAPARRAL = new BiomeChaparral();

    public static Biome hills = new BiomeNHills(new BiomeProperties("Hills").setBaseHeight(1.15F).setHeightVariation(0.558F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    ///public static Biome hills = new BiomeNHills(new BiomeProperties("Hills").setBaseHeight(1.44F).setHeightVariation(0.64F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    public static Biome mountains = new BiomeNHills(new BiomeProperties("Mountains").setBaseHeight(1.5F).setHeightVariation(0.69F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));


    //COOL
    public static Biome cliffs = new BiomeCliffs(new BiomeProperties("Cliffs").setBaseHeight(3.4F).setHeightVariation(0.4F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    public static Biome pineland = new BiomePineland(new BiomeProperties("Pineland").setBaseHeight(0.98F).setHeightVariation(0.54F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
    public static Biome pineforest = new BiomePineForest(new BiomeProperties("Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));

    //DESERT
    public static Biome scrubland = new BiomeScrubland(new BiomeProperties("Scrubland").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    public static Biome sandyscrubland = new BiomeSandyScrubland(new BiomeProperties("Sandy Scrubland").setBaseHeight(0.123F).setHeightVariation(0.044F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(0.9F));
    public static Biome lushdesert = new BiomeLushDesert(new BiomeProperties("Lush Desert").setBaseHeight(0.123F).setHeightVariation(0.044F).setTemperature(Biomes.JUNGLE.getDefaultTemperature()).setRainfall(0.9F));
    public static Biome sahel = new BiomeSahel(new BiomeProperties("Sahel").setBaseHeight(0.125F).setHeightVariation(0.046F).setTemperature(0.95F).setRainfall(0.9F));
    public static Biome rockland = new BiomeRockland(new BiomeProperties("Rockland").setBaseHeight(0.82F).setHeightVariation(0.48F).setTemperature(0.8F).setRainfall(0.4F));
    public static Biome serengeti = new BiomeSerengeti(new BiomeProperties("Serengeti").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));

    //ICY
    public static Biome alps = new BiomeAlps(new BiomeProperties("Alps").setBaseHeight(5F).setHeightVariation(0.8F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    public static Biome coldforest = new BiomeColdForest(BiomeForest.Type.NORMAL, "Cold Forest");
    public static Biome coldbirchforest = new BiomeColdForest(BiomeForest.Type.BIRCH, "Cold Birch Forest");
    public static Biome coldroofedforest = new BiomeColdForest(BiomeForest.Type.ROOFED, "Cold Roofed Forest");
    public static Biome coldflowerforest = new BiomeColdForest(BiomeForest.Type.FLOWER, "Cold Flower Forest");
    public static Biome coldmegataiga = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA, "Cold Mega Taiga");
    public static Biome coldmegasprucetaiga = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga");
    public static Biome coldforesthills = new BiomeColdForestHills(BiomeForest.Type.NORMAL, "Cold Forest Hills");
    public static Biome coldbirchforesthills = new BiomeColdForestHills(BiomeForest.Type.BIRCH, "Cold Birch Forest Hills");
    public static Biome coldroofedforesthills = new BiomeColdForestHills(BiomeForest.Type.ROOFED, "Cold Roofed Forest Hills");
    public static Biome coldflowerforesthills = new BiomeColdForestHills(BiomeForest.Type.FLOWER, "Cold Flower Forest Hills");
    public static Biome coldmegataigahills = new BiomeColdMegaTaigaHills(BiomeTaiga.Type.MEGA, "Cold Mega Taiga Hills");
    public static Biome coldmegasprucetaigahills = new BiomeColdMegaTaigaHills(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga Hills");
    public static Biome icytundra = new BiomeIcyTundra(new BiomeProperties("Icy Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(-1).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()).setSnowEnabled());
    public static Biome tundra = new BiomeTundra(new BiomeProperties("Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(0.20F).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));
    public static Biome snowypineforest = new BiomeSnowyPineForest(new BiomeProperties("Snowy Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
    public static Biome snowdune = new BiomeSnowDune(new BiomeProperties("Snow Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.FROZEN_OCEAN.getDefaultTemperature()).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));

    private static void addBiome(Biome biome, String name, int weight, boolean hasVillage, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {
        biome.setRegistryName(Reference.MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println(String.format("Biome: %s is now registered", biome.getBiomeName()));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addStrongholdBiome(biome);
        BiomeManager.addVillageBiome(biome, hasVillage);

        System.out.println(String.format("Biome: %s is now added to the spawn biome's", biome.getBiomeName()));
        System.out.println(String.format("Biome: %s has a %d percent chance to spawn", biome.getBiomeName(), weight));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        addBiome(GOLDEN_SAVANNA, "golden_savanna", 5, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(GOLDEN_SAVANNA_PLATEAU, "golden_savanna_plateau", 1, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(SAND_DUNE, "sand_dune", 5, false, BiomeType.DESERT, Type.SANDY);
        addBiome(RED_SAND_DUNE, "red_sand_dune", 3, false, BiomeType.DESERT, Type.SANDY);
        addBiome(CHAPARRAL, "chaparral", 5, false, BiomeType.DESERT, Type.SANDY);

        //WARM
        addBiome(mountains, "mountains", 4, true, BiomeType.WARM, Type.HILLS);
        addBiome(hills, "hills", 6, true, BiomeType.WARM, Type.HILLS);

        //COOL
        addBiome(cliffs, "cliffs", 7, false, BiomeType.COOL, Type.MOUNTAIN, Type.CONIFEROUS);
        addBiome(pineland, "pineland", 6, false, BiomeType.COOL, Type.HILLS, Type.CONIFEROUS);
        addBiome(pineforest, "pine_forest", 5, false, BiomeType.COOL, Type.CONIFEROUS);

        //DESERT
        addBiome(scrubland, "scrubland", 5, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(serengeti, "serengeti", 5, true, BiomeType.DESERT, Type.SAVANNA);
        addBiome(sandyscrubland, "sandy_scrubland", 3, true, BiomeType.DESERT, Type.SANDY);
        addBiome(lushdesert, "lush_desert", 4, true, BiomeType.DESERT, Type.SAVANNA, Type.LUSH);
        addBiome(sahel, "sahel", 6, true, BiomeType.DESERT, Type.SANDY);
        addBiome(rockland, "rockland", 5, false, BiomeType.DESERT, Type.SAVANNA);

        //ICY
        addBiome(alps, "alps", 6, false, BiomeType.ICY, Type.MOUNTAIN, Type.CONIFEROUS, Type.SNOWY);
        addBiome(coldforest, "cold_forest", 7, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldbirchforest, "cold_birch_forest", 7, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldroofedforest, "cold_roofed_forest", 6, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldflowerforest, "cold_flower_forest", 1, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldmegataiga, "cold_mega_taiga", 5, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(coldmegasprucetaiga, "cold_mega_spruce_taiga", 4, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(coldforesthills, "cold_forest_hills", 7, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldbirchforesthills, "cold_birch_forest_hills", 2, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldroofedforesthills, "cold_roofed_forest_hills", 2, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldflowerforesthills, "cold_flower_forest_hills", 1, false, BiomeType.ICY, Type.FOREST, Type.SNOWY);
        addBiome(coldmegataigahills, "cold_mega_taiga_hills", 2, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(coldmegasprucetaigahills, "cold_mega_spruce_taiga_hills", 2, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        ;
        addBiome(icytundra, "icy_tundra", 5, false, BiomeType.ICY, Type.COLD, Type.SNOWY);
        addBiome(tundra, "tundra", 6, false, BiomeType.ICY, Type.COLD);
        addBiome(snowypineforest, "snowy_pine_forest", 6, false, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
        addBiome(snowdune, "snowdune", 2, false, BiomeType.ICY, Type.COLD, Type.SNOWY);

        BiomeManager.addVillageBiome(Biomes.JUNGLE_EDGE, true);
        BiomeManager.addVillageBiome(Biomes.MESA, true);
        BiomeManager.addVillageBiome(Biomes.EXTREME_HILLS, true);
    }

}
