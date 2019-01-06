package team.hdt.neutronia.base.utils.registry;

import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hdt.neutronia.base.utils.IntIterable;
import team.hdt.neutronia.village.VillagerProfession;
import team.hdt.neutronia.village.VillagerType;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class Registry<T> implements IntIterable<T> {
   protected static final Logger LOGGER = LogManager.getLogger();
   private static final Map<ResourceLocation, Supplier<?>> field_11140 = Maps.newLinkedHashMap();
   public static final ModifiableRegistry<ModifiableRegistry<?>> REGISTRIES = new IdRegistry<>();
   public static final DefaultMappedRegistry VILLAGER_TYPE = method_10224("villager_type", new DefaultMappedRegistry<>("plains"), () -> VillagerType.PLAINS);
   public static final DefaultMappedRegistry VILLAGER_PROFESSION = method_10224("villager_profession", new DefaultMappedRegistry<>("none"), () -> VillagerProfession.NONE);

   private static <T> void method_10227(String string_1, Supplier<T> supplier_1) {
      field_11140.put(new ResourceLocation(string_1), supplier_1);
   }

   private static <T, R extends ModifiableRegistry<T>> R method_10224(String string_1, R modifiableRegistry_1, Supplier<T> supplier_1) {
      method_10227(string_1, supplier_1);
      registerRegistry(string_1, modifiableRegistry_1);
      return modifiableRegistry_1;
   }

   private static <T> Registry<T> registerRegistry(String string_1, ModifiableRegistry<T> modifiableRegistry_1) {
      REGISTRIES.register(new ResourceLocation(string_1), modifiableRegistry_1);
      return modifiableRegistry_1;
   }

   @Nullable
   public abstract ResourceLocation getId(T var1);

   public abstract int getRawId(@Nullable T var1);

   @Nullable
   public abstract T get(@Nullable ResourceLocation var1);

   public abstract Set<ResourceLocation> keys();

   @Nullable
   public abstract T getRandom(Random var1);

   public Stream<T> stream() {
      return StreamSupport.stream(this.spliterator(), false);
   }

   public abstract boolean contains(ResourceLocation var1);

   public static <T> T register(Registry<? super T> registry_1, String string_1, T object_1) {
      return register(registry_1, new ResourceLocation(string_1), object_1);
   }

   public static <T> T register(Registry<? super T> registry_1, ResourceLocation identifier_1, T object_1) {
      return (T) ((ModifiableRegistry)registry_1).register(identifier_1, object_1);
   }

   public static <T> T set(Registry<? super T> registry_1, int int_1, String string_1, T object_1) {
      return (T) ((ModifiableRegistry)registry_1).set(int_1, new ResourceLocation(string_1), object_1);
   }

   static {
      field_11140.entrySet().forEach((map$Entry_1) -> {
         if (((Supplier)map$Entry_1.getValue()).get() == null) {
            LOGGER.error("Unable to bootstrap registry '{}'", map$Entry_1.getKey());
         }

      });
      REGISTRIES.forEach((modifiableRegistry_1) -> {
         if (modifiableRegistry_1.isEmpty()) {
            LOGGER.error("Registry '{}' was empty after loading", REGISTRIES.getId(modifiableRegistry_1));
            /*if (SharedConstants.isDevelopment) {
               throw new IllegalStateException("Registry: '" + REGISTRIES.getId(modifiableRegistry_1) + "' is empty, not allowed, fix me!");
            }*/
         }

         if (modifiableRegistry_1 instanceof DefaultMappedRegistry) {
             ResourceLocation identifier_1 = ((DefaultMappedRegistry)modifiableRegistry_1).getDefaultId();
             Validate.notNull(modifiableRegistry_1.get(identifier_1), "Missing default of DefaultedMappedRegistry: " + identifier_1, new Object[0]);
         }

      });
   }
}
