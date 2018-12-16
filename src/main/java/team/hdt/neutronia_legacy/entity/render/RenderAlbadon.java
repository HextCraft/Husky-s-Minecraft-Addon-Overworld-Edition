package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.base.util.Reference;
import team.hdt.neutronia_legacy.entity.EntityAlbadon;
import team.hdt.neutronia_legacy.entity.render.model.ModelAlbadon;

public class RenderAlbadon extends RenderLiving<EntityAlbadon> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/albadon.png");

    public RenderAlbadon(RenderManager manager) {
        super(manager, new ModelAlbadon(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAlbadon entity) {
        return SCORP_TEXTURE;
    }
}
