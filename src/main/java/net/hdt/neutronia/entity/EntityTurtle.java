/*
package net.hdt.neutronia.entity;

import com.google.common.collect.Sets;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class EntityTurtle extends EntityAnimal
{
    private static final DataParameter<BlockPos> field_203030_by = EntityDataManager.<BlockPos>createKey(EntityTurtle.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Boolean> field_203031_bz = EntityDataManager.<Boolean>createKey(EntityTurtle.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> field_203024_bB = EntityDataManager.<Boolean>createKey(EntityTurtle.class, DataSerializers.BOOLEAN);
    private static final DataParameter<BlockPos> field_203025_bC = EntityDataManager.<BlockPos>createKey(EntityTurtle.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Boolean> field_203026_bD = EntityDataManager.<Boolean>createKey(EntityTurtle.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> field_203027_bE = EntityDataManager.<Boolean>createKey(EntityTurtle.class, DataSerializers.BOOLEAN);
    private int field_203028_bF;
    public static final Predicate<Entity> field_203029_bx = (p_210131_0_) ->
    {
        if (!(p_210131_0_ instanceof EntityLivingBase))
        {
            return false;
        }
        else {
            return ((EntityLivingBase)p_210131_0_).isChild() && !p_210131_0_.isInWater();
        }
    };

    public EntityTurtle(World p_i48794_1_)
    {
        super(p_i48794_1_);
        this.setSize(1.2F, 0.4F);
        this.moveHelper = new EntityTurtle.MoveHelper(this);
        this.spawnableBlock = Blocks.SAND;
        this.stepHeight = 1.0F;
    }

    public void func_203011_g(BlockPos p_203011_1_)
    {
        this.dataManager.set(field_203030_by, p_203011_1_);
    }

    private BlockPos func_203018_dA()
    {
        return (BlockPos)this.dataManager.get(field_203030_by);
    }

    private void func_203019_h(BlockPos p_203019_1_)
    {
        this.dataManager.set(field_203025_bC, p_203019_1_);
    }

    private BlockPos func_203013_dB()
    {
        return (BlockPos)this.dataManager.get(field_203025_bC);
    }

    public boolean func_203020_dx()
    {
        return this.dataManager.get(field_203031_bz);
    }

    private void func_203017_r(boolean p_203017_1_)
    {
        this.dataManager.set(field_203031_bz, p_203017_1_);
    }

    public boolean func_203023_dy()
    {
        return this.dataManager.get(field_203024_bB);
    }

    private void func_203015_s(boolean p_203015_1_)
    {
        this.field_203028_bF = p_203015_1_ ? 1 : 0;
        this.dataManager.set(field_203024_bB, p_203015_1_);
    }

    private boolean func_203022_dF()
    {
        return this.dataManager.get(field_203026_bD);
    }

    private void func_203012_t(boolean p_203012_1_)
    {
        this.dataManager.set(field_203026_bD, p_203012_1_);
    }

    private boolean func_203014_dG()
    {
        return this.dataManager.get(field_203027_bE);
    }

    private void func_203021_u(boolean p_203021_1_)
    {
        this.dataManager.set(field_203027_bE, p_203021_1_);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(field_203030_by, BlockPos.ORIGIN);
        this.dataManager.register(field_203031_bz, false);
        this.dataManager.register(field_203025_bC, BlockPos.ORIGIN);
        this.dataManager.register(field_203026_bD, false);
        this.dataManager.register(field_203027_bE, false);
        this.dataManager.register(field_203024_bB, false);
    }

    */
