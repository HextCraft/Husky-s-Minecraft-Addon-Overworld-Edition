package team.hdt.neutronia.village;

public class VillagerData {
   private final VillagerType type;
   private final VillagerProfession profession;
   private final int level;

   public VillagerData(VillagerType villagerType_1, VillagerProfession villagerProfession_1, int int_1) {
      this.type = villagerType_1;
      this.profession = villagerProfession_1;
      this.level = Math.max(1, int_1);
   }

   public VillagerType getType() {
      return this.type;
   }

   public VillagerProfession getProfession() {
      return this.profession;
   }

   public int getLevel() {
      return this.level;
   }

   public VillagerData withType(VillagerType villagerType_1) {
      return new VillagerData(villagerType_1, this.profession, this.level);
   }

   public VillagerData withProfession(VillagerProfession villagerProfession_1) {
      return new VillagerData(this.type, villagerProfession_1, this.level);
   }

   public VillagerData withLevel(int int_1) {
      return new VillagerData(this.type, this.profession, int_1);
   }

}
