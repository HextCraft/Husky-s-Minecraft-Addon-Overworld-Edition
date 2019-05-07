package team.abnormal.neutronia.client;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.neutronia.client.render.RenderCod;
import team.abnormal.neutronia.client.render.RenderFox;
import team.abnormal.neutronia.client.render.RenderPillager;
import team.abnormal.neutronia.client.render.RenderSalmon;
import team.abnormal.neutronia.entity.illager.EntityPillager;
import team.abnormal.neutronia.entity.passive.EntityCod;
import team.abnormal.neutronia.entity.passive.EntityFox;
import team.abnormal.neutronia.entity.passive.EntitySalmon;

@SideOnly(Side.CLIENT)
public class NEntityRender {
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPillager.class, RenderPillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCod.class, RenderCod::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySalmon.class, RenderSalmon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, RenderFox::new);
    }
}
