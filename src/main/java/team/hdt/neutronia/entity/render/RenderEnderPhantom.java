package team.hdt.neutronia.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia.base.util.Reference;
import team.hdt.neutronia.entity.EntityEnderPhantom;
import team.hdt.neutronia.entity.render.model.ModelPhantom;

public class RenderEnderPhantom extends RenderLiving<EntityEnderPhantom> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/phantom/ender_phantom.png");

    public RenderEnderPhantom(RenderManager manager) {
        super(manager, new ModelPhantom(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityEnderPhantom entity) {
        return SCORP_TEXTURE;
    }
}
