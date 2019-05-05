package team.abnormal.neutronia.client;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.neutronia.client.render.RenderPillager;
import team.abnormal.neutronia.entity.illager.EntityPillager;

@SideOnly(Side.CLIENT)
public class NEntityRender {
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPillager.class, RenderPillager::new);
    }
}
