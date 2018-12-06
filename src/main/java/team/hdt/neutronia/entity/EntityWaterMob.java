//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hdt.neutronia.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public abstract class EntityWaterMob extends EntityCreature implements IAnimals {

    protected double field_211517_W;
    protected boolean field_205013_W;

    protected EntityWaterMob(World p_i48565_2_) {
        super(p_i48565_2_);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public boolean func_205019_a(World p_205019_1_) {
        return !p_205019_1_.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()).isEmpty();
    }

    public int getTalkInterval() {
        return 120;
    }

    protected boolean canDespawn() {
        return true;
    }

    protected int getExperiencePoints(EntityPlayer p_getExperiencePoints_1_) {
        return 1 + this.world.rand.nextInt(3);
    }

    protected void func_209207_l(int p_209207_1_) {
        if (this.isEntityAlive() && !this.isInWaterOrBubbleColumn()) {
            this.setAir(p_209207_1_ - 1);
            if (this.getAir() == -20) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.setAir(300);
        }

    }

    public void onEntityUpdate() {
        int lvt_1_1_ = this.getAir();
        super.onEntityUpdate();
        this.func_209207_l(lvt_1_1_);
    }

    public boolean isPushedByWater() {
        return false;
    }

    public boolean canBeLeashedTo(EntityPlayer p_canBeLeashedTo_1_) {
        return false;
    }

    public boolean isInWater() {
        return this.inWater;
    }

    private boolean isInRain() {
        Vec3i vec3i = new Vec3i(posX, posY, posZ);
        BlockPos.PooledMutableBlockPos lvt_1_1_ = BlockPos.PooledMutableBlockPos.retain(vec3i);
        Throwable var2 = null;

        boolean var3;
        try {
            var3 = this.world.isRainingAt(lvt_1_1_) || this.world.isRainingAt(lvt_1_1_.setPos(this.posX, this.posY + (double) this.height, this.posZ));
        } catch (Throwable var12) {
            var2 = var12;
            throw var12;
        } finally {
            if (var2 != null) {
                try {
                    lvt_1_1_.release();
                } catch (Throwable var11) {
                    var2.addSuppressed(var11);
                }
            } else {
                lvt_1_1_.release();
            }

        }

        return var3;
    }

    /*private boolean isInBubbleColumn() {
        return this.world.getBlockState(new BlockPos(this)).getBlock() == Blocks.BUBBLE_COLUMN;
    }*/

    public boolean isWet() {
        return this.isInWater() || this.isInRain();
    }

    public boolean isInWaterRainOrBubbleColumn() {
        return this.isInWater() || this.isInRain()/* || this.isInBubbleColumn()*/;
    }

    protected SoundEvent getHighspeedSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }

    public boolean isInWaterOrBubbleColumn() {
        return this.isInWater()/* || this.isInBubbleColumn()*/;
    }

    public boolean func_204231_K() {
        return this.field_205013_W && this.isInWater();
    }

    private void func_205011_p() {
        this.handleWaterMovement();
    }

    /*public void func_205343_av() {
        if (this.func_203007_ba()) {
            this.func_204711_a(this.isSprinting() && this.isInWater() && !this.isRiding());
        } else {
            this.func_204711_a(this.isSprinting() && this.func_204231_K() && !this.isRiding());
        }

    }

    public boolean handleWaterMovement() {
        if (this.getRidingEntity() instanceof EntityBoat) {
            this.inWater = false;
        } else if (this.func_210500_b(FluidTags.WATER)) {
            if (!this.inWater && !this.firstUpdate) {
                this.doWaterSplashEffect();
            }

            this.fallDistance = 0.0F;
            this.inWater = true;
            this.extinguish();
        } else {
            this.inWater = false;
        }

        return this.inWater;
    }

    private void func_205012_q() {
        this.field_205013_W = this.isInFluid(FluidTags.WATER);
    }*/

    protected void doWaterSplashEffect() {
        Entity lvt_1_1_ = this.isBeingRidden() && this.getControllingPassenger() != null ? this.getControllingPassenger() : this;
        float lvt_2_1_ = lvt_1_1_ == this ? 0.2F : 0.9F;
        float lvt_3_1_ = MathHelper.sqrt(lvt_1_1_.motionX * lvt_1_1_.motionX * 0.20000000298023224D + lvt_1_1_.motionY * lvt_1_1_.motionY + lvt_1_1_.motionZ * lvt_1_1_.motionZ * 0.20000000298023224D) * lvt_2_1_;
        if (lvt_3_1_ > 1.0F) {
            lvt_3_1_ = 1.0F;
        }

        if ((double) lvt_3_1_ < 0.25D) {
            this.playSound(this.getSplashSound(), lvt_3_1_, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
        } else {
            this.playSound(this.getHighspeedSplashSound(), lvt_3_1_, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
        }

        float lvt_4_1_ = (float) MathHelper.floor(this.getEntityBoundingBox().minY);

        int lvt_5_2_;
        float lvt_6_2_;
        float lvt_7_2_;
        for (lvt_5_2_ = 0; (float) lvt_5_2_ < 1.0F + this.width * 20.0F; ++lvt_5_2_) {
            lvt_6_2_ = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
            lvt_7_2_ = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
            this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (double) lvt_6_2_, (double) (lvt_4_1_ + 1.0F), this.posZ + (double) lvt_7_2_, this.motionX, this.motionY - (double) (this.rand.nextFloat() * 0.2F), this.motionZ);
        }

        for (lvt_5_2_ = 0; (float) lvt_5_2_ < 1.0F + this.width * 20.0F; ++lvt_5_2_) {
            lvt_6_2_ = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
            lvt_7_2_ = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
            this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + (double) lvt_6_2_, (double) (lvt_4_1_ + 1.0F), this.posZ + (double) lvt_7_2_, this.motionX, this.motionY, this.motionZ);
        }

    }

}
