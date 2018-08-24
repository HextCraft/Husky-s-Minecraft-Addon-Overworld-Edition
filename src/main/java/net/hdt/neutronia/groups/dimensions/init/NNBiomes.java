package net.hdt.neutronia.groups.dimensions.init;

import net.hdt.neutronia.groups.dimensions.world.biomes.nether.BiomeSoulsandDesert;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;
import static net.minecraftforge.common.BiomeDictionary.Type.*;

@GameRegistry.ObjectHolder(MOD_ID)
public class NNBiomes {
    public static final BiomeSoulsandDesert SOULSAND_DESERT = null;

    public static void init() {
        BiomeDictionary.addTypes(SOULSAND_DESERT, NETHER, HOT, DRY, SANDY);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onRegisterBiomes(RegistryEvent.Register<Biome> event) {
            event.getRegistry().registerAll(
                    new BiomeSoulsandDesert()
            );
        }
    }
}
