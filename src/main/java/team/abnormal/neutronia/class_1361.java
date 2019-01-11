/*
package team.abnormal.neutronia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;

public class class_1361 extends EntityAIBase {
   protected EntityMob field_6486;
   protected Entity field_6484;
   protected float field_6482;
   private int field_6483;
   private final float field_6481;
   protected Class<? extends Entity> field_6485;

   public class_1361(EntityMob mobEntity_1, Class<? extends Entity> class_1, float float_1) {
      this(mobEntity_1, class_1, float_1, 0.02F);
   }

   public class_1361(EntityMob mobEntity_1, Class<? extends Entity> class_1, float float_1, float float_2) {
      this.field_6486 = mobEntity_1;
      this.field_6485 = class_1;
      this.field_6482 = float_1;
      this.field_6481 = float_2;
      this.setMutexBits(2);
   }

   public boolean shouldExecute() {
      if (this.field_6486.getRNG().nextFloat() >= this.field_6481) {
         return false;
      } else {
         if (this.field_6486.getAttackTarget() != null) {
            this.field_6484 = this.field_6486.getAttackTarget();
         }

         if (this.field_6485 == EntityPlayer.class) {
            this.field_6484 = this.field_6486.world.getClosestPlayer(this.field_6486.posX, this.field_6486.posY, this.field_6486.posZ, (double)this.field_6482, EntityPredicates.EXCEPT_SPECTATOR.and(EntityPredicates.method_5913(this.field_6486)));
         } else {
            this.field_6484 = this.field_6486.world.getClosestPlayer(this.field_6485, this.field_6486.getEntityBoundingBox().expand((double)this.field_6482, 3.0D, (double)this.field_6482), this.field_6486);
         }

         return this.field_6484 != null;
      }
   }

   public boolean shouldContinueExecuting() {
      if (!this.field_6484.isDead) {
         return false;
      } else if (this.field_6486.getDistanceSq(this.field_6484) > (double)(this.field_6482 * this.field_6482)) {
         return false;
      } else {
         return this.field_6483 > 0;
      }
   }

   public void startExecuting() {
      this.field_6483 = 40 + this.field_6486.getRNG().nextInt(40);
   }

   public void onRemove() {
      this.field_6484 = null;
   }

   public void tick() {
      this.field_6486.getLookHelper().setLookPosition(this.field_6484.posX, this.field_6484.posY + (double)this.field_6484.getEyeHeight(), this.field_6484.posZ, this.field_6486.rotationYaw, this.field_6486.rotationPitch);
      --this.field_6483;
   }
}
*/
