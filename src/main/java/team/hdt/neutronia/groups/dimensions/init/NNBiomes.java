package team.hdt.neutronia.groups.dimensions.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia.groups.dimensions.world.biomes.nether.BiomeSoulsandDesert;

import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static team.hdt.neutronia.base.lib.LibMisc.MOD_ID;

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
