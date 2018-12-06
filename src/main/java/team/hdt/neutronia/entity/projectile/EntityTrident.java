package team.hdt.neutronia.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import team.hdt.neutronia.base.Neutronia;
import team.hdt.neutronia.init.NItems;
import team.hdt.neutronia.init.NSounds;

import javax.annotation.Nullable;
import java.util.Objects;

public class EntityTrident extends EntityArrow {

    private static final DataParameter<Byte> field_203053_g = EntityDataManager.createKey(EntityTrident.class, DataSerializers.BYTE);
    public int field_203052_f;
    private ItemStack field_203054_h = new ItemStack(NItems.trident);
    private boolean field_203051_au;

    public EntityTrident(World world) {
        super(world);
    }

    public EntityTrident(World world, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        super(world, entityLivingBase);
        this.field_203054_h = itemStack.copy();
        this.dataManager.set(field_203053_g, (byte) Neutronia.func_203191_f(itemStack));
    }

    public EntityTrident(World p_i48791_1_, double p_i48791_2_, double p_i48791_4_, double p_i48791_6_) {
        super(p_i48791_1_, p_i48791_4_, p_i48791_6_, p_i48791_2_);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(field_203053_g, (byte) 0);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        if (this.timeInGround > 4) {
            this.field_203051_au = true;
        }

        if ((this.field_203051_au || this.getIsCritical()) && this.shootingEntity != null) {
            int i = this.dataManager.get(field_203053_g);

            if (i > 0 && !this.func_207403_q()) {
                if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED) {
                    this.entityDropItem(this.getArrowStack(), 0.1F);
                }

                this.setDead();
            } else if (i > 0) {
                this.setNoGravity(true);
                Vec3d vec3d = new Vec3d(this.shootingEntity.posX - this.posX, this.shootingEntity.posY + (double) this.shootingEntity.getEyeHeight() - this.posY, this.shootingEntity.posZ - this.posZ);
                this.posY += vec3d.y * 0.015D * (double) i;

                if (this.world.isRemote) {
                    this.lastTickPosY = this.posY;
                }

                vec3d = vec3d.normalize();
                double d0 = 0.05D * (double) i;
                this.motionX += vec3d.x * d0 - this.motionX * 0.05D;
                this.motionY += vec3d.y * d0 - this.motionY * 0.05D;
                this.motionZ += vec3d.z * d0 - this.motionZ * 0.05D;

                if (this.field_203052_f == 0) {
                    this.playSound(NSounds.field_203270_il, 10.0F, 1.0F);
                }

                ++this.field_203052_f;
            }
        }

        super.onUpdate();
    }

    private boolean func_207403_q() {
        if (this.shootingEntity != null && this.shootingEntity.isEntityAlive()) {
            return !(this.shootingEntity instanceof EntityPlayerMP) || !((EntityPlayerMP) this.shootingEntity).isSpectator();
        } else {
            return false;
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return field_203054_h;
    }

    @Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end) {
        return this.field_203051_au ? null : super.findEntityOnPath(start, end);
    }

    protected void onHit(RayTraceResult raytraceResultIn) {
        Entity entity = raytraceResultIn.entityHit;
        float f = 8.0F;

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
            f += EnchantmentHelper.getModifierForCreature(this.field_203054_h, entitylivingbase.getCreatureAttribute());
        }

        DamageSource damagesource = Neutronia.func_203096_a(this, this.shootingEntity == null ? this : this.shootingEntity);
        this.field_203051_au = true;
        SoundEvent soundevent = NSounds.field_203268_ij;

        if (entity.attackEntityFrom(damagesource, f) && entity instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase1 = (EntityLivingBase) entity;

            if (this.shootingEntity instanceof EntityLivingBase) {
                EnchantmentHelper.applyThornEnchantments(entitylivingbase1, this.shootingEntity);
                EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity, entitylivingbase1);
            }

            this.arrowHit(entitylivingbase1);
        }

        this.motionX *= -0.009999999776482582D;
        this.motionY *= -0.10000000149011612D;
        this.motionZ *= -0.009999999776482582D;
        float f1 = 1.0F;

        if (this.world.isThundering() && Neutronia.func_203192_h(this.field_203054_h)) {
            BlockPos blockpos = entity.getPosition();

            if (this.world.canSeeSky(blockpos)) {
                EntityLightningBolt entitylightningbolt = new EntityLightningBolt(this.world, (double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), false);
                entitylightningbolt.isPassenger(Objects.requireNonNull(this.shootingEntity instanceof EntityPlayerMP ? (EntityPlayerMP) this.shootingEntity : null));
                this.world.addWeatherEffect(entitylightningbolt);
                soundevent = NSounds.field_203275_iq;
                f1 = 5.0F;
            }
        }

        this.playSound(soundevent, f1, 1.0F);
    }

    @Override
    protected SoundEvent getSplashSound() {
        return NSounds.field_203268_ij;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return NSounds.field_203269_ik;
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if (this.shootingEntity == null || this.shootingEntity == entityIn) {
            super.onCollideWithPlayer(entityIn);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("Trident", 10)) {
//            this.field_203054_h = ItemStack.func_199557_a(compound.getCompoundTag("Trident"));
        }

        this.field_203051_au = compound.getBoolean("DealtDamage");
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setTag("Trident", this.field_203054_h.writeToNBT(new NBTTagCompound()));
        compound.setBoolean("DealtDamage", this.field_203051_au);
    }

    /*protected void func_203048_f()
    {
        if (this.pickupStatus != EntityArrow.PickupStatus.ALLOWED)
        {
            super.func_203048_f();
        }
    }*/

    protected float func_203044_p() {
        return 0.99F;
    }

    public boolean isInRangeToRender3d(double x, double y, double z) {
        return true;
    }

}