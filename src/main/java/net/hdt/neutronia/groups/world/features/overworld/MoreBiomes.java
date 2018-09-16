package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.groups.world.biomes.BiomeGoldenSavanna;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MoreBiomes extends Component {

    private static final Biome GOLDEN_SAVANNA = new BiomeGoldenSavanna(new Biome.BiomeProperties("Golden Savanna").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
    private static final Biome GOLDEN_SAVANNA_PLATEAU = new BiomeGoldenSavanna(new Biome.BiomeProperties("Golden Savanna Plateau").setBaseHeight(Biomes.SAVANNA_PLATEAU.getBaseHeight()).setHeightVariation(Biomes.SAVANNA_PLATEAU.getHeightVariation()).setTemperature(Biomes.SAVANNA_PLATEAU.getDefaultTemperature()).setRainfall(Biomes.SAVANNA_PLATEAU.getRainfall()));

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        addBiome(GOLDEN_SAVANNA, "golden_savanna", 5, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.SAVANNA);
        addBiome(GOLDEN_SAVANNA_PLATEAU, "golden_savanna_plateau", 1, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.SAVANNA);
    }

    private static void addBiome(Biome biome, String name, int weight, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {
        biome.setRegistryName(Reference.MOD_ID, name);
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
