package team.hdt.neutronia.client.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.VillagerClothingFeatureRenderer;
import net.minecraft.client.render.entity.model.VillagerEntityModel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.client.entity.renderer.layers.VillagerClothingFeatureRenderer;
import team.hdt.neutronia.entity.VillagerEntity;

@SideOnly(Side.CLIENT)
public class VillagerEntityRenderer extends MobEntityRenderer<VillagerEntity, VillagerEntityModel<VillagerEntity>> {
   private static final ResourceLocation VILLAGER_SKIN = new ResourceLocation("textures/entity/villager/villager.png");

   public VillagerEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1, IReloadableResourceManager reloadableResourceManager_1) {
      super(entityRenderDispatcher_1, new VillagerEntityModel(0.0F), 0.5F);
      this.addFeature(new HeadFeatureRenderer(this));
      this.addFeature(new VillagerClothingFeatureRenderer(reloadableResourceManager_1, "villager"));
   }

   protected ResourceLocation getTexture(VillagerEntity villagerEntity_1) {
      return VILLAGER_SKIN;
   }

   protected void method_4149(VillagerEntity villagerEntity_1, float float_1) {
      float float_2 = 0.9375F;
      if (villagerEntity_1.isChild()) {
         float_2 = (float)((double)float_2 * 0.5D);
         this.field_4673 = 0.25F;
      } else {
         this.field_4673 = 0.5F;
      }

      GlStateManager.scale(float_2, float_2, float_2);
   }
}
