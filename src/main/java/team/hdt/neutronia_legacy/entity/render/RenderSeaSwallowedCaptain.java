package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.base.util.Reference;
import team.hdt.neutronia_legacy.entity.EntitySeaSwallowedCaptain;
import team.hdt.neutronia_legacy.entity.render.model.ModelSeaSwallowedCaptain;

public class RenderSeaSwallowedCaptain extends RenderLiving<EntitySeaSwallowedCaptain> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/sea_swallowed_captain.png");

    public RenderSeaSwallowedCaptain(RenderManager manager) {
        super(manager, new ModelSeaSwallowedCaptain(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySeaSwallowedCaptain entity) {
        return SCORP_TEXTURE;
    }
}
