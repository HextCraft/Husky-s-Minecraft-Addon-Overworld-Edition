package net.thegaminghuskymc.mcaddon.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.thegaminghuskymc.mcaddon.entity.EntityAtmosCrow;
import net.thegaminghuskymc.mcaddon.entity.EntitySeaTurtle;
import net.thegaminghuskymc.mcaddon.entity.render.model.ModelAtmosCrow;
import net.thegaminghuskymc.mcaddon.entity.render.model.ModelSeaTurtle;
import net.thegaminghuskymc.mcaddon.util.Reference;

public class RenderSeaTurtle extends RenderLiving<EntitySeaTurtle> {

    public static final ResourceLocation SCORP_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entity/ocean_creatures/sea_turtle.png");

    public RenderSeaTurtle(RenderManager manager)
    {
        super(manager, new ModelSeaTurtle(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySeaTurtle entity)
    {
        return  SCORP_TEXTURE;
    }

    @Override
    protected void applyRotations(EntitySeaTurtle entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
