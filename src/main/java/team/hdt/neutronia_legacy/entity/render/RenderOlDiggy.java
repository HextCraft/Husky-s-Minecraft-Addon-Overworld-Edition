package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.base.util.Reference;
import team.hdt.neutronia_legacy.entity.EntityOlDiggy;
import team.hdt.neutronia_legacy.entity.render.model.ModelOlDiggy;

public class RenderOlDiggy extends RenderLiving<EntityOlDiggy> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/ol_diggy.png");

    public RenderOlDiggy(RenderManager manager) {
        super(manager, new ModelOlDiggy(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityOlDiggy entity) {
        return SCORP_TEXTURE;
    }
}
