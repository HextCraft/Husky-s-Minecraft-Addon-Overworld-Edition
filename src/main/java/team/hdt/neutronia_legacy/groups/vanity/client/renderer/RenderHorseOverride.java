package team.hdt.neutronia_legacy.groups.vanity.client.renderer;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.groups.vanity.client.model.ModelHorseOverride;

import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderHorseOverride extends RenderLiving<EntityHorse> {
    private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.newHashMap();

    public RenderHorseOverride(RenderManager p_i47205_1_) {
        super(p_i47205_1_, new ModelHorseOverride(), 0.75F);
    }

    protected void preRenderCallback(EntityHorse entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.1F, 1.1F, 1.1F);
    }

    protected ResourceLocation getEntityTexture(EntityHorse entity) {
        String s = entity.getHorseTexture();
        ResourceLocation resourcelocation = LAYERED_LOCATION_CACHE.get(s);
        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s);
            Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(entity.getVariantTexturePaths()));
            LAYERED_LOCATION_CACHE.put(s, resourcelocation);
        }

        return resourcelocation;
    }
}