package team.hdt.neutronia_legacy.groups.dimensions.init;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia_legacy.base.Neutronia;
import team.hdt.neutronia_legacy.groups.dimensions.world.biomes.end.*;
import team.hdt.neutronia_legacy.groups.dimensions.world.providers.EndWorldProvider;

import static net.minecraftforge.common.BiomeDictionary.Type.END;
import static team.hdt.neutronia_legacy.base.lib.LibMisc.MOD_ID;

@GameRegistry.ObjectHolder(MOD_ID)
public class NEBiomes {

    public static final BiomeChorusForest CHORUS_FOREST = null;
    public static final BiomeEndIslands END_ISLANDS = null;
    public static final BiomeOvergrownIslands OVERGROWN_ISLANDS = null;
    public static final BiomeStarlands STARLANDS = null;
    public static final BiomeVoidFalls VOID_FALLS = null;

    public static void init() {
        assert false;
        BiomeDictionary.addTypes(CHORUS_FOREST, END);
        assert false;
        BiomeDictionary.addTypes(END_ISLANDS, END);
        assert false;
        BiomeDictionary.addTypes(OVERGROWN_ISLANDS, END);
        assert false;
        BiomeDictionary.addTypes(STARLANDS, END);
        assert false;
        BiomeDictionary.addTypes(VOID_FALLS, END);
    }

    public static void postInit() {
        DimensionManager.unregisterDimension(1);
        DimensionType end = DimensionType.register("End", "_end", 1, EndWorldProvider.class, false);
        DimensionManager.registerDimension(1, end);
        Neutronia.LOGGER.info("The End has been overridden.");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onRegisterBiomes(RegistryEvent.Register<Biome> event) {
            event.getRegistry().registerAll(
                    new BiomeChorusForest(),
                    new BiomeEndIslands(),
                    new BiomeOvergrownIslands(),
                    new BiomeStarlands(),
                    new BiomeVoidFalls()
            );
        }
    }
}