/**
 * (abstract) Protected helper method to write subclass entity data to NBT.
 * <p>
 * (abstract) Protected helper method to read subclass entity data from NBT.
 * <p>
 * Get this Entity's EnumCreatureAttribute
 * <p>
 * Get number of ticks, at least during which the living entity will be silent.
 * <p>
 * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
 * <p>
 * Returns new PathNavigateGround instance
 * <p>
 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
 * the animal type)
 * <p>
 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
 * use this to react to sunlight and start to burn.
 * <p>
 * This is called when Entity's growing age timer reaches 0 (negative values are considered as a child, positive as
 * an adult)
 * <p>
 * Called when a lightning bolt hits the entity.
 * <p>
 * Called when the mob's health reaches 0.
 * <p>
 * (abstract) Protected helper method to read subclass entity data from NBT.
 * <p>
 * Get this Entity's EnumCreatureAttribute
 * <p>
 * Get number of ticks, at least during which the living entity will be silent.
 * <p>
 * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
 * <p>
 * Returns new PathNavigateGround instance
 * <p>
 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
 * the animal type)
 * <p>
 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
 * use this to react to sunlight and start to burn.
 * <p>
 * This is called when Entity's growing age timer reaches 0 (negative values are considered as a child, positive as
 * an adult)
 * <p>
 * Called when a lightning bolt hits the entity.
 * <p>
 * Called when the mob's health reaches 0.
 * <p>
 * (abstract) Protected helper method to read subclass entity data from NBT.
 * <p>
 * Get this Entity's EnumCreatureAttribute
 * <p>
 * Get number of ticks, at least during which the living entity will be silent.
 * <p>
 * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
 * <p>
 * Returns new PathNavigateGround instance
 * <p>
 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
 * the animal type)
 * <p>
 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
 * use this to react to sunlight and start to burn.
 * <p>
 * This is called when Entity's growing age timer reaches 0 (negative values are considered as a child, positive as
 * an adult)
 * <p>
 * Called when a lightning bolt hits the entity.
 * <p>
 * Called when the mob's health reaches 0.
 * <p>
 * (abstract) Protected helper method to read subclass entity data from NBT.
 * <p>
 * Get this Entity's EnumCreatureAttribute
 * <p>
 * Get number of ticks, at least during which the living entity will be silent.
 * <p>
 * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
 * <p>
 * Returns new PathNavigateGround instance
 * <p>
 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
 * the animal type)
 * <p>
 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
 * use this to react to sunlight and start to burn.
 * <p>
 * This is called when Entity's growing age timer reaches 0 (negative values are considered as a child, positive as
 * an adult)
 * <p>
 * Called when a lightning bolt hits the entity.
 * <p>
 * Called when the mob's health reaches 0.
 *//*

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("HomePosX", this.func_203018_dA().getX());
        compound.setInteger("HomePosY", this.func_203018_dA().getY());
        compound.setInteger("HomePosZ", this.func_203018_dA().getZ());
        compound.setBoolean("HasEgg", this.func_203020_dx());
        compound.setInteger("TravelPosX", this.func_203013_dB().getX());
        compound.setInteger("TravelPosY", this.func_203013_dB().getY());
        compound.setInteger("TravelPosZ", this.func_203013_dB().getZ());
    }

    */
/**
 * (abstract) Protected helper method to read subclass entity data from NBT.
 *//*

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        int i = compound.getInteger("HomePosX");
        int j = compound.getInteger("HomePosY");
        int k = compound.getInteger("HomePosZ");
        this.func_203011_g(new BlockPos(i, j, k));
        super.readEntityFromNBT(compound);
        this.func_203017_r(compound.getBoolean("HasEgg"));
        int l = compound.getInteger("TravelPosX");
        int i1 = compound.getInteger("TravelPosY");
        int j1 = compound.getInteger("TravelPosZ");
        this.func_203019_h(new BlockPos(l, i1, j1));
    }

    @Nullable
    public IEntityLivingData func_204210_a(DifficultyInstance p_204210_1_, @Nullable IEntityLivingData p_204210_2_, @Nullable NBTTagCompound p_204210_3_)
    {
        this.func_203011_g(new BlockPos(this.posX, this.posY, this.posZ));
        this.func_203019_h(BlockPos.ORIGIN);
        return super.func_204210_a(p_204210_1_, p_204210_2_, p_204210_3_);
    }

    public boolean func_205020_a()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        return blockpos.getY() < world.getSeaLevel() + 4 && super.getCanSpawnHere();
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityTurtle.AIPanic(this, 1.2D));
        this.tasks.addTask(1, new EntityTurtle.AIMate(this, 1.0D));
        this.tasks.addTask(1, new EntityTurtle.AILayEgg(this, 1.0D));
        this.tasks.addTask(2, new EntityTurtle.AIPlayerTempt(this, 1.1D, Blocks.field_203198_aQ.func_199767_j()));
        this.tasks.addTask(3, new EntityTurtle.AIGoToWater(this, 1.0D));
        this.tasks.addTask(4, new EntityTurtle.AIGoHome(this, 1.0D));
        this.tasks.addTask(7, new EntityTurtle.AITravel(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityTurtle.AIWander(this, 1.0D, 100));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    public boolean isPushedByWater()
    {
        return false;
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    */
/**
 * Get this Entity's EnumCreatureAttribute
 *//*

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }

    */
