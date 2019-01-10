package team.abnormal.neutronia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;

public class class_1361 extends Goal {
   protected MobEntity field_6486;
   protected Entity field_6484;
   protected float field_6482;
   private int field_6483;
   private final float field_6481;
   protected Class<? extends Entity> field_6485;

   public class_1361(MobEntity mobEntity_1, Class<? extends Entity> class_1, float float_1) {
      this(mobEntity_1, class_1, float_1, 0.02F);
   }

   public class_1361(MobEntity mobEntity_1, Class<? extends Entity> class_1, float float_1, float float_2) {
      this.field_6486 = mobEntity_1;
      this.field_6485 = class_1;
      this.field_6482 = float_1;
      this.field_6481 = float_2;
      this.setControlBits(2);
   }

   public boolean canStart() {
      if (this.field_6486.getRand().nextFloat() >= this.field_6481) {
         return false;
      } else {
         if (this.field_6486.getTarget() != null) {
            this.field_6484 = this.field_6486.getTarget();
         }

         if (this.field_6485 == PlayerEntity.class) {
            this.field_6484 = this.field_6486.world.getClosestPlayer(this.field_6486.x, this.field_6486.y, this.field_6486.z, (double)this.field_6482, EntityPredicates.EXCEPT_SPECTATOR.and(EntityPredicates.method_5913(this.field_6486)));
         } else {
            this.field_6484 = this.field_6486.world.getClosestVisibleEntityTo(this.field_6485, this.field_6486.getBoundingBox().expand((double)this.field_6482, 3.0D, (double)this.field_6482), this.field_6486);
         }

         return this.field_6484 != null;
      }
   }

   public boolean shouldContinue() {
      if (!this.field_6484.isValid()) {
         return false;
      } else if (this.field_6486.squaredDistanceTo(this.field_6484) > (double)(this.field_6482 * this.field_6482)) {
         return false;
      } else {
         return this.field_6483 > 0;
      }
   }

   public void start() {
      this.field_6483 = 40 + this.field_6486.getRand().nextInt(40);
   }

   public void onRemove() {
      this.field_6484 = null;
   }

   public void tick() {
      this.field_6486.getLookControl().lookAt(this.field_6484.x, this.field_6484.y + (double)this.field_6484.getEyeHeight(), this.field_6484.z, (float)this.field_6486.method_5986(), (float)this.field_6486.method_5978());
      --this.field_6483;
   }
}
