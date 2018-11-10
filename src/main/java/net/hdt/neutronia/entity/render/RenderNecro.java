package net.hdt.neutronia.entity.render;

import net.hdt.neutronia.entity.EntityNecroVillager;
import net.hdt.neutronia.entity.render.model.ModelNecro;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

@SideOnly(Side.CLIENT)
public class RenderNecro extends RenderLiving<EntityNecroVillager> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/villagers/professions/necro.png");

    public RenderNecro(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelNecro(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNecroVillager entity) {
        return MUMMY_TEXTURE;
    }

}
