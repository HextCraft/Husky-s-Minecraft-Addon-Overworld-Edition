package team.abnormal.neutronia.properties;

import net.minecraft.util.IStringSerializable;

public enum BambooLeaves implements IStringSerializable {
   NONE("none"),
   SMALL("small"),
   LARGE("large");

   private final String name;

   BambooLeaves(String name) {
      this.name = name;
   }

   @Override
   public String getName() {
      return this.name;
   }

}
