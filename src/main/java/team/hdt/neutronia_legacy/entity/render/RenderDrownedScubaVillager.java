package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.entity.EntityDrownedScubaVillager;
import team.hdt.neutronia_legacy.entity.render.model.ModelDrownedScubaVillager;

import static team.hdt.neutronia_legacy.base.util.Reference.MOD_ID;

@SideOnly(Side.CLIENT)
public class RenderDrownedScubaVillager extends RenderLivingBase<EntityDrownedScubaVillager> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/villagers/professions/drowned_scuba_diver.png");

    public RenderDrownedScubaVillager(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDrownedScubaVillager(), 0.5F);
    }

    @Override
    public ModelBase getMainModel() {
        return super.getMainModel();
    }


    @Override
    public void doRender(EntityDrownedScubaVillager entity, double x, double y, double z, float entityYaw, float partialTicks) {
        /*GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1F);
        GlStateManager.disableAlpha();
        GlStateManager.popMatrix();*/
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDrownedScubaVillager entity) {
        return MUMMY_TEXTURE;
    }

}
