package team.hdt.neutronia.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia.base.util.Reference;
import team.hdt.neutronia.entity.EntityBloodPhantom;
import team.hdt.neutronia.entity.render.model.ModelPhantom;

public class RenderRedPhantom extends RenderLiving<EntityBloodPhantom> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/phantom/red_phantom.png");

    public RenderRedPhantom(RenderManager manager) {
        super(manager, new ModelPhantom(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBloodPhantom entity) {
        return SCORP_TEXTURE;
    }
}
