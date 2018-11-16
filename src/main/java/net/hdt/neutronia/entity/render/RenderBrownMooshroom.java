package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.entity.passive.EntityBrownMooshroom;
import net.hdt.neutronia.entity.render.layer.LayerBrownMooshroomMushroom;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBrownMooshroom extends RenderLiving<EntityBrownMooshroom> {

    private static final ResourceLocation MOOSHROOM_TEXTURES = new ResourceLocation(LibMisc.MOD_ID, "textures/entity/brown_mooshroom.png");

    public RenderBrownMooshroom(RenderManager p_i47200_1_) {
        super(p_i47200_1_, new ModelCow(), 0.7F);
        this.addLayer(new LayerBrownMooshroomMushroom(this));
    }

    public ModelCow getMainModel() {
        return (ModelCow)super.getMainModel();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBrownMooshroom entity) {
        return MOOSHROOM_TEXTURES;
    }

}