package team.hdt.neutronia.client.entity.renderer.layers;

import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import team.hdt.neutronia.client.entity.model.EntityModel;
import team.hdt.neutronia.client.entity.model.class_3882;

public class LayerHeadFeature<T extends EntityLiving, M extends EntityModel<T> & class_3882> implements LayerRenderer<T> {

    @Override
    public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        RenderVillager
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

}