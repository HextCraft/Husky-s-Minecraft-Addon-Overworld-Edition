package team.hdt.neutronia_legacy.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.util.MathHelper;
import team.hdt.neutronia_legacy.init.NSounds;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class EntityPhantomBase extends EntityFlying implements IMob {
    private static final DataParameter<Integer> field_203035_a;

    static {
        field_203035_a = EntityDataManager.createKey(EntityPhantom.class, DataSerializers.VARINT);
    }

    private Vec3d field_203036_b;
    private BlockPos field_203037_c;
    private EntityPhantom.AttackPhase field_203038_bx;

    public EntityPhantomBase(World p_i48793_1_) {
        super(p_i48793_1_);
        this.field_203036_b = Vec3d.ZERO;
        this.field_203037_c = BlockPos.ORIGIN;
        this.field_203038_bx = EntityPhantom.AttackPhase.CIRCLE;
        this.experienceValue = 5;
        this.setSize(0.9F, 0.5F);
        this.moveHelper = new EntityPhantom.MoveHelper(this);
    }

    @Override
    public EntityLookHelper getLookHelper() {
        return new EntityPhantom.LookHelper(this);
    }

    protected EntityBodyHelper createBodyHelper() {
        return new EntityPhantom.BodyHelper(this);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityPhantom.AIPickAttack());
        this.tasks.addTask(2, new EntityPhantom.AISweepAttack());
        this.tasks.addTask(3, new EntityPhantom.AIOrbitPoint());
        this.targetTasks.addTask(1, new EntityPhantom.AIAttackPlayer());
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(field_203035_a, 0);
    }

    public void func_203034_a(int p_203034_1_) {
        if (p_203034_1_ < 0) {
            p_203034_1_ = 0;
        } else if (p_203034_1_ > 64) {
            p_203034_1_ = 64;
        }

        this.dataManager.set(field_203035_a, p_203034_1_);
        this.func_203033_m();
    }

    public void func_203033_m() {
        int lvt_1_1_ = (Integer) this.dataManager.get(field_203035_a);
        this.setSize(0.9F + 0.2F * (float) lvt_1_1_, 0.5F + 0.1F * (float) lvt_1_1_);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double) (6 + lvt_1_1_));
    }

    public int func_203032_dq() {
        return (Integer) this.dataManager.get(field_203035_a);
    }

    public float getEyeHeight() {
        return this.height * 0.35F;
    }

    public void notifyDataManagerChange(DataParameter<?> p_notifyDataManagerChange_1_) {
        if (field_203035_a.equals(p_notifyDataManagerChange_1_)) {
            this.func_203033_m();
        }

        super.notifyDataManagerChange(p_notifyDataManagerChange_1_);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote) {
            float lvt_1_1_ = net.minecraft.util.math.MathHelper.cos((float) (this.getEntityId() * 3 + this.ticksExisted) * 0.13F + 3.1415927F);
            float lvt_2_1_ = net.minecraft.util.math.MathHelper.cos((float) (this.getEntityId() * 3 + this.ticksExisted + 1) * 0.13F + 3.1415927F);
            if (lvt_1_1_ > 0.0F && lvt_2_1_ <= 0.0F) {
                this.world.playSound(this.posX, this.posY, this.posZ, NSounds.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95F + this.rand.nextFloat() * 0.05F, 0.95F + this.rand.nextFloat() * 0.05F, false);
            }

            int lvt_3_1_ = this.func_203032_dq();
            float lvt_4_1_ = net.minecraft.util.math.MathHelper.cos(this.rotationYaw * 0.017453292F) * (1.3F + 0.21F * (float) lvt_3_1_);
            float lvt_5_1_ = net.minecraft.util.math.MathHelper.sin(this.rotationYaw * 0.017453292F) * (1.3F + 0.21F * (float) lvt_3_1_);
            float lvt_6_1_ = (0.3F + lvt_1_1_ * 0.45F) * ((float) lvt_3_1_ * 0.2F + 1.0F);
//            this.world.spawnParticle(EnumNeutroniaParticles.MYCELIUM, this.posX + (double)lvt_4_1_, this.posY + (double)lvt_6_1_, this.posZ + (double)lvt_5_1_, 0.0D, 0.0D, 0.0D);
//            this.world.addParticle(EnumNeutroniaParticles.MYCELIUM, this.posX - (double)lvt_4_1_, this.posY + (double)lvt_6_1_, this.posZ - (double)lvt_5_1_, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }

    }

    public void onLivingUpdate() {
        if (this.isBurning()) {
            this.setFire(8);
        }

        super.onLivingUpdate();
    }

    /*public IEntityLivingData func_204210_a(DifficultyInstance p_204210_1_, @Nullable IEntityLivingData p_204210_2_, @Nullable NBTTagCompound p_204210_3_) {
        this.field_203037_c = (new BlockPos(this)).up(5);
        this.func_203034_a(0);
        return super.func_204210_a(p_204210_1_, p_204210_2_, p_204210_3_);
    }*/

    protected void updateAITasks() {
        super.updateAITasks();
    }

    public void readEntityFromNBT(NBTTagCompound p_readEntityFromNBT_1_) {
        super.readEntityFromNBT(p_readEntityFromNBT_1_);
        if (p_readEntityFromNBT_1_.hasKey("AX")) {
            this.field_203037_c = new BlockPos(p_readEntityFromNBT_1_.getInteger("AX"), p_readEntityFromNBT_1_.getInteger("AY"), p_readEntityFromNBT_1_.getInteger("AZ"));
        }

        this.func_203034_a(p_readEntityFromNBT_1_.getInteger("Size"));
    }

    public void writeEntityToNBT(NBTTagCompound p_writeEntityToNBT_1_) {
        super.writeEntityToNBT(p_writeEntityToNBT_1_);
        p_writeEntityToNBT_1_.setInteger("AX", this.field_203037_c.getX());
        p_writeEntityToNBT_1_.setInteger("AY", this.field_203037_c.getY());
        p_writeEntityToNBT_1_.setInteger("AZ", this.field_203037_c.getZ());
        p_writeEntityToNBT_1_.setInteger("Size", this.func_203032_dq());
    }

    public boolean isInRangeToRenderDist(double p_isInRangeToRenderDist_1_) {
        return true;
    }

    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    protected SoundEvent getAmbientSound() {
        return NSounds.ENTITY_PHANTOM_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_getHurtSound_1_) {
        return NSounds.ENTITY_PHANTOM_HURT;
    }

    /*@Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.field_203250_E;
    }*/

    protected SoundEvent getDeathSound() {
        return NSounds.ENTITY_PHANTOM_DEATH;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public boolean canAttackClass(Class<? extends EntityLivingBase> p_canAttackClass_1_) {
        return true;
    }

    static enum AttackPhase {
        CIRCLE,
        SWOOP;

        private AttackPhase() {
        }
    }

    class AIAttackPlayer extends EntityAIBase {
        private int field_203142_b;

        private AIAttackPlayer() {
            this.field_203142_b = 20;
        }

        public boolean shouldExecute() {
            if (this.field_203142_b > 0) {
                --this.field_203142_b;
                return false;
            } else {
                this.field_203142_b = 60;
                AxisAlignedBB lvt_1_1_ = getEntityBoundingBox().grow(16.0D, 64.0D, 16.0D);
                List<EntityPlayer> lvt_2_1_ = world.getEntitiesWithinAABB(EntityPlayer.class, lvt_1_1_);
                if (!lvt_2_1_.isEmpty()) {
                    lvt_2_1_.sort((p_203140_0_, p_203140_1_) -> {
                        return p_203140_0_.posY > p_203140_1_.posY ? -1 : 1;
                    });
                    Iterator var3 = lvt_2_1_.iterator();

                    while (var3.hasNext()) {
                        EntityPlayer lvt_4_1_ = (EntityPlayer) var3.next();
                        if (EntityAITarget.isSuitableTarget(new EntityPhantomBase(world), lvt_4_1_, false, false)) {
                            setAttackTarget(lvt_4_1_);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        public boolean shouldContinueExecuting() {
            return EntityAITarget.isSuitableTarget(new EntityPhantomBase(world), getAttackTarget(), false, false);
        }
    }

    class AIPickAttack extends EntityAIBase {
        private int field_203145_b;

        private AIPickAttack() {
        }

        public boolean shouldExecute() {
            return EntityAITarget.isSuitableTarget(new EntityPhantomBase(world), getAttackTarget(), false, false);
        }

        public void startExecuting() {
            this.field_203145_b = 10;
            field_203038_bx = EntityPhantom.AttackPhase.CIRCLE;
            this.func_203143_f();
        }

        public void resetTask() {
            field_203037_c = world.getTopSolidOrLiquidBlock(field_203037_c).up(10 + rand.nextInt(20));
        }

        public void updateTask() {
            if (field_203038_bx == EntityPhantom.AttackPhase.CIRCLE) {
                --this.field_203145_b;
                if (this.field_203145_b <= 0) {
                    field_203038_bx = EntityPhantom.AttackPhase.SWOOP;
                    this.func_203143_f();
                    this.field_203145_b = (8 + rand.nextInt(4)) * 20;
                    playSound(NSounds.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + rand.nextFloat() * 0.1F);
                }
            }

        }

        private void func_203143_f() {
            field_203037_c = (new BlockPos(getAttackTarget())).up(20 + rand.nextInt(20));
            if (field_203037_c.getY() < world.getSeaLevel()) {
                field_203037_c = new BlockPos(field_203037_c.getX(), world.getSeaLevel() + 1, field_203037_c.getZ());
            }

        }
    }

    class AISweepAttack extends EntityPhantom.AIMove {
        private AISweepAttack() {
            super();
        }

        public boolean shouldExecute() {
            return getAttackTarget() != null && field_203038_bx == EntityPhantom.AttackPhase.SWOOP;
        }

        public void startExecuting() {
        }

        public void updateTask() {
            EntityLivingBase lvt_1_1_ = getAttackTarget();
            field_203036_b = new Vec3d(Objects.requireNonNull(lvt_1_1_).posX, lvt_1_1_.posY + (double) lvt_1_1_.height * 0.5D, lvt_1_1_.posZ);
            if (getEntityBoundingBox().grow(0.20000000298023224D).intersects(lvt_1_1_.getEntityBoundingBox())) {
                attackEntityAsMob(lvt_1_1_);
                field_203038_bx = EntityPhantom.AttackPhase.CIRCLE;
                world.playEvent(1039, new BlockPos(new EntityPhantomBase(world)), 0);
            } else if (collidedHorizontally || hurtTime > 0) {
                field_203038_bx = EntityPhantom.AttackPhase.CIRCLE;
            }

        }
    }

    class AIOrbitPoint extends EntityPhantom.AIMove {
        private float field_203150_c;
        private float field_203151_d;
        private float field_203152_e;
        private float field_203153_f;

        private AIOrbitPoint() {
            super();
        }

        public boolean shouldExecute() {
            return getAttackTarget() == null || field_203038_bx == EntityPhantom.AttackPhase.CIRCLE;
        }

        public void startExecuting() {
            this.field_203151_d = 5.0F + rand.nextFloat() * 10.0F;
            this.field_203152_e = -4.0F + rand.nextFloat() * 9.0F;
            this.field_203153_f = rand.nextBoolean() ? 1.0F : -1.0F;
            this.func_203148_i();
        }

        public void updateTask() {
            if (rand.nextInt(350) == 0) {
                this.field_203152_e = -4.0F + rand.nextFloat() * 9.0F;
            }

            if (rand.nextInt(250) == 0) {
                ++this.field_203151_d;
                if (this.field_203151_d > 15.0F) {
                    this.field_203151_d = 5.0F;
                    this.field_203153_f = -this.field_203153_f;
                }
            }

            if (rand.nextInt(450) == 0) {
                this.field_203150_c = rand.nextFloat() * 2.0F * 3.1415927F;
                this.func_203148_i();
            }

            if (this.func_203146_f()) {
                this.func_203148_i();
            }

            if (field_203036_b.y < posY && !world.isAirBlock((new BlockPos(new EntityPhantomBase(world))).down(1))) {
                this.field_203152_e = Math.max(1.0F, this.field_203152_e);
                this.func_203148_i();
            }

            if (field_203036_b.y > posY && !world.isAirBlock((new BlockPos(new EntityPhantomBase(world))).up(1))) {
                this.field_203152_e = Math.min(-1.0F, this.field_203152_e);
                this.func_203148_i();
            }

        }

        private void func_203148_i() {
            if (BlockPos.ORIGIN.equals(field_203037_c)) {
                field_203037_c = new BlockPos(new EntityPhantomBase(world));
            }

            this.field_203150_c += this.field_203153_f * 15.0F * 0.017453292F;
            field_203036_b = (new Vec3d(field_203037_c)).add((double) (this.field_203151_d * MathHelper.cos(this.field_203150_c)), (double) (-4.0F + this.field_203152_e), (double) (this.field_203151_d * MathHelper.sin(this.field_203150_c)));
        }
    }

    abstract class AIMove extends EntityAIBase {
        public AIMove() {
            this.setMutexBits(1);
        }

        protected boolean func_203146_f() {
            return field_203036_b.squareDistanceTo(posX, posY, posZ) < 4.0D;
        }
    }

    class LookHelper extends EntityLookHelper {
        public LookHelper(EntityLiving p_i48802_2_) {
            super(p_i48802_2_);
        }

        public void onUpdateLook() {
        }
    }

    class BodyHelper extends EntityBodyHelper {
        public BodyHelper(EntityLivingBase p_i48805_2_) {
            super(p_i48805_2_);
        }

        public void updateRenderAngles() {
            rotationYawHead = renderYawOffset;
            renderYawOffset = rotationYaw;
        }
    }

    class MoveHelper extends EntityMoveHelper {
        private float field_203105_j = 0.1F;

        public MoveHelper(EntityLiving p_i48801_2_) {
            super(p_i48801_2_);
        }

        public void onUpdateMoveHelper() {
            if (collidedHorizontally) {
                rotationYaw += 180.0F;
                this.field_203105_j = 0.1F;
            }

            float lvt_1_1_ = (float) (field_203036_b.x - posX);
            float lvt_2_1_ = (float) (field_203036_b.y - posY);
            float lvt_3_1_ = (float) (field_203036_b.z - posZ);
            double lvt_4_1_ = (double) MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_);
            double lvt_6_1_ = 1.0D - (double) MathHelper.abs(lvt_2_1_ * 0.7F) / lvt_4_1_;
            lvt_1_1_ = (float) ((double) lvt_1_1_ * lvt_6_1_);
            lvt_3_1_ = (float) ((double) lvt_3_1_ * lvt_6_1_);
            lvt_4_1_ = (double) MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_);
            double lvt_8_1_ = (double) MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_2_1_ * lvt_2_1_);
            float lvt_10_1_ = rotationYaw;
            float lvt_11_1_ = (float) MathHelper.atan2((double) lvt_3_1_, (double) lvt_1_1_);
            float lvt_12_1_ = MathHelper.wrapDegrees(rotationYaw + 90.0F);
            float lvt_13_1_ = MathHelper.wrapDegrees(lvt_11_1_ * 57.295776F);
            rotationYaw = MathHelper.clamp(lvt_12_1_, lvt_13_1_, 4.0F) - 90.0F;
            renderYawOffset = rotationYaw;
            if (MathHelper.atan2(lvt_10_1_, rotationYaw) < 3.0F) {
                this.field_203105_j = MathHelper.func_203300_b(this.field_203105_j, 1.8F, 0.005F * (1.8F / this.field_203105_j));
            } else {
                this.field_203105_j = MathHelper.func_203300_b(this.field_203105_j, 0.2F, 0.025F);
            }

            float lvt_14_1_ = (float) (-(MathHelper.atan2((double) (-lvt_2_1_), lvt_4_1_) * 57.2957763671875D));
            rotationPitch = lvt_14_1_;
            float lvt_15_1_ = rotationYaw + 90.0F;
            double lvt_16_1_ = (double) (this.field_203105_j * MathHelper.cos(lvt_15_1_ * 0.017453292F)) * Math.abs((double) lvt_1_1_ / lvt_8_1_);
            double lvt_18_1_ = (double) (this.field_203105_j * MathHelper.sin(lvt_15_1_ * 0.017453292F)) * Math.abs((double) lvt_3_1_ / lvt_8_1_);
            double lvt_20_1_ = (double) (this.field_203105_j * MathHelper.sin(lvt_14_1_ * 0.017453292F)) * Math.abs((double) lvt_2_1_ / lvt_8_1_);
            motionX += (lvt_16_1_ - motionX) * 0.2D;
            motionY += (lvt_20_1_ - motionY) * 0.2D;
            motionZ += (lvt_18_1_ - motionZ) * 0.2D;
        }
    }

}