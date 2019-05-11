package team.abnormal.neutronia.init;

import com.google.common.collect.Lists;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import team.abnormal.neutronia.base.NeutroniaRevamped;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.entity.illager.EntityPillager;
import team.abnormal.neutronia.entity.passive.EntityCod;
import team.abnormal.neutronia.entity.passive.EntityFox;
import team.abnormal.neutronia.entity.passive.EntityHyena;
import team.abnormal.neutronia.entity.passive.EntitySalmon;

import java.util.List;
import java.util.Set;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class NEntitys {
    public static void init() {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "pillager"), EntityPillager.class, prefix("Pillager"), 1, NeutroniaRevamped.instance, 100, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "cod"), EntityCod.class, prefix("Cod"), 2, NeutroniaRevamped.instance, 80, 3, true, 12691306, 15058059);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "salmon"), EntitySalmon.class, prefix("Salmon"), 3, NeutroniaRevamped.instance, 80, 3, true, 10489616, 951412);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "fox"), EntityFox.class, prefix("Fox"), 4, NeutroniaRevamped.instance, 90, 3, true, 14005919, 13396256);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "hyena"), EntityHyena.class, prefix("Hyena"), 5, NeutroniaRevamped.instance, 90, 3, true,0x4f453d,0x64453d);

        EntitySpawnPlacementRegistry.setPlacementType(EntityCod.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntitySalmon.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntityRegistry.addSpawn(EntityCod.class, 10, 2, 3, EnumCreatureType.WATER_CREATURE, Biomes.RIVER,Biomes.OCEAN,Biomes.DEEP_OCEAN);
        EntityRegistry.addSpawn(EntitySalmon.class, 10, 2, 3, EnumCreatureType.WATER_CREATURE, Biomes.RIVER,Biomes.OCEAN,Biomes.DEEP_OCEAN);

        List<Biome> savanna_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(SAVANNA) && !types.contains(WASTELAND) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty() && !biome.getSpawnableList(EnumCreatureType.CREATURE).contains(savanna_biomes)) {
                savanna_biomes.add(biome);
            }
        }

        EntityRegistry.addSpawn(EntityHyena.class, 5, 3, 6, EnumCreatureType.CREATURE, savanna_biomes.toArray(new Biome[savanna_biomes.size()]));
    }

    private static String prefix(String path) {
        return Reference.MOD_ID + "." + path;
    }
}
