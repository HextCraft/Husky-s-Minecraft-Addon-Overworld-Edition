package team.hdt.neutronia.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia.entity.EntityScorp;
import team.hdt.neutronia.entity.render.model.ModelScorpion;

import static team.hdt.neutronia.base.util.Reference.MOD_ID;

public class RenderScorp extends RenderLiving<EntityScorp> {

    public RenderScorp(RenderManager manager) {
        super(manager, new ModelScorpion(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityScorp entity) {
        return new ResourceLocation(MOD_ID, "textures/entity/scorpion/grey_scorpion.png");
    }

}
