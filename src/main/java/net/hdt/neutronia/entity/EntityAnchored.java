package net.hdt.neutronia.entity;

import com.google.common.base.Predicate;
import net.hdt.neutronia.base.util.handlers.LootTableHandler;
import net.hdt.neutronia.entity.projectile.EntityTrident;
import net.hdt.neutronia.init.NItems;
import net.hdt.neutronia.init.NSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Calendar;
import java.util.Random;

public class EntityAnchored extends EntityZombie implements IRangedAttackMob {

    public static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityAnchored.class, DataSerializers.BOOLEAN);
    private final PathNavigateSwimmer pathNavigateSwimmer;
    private final PathNavigateGround pathNavigateGround;
    private boolean field_204718_bx;

    public EntityAnchored(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.stepHeight = 1.0F;
        this.moveHelper = new EntityAnchored.MoveHelper(this);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.pathNavigateSwimmer = new PathNavigateSwimmer(this, worldIn);
        this.pathNavigateGround = new PathNavigateGround(this, worldIn);
        setBreakDoorsAItask(false);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAnchored.AIGoToWater(this, 1.0D));
        this.tasks.addTask(2, new EntityAnchored.AITridentAttack(this, 1.0D, 40, 10.0F));
        this.tasks.addTask(2, new EntityAnchored.AIAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAnchored.AIGoToBeach(this, 1.0D));
        this.tasks.addTask(6, new EntityAnchored.AISwimUp(this, 1.0D, this.world.getSeaLevel()));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(0, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))).getBlock() == Blocks.WATER;
    }

    @Override
    protected PathNavigate createNavigator(World world) {
        return new PathNavigateSwimmer(this, world);
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return world.getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + world.getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
    }

    /**
     * Checks that the entity is not colliding with any blocks / liquids
     */
    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }

    protected void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPigZombie.class));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(ARMS_RAISED, false);
    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED);
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, armsRaised);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag) {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F)
                entityIn.setFire(2 * (int) f);
        }
        return flag;
    }

    /**
     * TODO: Change Sounds...
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    // TODO ^

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.ANCHORED;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);

        if (this.rand.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.rand.nextInt(3);

            if (i == 0)
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(NItems.anchor));
            else
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
    }

    @Override
    public float getEyeHeight() {
        return 1.74F;
    }

    @Override
    protected boolean canEquipItem(ItemStack stack) {
        return stack.getItem() == NItems.anchor;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        if (!getCanSpawnHere())
            despawnEntity();
        else {
            livingdata = super.onInitialSpawn(difficulty, livingdata);
            float f = difficulty.getClampedAdditionalDifficulty();
            this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);
            if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
                Calendar calendar = this.world.getCurrentDate();
                if (calendar.get(Calendar.MONTH) + 1 == 10 && calendar.get(Calendar.DATE) == 31 && this.rand.nextFloat() < 0.25F) {
                    this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                    this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
                }
            }
            this.setEquipmentBasedOnDifficulty(difficulty);
            this.setEnchantmentBasedOnDifficulty(difficulty);

            this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(new AttributeModifier("Spawn Bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
            double d0 = this.rand.nextDouble() * 1.5D * (double) f;

            if (d0 > 1.0D)
                this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random Anchored-Spawn Bonus", d0, 2));

            if (this.rand.nextFloat() < f * 0.0F && this.world.getDifficulty() == EnumDifficulty.HARD) {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier("Leader Anchored Bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
            }
        }
        return livingdata;
    }

    private boolean func_204715_dF() {
        if (this.field_204718_bx) {
            return true;
        } else {
            EntityLivingBase entitylivingbase = this.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isInWater();
        }
    }

    public boolean func_204714_e(@Nullable EntityLivingBase p_204714_1_) {
        if (p_204714_1_ != null) {
            return !this.world.isDaytime() || p_204714_1_.isInWater();
        } else {
            return false;
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (cause.getTrueSource() instanceof EntityCreeper) {
            EntityCreeper entityCreeper = (EntityCreeper) cause.getTrueSource();

            if (entityCreeper.getPowered() && entityCreeper.ableToCauseSkullDrop()) {
                entityCreeper.incrementDroppedSkulls();
                ItemStack itemStack = this.getSkullDrop();

                if (!itemStack.isEmpty())
                    this.entityDropItem(itemStack, 0.0F);
            }
        }
    }

    protected ItemStack getSkullDrop() {
        return new ItemStack(Items.SKULL, 1, 2);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityTrident entitytrident = new EntityTrident(this.world, this, new ItemStack(NItems.trident));
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - entitytrident.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        entitytrident.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
        this.playSound(NSounds.ENTITY_DROWNED_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entitytrident);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        this.field_204718_bx = swingingArms;
    }

    private boolean func_204710_dB() {
        Path path = this.getNavigator().getPath();

        if (path != null) {
            PathPoint pathpoint = path.getTarget();

            double d0 = this.getDistanceSq((double) pathpoint.x, (double) pathpoint.y, (double) pathpoint.z);

            if (d0 < 4.0D) {
                return true;
            }
        }

        return false;
    }

    static class AIAttack extends EntityAIZombieAttack {
        private final EntityAnchored drownedIn;

        AIAttack(EntityAnchored drownedIn, double speedIn, boolean longMemoryIn) {
            super(drownedIn, speedIn, longMemoryIn);
            this.drownedIn = drownedIn;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && this.drownedIn.func_204714_e(this.drownedIn.getAttackTarget());
        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && this.drownedIn.func_204714_e(this.drownedIn.getAttackTarget());
        }
    }

    static class AIGoToBeach extends EntityAIMoveToBlock {
        private final EntityAnchored drownedIn;

        public AIGoToBeach(EntityAnchored drownedIn, double speedIn) {
            super(drownedIn, speedIn, 8);
            this.drownedIn = drownedIn;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && !this.drownedIn.world.isDaytime() && this.drownedIn.isInWater() && this.drownedIn.posY >= (double) (this.drownedIn.world.getSeaLevel() - 3);
        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting();
        }

        protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
            BlockPos blockpos = pos.up();
            return (worldIn.isAirBlock(blockpos) && worldIn.isAirBlock(blockpos.up())) && worldIn.getBlockState(pos).isTopSolid();
        }

        public void startExecuting() {
            this.drownedIn.setSwingingArms(false);
            this.drownedIn.navigator = this.drownedIn.pathNavigateGround;
            super.startExecuting();
        }

        public void resetTask() {
            super.resetTask();
        }
    }

    static class AIGoToWater extends EntityAIBase {
        private final EntityCreature creatureIn;
        private final double speedIn;
        private final World worldIn;
        private double posX;
        private double posY;
        private double posZ;

        public AIGoToWater(EntityCreature creatureIn, double speedIn) {
            this.creatureIn = creatureIn;
            this.speedIn = speedIn;
            this.worldIn = creatureIn.world;
            this.setMutexBits(1);
        }

        public boolean shouldExecute() {
            if (!this.worldIn.isDaytime()) {
                return false;
            } else if (this.creatureIn.isInWater()) {
                return false;
            } else {
                Vec3d vec3d = this.getWaterBlock();

                if (vec3d == null) {
                    return false;
                } else {
                    this.posX = vec3d.x;
                    this.posY = vec3d.y;
                    this.posZ = vec3d.z;
                    return true;
                }
            }
        }

        public boolean shouldContinueExecuting() {
            return !this.creatureIn.getNavigator().noPath();
        }

        public void startExecuting() {
            this.creatureIn.getNavigator().tryMoveToXYZ(this.posX, this.posY, this.posZ, this.speedIn);
        }

        @Nullable
        private Vec3d getWaterBlock() {
            Random random = this.creatureIn.getRNG();
            BlockPos blockpos = new BlockPos(this.creatureIn.posX, this.creatureIn.getEntityBoundingBox().minY, this.creatureIn.posZ);

            for (int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = blockpos.add(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);

                if (this.worldIn.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
                    return new Vec3d((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
                }
            }

            return null;
        }
    }

    static class AISwimUp extends EntityAIBase {
        private final double speedIn;
        private final int seaLevel;
        private EntityAnchored drownedIn;
        private boolean isOverWater;

        public AISwimUp(EntityAnchored p_i48908_1_, double p_i48908_2_, int seaLevel) {
            this.drownedIn = p_i48908_1_;
            this.speedIn = p_i48908_2_;
            this.seaLevel = seaLevel;
        }

        public boolean shouldExecute() {
            return !this.drownedIn.world.isDaytime() && this.drownedIn.isInWater() && this.drownedIn.posY < (double) (this.seaLevel - 2);
        }

        public boolean shouldContinueExecuting() {
            return this.shouldExecute() && !this.isOverWater;
        }

        public void updateTask() {
            if (this.drownedIn.posY < (double) (this.seaLevel - 1) && (this.drownedIn.getNavigator().noPath())) {
                Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.drownedIn, 4, 8, new Vec3d(this.drownedIn.posX, (double) (this.seaLevel - 1), this.drownedIn.posZ));

                if (vec3d == null) {
                    this.isOverWater = true;
                    return;
                }

                this.drownedIn.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speedIn);
            }
        }

        public void startExecuting() {
            this.drownedIn.setSwingingArms(true);
            this.isOverWater = false;
        }

        public void resetTask() {
            this.drownedIn.setSwingingArms(false);
        }
    }

    static class AITridentAttack extends EntityAIAttackRanged {
        private final EntityAnchored drownedIn;

        public AITridentAttack(IRangedAttackMob rangedAttackMobIn, double moveSpeed, int maxAttackTime, float maxAttackDistanceIn) {
            super(rangedAttackMobIn, moveSpeed, maxAttackTime, maxAttackDistanceIn);
            this.drownedIn = (EntityAnchored) rangedAttackMobIn;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && this.drownedIn.getHeldItemMainhand().getItem() == NItems.trident;
        }

        public void startExecuting() {
            super.startExecuting();
            this.drownedIn.setSwingingArms(true);
        }

        public void resetTask() {
            super.resetTask();
            this.drownedIn.setSwingingArms(false);
        }
    }

    static class AttackTargetPredicate implements Predicate<EntityPlayer> {
        private final EntityAnchored drownedIn;

        public AttackTargetPredicate(EntityAnchored drownedIn) {
            this.drownedIn = drownedIn;
        }

        public boolean apply(@Nullable EntityPlayer player) {
            return this.drownedIn.func_204714_e(player);
        }
    }

    static class MoveHelper extends EntityMoveHelper {
        private final EntityAnchored anchoredIn;

        public MoveHelper(EntityAnchored anchoredIn) {
            super(anchoredIn);
            this.anchoredIn = anchoredIn;
        }

        public void onUpdateMoveHelper() {
            EntityLivingBase entityLiving = this.anchoredIn.getAttackTarget();
            if (this.anchoredIn.func_204715_dF() && this.anchoredIn.isInWater()) {
                if (entityLiving != null && entityLiving.posY > this.anchoredIn.posY || this.anchoredIn.field_204718_bx) {
                    this.anchoredIn.motionY += 0.002D;
                }

                if (this.action != Action.MOVE_TO || this.anchoredIn.getNavigator().noPath()) {
                    this.anchoredIn.setAIMoveSpeed(0.0F);
                    return;
                }

                double lvt_2_1_ = this.posX - this.anchoredIn.posX;
                double lvt_4_1_ = this.posY - this.anchoredIn.posY;
                double lvt_6_1_ = this.posZ - this.anchoredIn.posZ;
                double lvt_8_1_ = (double) MathHelper.sqrt(lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_);
                lvt_4_1_ /= lvt_8_1_;
                float lvt_10_1_ = (float) (MathHelper.atan2(lvt_6_1_, lvt_2_1_) * 57.2957763671875D) - 90.0F;
                this.anchoredIn.rotationYaw = this.limitAngle(this.anchoredIn.rotationYaw, lvt_10_1_, 90.0F);
                this.anchoredIn.renderYawOffset = this.anchoredIn.rotationYaw;
                float lvt_11_1_ = (float) (this.speed * this.anchoredIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.anchoredIn.setAIMoveSpeed(this.anchoredIn.getAIMoveSpeed() + (lvt_11_1_ - this.anchoredIn.getAIMoveSpeed()) * 0.125F);
                this.anchoredIn.motionY += (double) this.anchoredIn.getAIMoveSpeed() * lvt_4_1_ * 0.1D;
                this.anchoredIn.motionX += (double) this.anchoredIn.getAIMoveSpeed() * lvt_2_1_ * 0.005D;
                this.anchoredIn.motionZ += (double) this.anchoredIn.getAIMoveSpeed() * lvt_6_1_ * 0.005D;
            } else {
                if (!this.anchoredIn.onGround) {
                    this.anchoredIn.motionY -= 0.008D;
                }

                super.onUpdateMoveHelper();
            }
        }
    }

}