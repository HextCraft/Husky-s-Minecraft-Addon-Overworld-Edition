package team.hdt.neutronia.entity.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.entity.EntityDrowned;
import team.hdt.neutronia.entity.render.layer.LayerDrownedOuter;

@SideOnly(Side.CLIENT)
public class RenderDrowned extends RenderBiped<EntityDrowned> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation("neutronia:textures/entity/sea/drowned/drowned.png");

    public RenderDrowned(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
        this.addLayer(new LayerDrownedOuter(this));
    }

    @Override
    protected boolean setBrightness(EntityDrowned entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        return true;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDrowned entity) {
        return MUMMY_TEXTURE;
    }

}
