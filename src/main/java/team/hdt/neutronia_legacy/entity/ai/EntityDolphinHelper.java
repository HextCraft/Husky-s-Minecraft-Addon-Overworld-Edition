/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hdt.neutronia_legacy.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.util.math.MathHelper;

public class EntityDolphinHelper extends EntityLookHelper {
    private final int field_205139_h;

    public EntityDolphinHelper(EntityLiving p_i48942_1_, int p_i48942_2_) {
        super(p_i48942_1_);
        this.field_205139_h = p_i48942_2_;
    }

    public void onUpdateLook() {
        if (this.isLooking) {
            this.isLooking = false;
            double lvt_1_1_ = this.posX - this.entity.posX;
            double lvt_3_1_ = this.posY - (this.entity.posY + (double)this.entity.getEyeHeight());
            double lvt_5_1_ = this.posZ - this.entity.posZ;
            double lvt_7_1_ = (double)MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_);
            float lvt_9_1_ = (float)(MathHelper.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F + 20.0F;
            float lvt_10_1_ = (float)(-(MathHelper.atan2(lvt_3_1_, lvt_7_1_) * 57.2957763671875D)) + 10.0F;
            this.entity.rotationPitch = this.updateRotation(this.entity.rotationPitch, lvt_10_1_, this.deltaLookPitch);
            this.entity.rotationYawHead = this.updateRotation(this.entity.rotationYawHead, lvt_9_1_, this.deltaLookYaw);
        } else {
            if (this.entity.getNavigator().noPath()) {
                this.entity.rotationPitch = this.updateRotation(this.entity.rotationPitch, 0.0F, 5.0F);
            }

            this.entity.rotationYawHead = this.updateRotation(this.entity.rotationYawHead, this.entity.renderYawOffset, this.deltaLookYaw);
        }

        float lvt_1_2_ = MathHelper.wrapDegrees(this.entity.rotationYawHead - this.entity.renderYawOffset);
        if (lvt_1_2_ < (float)(-this.field_205139_h)) {
            this.entity.renderYawOffset -= 4.0F;
        } else if (lvt_1_2_ > (float)this.field_205139_h) {
            this.entity.renderYawOffset += 4.0F;
        }

    }
}
*/
