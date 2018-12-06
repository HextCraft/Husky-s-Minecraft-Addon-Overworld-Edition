package team.hdt.neutronia.entity.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia.base.lib.LibMisc;
import team.hdt.neutronia.entity.EntityOwl;
import team.hdt.neutronia.entity.render.model.ModelOwl;

public class RenderOwl extends RenderLiving<EntityOwl> {

    private static final ResourceLocation[] textures = new ResourceLocation[7];

    static {
        textures[0] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/owl/black_owl.png");
        textures[1] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/owl/brown_owl.png");
        textures[2] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/owl/evil_owl.png");
        textures[3] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/owl/gray_owl.png");
        textures[4] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/owl/hell_owl.png");
        textures[5] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/owl/ice_owl.png");
        textures[6] = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/owl/white_owl.png");
    }

    public RenderOwl(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelOwl(), 0.3f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityOwl entity) {
        return textures[entity.getFamiliarSkin()];
    }

    @Override
    protected void preRenderCallback(EntityOwl entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        if (entitylivingbaseIn.isChild()) {
            GlStateManager.scale(0.4d, 0.4d, 0.4d);
        } else {
            GlStateManager.scale(0.6d, 0.6d, 0.6d);
        }
    }

}
