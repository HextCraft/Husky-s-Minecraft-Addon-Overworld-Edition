package team.hdt.neutronia.entity.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.entity.EntityLostMiner;

@SideOnly(Side.CLIENT)
public class RenderLostMiner extends RenderBiped<EntityLostMiner> {

    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation("neutronia:textures/entity/miner/lost_miner.png");

    public RenderLostMiner(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Override
    protected boolean setBrightness(EntityLostMiner entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        return true;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLostMiner entity) {
        return MUMMY_TEXTURE;
    }

}
