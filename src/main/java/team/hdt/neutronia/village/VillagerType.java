package team.hdt.neutronia.village;

import com.google.common.collect.Maps;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import team.hdt.neutronia.base.utils.Utils;
import team.hdt.neutronia.base.utils.registry.Registry;

import java.util.Map;

public interface VillagerType {
   VillagerType DESERT = create("desert");
   VillagerType JUNGLE = create("jungle");
   VillagerType PLAINS = create("plains");
   VillagerType SAVANNA = create("savanna");
   VillagerType SNOW = create("snow");
   VillagerType SWAMP = create("swamp");
   VillagerType TAIGA = create("taiga");
   Map<Biome, VillagerType> biomeToType = Utils.consume(Maps.newHashMap(), (hashMap_1) -> {
      hashMap_1.put(Biomes.BADLANDS, DESERT);
      hashMap_1.put(Biomes.BADLANDS_PLATEAU, DESERT);
      hashMap_1.put(Biomes.DESERT, DESERT);
      hashMap_1.put(Biomes.DESERT_HILLS, DESERT);
      hashMap_1.put(Biomes.DESERT_LAKES, DESERT);
      hashMap_1.put(Biomes.ERODED_BADLANDS, DESERT);
      hashMap_1.put(Biomes.MODIFIED_BADLANDS_PLATEAU, DESERT);
      hashMap_1.put(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, DESERT);
      hashMap_1.put(Biomes.WOODED_BADLANDS_PLATEAU, DESERT);
      hashMap_1.put(Biomes.BAMBOO_JUNGLE, JUNGLE);
      hashMap_1.put(Biomes.BAMBOO_JUNGLE_HILLS, JUNGLE);
      hashMap_1.put(Biomes.JUNGLE, JUNGLE);
      hashMap_1.put(Biomes.JUNGLE_EDGE, JUNGLE);
      hashMap_1.put(Biomes.JUNGLE_HILLS, JUNGLE);
      hashMap_1.put(Biomes.MODIFIED_JUNGLE, JUNGLE);
      hashMap_1.put(Biomes.MODIFIED_JUNGLE_EDGE, JUNGLE);
      hashMap_1.put(Biomes.SAVANNA_PLATEAU, SAVANNA);
      hashMap_1.put(Biomes.SAVANNA, SAVANNA);
      hashMap_1.put(Biomes.SHATTERED_SAVANNA, SAVANNA);
      hashMap_1.put(Biomes.SHATTERED_SAVANNA_PLATEAU, SAVANNA);
      hashMap_1.put(Biomes.DEEP_FROZEN_OCEAN, SNOW);
      hashMap_1.put(Biomes.FROZEN_OCEAN, SNOW);
      hashMap_1.put(Biomes.FROZEN_RIVER, SNOW);
      hashMap_1.put(Biomes.ICE_SPIKES, SNOW);
      hashMap_1.put(Biomes.SNOWY_BEACH, SNOW);
      hashMap_1.put(Biomes.SNOWY_MOUNTAINS, SNOW);
      hashMap_1.put(Biomes.SNOWY_TAIGA, SNOW);
      hashMap_1.put(Biomes.SNOWY_TAIGA_HILLS, SNOW);
      hashMap_1.put(Biomes.SNOWY_TAIGA_MOUNTAINS, SNOW);
      hashMap_1.put(Biomes.SNOWY_TUNDRA, SNOW);
      hashMap_1.put(Biomes.SWAMP, SWAMP);
      hashMap_1.put(Biomes.SWAMP_HILLS, SWAMP);
      hashMap_1.put(Biomes.GIANT_SPRUCE_TAIGA, TAIGA);
      hashMap_1.put(Biomes.GIANT_SPRUCE_TAIGA_HILLS, TAIGA);
      hashMap_1.put(Biomes.GIANT_TREE_TAIGA, TAIGA);
      hashMap_1.put(Biomes.GIANT_TREE_TAIGA_HILLS, TAIGA);
      hashMap_1.put(Biomes.GRAVELLY_MOUNTAINS, TAIGA);
      hashMap_1.put(Biomes.MODIFIED_GRAVELLY_MOUNTAINS, TAIGA);
      hashMap_1.put(Biomes.MOUNTAIN_EDGE, TAIGA);
      hashMap_1.put(Biomes.MOUNTAINS, TAIGA);
      hashMap_1.put(Biomes.TAIGA, TAIGA);
      hashMap_1.put(Biomes.TAIGA_HILLS, TAIGA);
      hashMap_1.put(Biomes.TAIGA_MOUNTAINS, TAIGA);
      hashMap_1.put(Biomes.WOODED_MOUNTAINS, TAIGA);
   });

   static VillagerType create(final String string_1) {
      return (VillagerType) Registry.VILLAGER_TYPE.register(new ResourceLocation(string_1), new VillagerType() {
         public String toString() {
            return string_1;
         }
      });
   }

   static VillagerType forBiome(Biome biome_1) {
      return (VillagerType)biomeToType.getOrDefault(biome_1, PLAINS);
   }
}
