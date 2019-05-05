package team.abnormal.neutronia.init;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import team.abnormal.neutronia.base.NeutroniaRevamped;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.entity.EntityCod;
import team.abnormal.neutronia.entity.illager.EntityPillager;

public class NEntitys {
    public static void init() {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "pillager"), EntityPillager.class, prefix("Pillager"), 1, NeutroniaRevamped.instance, 100, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "cod"), EntityCod.class, prefix("Cod"), 2, NeutroniaRevamped.instance, 80, 3, true);

        EntitySpawnPlacementRegistry.setPlacementType(EntityCod.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntityRegistry.addSpawn(EntityCod.class, 10, 2, 3, EnumCreatureType.WATER_CREATURE, Biomes.RIVER);
    }

    private static String prefix(String path) {
        return Reference.MOD_ID + "." + path;
    }
}
