package team.abnormal.neutronia.client;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.neutronia.client.render.*;
import team.abnormal.neutronia.entity.illager.EntityPillager;
import team.abnormal.neutronia.entity.passive.EntityCod;
import team.abnormal.neutronia.entity.passive.EntityFox;
import team.abnormal.neutronia.entity.passive.EntityHyena;
import team.abnormal.neutronia.entity.passive.EntitySalmon;

@SideOnly(Side.CLIENT)
public class NEntityRender {
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPillager.class, RenderPillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCod.class, RenderCod::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySalmon.class, RenderSalmon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, RenderFox::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHyena.class, RenderHyena::new);
    }
}
