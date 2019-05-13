/*
package team.abnormal.neutronia.village;

import com.google.common.collect.Maps;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import team.abnormal.neutronia.base.utils.Utils;
import team.abnormal.neutronia.base.utils.registry.Registry;

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
      hashMap_1.put(Biomes.MESA, DESERT);
      hashMap_1.put(Biomes.MESA_CLEAR_ROCK, DESERT);
      hashMap_1.put(Biomes.DESERT, DESERT);
      hashMap_1.put(Biomes.DESERT_HILLS, DESERT);
      hashMap_1.put(Biomes.MUTATED_DESERT, DESERT);
      hashMap_1.put(Biomes.MUTATED_MESA, DESERT);
      hashMap_1.put(Biomes.MUTATED_MESA_CLEAR_ROCK, DESERT);
      hashMap_1.put(Biomes.MUTATED_MESA_ROCK, DESERT);
      hashMap_1.put(Biomes.MESA_ROCK, DESERT);
      hashMap_1.put(Biomes.JUNGLE, JUNGLE);
      hashMap_1.put(Biomes.JUNGLE_EDGE, JUNGLE);
      hashMap_1.put(Biomes.JUNGLE_HILLS, JUNGLE);
      hashMap_1.put(Biomes.MUTATED_JUNGLE, JUNGLE);
      hashMap_1.put(Biomes.MUTATED_JUNGLE_EDGE, JUNGLE);
      hashMap_1.put(Biomes.SAVANNA_PLATEAU, SAVANNA);
      hashMap_1.put(Biomes.SAVANNA, SAVANNA);
      hashMap_1.put(Biomes.MUTATED_SAVANNA, SAVANNA);
      hashMap_1.put(Biomes.MUTATED_SAVANNA_ROCK, SAVANNA);
      hashMap_1.put(Biomes.FROZEN_OCEAN, SNOW);
      hashMap_1.put(Biomes.FROZEN_RIVER, SNOW);
      hashMap_1.put(Biomes.MUTATED_ICE_FLATS, SNOW);
      hashMap_1.put(Biomes.COLD_BEACH, SNOW);
      hashMap_1.put(Biomes.ICE_MOUNTAINS, SNOW);
      hashMap_1.put(Biomes.COLD_TAIGA, SNOW);
      hashMap_1.put(Biomes.COLD_TAIGA_HILLS, SNOW);
      hashMap_1.put(Biomes.MUTATED_TAIGA_COLD, SNOW);
      hashMap_1.put(Biomes.ICE_PLAINS, SNOW);
      hashMap_1.put(Biomes.SWAMPLAND, SWAMP);
      hashMap_1.put(Biomes.MUTATED_SWAMPLAND, SWAMP);
      hashMap_1.put(Biomes.MUTATED_REDWOOD_TAIGA, TAIGA);
      hashMap_1.put(Biomes.MUTATED_REDWOOD_TAIGA_HILLS, TAIGA);
      hashMap_1.put(Biomes.REDWOOD_TAIGA, TAIGA);
      hashMap_1.put(Biomes.REDWOOD_TAIGA_HILLS, TAIGA);
      hashMap_1.put(Biomes.MUTATED_EXTREME_HILLS, TAIGA);
      hashMap_1.put(Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, TAIGA);
      hashMap_1.put(Biomes.EXTREME_HILLS_EDGE, TAIGA);
      hashMap_1.put(Biomes.EXTREME_HILLS, TAIGA);
      hashMap_1.put(Biomes.TAIGA, TAIGA);
      hashMap_1.put(Biomes.TAIGA_HILLS, TAIGA);
      hashMap_1.put(Biomes.MUTATED_TAIGA, TAIGA);
      hashMap_1.put(Biomes.EXTREME_HILLS_WITH_TREES, TAIGA);
   });

   static VillagerType create(final String string_1) {
      return (VillagerType) Registry.VILLAGER_TYPE.register(new ResourceLocation(string_1), new VillagerType() {
         public String toString() {
            return string_1;
         }
      });
   }

   static VillagerType forBiome(Biome biome_1) {
      return biomeToType.getOrDefault(biome_1, PLAINS);
   }
}
*/
