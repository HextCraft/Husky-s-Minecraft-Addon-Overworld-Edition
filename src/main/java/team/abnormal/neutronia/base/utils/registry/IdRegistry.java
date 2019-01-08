package team.abnormal.neutronia.base.utils.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.abnormal.neutronia.base.utils.Int2ObjectBiMap;

import javax.annotation.Nullable;
import java.util.*;

public class IdRegistry<T> extends ModifiableRegistry<T> {
   protected static final Logger ID_LOGGER = LogManager.getLogger();
   protected final Int2ObjectBiMap<T> idStore = new Int2ObjectBiMap<>(256);
   protected final BiMap<ResourceLocation, T> objectMap = HashBiMap.create();
   protected Object[] randomValueArray;
   private int nextId;

   public <V extends T> V set(int int_1, ResourceLocation identifier_1, V object_1) {
      this.idStore.put(object_1, int_1);
      Validate.notNull(identifier_1);
      Validate.notNull(object_1);
      this.randomValueArray = null;
      if (this.objectMap.containsKey(identifier_1)) {
         ID_LOGGER.debug("Adding duplicate key '{}' to registry", identifier_1);
      }

      this.objectMap.put(identifier_1, object_1);
      if (this.nextId <= int_1) {
         this.nextId = int_1 + 1;
      }

      return object_1;
   }

   public <V extends T> V register(ResourceLocation identifier_1, V object_1) {
      return this.set(this.nextId, identifier_1, object_1);
   }

   @Nullable
   public ResourceLocation getId(T object_1) {
      return this.objectMap.inverse().get(object_1);
   }

   public int getRawId(@Nullable T object_1) {
      return this.idStore.getId(object_1);
   }

   @Nullable
   public T getInt(int int_1) {
      return this.idStore.getInt(int_1);
   }

   public Iterator<T> iterator() {
      return this.idStore.iterator();
   }

   @Nullable
   public T get(@Nullable ResourceLocation identifier_1) {
      return this.objectMap.get(identifier_1);
   }

   public Set<ResourceLocation> keys() {
      return Collections.unmodifiableSet(this.objectMap.keySet());
   }

   public boolean isEmpty() {
      return this.objectMap.isEmpty();
   }

   @Nullable
   public T getRandom(Random random_1) {
      if (this.randomValueArray == null) {
         Collection<?> collection_1 = this.objectMap.values();
         if (collection_1.isEmpty()) {
            return null;
         }

         this.randomValueArray = collection_1.toArray(new Object[0]);
      }

      return (T) this.randomValueArray[random_1.nextInt(this.randomValueArray.length)];
   }

   public boolean contains(ResourceLocation identifier_1) {
      return this.objectMap.containsKey(identifier_1);
   }
}
