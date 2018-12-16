package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.entity.EntityArcticWolf;
import team.hdt.neutronia_legacy.entity.render.model.ModelWolfArtic;

import static team.hdt.neutronia_legacy.base.util.Reference.MOD_ID;

@SideOnly(Side.CLIENT)
public class RenderArcticWolf extends RenderLiving<EntityArcticWolf> {
    //    private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf.png");
//    private static final ResourceLocation TAMED_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
//    private static final ResourceLocation ANRGY_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_angry.png");
    private static final ResourceLocation FOX_TEXTURES = new ResourceLocation(MOD_ID, "textures/entity/arctic_wolf.png");

    public RenderArcticWolf(RenderManager p_i47187_1_) {
        super(p_i47187_1_, new ModelWolfArtic(), 0.5F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityArcticWolf livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityArcticWolf entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isWolfWet()) {
            float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
            GlStateManager.color(f, f, f);
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityArcticWolf entity) {
        /*if (entity.isTamed())
        {
            return TAMED_WOLF_TEXTURES;
        }
        else
        {
            return entity.isAngry() ? ANRGY_WOLF_TEXTURES : WOLF_TEXTURES;
        }*/
        return FOX_TEXTURES;
    }
}