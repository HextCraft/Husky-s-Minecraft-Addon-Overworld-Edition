package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.entity.EntitySeaTurtle;
import net.hdt.neutronia.entity.render.model.ModelSeaTurtle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLion extends RenderLiving<EntitySeaTurtle> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/sea/ocean_creatures/sea_turtle.png");

    public RenderLion(RenderManager manager) {
        super(manager, new ModelSeaTurtle(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySeaTurtle entity) {
        return new ResourceLocation(LibMisc.MOD_ID, ":textures");
    }

}