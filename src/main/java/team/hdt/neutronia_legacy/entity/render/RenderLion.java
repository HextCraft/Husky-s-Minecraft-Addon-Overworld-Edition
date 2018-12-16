package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.base.util.Reference;
import team.hdt.neutronia_legacy.entity.EntityLion;
import team.hdt.neutronia_legacy.entity.render.model.ModelLion;

public class RenderLion extends RenderLiving<EntityLion> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lion.png");

    public RenderLion(RenderManager manager) {
        super(manager, new ModelLion(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLion entity) {
        return TEXTURE;
    }

}