/**
 * Get number of ticks, at least during which the living entity will be silent.
 *//*

    public int getTalkInterval()
    {
        return 200;
    }

    @Nullable
    protected SoundEvent getAmbientSound()
    {
        return !this.isInWater() && this.onGround && !this.isChild() ? SoundEvents.field_203277_iv : super.getAmbientSound();
    }

    protected void func_203006_d(float p_203006_1_)
    {
        super.func_203006_d(p_203006_1_ * 1.5F);
    }

    protected SoundEvent getSwimSound()
    {
        return NSounds.ENTITY_TURTLE_SWIM;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return this.isChild() ? SoundEvents.field_203262_iB : SoundEvents.field_203261_iA;
    }

    @Nullable
    protected SoundEvent getDeathSound()
    {
        return this.isChild() ? SoundEvents.field_203264_iD : SoundEvents.field_203263_iC;
    }

    protected void playStepSound(BlockPos pos, IBlockState blockIn)
    {
        SoundEvent soundevent = this.isChild() ? SoundEvents.field_203267_iG : SoundEvents.field_203266_iF;
        this.playSound(soundevent, 0.15F, 1.0F);
    }

    public boolean func_204701_dC()
    {
        return super.func_204701_dC() && !this.func_203020_dx();
    }

    protected float func_203009_ad()
    {
        return this.distanceWalkedOnStepModified + 0.15F;
    }

    */
/**
 * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
 *//*

    public void setScaleForAge(boolean child)
    {
        this.setScale(child ? 0.3F : 1.0F);
    }

    */
/**
 * Returns new PathNavigateGround instance
 *//*

    protected PathNavigate createNavigator(World worldIn)
    {
        return new EntityTurtle.PathNavigater(this, worldIn);
    }

    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        return new EntityTurtle(this.world);
    }

    */
/**
 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
 * the animal type)
 *//*

    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Blocks.field_203198_aQ.func_199767_j();
    }

    public float func_205022_a(BlockPos p_205022_1_, IWorldReaderBase p_205022_2_)
    {
        return !this.func_203022_dF() && p_205022_2_.func_204610_c(p_205022_1_).func_206884_a(FluidTags.WATER) ? 10.0F : super.func_205022_a(p_205022_1_, p_205022_2_);
    }

    */
/**
 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
 * use this to react to sunlight and start to burn.
 *//*

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.func_203023_dy() && this.field_203028_bF >= 1 && this.field_203028_bF % 5 == 0)
        {
            BlockPos blockpos = new BlockPos(this);

            if (this.world.getBlockState(blockpos.down()).getBlock() == Blocks.SAND)
            {
                this.world.playEvent(2001, blockpos, Block.func_196246_j(Blocks.SAND.getDefaultState()));
            }
        }
    }

    */
/**
 * This is called when Entity's growing age timer reaches 0 (negative values are considered as a child, positive as
 * an adult)
 *//*

    protected void onGrowingAdult()
    {
        super.onGrowingAdult();
        this.func_199702_a(Items.field_203183_eM, 1);
    }

    public void travel(float strafe, float vertical, float forward)
    {
        if (this.isServerWorld() && this.isInWater())
        {
            this.moveRelative(strafe, vertical, forward, 0.1F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 0.8999999761581421D;
            this.motionZ *= 0.8999999761581421D;

            if (this.getAttackTarget() == null && (!this.func_203022_dF() || this.getDistanceSq(this.func_203018_dA()) >= 400.0D))
            {
                this.motionY -= 0.005D;
            }
        }
        else
        {
            super.travel(strafe, vertical, forward);
        }
    }

    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return false;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.field_203249_aB;
    }

    */
/**
 * Called when a lightning bolt hits the entity.
 *//*

    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        this.attackEntityFrom(DamageSource.LIGHTNING_BOLT, Float.MAX_VALUE);
    }

    */
