package team.hdt.neutronia.init;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.text.WordUtils;
import team.hdt.neutronia.base.Reference;
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
    private static final Biome HILLS = new NHillsBiome(new Biome.BiomeProperties("Hills").setBaseHeight(1.15F).setHeightVariation(0.558F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome MOUNTAINS = new NHillsBiome(new Biome.BiomeProperties("Mountains").setBaseHeight(1.5F).setHeightVariation(0.69F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));

    //COOL
    private static final Biome CLIFFS = new CliffsBiome(new Biome.BiomeProperties("Cliffs").setBaseHeight(3.4F).setHeightVariation(0.4F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome PINE_LAND = new PinelandBiome(new Biome.BiomeProperties("Pineland").setBaseHeight(0.98F).setHeightVariation(0.54F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
    private static final Biome PINE_FOREST = new PineForestBiome(new Biome.BiomeProperties("Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));

    //DESERT
    private static final Biome ROCK_LAND = new RocklandBiome(new Biome.BiomeProperties("Rockland").setBaseHeight(0.82F).setHeightVariation(0.48F).setTemperature(0.8F).setRainfall(0.4F));
    private static final Biome GOLDEN_SAVANNA = new GoldenSavannaBiome(new Biome.BiomeProperties("Golden Savanna").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    private static final Biome GOLDEN_SAVANNA_PLATEAU = new GoldenSavannaBiome(new Biome.BiomeProperties("Golden Savanna Plateau").setBaseHeight(Biomes.SAVANNA_PLATEAU.getBaseHeight()).setHeightVariation(Biomes.SAVANNA_PLATEAU.getHeightVariation()).setTemperature(Biomes.SAVANNA_PLATEAU.getDefaultTemperature()).setRainfall(Biomes.SAVANNA_PLATEAU.getRainfall()));
    private static final Biome SAND_DUNE = new SandDuneBiome(new Biome.BiomeProperties("Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
    private static final Biome RED_SAND_DUNE = new RedSandDuneBiome(new Biome.BiomeProperties("Red Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());

    //ICY
    private static final Biome ALPS = new AlpinesBiome(new Biome.BiomeProperties("Alps").setBaseHeight(5F).setHeightVariation(0.8F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
    private static final Biome COLD_FOREST = new ColdForestBiome(BiomeForest.Type.NORMAL, "Cold Forest");
    private static final Biome COLD_BIRCH_FOREST = new ColdForestBiome(BiomeForest.Type.BIRCH, "Cold Birch Forest");
    private static final Biome COLD_ROOFED_FOREST = new ColdForestBiome(BiomeForest.Type.ROOFED, "Cold Roofed Forest");
    private static final Biome COLD_FLOWER_FOREST = new ColdForestBiome(BiomeForest.Type.FLOWER, "Cold Flower Forest");
    private static final Biome COLD_MEGA_TAIGA = new ColdMegaTaigaBiome(BiomeTaiga.Type.MEGA, "Cold Mega Taiga");
    private static final Biome COLD_MEGA_SPRUCE_TAIGA = new ColdMegaTaigaBiome(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga");
    private static final Biome COLD_FOREST_HILLS = new ColdForestHillsBiome(BiomeForest.Type.NORMAL, "Cold Forest Hills");
    private static final Biome COLD_BIRCH_FOREST_HILLS = new ColdForestHillsBiome(BiomeForest.Type.BIRCH, "Cold Birch Forest Hills");
    private static final Biome COLD_ROOFED_FOREST_HILLS = new ColdForestHillsBiome(BiomeForest.Type.ROOFED, "Cold Roofed Forest Hills");
    private static final Biome COLD_FLOWER_FOREST_HILLS = new ColdForestHillsBiome(BiomeForest.Type.FLOWER, "Cold Flower Forest Hills");
    private static final Biome COLD_MEGA_TAIGA_HILLS = new ColdMegaTaigaHillsBiome(BiomeTaiga.Type.MEGA, "Cold Mega Taiga Hills");
    private static final Biome COLD_MEGA_SPRUCE_TAIGA_HILLS = new ColdMegaTaigaHillsBiome(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga Hills");
    private static final Biome ICY_TUNDRA = new IcyTundraBiome(new Biome.BiomeProperties("Icy Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(-1).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()).setSnowEnabled());
    private static final Biome TUNDRA = new TundraBiome(new Biome.BiomeProperties("Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(0.20F).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));
    private static final Biome SNOWY_PINE_FOREST = new SnowyPineForestBiome(new Biome.BiomeProperties("Snowy Pine Forest").setBaseHeight(Biomes.TAIGA.getBaseHeight()).setHeightVariation(Biomes.TAIGA.getHeightVariation()).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
    private static final Biome SNOW_DUNE = new SnowDunesBiome(new Biome.BiomeProperties("Snow Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.FROZEN_OCEAN.getDefaultTemperature()).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));

    public static final TropicalDesertBiome TROPICAL_DESERT = new TropicalDesertBiome(new Biome.BiomeProperties("Tropical Desert").setBaseHeight(0.3F).setHeightVariation(0.3F).setTemperature(0.95F).setRainfall(0.8F));
    public static final SparseJungleBiome SPARSE_JUNGLE = new SparseJungleBiome(new Biome.BiomeProperties("Sparse Jungle").setBaseHeight(0.2F).setHeightVariation(0.4F).setTemperature(0.6F).setRainfall(0.6F));
    public static final TropicalDesertBiome TROPICAL_DESERT_HILLS = new TropicalDesertBiome(new Biome.BiomeProperties("Tropical Desert Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.95F).setWaterColor(0x0093C9).setRainfall(0.8F));
    public static final TropicalMesaBiome TROPICAL_MESA = new TropicalMesaBiome(false, false, false, new Biome.BiomeProperties("Tropical Mesa").setTemperature(2.0F).setRainfall(0.0F));
    public static final TropicalMesaBiome TROPICAL_MESA_HILLS = new TropicalMesaBiome(true, false, false, new Biome.BiomeProperties("Tropical Mesa Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F));
    public static final TropicalMesaBiome TROPICAL_MESA_BRYCE = new TropicalMesaBiome(false, true, false, new Biome.BiomeProperties("Tropical Mesa (Bryce)").setTemperature(2.0F).setRainfall(0.0F));
    public static final TropicalMesaBiome TROPICAL_MESA_F = new TropicalMesaBiome(false, false, false, new Biome.BiomeProperties("Tropical Mesa F").setTemperature(2.0F).setRainfall(0.0F));
    public static final TropicalMesaBiome TROPICAL_MESA_HILLS_F = new TropicalMesaBiome(true, false, false, new Biome.BiomeProperties("Tropical Mesa Hills F").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(2.0F).setRainfall(0.0F));
    public static final TropicalMesaBiome TROPICAL_MESA_BRYCE_F = new TropicalMesaBiome(false, true, false, new Biome.BiomeProperties("Tropical Mesa F (Bryce)").setTemperature(2.0F).setRainfall(0.0F));
    public static final RoofedSwampBiome ROOFED_SWAMP = new RoofedSwampBiome(new Biome.BiomeProperties("Roofed Swamp").setBaseHeight(-0.1F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.4F).setWaterColor(14745518));
    public static final WoodlandBiome WOODLAND = new WoodlandBiome(false, WoodlandBiome.EnumType.NORMAL, new Biome.BiomeProperties("Woodland").setBaseHeight(0.2F).setHeightVariation(0.2F));
    public static final WoodlandBiome WOODLAND_HILLS = new WoodlandBiome(true, WoodlandBiome.EnumType.NORMAL, new Biome.BiomeProperties("Woodland Hills").setBaseHeight(0.3F).setHeightVariation(0.3F));
    public static final WoodlandBiome COLD_WOODLAND = new WoodlandBiome(false, WoodlandBiome.EnumType.COLD, new Biome.BiomeProperties("Cold Woodland").setBaseHeight(0.2F).setHeightVariation(0.2F).setSnowEnabled());
    public static final WoodlandBiome COLD_WOODLAND_HILLS = new WoodlandBiome(true, WoodlandBiome.EnumType.COLD, new Biome.BiomeProperties("Cold Woodland Hills").setBaseHeight(0.3F).setHeightVariation(0.3F).setSnowEnabled());
    public static final MarshBiome MARSH = new MarshBiome(new Biome.BiomeProperties("Marsh").setBaseHeight(-0.2F).setHeightVariation(0.01F).setTemperature(0.8F).setRainfall(0.4F));
    public static final GlacierBiome GLACIER = new GlacierBiome(new Biome.BiomeProperties("Glacier").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0.2F).setWaterColor(0x193D77).setSnowEnabled());
    public static final WoodlandDesertBiome WOODLAND_DESERT = new WoodlandDesertBiome(new Biome.BiomeProperties("Woodland Desert").setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final WoodlandDesertBiome WOODLAND_DESERT_HILLS = new WoodlandDesertBiome(new Biome.BiomeProperties("Woodland Desert Hills").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(1.8F).setRainfall(0.0F).setRainDisabled());
    public static final PaintedDesertBiome PAINTED_DESERT = new PaintedDesertBiome(new Biome.BiomeProperties("Painted Desert").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(2.0F).setRainfall(0.0F).setRainDisabled());
    public static final ArcticBiome ARCTIC = new ArcticBiome(new Biome.BiomeProperties("Arctic").setTemperature(0.2F).setWaterColor(0x193D77).setSnowEnabled());

    public static void init() {
        registerBiome(BLUE_MESA, "blue_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(BLUE_MESA_ROCK, "blue_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(BLUE_MESA_CLEAR_ROCK, "blue_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(MUTATED_BLUE_MESA, "mutated_blue_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(MUTATED_BLUE_MESA_ROCK, "mutated_blue_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(MUTATED_BLUE_MESA_CLEAR_ROCK, "mutated_blue_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);

        registerBiome(WHITE_MESA, "white_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(WHITE_MESA_ROCK, "white_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(WHITE_MESA_CLEAR_ROCK, "white_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(MUTATED_WHITE_MESA, "mutated_white_mesa", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(MUTATED_WHITE_MESA_ROCK, "mutated_white_mesa_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);
        registerBiome(MUTATED_WHITE_MESA_CLEAR_ROCK, "mutated_white_mesa_clear_rock", 10, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY);

        registerBiome(RED_DESERT, "red_desert", 10, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        registerBiome(BLACK_DESERT, "black_desert", 20, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        registerBiome(WHITE_DESERT, "white_desert", 20, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        registerBiome(MARSHLANDS, "marshlands", 20, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP);

        //WARM
        registerBiome(MOUNTAINS, "mountains", 4, BiomeManager.BiomeType.WARM, true, BiomeDictionary.Type.HILLS);
        registerBiome(HILLS, "hills", 6, BiomeManager.BiomeType.WARM, true, BiomeDictionary.Type.HILLS);

        //COOL
        registerBiome(CLIFFS, "cliffs", 7, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.CONIFEROUS);
        registerBiome(PINE_LAND, "pine_land", 6, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.CONIFEROUS);
        registerBiome(PINE_FOREST, "pine_forest", 5, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.CONIFEROUS);

        //DESERT
        registerBiome(ROCK_LAND, "rock_land", 5, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.SAVANNA);
        registerBiome(GOLDEN_SAVANNA, "golden_savanna", 5, BiomeManager.BiomeType.DESERT, true, BiomeDictionary.Type.SAVANNA);
        registerBiome(GOLDEN_SAVANNA_PLATEAU, "golden_savanna_plateau", 1, BiomeManager.BiomeType.DESERT, true, BiomeDictionary.Type.SAVANNA);
        registerBiome(SAND_DUNE, "sand_dune", 5, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.SANDY);
        registerBiome(RED_SAND_DUNE, "red_sand_dune", 3, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.SANDY);

        //ICY
        registerBiome(ALPS, "alps", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_FOREST, "cold_forest", 7, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_BIRCH_FOREST, "cold_birch_forest", 7, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_ROOFED_FOREST, "cold_roofed_forest", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_FLOWER_FOREST, "cold_flower_forest", 1, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_MEGA_TAIGA, "cold_mega_taiga", 5, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_MEGA_SPRUCE_TAIGA, "cold_mega_spruce_taiga", 4, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_FOREST_HILLS, "cold_forest_hills", 7, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_BIRCH_FOREST_HILLS, "cold_birch_forest_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_ROOFED_FOREST_HILLS, "cold_roofed_forest_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_FLOWER_FOREST_HILLS, "cold_flower_forest_hills", 1, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_MEGA_TAIGA_HILLS, "cold_mega_taiga_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        registerBiome(COLD_MEGA_SPRUCE_TAIGA_HILLS, "cold_mega_spruce_taiga_hills", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        registerBiome(ICY_TUNDRA, "icy_tundra", 5, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY);
        registerBiome(TUNDRA, "tundra", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD);
        registerBiome(SNOWY_PINE_FOREST, "snowy_pine_forest", 6, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SNOWY);
        registerBiome(SNOW_DUNE, "snow_dune", 2, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY);

        registerBiome(TROPICAL_DESERT, "tropical_desert", 8, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST);
        registerBiome(TROPICAL_DESERT_HILLS, "tropical_desert_hills", 8, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.RARE);
        registerBiome(SPARSE_JUNGLE, "sparse_jungle", 5, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.FOREST);
        registerBiome(TROPICAL_MESA, "tropical_mesa", 6, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.MESA);
        registerBiome(TROPICAL_MESA_HILLS, "tropical_mesa_hills", 6, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.MESA, BiomeDictionary.Type.HILLS);
        registerBiome(TROPICAL_MESA_BRYCE, "tropical_mesa_bryce", 5, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.MESA, BiomeDictionary.Type.RARE);
        registerBiome(TROPICAL_MESA_F, "tropical_mesa_f", 4, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.MESA, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.RARE);
        registerBiome(TROPICAL_MESA_HILLS_F, "tropical_mesa_hills_f", 4, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.MESA, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.RARE);
        registerBiome(TROPICAL_MESA_BRYCE_F, "tropical_mesa_bryce_f", 3, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.MESA, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.RARE);
        registerBiome(ROOFED_SWAMP, "roofed_swamp", 12, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE);
        registerBiome(WOODLAND, "woodland", 11, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.FOREST);
        registerBiome(WOODLAND_HILLS, "woodland_hills", 11, BiomeManager.BiomeType.WARM, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS);
        registerBiome(COLD_WOODLAND, "cold_woodland", 12, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.CONIFEROUS);
        registerBiome(COLD_WOODLAND_HILLS, "cold_woodland_hills", 12, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.HILLS);
        registerBiome(MARSH, "marsh", 11, BiomeManager.BiomeType.COOL, false, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET);
        registerBiome(GLACIER, "glacier", 7, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.RARE);
        registerBiome(WOODLAND_DESERT, "woodland_desert", 9, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SAVANNA);
        registerBiome(WOODLAND_DESERT_HILLS, "woodland_desert_hills", 9, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.HILLS);
        registerBiome(PAINTED_DESERT, "painted_desert", 5, BiomeManager.BiomeType.DESERT, false, BiomeDictionary.Type.DRY, BiomeDictionary.Type.RARE, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY);
        registerBiome(ARCTIC, "arctic", 50, BiomeManager.BiomeType.ICY, false, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RARE, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.SNOWY);

        BiomeManager.addVillageBiome(Biomes.JUNGLE_EDGE, true);
        BiomeManager.addVillageBiome(Biomes.MESA, true);
        BiomeManager.addVillageBiome(Biomes.EXTREME_HILLS, true);
    }

    private static void registerBiome(Biome biome, String name, int weight, BiomeManager.BiomeType biomeType, boolean villageBiome, BiomeDictionary.Type... types) {
        biome.setRegistryName(MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        Reference.LOGGER.info(String.format("Biome: %s is now registered", WordUtils.capitalizeFully(name.replace("_", ""))));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addStrongholdBiome(biome);
        BiomeManager.addVillageBiome(biome, villageBiome);
        Reference.LOGGER.info(String.format("Biome: %s is now added to the spawn biome's", name));
        Reference.LOGGER.info(String.format("Biome: %s has a %d percent chance to spawn", name, new BiomeManager.BiomeEntry(biome, weight).itemWeight));
    }

}
