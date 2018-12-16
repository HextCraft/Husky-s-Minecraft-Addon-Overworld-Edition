package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.base.util.Reference;
import team.hdt.neutronia_legacy.entity.EntitySeaTurtle;
import team.hdt.neutronia_legacy.entity.render.model.ModelSeaTurtle;

public class RenderSeaTurtle extends RenderLiving<EntitySeaTurtle> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/sea/ocean_creatures/sea_turtle.png");

    public RenderSeaTurtle(RenderManager manager) {
        super(manager, new ModelSeaTurtle(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySeaTurtle entity) {
        return SCORP_TEXTURE;
    }

    @Override
    protected void applyRotations(EntitySeaTurtle entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
