package team.hdt.neutronia.client.entity.renderer.layers;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.class_3884;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.resources.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.client.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.base.utils.Utils;
import team.hdt.neutronia.base.utils.registry.DefaultMappedRegistry;
import team.hdt.neutronia.base.utils.registry.Registry;
import team.hdt.neutronia.village.VillagerData;
import team.hdt.neutronia.village.VillagerDataContainer;
import team.hdt.neutronia.village.VillagerProfession;
import team.hdt.neutronia.village.VillagerType;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@SideOnly(Side.CLIENT)
public class VillagerClothingFeatureRenderer<T extends EntityLiving & VillagerDataContainer, M extends ModelBase & class_3884> implements ISelectiveResourceReloadListener, LayerRenderer<T> {
   private static final Int2ObjectMap<ResourceLocation> LEVEL_TO_ID = (Int2ObjectMap) Utils.consume(new Int2ObjectOpenHashMap(), (int2ObjectOpenHashMap_1) -> {
      int2ObjectOpenHashMap_1.put(1, new ResourceLocation("stone"));
      int2ObjectOpenHashMap_1.put(2, new ResourceLocation("iron"));
      int2ObjectOpenHashMap_1.put(3, new ResourceLocation("gold"));
      int2ObjectOpenHashMap_1.put(4, new ResourceLocation("emerald"));
      int2ObjectOpenHashMap_1.put(5, new ResourceLocation("diamond"));
   });
   private final Object2ObjectMap<VillagerType, VillagerResourceMetadata.HatType> villagerTypeToHat = new Object2ObjectOpenHashMap();
   private final Object2ObjectMap<VillagerProfession, VillagerResourceMetadata.HatType> professionToHat = new Object2ObjectOpenHashMap();
   private final IReloadableResourceManager resourceManager;
   private final String entityType;

   public VillagerClothingFeatureRenderer(IReloadableResourceManager reloadableResourceManager_1, String string_1) {
      super();
      this.resourceManager = reloadableResourceManager_1;
      this.entityType = string_1;
      reloadableResourceManager_1.registerReloadListener(this);
   }

   @Override
   public void doRenderLayer(T livingEntity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6, float float_7) {
      VillagerData villagerData_1 = (livingEntity_1).getVillagerData();
      VillagerType villagerType_1 = villagerData_1.getType();
      VillagerProfession villagerProfession_1 = villagerData_1.getProfession();
      VillagerResourceMetadata.HatType villagerResourceMetadata$HatType_1 = this.getHatType(this.villagerTypeToHat, "type", Registry.VILLAGER_TYPE, villagerType_1);
      VillagerResourceMetadata.HatType villagerResourceMetadata$HatType_2 = this.getHatType(this.professionToHat, "profession", Registry.VILLAGER_PROFESSION, villagerProfession_1);
      M entityModel_1 = this.getModel();
      this.bindTexture(this.findTexture("type", Registry.VILLAGER_TYPE.getId(villagerType_1)));
      ((class_3884)entityModel_1).method_17150(villagerResourceMetadata$HatType_2 == VillagerResourceMetadata.HatType.NONE || villagerResourceMetadata$HatType_2 == VillagerResourceMetadata.HatType.PARTIAL && villagerResourceMetadata$HatType_1 != VillagerResourceMetadata.HatType.FULL);
      entityModel_1.render(livingEntity_1, float_1, float_2, float_4, float_5, float_6, float_7);
      ((class_3884)entityModel_1).method_17150(true);
      if (villagerProfession_1 != VillagerProfession.NONE && !livingEntity_1.isChild()) {
         this.bindTexture(this.findTexture("profession", Registry.VILLAGER_PROFESSION.getId(villagerProfession_1)));
         entityModel_1.render(livingEntity_1, float_1, float_2, float_4, float_5, float_6, float_7);
         this.bindTexture(this.findTexture("profession_level", LEVEL_TO_ID.get(MathHelper.clamp(villagerData_1.getLevel(), 1, LEVEL_TO_ID.size()))));
         entityModel_1.render(livingEntity_1, float_1, float_2, float_4, float_5, float_6, float_7);
      }

   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   private ResourceLocation findTexture(String string_1, ResourceLocation identifier_1) {
      return new ResourceLocation(identifier_1.getNamespace(), "textures/entity/" + this.entityType + "/" + string_1 + "/" + identifier_1.getPath() + ".png");
   }

   public <K> VillagerResourceMetadata.HatType getHatType(Object2ObjectMap<K, VillagerResourceMetadata.HatType> object2ObjectMap_1, String string_1, DefaultMappedRegistry<K> defaultMappedRegistry_1, K object_1) {
      return object2ObjectMap_1.computeIfAbsent(object_1, (object_2) -> {
         try {
            IResource resource_1 = this.resourceManager.getResource(this.findTexture(string_1, defaultMappedRegistry_1.getId(object_1)));
            Throwable var6 = null;

            VillagerResourceMetadata.HatType var8;
            try {
               VillagerResourceMetadata villagerResourceMetadata_1 = (VillagerResourceMetadata)resource_1.getMetadata(VillagerResourceMetadata.READER);
               if (villagerResourceMetadata_1 == null) {
                  return VillagerResourceMetadata.HatType.NONE;
               }

               var8 = villagerResourceMetadata_1.getHatType();
            } catch (Throwable var19) {
               var6 = var19;
               throw var19;
            } finally {
               if (resource_1 != null) {
                  if (var6 != null) {
                     try {
                        resource_1.close();
                     } catch (Throwable var18) {
                        var6.addSuppressed(var18);
                     }
                  } else {
                     resource_1.close();
                  }
               }

            }

            return var8;
         } catch (IOException var21) {
            return VillagerResourceMetadata.HatType.NONE;
         }
      });
   }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.professionToHat.clear();
        this.villagerTypeToHat.clear();
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {

    }

}