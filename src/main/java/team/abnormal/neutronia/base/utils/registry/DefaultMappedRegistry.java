package team.abnormal.neutronia.base.utils.registry;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class DefaultMappedRegistry<T> extends IdRegistry<T> {
   private final ResourceLocation defaultId;
   private T defaultValue;

   public DefaultMappedRegistry(String string_1) {
      this.defaultId = new ResourceLocation(string_1);
   }

   public <V extends T> V set(int int_1, ResourceLocation identifier_1, V object_1) {
      if (this.defaultId.equals(identifier_1)) {
         this.defaultValue = object_1;
      }

      return super.set(int_1, identifier_1, object_1);
   }

   public int getRawId(@Nullable T object_1) {
      int int_1 = super.getRawId(object_1);
      return int_1 == -1 ? super.getRawId(this.defaultValue) : int_1;
   }

   @Nonnull
   public ResourceLocation getId(T object_1) {
       ResourceLocation identifier_1 = super.getId(object_1);
      return identifier_1 == null ? this.defaultId : identifier_1;
   }

   @Nonnull
   public T get(@Nullable ResourceLocation identifier_1) {
      T object_1 = super.get(identifier_1);
      return object_1 == null ? this.defaultValue : object_1;
   }

   @Nonnull
   public T getInt(int int_1) {
      T object_1 = super.getInt(int_1);
      return object_1 == null ? this.defaultValue : object_1;
   }

   @Nonnull
   public T getRandom(Random random_1) {
      T object_1 = super.getRandom(random_1);
      return object_1 == null ? this.defaultValue : object_1;
   }

   public ResourceLocation getDefaultId() {
      return this.defaultId;
   }
}
