package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.base.util.Reference;
import team.hdt.neutronia_legacy.entity.EntityInferno;
import team.hdt.neutronia_legacy.entity.render.layer.LayerInfernoActiveGlow;
import team.hdt.neutronia_legacy.entity.render.model.ModelInferno;
import team.hdt.neutronia_legacy.entity.render.model.ModelInfernoProtect;

public class RenderInferno extends RenderLiving<EntityInferno> {

    public static final ResourceLocation TEXTURE_ACTIVE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/inferno_passive.png");
    public static final ResourceLocation TEXTURE_PASSIVE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/inferno_passive.png");

    public RenderInferno(RenderManager manager) {
        super(manager, new ModelInferno(), 0.5F);
        this.addLayer(new LayerInfernoActiveGlow(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInferno entity) {
        if (entity.isPassive()) return TEXTURE_PASSIVE;
        else return TEXTURE_ACTIVE;
    }

    @Override
    public void doRender(EntityInferno entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isPassive()) {
            this.mainModel = new ModelInfernoProtect();
            this.layerRenderers.clear();
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void applyRotations(EntityInferno entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

}