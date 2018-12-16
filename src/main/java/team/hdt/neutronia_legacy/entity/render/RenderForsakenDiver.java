package team.hdt.neutronia_legacy.entity.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.entity.EntityForsakenDiver;
import team.hdt.neutronia_legacy.entity.render.layer.LayerForsakenDiverHelmet;

@SideOnly(Side.CLIENT)
public class RenderForsakenDiver extends RenderBiped<EntityForsakenDiver> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation("neutronia_legacy:textures/entity/sea/diver/forsaken_diver.png");

    public RenderForsakenDiver(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
        this.addLayer(new LayerForsakenDiverHelmet(this));
    }

    @Override
    protected boolean setBrightness(EntityForsakenDiver entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        return true;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityForsakenDiver entity) {
        return MUMMY_TEXTURE;
    }

}
