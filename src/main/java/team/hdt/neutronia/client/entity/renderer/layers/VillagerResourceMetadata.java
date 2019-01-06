package team.hdt.neutronia.client.entity.renderer.layers;

import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class VillagerResourceMetadata implements IMetadataSection {
   public static final VillagerResourceMetadataReader READER = new VillagerResourceMetadataReader();
   private final VillagerResourceMetadata.HatType hatType;

   public VillagerResourceMetadata(VillagerResourceMetadata.HatType villagerResourceMetadata$HatType_1) {
      this.hatType = villagerResourceMetadata$HatType_1;
   }

   public VillagerResourceMetadata.HatType getHatType() {
      return this.hatType;
   }

   @SideOnly(Side.CLIENT)
   public enum HatType {
      NONE("none"),
      PARTIAL("partial"),
      FULL("full");

      private static final Map<String, VillagerResourceMetadata.HatType> byName = Arrays.stream(values()).collect(Collectors.toMap(VillagerResourceMetadata.HatType::getName, (villagerResourceMetadata$HatType_1) -> villagerResourceMetadata$HatType_1));
      private final String name;

      HatType(String string_1) {
         this.name = string_1;
      }

      public String getName() {
         return this.name;
      }

      public static VillagerResourceMetadata.HatType from(String string_1) {
         return byName.getOrDefault(string_1, NONE);
      }
   }
}
