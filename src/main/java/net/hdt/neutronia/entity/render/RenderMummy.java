package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityMummy;
import net.hdt.neutronia.entity.render.model.ModelMummy;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

@SideOnly(Side.CLIENT)
public class RenderMummy extends RenderLiving<EntityMummy> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/mummy/mummy.png");

    public RenderMummy(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMummy(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMummy entity) {
        return MUMMY_TEXTURE;
    }

}
