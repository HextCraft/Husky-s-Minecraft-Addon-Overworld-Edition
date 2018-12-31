package team.hdt.neutronia.init;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import team.hdt.neutronia.world.biomes.*;

import static team.hdt.neutronia.base.Reference.MOD_ID;

public class NBiomes {

    /**
     * TODO: Marshlands, Peat Bogs, Snowy Mega Taiga, Alpines, Mountain Ranges, Freshwater Swamp Forests
     **/

    public static final Biome BLUE_MESA = new BlueMesaBiome(false, false, (new Biome.BiomeProperties("Blue Mesa")).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome BLUE_MESA_ROCK = new BlueMesaBiome(false, true, (new Biome.BiomeProperties("Blue Mesa Plateau F")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome BLUE_MESA_CLEAR_ROCK = new BlueMesaBiome(false, false, (new Biome.BiomeProperties("Blue Mesa Plateau")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome MUTATED_BLUE_MESA = new BlueMesaBiome(true, false, (new Biome.BiomeProperties("Blue Mesa (Bryce)")).setBaseBiome("blue_mesa").setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome MUTATED_BLUE_MESA_ROCK = new BlueMesaBiome(false, true, (new Biome.BiomeProperties("Blue Mesa Plateau F M")).setBaseBiome("blue_mesa_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome MUTATED_BLUE_MESA_CLEAR_ROCK = new BlueMesaBiome(false, false, (new Biome.BiomeProperties("Blue Mesa Plateau M")).setBaseBiome("blue_mesa_clear_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome WHITE_MESA = new SilverMesaBiome(false, false, (new Biome.BiomeProperties("Silver Mesa")).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome WHITE_MESA_ROCK = new SilverMesaBiome(false, true, (new Biome.BiomeProperties("Silver Mesa Plateau F")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome WHITE_MESA_CLEAR_ROCK = new SilverMesaBiome(false, false, (new Biome.BiomeProperties("Silver Mesa Plateau")).setBaseHeight(1.5F).setHeightVariation(0.025F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome MUTATED_WHITE_MESA = new SilverMesaBiome(true, false, (new Biome.BiomeProperties("Silver Mesa (Bryce)")).setBaseBiome("silver_mesa").setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome MUTATED_WHITE_MESA_ROCK = new SilverMesaBiome(false, true, (new Biome.BiomeProperties("Silver Mesa Plateau F M")).setBaseBiome("silver_mesa_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome MUTATED_WHITE_MESA_CLEAR_ROCK = new SilverMesaBiome(false, false, (new Biome.BiomeProperties("Silver Mesa Plateau M")).setBaseBiome("silver_mesa_clear_rock").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final Biome RED_DESERT = new RedDesertBiome();
    public static final Biome BLACK_DESERT = new BlackDesertBiome();
    public static final Biome WHITE_DESERT = new WhiteDesertBiome();
    public static final Biome MARSHLANDS = new MarshlandsBiome();

    //WARM
    private static final Biome HILLS = new BiomeNHills(new Biome.BiomeProperties("Hills").setBaseHeight(1.15F).setHeightVariation(0.558F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome MOUNTAINS = new BiomeNHills(new Biome.BiomeProperties("Mountains").setBaseHeight(1.5F).setHeightVariation(0.69F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));

    //COOL
    private static final Biome CLIFFS = new BiomeCliffs(new Biome.BiomeProperties("Cliffs").setBaseHeight(3.4F).setHeightVariation(0.4F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome PINE_LAND = new BiomePineland(new Biome.BiomeProperties("Pineland").setBaseHeight(0.98F).setHeightVariation(0.54F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
    private static final Biome PINE_FOREST = new BiomePineForest(new Biome.BiomeProperties("Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));

    //DESERT
    private static final Biome ROCK_LAND = new BiomeRockland(new Biome.BiomeProperties("Rockland").setBaseHeight(0.82F).setHeightVariation(0.48F).setTemperature(0.8F).setRainfall(0.4F));
    private static final Biome GOLDEN_SAVANNA = new BiomeGoldenSavanna(new Biome.BiomeProperties("Golden Savanna").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    private static final Biome GOLDEN_SAVANNA_PLATEAU = new BiomeGoldenSavanna(new Biome.BiomeProperties("Golden Savanna Plateau").setBaseHeight(Biomes.SAVANNA_PLATEAU.getBaseHeight()).setHeightVariation(Biomes.SAVANNA_PLATEAU.getHeightVariation()).setTemperature(Biomes.SAVANNA_PLATEAU.getDefaultTemperature()).setRainfall(Biomes.SAVANNA_PLATEAU.getRainfall()));
    private static final Biome SAND_DUNE = new BiomeSandDune(new Biome.BiomeProperties("Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
    private static final Biome RED_SAND_DUNE = new BiomeRedSandDune(new Biome.BiomeProperties("Red Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());

    //ICY
    private static final Biome ALPS = new BiomeAlps(new Biome.BiomeProperties("Alps").setBaseHeight(5F).setHeightVariation(0.8F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
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
    private static final Biome ICY_TUNDRA = new BiomeIcyTundra(new Biome.BiomeProperties("Icy Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(-1).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()).setSnowEnabled());
    private static final Biome TUNDRA = new BiomeTundra(new Biome.BiomeProperties("Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(0.20F).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));
    private static final Biome SNOWY_PINE_FOREST = new BiomeSnowyPineForest(new Biome.BiomeProperties("Snowy Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
    private static final Biome SNOW_DUNE = new BiomeSnowDune(new Biome.BiomeProperties("Snow Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.FROZEN_OCEAN.getDefaultTemperature()).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));

    public static void init() {
        addBiome(BLUE_MESA, "blue_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(BLUE_MESA_ROCK, "blue_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(BLUE_MESA_CLEAR_ROCK, "blue_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(MUTATED_BLUE_MESA, "mutated_blue_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(MUTATED_BLUE_MESA_ROCK, "mutated_blue_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(MUTATED_BLUE_MESA_CLEAR_ROCK, "mutated_blue_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);

        addBiome(WHITE_MESA, "white_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(WHITE_MESA_ROCK, "white_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(WHITE_MESA_CLEAR_ROCK, "white_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(MUTATED_WHITE_MESA, "mutated_white_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(MUTATED_WHITE_MESA_ROCK, "mutated_white_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        addBiome(MUTATED_WHITE_MESA_CLEAR_ROCK, "mutated_white_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);

        addBiome(RED_DESERT, "red_desert", 10, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        addBiome(BLACK_DESERT, "black_desert", 20, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        addBiome(WHITE_DESERT, "white_desert", 20, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        addBiome(MARSHLANDS, "marshlands", 20, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP);

        //WARM
        addBiome(MOUNTAINS, "mountains", 4, BiomeManager.BiomeType.WARM, true, BiomeDictionary.Type.HILLS);
        addBiome(HILLS, "hills", 6, BiomeManager.BiomeType.WARM, true, BiomeDictionary.Type.HILLS);

        //COOL
        addBiome(CLIFFS, "cliffs", 7, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.CONIFEROUS);
        addBiome(PINE_LAND, "pine_land", 6, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.CONIFEROUS);
        addBiome(PINE_FOREST, "pine_forest", 5, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.CONIFEROUS);

        //DESERT
        addBiome(ROCK_LAND, "rock_land", 5, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.SAVANNA);
        addBiome(GOLDEN_SAVANNA, "golden_savanna", 5, BiomeManager.BiomeType.DESERT, true, BiomeDictionary.Type.SAVANNA);
        addBiome(GOLDEN_SAVANNA_PLATEAU, "golden_savanna_plateau", 1, BiomeManager.BiomeType.DESERT, true, BiomeDictionary.Type.SAVANNA);
        addBiome(SAND_DUNE, "sand_dune", 5, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.SANDY);
        addBiome(RED_SAND_DUNE, "red_sand_dune", 3, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.SANDY);

        //ICY
        addBiome(ALPS, "alps", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_FOREST, "cold_forest", 7, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_BIRCH_FOREST, "cold_birch_forest", 7, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_ROOFED_FOREST, "cold_roofed_forest", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_FLOWER_FOREST, "cold_flower_forest", 1, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_MEGA_TAIGA, "cold_mega_taiga", 5, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_MEGA_SPRUCE_TAIGA, "cold_mega_spruce_taiga", 4, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_FOREST_HILLS, "cold_forest_hills", 7, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_BIRCH_FOREST_HILLS, "cold_birch_forest_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_ROOFED_FOREST_HILLS, "cold_roofed_forest_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_FLOWER_FOREST_HILLS, "cold_flower_forest_hills", 1, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_MEGA_TAIGA_HILLS, "cold_mega_taiga_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        addBiome(COLD_MEGA_SPRUCE_TAIGA_HILLS, "cold_mega_spruce_taiga_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        addBiome(ICY_TUNDRA, "icy_tundra", 5, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY);
        addBiome(TUNDRA, "tundra", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD);
        addBiome(SNOWY_PINE_FOREST, "snowy_pine_forest", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        addBiome(SNOW_DUNE, "snowdune", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY);

        BiomeManager.addVillageBiome(Biomes.JUNGLE_EDGE, true);
        BiomeManager.addVillageBiome(Biomes.MESA, true);
        BiomeManager.addVillageBiome(Biomes.EXTREME_HILLS, true);
    }

    private static void addBiome(Biome biome, String name, int weight, BiomeManager.BiomeType biomeType, boolean villageBiome, BiomeDictionary.Type... types) {
        biome.setRegistryName(MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println(String.format("Biome: %s is now registered", name));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addStrongholdBiome(biome);
        BiomeManager.addVillageBiome(biome, villageBiome);
        System.out.println(String.format("Biome: %s is now added to the spawn biome's", name));
        System.out.println(String.format("Biome: %s has a %d percent chance to spawn", name, new BiomeManager.BiomeEntry(biome, weight).itemWeight));
    }

}
