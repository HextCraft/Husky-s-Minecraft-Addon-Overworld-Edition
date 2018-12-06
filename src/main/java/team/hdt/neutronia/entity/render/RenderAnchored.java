package team.hdt.neutronia.entity.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.entity.EntityAnchored;
import team.hdt.neutronia.entity.render.layer.LayerDiverSuit;

@SideOnly(Side.CLIENT)
public class RenderAnchored extends RenderBiped<EntityAnchored> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation("neutronia:textures/entity/sea/diver/diver.png");

    public RenderAnchored(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
        this.addLayer(new LayerDiverSuit(this));
    }

    @Override
    protected boolean setBrightness(EntityAnchored entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        return true;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAnchored entity) {
        return MUMMY_TEXTURE;
    }

}
