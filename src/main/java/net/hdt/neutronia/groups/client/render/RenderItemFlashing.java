package net.hdt.neutronia.groups.client.render;

import net.hdt.neutronia.groups.client.features.ItemsFlashBeforeExpiring;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderItemFlashing extends RenderEntityItem {

    public RenderItemFlashing(RenderManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getMinecraft().getRenderItem());
    }

    public static IRenderFactory factory() {
        return manager -> new RenderItemFlashing(manager);
    }

    @Override
    public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
        int timeLeft = entity.lifespan - entity.getAge();
        if (timeLeft < ItemsFlashBeforeExpiring.minTime && timeLeft % 20 < 8 && entity.ticksExisted > 1)
            return;

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
