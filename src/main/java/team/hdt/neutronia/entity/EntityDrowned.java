package team.hdt.neutronia.entity;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import team.hdt.neutronia.base.util.handlers.LootTableHandler;
import team.hdt.neutronia.entity.projectile.EntityTrident;
import team.hdt.neutronia.init.NItems;
import team.hdt.neutronia.init.NSounds;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityDrowned extends EntityZombie implements IRangedAttackMob {

    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityDrowned.class, DataSerializers.BOOLEAN);
    private final PathNavigateSwimmer pathNavigateSwimmer;
    private final PathNavigateGround pathNavigateGround;
    private boolean field_204718_bx;

    public EntityDrowned(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.stepHeight = 1.0F;
        this.moveHelper = new EntityDrowned.MoveHelper(this);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.pathNavigateSwimmer = new PathNavigateSwimmer(this, worldIn);
        this.pathNavigateGround = new PathNavigateGround(this, worldIn);
        setBreakDoorsAItask(false);
    }

    protected boolean shouldBurnInDay() {
        return false;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityDrowned.AIGoToWater(this, 1.0D));
        this.tasks.addTask(2, new EntityDrowned.AITridentAttack(this, 1.0D, 40, 10.0F));
        this.tasks.addTask(2, new EntityDrowned.AIAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityDrowned.AIGoToBeach(this, 1.0D));
        this.tasks.addTask(6, new EntityDrowned.AISwimUp(this, 1.0D, this.world.getSeaLevel()));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityDrowned.class));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 10, true, false, new EntityDrowned.AttackTargetPredicate(this)));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, true));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))).getBlock() == Blocks.WATER;
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
        return LootTableHandler.DROWNED;
    }

    @Override
    public float getEyeHeight() {
        return 1.74F;
    }

    @Override
    protected boolean canEquipItem(ItemStack stack) {
        return stack.getItem() == NItems.anchor;
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

    protected PathNavigate createNavigator(World p_createNavigator_1_) {
        return super.createNavigator(p_createNavigator_1_);
    }

    /*public IEntityLivingData onInitialSpawn(DifficultyInstance p_onInitialSpawn_1_, @Nullable IEntityLivingData p_onInitialSpawn_2_) {
        p_onInitialSpawn_2_ = super.onInitialSpawn(p_onInitialSpawn_1_, p_onInitialSpawn_2_);
        if (this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03F) {
            this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(NItems.nautilusShell));
            this.inventoryHandsDropChances[EntityEquipmentSlot.OFFHAND.getIndex()] = 2.0F;
        }

        return p_onInitialSpawn_2_;
    }*/

    /*public boolean canSpawn(World p_canSpawn_1_) {
        Biome lvt_2_1_ = p_canSpawn_1_.getBiome(new BlockPos(this.posX, this.posY, this.posZ));
        if (lvt_2_1_ != Biomes.RIVER && lvt_2_1_ != Biomes.FROZEN_RIVER) {
            return this.rand.nextInt(40) == 0 && this.func_204712_dC() && super.canSpawn(p_canSpawn_1_);
        } else {
            return this.rand.nextInt(15) == 0 && super.canSpawn(p_canSpawn_1_);
        }
    }*/

    private boolean func_204712_dC() {
        return this.getEntityBoundingBox().minY < (double) (this.world.getSeaLevel() - 5);
    }

    protected boolean canBreakDoors() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? NSounds.ENTITY_DROWNED_AMBIENT_WATER : NSounds.ENTITY_DROWNED_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_getHurtSound_1_) {
        return this.isInWater() ? NSounds.ENTITY_DROWNED_HURT_WATER : NSounds.ENTITY_DROWNED_HURT;
    }

    protected SoundEvent getDeathSound() {
        return this.isInWater() ? NSounds.ENTITY_DROWNED_DEATH_WATER : NSounds.ENTITY_DROWNED_DEATH;
    }

    protected SoundEvent getStepSound() {
        return NSounds.ENTITY_DROWNED_STEP;
    }

    protected SoundEvent getSwimSound() {
        return NSounds.ENTITY_DROWNED_SWIM;
    }

    protected ItemStack getSkullDrop() {
        return ItemStack.EMPTY;
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance p_setEquipmentBasedOnDifficulty_1_) {
        if ((double) this.rand.nextFloat() > 0.9D) {
            int lvt_2_1_ = this.rand.nextInt(16);
            if (lvt_2_1_ < 10) {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(NItems.trident));
            } else {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
            }
        }
    }

    protected boolean shouldDrown() {
        return false;
    }

    public boolean isNotColliding(World p_isNotColliding_1_) {
        return p_isNotColliding_1_.checkNoEntityCollision(this.getEntityBoundingBox(), this) && p_isNotColliding_1_.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }

    public boolean shouldAttack(@Nullable EntityLivingBase p_shouldAttack_1_) {
        if (p_shouldAttack_1_ != null) {
            return !this.world.isDaytime() || p_shouldAttack_1_.isInWater();
        } else {
            return false;
        }
    }

    public boolean isPushedByWater() {
        return false;
    }

    private boolean func_204715_dF() {
        if (this.field_204718_bx) {
            return true;
        } else {
            EntityLivingBase lvt_1_1_ = this.getAttackTarget();
            return lvt_1_1_ != null && lvt_1_1_.isInWater();
        }
    }

    public void travel(float p_travel_1_, float p_travel_2_, float p_travel_3_) {
        if (this.isServerWorld() && this.isInWater() && this.func_204715_dF()) {
            this.moveRelative(p_travel_1_, p_travel_2_, p_travel_3_, 0.01F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 0.8999999761581421D;
            this.motionZ *= 0.8999999761581421D;
        } else {
            super.travel(p_travel_1_, p_travel_2_, p_travel_3_);
        }

    }

    protected boolean isCloseToPathTarget() {
        Path lvt_1_1_ = this.getNavigator().getPath();
        if (lvt_1_1_ != null) {
            PathPoint lvt_2_1_ = lvt_1_1_.getTarget();
            double lvt_3_1_ = this.getDistanceSq((double) lvt_2_1_.x, (double) lvt_2_1_.y, (double) lvt_2_1_.z);
            return lvt_3_1_ < 4.0D;
        }

        return false;
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

            return d0 < 4.0D;
        }

        return false;
    }

    static class AIAttack extends EntityAIZombieAttack {
        private final EntityDrowned drownedIn;

        AIAttack(EntityDrowned drownedIn, double speedIn, boolean longMemoryIn) {
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
        private final EntityDrowned drownedIn;

        public AIGoToBeach(EntityDrowned drownedIn, double speedIn) {
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
        private EntityDrowned drownedIn;
        private boolean isOverWater;

        public AISwimUp(EntityDrowned p_i48908_1_, double p_i48908_2_, int seaLevel) {
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
        private final EntityDrowned drownedIn;

        public AITridentAttack(IRangedAttackMob rangedAttackMobIn, double moveSpeed, int maxAttackTime, float maxAttackDistanceIn) {
            super(rangedAttackMobIn, moveSpeed, maxAttackTime, maxAttackDistanceIn);
            this.drownedIn = (EntityDrowned) rangedAttackMobIn;
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
        private final EntityDrowned drownedIn;

        public AttackTargetPredicate(EntityDrowned drownedIn) {
            this.drownedIn = drownedIn;
        }

        public boolean apply(@Nullable EntityPlayer player) {
            return this.drownedIn.func_204714_e(player);
        }
    }

    static class MoveHelper extends EntityMoveHelper {
        private final EntityDrowned drownedIn;

        public MoveHelper(EntityDrowned drownedIn) {
            super(drownedIn);
            this.drownedIn = drownedIn;
        }

        public void onUpdateMoveHelper() {
            EntityLivingBase entityLiving = this.drownedIn.getAttackTarget();

            if (this.drownedIn.func_204715_dF() && this.drownedIn.isInWater()) {
                if (entityLiving != null && entityLiving.posY > this.drownedIn.posY || this.drownedIn.field_204718_bx) {
                    this.drownedIn.motionY += 0.002D;
                }

                if (this.action != Action.MOVE_TO || this.drownedIn.getNavigator().noPath()) {
                    this.drownedIn.setAIMoveSpeed(0.0F);
                    return;
                }

                double lvt_2_1_ = this.posX - this.drownedIn.posX;
                double lvt_4_1_ = this.posY - this.drownedIn.posY;
                double lvt_6_1_ = this.posZ - this.drownedIn.posZ;
                double lvt_8_1_ = (double) MathHelper.sqrt(lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_);
                lvt_4_1_ /= lvt_8_1_;
                float lvt_10_1_ = (float) (MathHelper.atan2(lvt_6_1_, lvt_2_1_) * 57.2957763671875D) - 90.0F;
                this.drownedIn.rotationYaw = this.limitAngle(this.drownedIn.rotationYaw, lvt_10_1_, 90.0F);
                this.drownedIn.renderYawOffset = this.drownedIn.rotationYaw;
                float lvt_11_1_ = (float) (this.speed * this.drownedIn.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.drownedIn.setAIMoveSpeed(this.drownedIn.getAIMoveSpeed() + (lvt_11_1_ - this.drownedIn.getAIMoveSpeed()) * 0.125F);
                this.drownedIn.motionY += (double) this.drownedIn.getAIMoveSpeed() * lvt_4_1_ * 0.1D;
                this.drownedIn.motionX += (double) this.drownedIn.getAIMoveSpeed() * lvt_2_1_ * 0.005D;
                this.drownedIn.motionZ += (double) this.drownedIn.getAIMoveSpeed() * lvt_6_1_ * 0.005D;
            } else {
                if (!this.drownedIn.onGround) {
                    this.drownedIn.motionY -= 0.008D;
                }

                super.onUpdateMoveHelper();
            }
        }
    }

}