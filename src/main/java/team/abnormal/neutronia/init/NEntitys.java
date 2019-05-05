package team.abnormal.neutronia.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import team.abnormal.neutronia.base.NeutroniaRevamped;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.entity.illager.EntityPillager;

public class NEntitys {
    public static void registerEntities() {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "pillager"), EntityPillager.class, prefix("Pillager"), 1, NeutroniaRevamped.instance, 100, 3, true);
    }

    private static String prefix(String path) {
        return Reference.MOD_ID + "." + path;
    }
}