/**
 * Called when the mob's health reaches 0.
 *//*

    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

        if (cause == DamageSource.LIGHTNING_BOLT)
        {
            this.entityDropItem(new ItemStack(Items.BOWL, 1), 0.0F);
        }
    }

    protected boolean func_203016_dz()
    {
        BlockPos blockpos = this.getNavigator().func_208485_j();

        if (blockpos != null)
        {
            return this.getDistanceSq(blockpos) < 64.0D;
        }
        else
        {
            return false;
        }
    }

    static class AIGoHome extends EntityAIBase
    {
        private final EntityTurtle field_203127_a;
        private final double field_203128_b;
        private boolean field_203129_c;
        private int field_203130_d;

        AIGoHome(EntityTurtle p_i48821_1_, double p_i48821_2_)
        {
            this.field_203127_a = p_i48821_1_;
            this.field_203128_b = p_i48821_2_;
        }

        public boolean shouldExecute()
        {
            if (this.field_203127_a.isChild())
            {
                return false;
            }
            else if (this.field_203127_a.func_203020_dx())
            {
                return true;
            }
            else if (this.field_203127_a.getRNG().nextInt(700) != 0)
            {
                return false;
            }
            else
            {
                return this.field_203127_a.getDistanceSq(this.field_203127_a.func_203018_dA()) >= 4096.0D;
            }
        }

        public void startExecuting()
        {
            this.field_203127_a.func_203012_t(true);
            this.field_203129_c = false;
            this.field_203130_d = 0;
        }

        public void resetTask()
        {
            this.field_203127_a.func_203012_t(false);
        }

        public boolean shouldContinueExecuting()
        {
            return this.field_203127_a.getDistanceSq(this.field_203127_a.func_203018_dA()) >= 49.0D && !this.field_203129_c && this.field_203130_d <= 600;
        }

        public void updateTask()
        {
            BlockPos blockpos = this.field_203127_a.func_203018_dA();
            boolean flag = this.field_203127_a.getDistanceSq(blockpos) <= 256.0D;

            if (flag)
            {
                ++this.field_203130_d;
            }

            if (this.field_203127_a.func_203016_dz() || this.field_203127_a.getNavigator().noPath())
            {
                Vec3d vec3d = RandomPositionGenerator.func_203155_a(this.field_203127_a, 16, 3, new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()), (Math.PI / 10D));

                if (vec3d == null)
                {
                    vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.field_203127_a, 8, 7, new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()));
                }

                if (vec3d != null && !flag && this.field_203127_a.world.getBlockState(new BlockPos(vec3d)).getBlock() != Blocks.WATER)
                {
                    vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.field_203127_a, 16, 5, new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()));
                }

                if (vec3d == null)
                {
                    this.field_203129_c = true;
                    return;
                }

                this.field_203127_a.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.field_203128_b);
            }
        }
    }

    static class AIGoToWater extends EntityAIMoveToBlock
    {
        private final EntityTurtle field_203121_f;

        private AIGoToWater(EntityTurtle p_i48819_1_, double p_i48819_2_)
        {
            super(p_i48819_1_, p_i48819_1_.isChild() ? 2.0D : p_i48819_2_, 24);
            this.field_203121_f = p_i48819_1_;
            this.field_203112_e = -1;
        }

        public boolean shouldContinueExecuting()
        {
            return !this.field_203121_f.isInWater() && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.field_203121_f.world, this.destinationBlock);
        }

        public boolean shouldExecute()
        {
            if (this.field_203121_f.isChild() && !this.field_203121_f.isInWater())
            {
                return super.shouldExecute();
            }
            else
            {
                return !this.field_203121_f.func_203022_dF() && !this.field_203121_f.isInWater() && !this.field_203121_f.func_203020_dx() ? super.shouldExecute() : false;
            }
        }

        public int func_203111_j()
        {
            return 1;
        }

        public boolean func_203108_i()
        {
            return this.timeoutCounter % 160 == 0;
        }

        protected boolean shouldMoveTo(IWorldReaderBase worldIn, BlockPos pos)
        {
            Block block = worldIn.getBlockState(pos).getBlock();
            return block == Blocks.WATER;
        }
    }

    static class AILayEgg extends EntityAIMoveToBlock
    {
        private final EntityTurtle field_203122_f;

        AILayEgg(EntityTurtle p_i48818_1_, double p_i48818_2_)
        {
            super(p_i48818_1_, p_i48818_2_, 16);
            this.field_203122_f = p_i48818_1_;
        }

        public boolean shouldExecute()
        {
            return this.field_203122_f.func_203020_dx() && this.field_203122_f.getDistanceSq(this.field_203122_f.func_203018_dA()) < 81.0D ? super.shouldExecute() : false;
        }

        public boolean shouldContinueExecuting()
        {
            return super.shouldContinueExecuting() && this.field_203122_f.func_203020_dx() && this.field_203122_f.getDistanceSq(this.field_203122_f.func_203018_dA()) < 81.0D;
        }

        public void updateTask()
        {
            super.updateTask();
            BlockPos blockpos = new BlockPos(this.field_203122_f);

            if (!this.field_203122_f.isInWater() && this.getIsAboveDestination())
            {
                if (this.field_203122_f.field_203028_bF < 1)
                {
                    this.field_203122_f.func_203015_s(true);
                }
                else if (this.field_203122_f.field_203028_bF > 200)
                {
                    World world = this.field_203122_f.world;
                    world.playSound((EntityPlayer)null, blockpos, SoundEvents.field_203278_iw, SoundCategory.BLOCKS, 0.3F, 0.9F + world.rand.nextFloat() * 0.2F);
                    world.setBlockState(this.destinationBlock.up(), (IBlockState)Blocks.field_203213_jA.getDefaultState().func_206870_a(BlockTurtleEgg.field_203171_b, Integer.valueOf(this.field_203122_f.rand.nextInt(4) + 1)), 3);
                    this.field_203122_f.func_203017_r(false);
                    this.field_203122_f.func_203015_s(false);
                    this.field_203122_f.func_204700_e(600);
                }

                if (this.field_203122_f.func_203023_dy())
                {
                    this.field_203122_f.field_203028_bF++;
                }
            }
        }

        protected boolean shouldMoveTo(IWorldReaderBase worldIn, BlockPos pos)
        {
            if (!worldIn.isAirBlock(pos.up()))
            {
                return false;
            }
            else
            {
                Block block = worldIn.getBlockState(pos).getBlock();
                return block == Blocks.SAND;
            }
        }
    }

    static class AIMate extends EntityAIMate
    {
        private final EntityTurtle field_203107_f;

        AIMate(EntityTurtle p_i48822_1_, double p_i48822_2_)
        {
            super(p_i48822_1_, p_i48822_2_);
            this.field_203107_f = p_i48822_1_;
        }

        public boolean shouldExecute()
        {
            return super.shouldExecute() && !this.field_203107_f.func_203020_dx();
        }

        protected void spawnBaby()
        {
            EntityPlayerMP entityplayermp = this.animal.getLoveCause();

            if (entityplayermp == null && this.targetMate.getLoveCause() != null)
            {
                entityplayermp = this.targetMate.getLoveCause();
            }

            if (entityplayermp != null)
            {
                entityplayermp.func_195066_a(StatList.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(entityplayermp, this.animal, this.targetMate, (EntityAgeable)null);
            }

            this.field_203107_f.func_203017_r(true);
            this.animal.resetInLove();
            this.targetMate.resetInLove();
            Random random = this.animal.getRNG();

            if (this.world.getGameRules().getBoolean("doMobLoot"))
            {
                this.world.spawnEntity(new EntityXPOrb(this.world, this.animal.posX, this.animal.posY, this.animal.posZ, random.nextInt(7) + 1));
            }
        }
    }

    static class AIPanic extends EntityAIPanic
    {
        AIPanic(EntityTurtle p_i48816_1_, double p_i48816_2_)
        {
            super(p_i48816_1_, p_i48816_2_);
        }

        public boolean shouldExecute()
        {
            if (this.creature.getRevengeTarget() == null && !this.creature.isBurning())
            {
                return false;
            }
            else
            {
                BlockPos blockpos = this.getRandPos(this.creature.world, this.creature, 7, 4);

                if (blockpos != null)
                {
                    this.randPosX = (double)blockpos.getX();
                    this.randPosY = (double)blockpos.getY();
                    this.randPosZ = (double)blockpos.getZ();
                    return true;
                }
                else
                {
                    return this.findRandomPosition();
                }
            }
        }
    }

    static class AIPlayerTempt extends EntityAIBase
    {
        private final EntityTurtle field_203132_a;
        private final double field_203133_b;
        private EntityPlayer field_203134_c;
        private int field_203135_d;
        private final Set<Item> field_203136_e;

        AIPlayerTempt(EntityTurtle p_i48812_1_, double p_i48812_2_, Item p_i48812_4_)
        {
            this.field_203132_a = p_i48812_1_;
            this.field_203133_b = p_i48812_2_;
            this.field_203136_e = Sets.newHashSet(p_i48812_4_);
            this.setMutexBits(3);
        }

        public boolean shouldExecute()
        {
            if (this.field_203135_d > 0)
            {
                --this.field_203135_d;
                return false;
            }
            else
            {
                this.field_203134_c = this.field_203132_a.world.getClosestPlayerToEntity(this.field_203132_a, 10.0D);

                if (this.field_203134_c == null)
                {
                    return false;
                }
                else
                {
                    return this.func_203131_a(this.field_203134_c.getHeldItemMainhand()) || this.func_203131_a(this.field_203134_c.getHeldItemOffhand());
                }
            }
        }

        private boolean func_203131_a(ItemStack p_203131_1_)
        {
            return this.field_203136_e.contains(p_203131_1_.getItem());
        }

        public boolean shouldContinueExecuting()
        {
            return this.shouldExecute();
        }

        public void resetTask()
        {
            this.field_203134_c = null;
            this.field_203132_a.getNavigator().clearPath();
            this.field_203135_d = 100;
        }

        public void updateTask()
        {
            this.field_203132_a.getLookHelper().setLookPositionWithEntity(this.field_203134_c, (float)(this.field_203132_a.getHorizontalFaceSpeed() + 20), (float)this.field_203132_a.getVerticalFaceSpeed());

            if (this.field_203132_a.getDistanceSq(this.field_203134_c) < 6.25D)
            {
                this.field_203132_a.getNavigator().clearPath();
            }
            else
            {
                this.field_203132_a.getNavigator().tryMoveToEntityLiving(this.field_203134_c, this.field_203133_b);
            }
        }
    }

    static class AITravel extends EntityAIBase
    {
        private final EntityTurtle field_203137_a;
        private final double field_203138_b;
        private boolean field_203139_c;

        AITravel(EntityTurtle p_i48811_1_, double p_i48811_2_)
        {
            this.field_203137_a = p_i48811_1_;
            this.field_203138_b = p_i48811_2_;
        }

        public boolean shouldExecute()
        {
            return !this.field_203137_a.func_203022_dF() && !this.field_203137_a.func_203020_dx() && this.field_203137_a.isInWater();
        }

        public void startExecuting()
        {
            int i = 512;
            int j = 4;
            Random random = this.field_203137_a.rand;
            int k = random.nextInt(1025) - 512;
            int l = random.nextInt(9) - 4;
            int i1 = random.nextInt(1025) - 512;

            if ((double)l + this.field_203137_a.posY > (double)(this.field_203137_a.world.getSeaLevel() - 1))
            {
                l = 0;
            }

            BlockPos blockpos = new BlockPos((double)k + this.field_203137_a.posX, (double)l + this.field_203137_a.posY, (double)i1 + this.field_203137_a.posZ);
            this.field_203137_a.func_203019_h(blockpos);
            this.field_203137_a.func_203021_u(true);
            this.field_203139_c = false;
        }

        public void updateTask()
        {
            if (this.field_203137_a.func_203016_dz() || this.field_203137_a.getNavigator().noPath())
            {
                BlockPos blockpos = this.field_203137_a.func_203013_dB();
                Vec3d vec3d = RandomPositionGenerator.func_203155_a(this.field_203137_a, 16, 3, new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()), (Math.PI / 10D));

                if (vec3d == null)
                {
                    vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.field_203137_a, 8, 7, new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()));
                }

                if (vec3d != null)
                {
                    int i = MathHelper.floor(vec3d.x);
                    int j = MathHelper.floor(vec3d.z);
                    int k = 34;
                    MutableBoundingBox mutableboundingbox = new MutableBoundingBox(i - 34, 0, j - 34, i + 34, 0, j + 34);

                    if (!this.field_203137_a.world.isAreaLoaded(mutableboundingbox))
                    {
                        vec3d = null;
                    }
                }

                if (vec3d == null)
                {
                    this.field_203139_c = true;
                    return;
                }

                this.field_203137_a.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.field_203138_b);
            }
        }

        public boolean shouldContinueExecuting()
        {
            return this.field_203137_a.getDistanceSq(this.field_203137_a.func_203013_dB()) >= 36.0D && !this.field_203139_c && !this.field_203137_a.func_203022_dF() && !this.field_203137_a.isInLove() && !this.field_203137_a.func_203020_dx();
        }

        public void resetTask()
        {
            this.field_203137_a.func_203021_u(false);
            super.resetTask();
        }
    }

    static class AIWander extends EntityAIWander
    {
        private final EntityTurtle field_203123_h;

        private AIWander(EntityTurtle p_i48813_1_, double p_i48813_2_, int p_i48813_4_)
        {
            super(p_i48813_1_, p_i48813_2_, p_i48813_4_);
            this.field_203123_h = p_i48813_1_;
        }

        public boolean shouldExecute()
        {
            return !this.entity.isInWater() && !this.field_203123_h.func_203022_dF() && !this.field_203123_h.func_203020_dx() ? super.shouldExecute() : false;
        }
    }

    static class MoveHelper extends EntityMoveHelper
    {
        private final EntityTurtle field_203103_i;

        MoveHelper(EntityTurtle p_i48817_1_)
        {
            super(p_i48817_1_);
            this.field_203103_i = p_i48817_1_;
        }

        private void func_203102_g()
        {
            if (this.field_203103_i.isInWater())
            {
                this.field_203103_i.motionY += 0.005D;

                if (this.field_203103_i.getDistanceSq(this.field_203103_i.func_203018_dA()) > 256.0D)
                {
                    this.field_203103_i.setAIMoveSpeed(Math.max(this.field_203103_i.getAIMoveSpeed() / 2.0F, 0.08F));
                }

                if (this.field_203103_i.isChild())
                {
                    this.field_203103_i.setAIMoveSpeed(Math.max(this.field_203103_i.getAIMoveSpeed() / 3.0F, 0.06F));
                }
            }
            else if (this.field_203103_i.onGround)
            {
                this.field_203103_i.setAIMoveSpeed(Math.max(this.field_203103_i.getAIMoveSpeed() / 2.0F, 0.06F));
            }
        }

        public void onUpdateMoveHelper()
        {
            this.func_203102_g();

            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.field_203103_i.getNavigator().noPath())
            {
                double d0 = this.posX - this.field_203103_i.posX;
                double d1 = this.posY - this.field_203103_i.posY;
                double d2 = this.posZ - this.field_203103_i.posZ;
                double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                this.field_203103_i.rotationYaw = this.limitAngle(this.field_203103_i.rotationYaw, f, 90.0F);
                this.field_203103_i.renderYawOffset = this.field_203103_i.rotationYaw;
                float f1 = (float)(this.speed * this.field_203103_i.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.field_203103_i.setAIMoveSpeed(this.field_203103_i.getAIMoveSpeed() + (f1 - this.field_203103_i.getAIMoveSpeed()) * 0.125F);
                this.field_203103_i.motionY += (double)this.field_203103_i.getAIMoveSpeed() * d1 * 0.1D;
            }
            else
            {
                this.field_203103_i.setAIMoveSpeed(0.0F);
            }
        }
    }

    static class PathNavigater extends PathNavigateSwimmer
    {
        PathNavigater(EntityTurtle p_i48815_1_, World p_i48815_2_)
        {
            super(p_i48815_1_, p_i48815_2_);
        }

        protected boolean canNavigate()
        {
            return true;
        }

        protected PathFinder getPathFinder()
        {
            return new PathFinder(new WalkAndSwimNodeProcessor());
        }

        public boolean canEntityStandOnPos(BlockPos pos)
        {
            if (this.entity instanceof EntityTurtle)
            {
                EntityTurtle entityturtle = (EntityTurtle)this.entity;

                if (entityturtle.func_203014_dG())
                {
                    return this.world.getBlockState(pos).getBlock() == Blocks.WATER;
                }
            }

            return !this.world.getBlockState(pos.down()).func_196958_f();
        }
    }
}
*/
