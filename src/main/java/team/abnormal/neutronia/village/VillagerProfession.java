package team.abnormal.neutronia.village;

import net.minecraft.util.ResourceLocation;
import team.abnormal.neutronia.base.utils.registry.Registry;

public interface VillagerProfession {
   VillagerProfession NONE = register("none");
   VillagerProfession ARMORER = register("armorer");
   VillagerProfession BUTCHER = register("butcher");
   VillagerProfession CARTOGRAPHER = register("cartographer");
   VillagerProfession CLERIC = register("cleric");
   VillagerProfession FARMER = register("farmer");
   VillagerProfession FISHERMAN = register("fisherman");
   VillagerProfession FLETCHER = register("fletcher");
   VillagerProfession LEATHERWORKER = register("leatherworker");
   VillagerProfession LIBRARIAN = register("librarian");
   VillagerProfession MASON = register("mason");
   VillagerProfession NITWIT = register("nitwit");
   VillagerProfession SHEPHERD = register("shepherd");
   VillagerProfession TOOLSMITH = register("toolsmith");
   VillagerProfession WEAPONSMITH = register("weaponsmith");
   VillagerProfession DRUID = register("druid");
   VillagerProfession BARD = register("bard");
   VillagerProfession BAKER = register("baker");
   VillagerProfession CARPENTER = register("carpenter");
   VillagerProfession GUARD = register("guard");

   static VillagerProfession register(final String string_1) {
      return Registry.VILLAGER_PROFESSION.register(new ResourceLocation(string_1), new VillagerProfession() {
         public String toString() {
            return string_1;
         }
      });
   }
}
