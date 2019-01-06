package team.hdt.neutronia.base.utils;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.math.MathHelper;

public class Int2ObjectBiMap<K> implements IntIterable<K> {
   private static final Object empty = null;
   private K[] values;
   private int[] ids;
   private K[] idToValues;
   private int nextId;
   private int size;

   public Int2ObjectBiMap(int int_1) {
      int_1 = (int)((float)int_1 / 0.8F);
      this.values = (K[]) new Object[int_1];
      this.ids = new int[int_1];
      this.idToValues = (K[]) new Object[int_1];
   }

   public int getId(@Nullable K object_1) {
      return this.getIdFromIndex(this.findIndex(object_1, this.getIdealIndex(object_1)));
   }

   @Nullable
   public K getInt(int int_1) {
      return int_1 >= 0 && int_1 < this.idToValues.length ? this.idToValues[int_1] : null;
   }

   private int getIdFromIndex(int int_1) {
      return int_1 == -1 ? -1 : this.ids[int_1];
   }

   public int add(K object_1) {
      int int_1 = this.nextId();
      this.put(object_1, int_1);
      return int_1;
   }

   private int nextId() {
      while(this.nextId < this.idToValues.length && this.idToValues[this.nextId] != null) {
         ++this.nextId;
      }

      return this.nextId;
   }

   private void resize(int int_1) {
      K[] objects_1 = this.values;
      int[] ints_1 = this.ids;
      this.values = (K[]) new Object[int_1];
      this.ids = new int[int_1];
      this.idToValues = (K[]) new Object[int_1];
      this.nextId = 0;
      this.size = 0;

      for(int int_2 = 0; int_2 < objects_1.length; ++int_2) {
         if (objects_1[int_2] != null) {
            this.put(objects_1[int_2], ints_1[int_2]);
         }
      }

   }

   public void put(K object_1, int int_1) {
      int int_2 = Math.max(int_1, this.size + 1);
      int int_3;
      if ((float)int_2 >= (float)this.values.length * 0.8F) {
         for(int_3 = this.values.length << 1; int_3 < int_1; int_3 <<= 1) {
         }

         this.resize(int_3);
      }

      int_3 = this.findFree(this.getIdealIndex(object_1));
      this.values[int_3] = object_1;
      this.ids[int_3] = int_1;
      this.idToValues[int_1] = object_1;
      ++this.size;
      if (int_1 == this.nextId) {
         ++this.nextId;
      }

   }

   private int getIdealIndex(@Nullable K object_1) {
      return (MathHelper.abs(System.identityHashCode(object_1)) & Integer.MAX_VALUE) % this.values.length;
   }

   private int findIndex(@Nullable K object_1, int int_1) {
      int int_3;
      for(int_3 = int_1; int_3 < this.values.length; ++int_3) {
         if (this.values[int_3] == object_1) {
            return int_3;
         }

         if (this.values[int_3] == empty) {
            return -1;
         }
      }

      for(int_3 = 0; int_3 < int_1; ++int_3) {
         if (this.values[int_3] == object_1) {
            return int_3;
         }

         if (this.values[int_3] == empty) {
            return -1;
         }
      }

      return -1;
   }

   private int findFree(int int_1) {
      int int_3;
      for(int_3 = int_1; int_3 < this.values.length; ++int_3) {
         if (this.values[int_3] == empty) {
            return int_3;
         }
      }

      for(int_3 = 0; int_3 < int_1; ++int_3) {
         if (this.values[int_3] == empty) {
            return int_3;
         }
      }

      throw new RuntimeException("Overflowed :(");
   }

   public Iterator<K> iterator() {
      return Iterators.filter(Iterators.forArray(this.idToValues), Predicates.notNull());
   }

   public void clear() {
      Arrays.fill(this.values, (Object)null);
      Arrays.fill(this.idToValues, (Object)null);
      this.nextId = 0;
      this.size = 0;
   }

   public int size() {
      return this.size;
   }
}
