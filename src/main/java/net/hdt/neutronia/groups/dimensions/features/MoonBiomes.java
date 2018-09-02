package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.world.biomes.moon.BiomeMoonMain;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

public class MoonBiomes extends Component {

    public static final Biome MOON_MAIN = new BiomeMoonMain();

    private static void addBiome(Biome biome, String name) {
        biome.setRegistryName(MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println(String.format("Moon Biome: %s is now registered", name));
        BiomeDictionary.addTypes(biome, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.MAGICAL);
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 10));
        BiomeManager.removeSpawnBiome(biome);
        BiomeManager.removeStrongholdBiome(biome);
        BiomeManager.removeVillageBiome(biome);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        addBiome(MOON_MAIN, "moon");
    }

    @Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}

}
