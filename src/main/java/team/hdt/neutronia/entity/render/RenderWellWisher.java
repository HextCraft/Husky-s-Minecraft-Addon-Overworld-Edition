package team.hdt.neutronia.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia.base.util.Reference;
import team.hdt.neutronia.entity.EntityWellWisher;
import team.hdt.neutronia.entity.render.model.ModelTheWellWisher;

public class RenderWellWisher extends RenderLiving<EntityWellWisher> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/well_wisher.png");

    public RenderWellWisher(RenderManager manager) {
        super(manager, new ModelTheWellWisher(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWellWisher entity) {
        return SCORP_TEXTURE;
    }
}
