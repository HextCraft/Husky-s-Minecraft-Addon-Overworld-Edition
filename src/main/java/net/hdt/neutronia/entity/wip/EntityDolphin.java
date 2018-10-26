package net.hdt.neutronia.entity.wip;

import net.minecraft.world.IBlockView;
import net.minecraft.tag.FluidTags;
import net.minecraft.entity.ai.EntityTargetGenerator;
import net.minecraft.potion.PotionEffect;
import net.minecraft.reference.PotionEffectTypes;
import java.util.List;
import net.minecraft.entity.ai.action.ActionMove;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import java.util.Random;
import net.minecraft.entity.MovementType;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.sound.Sound;
import net.minecraft.reference.Biomes;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Hand;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.particle.config.ParticleConfig;
import net.minecraft.client.particle.Particles;
import net.minecraft.util.math.MathUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.reference.Sounds;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.ai.pathing.EntityNavigationAquatic;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.entity.ai.AiGoalGangAttack;
import net.minecraft.entity.ai.AiAvoidEntity;
import net.minecraft.entity.mob.EntityGuardian;
import net.minecraft.entity.ai.AiGoalFollowEntityOnBoat;
import net.minecraft.entity.ai.mob.AiGoalMeleeAttack;
import net.minecraft.entity.ai.AiGoalDolphinJump;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.AiGoalWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.AiGoalIdleGaze;
import net.minecraft.entity.ai.AiGoalRoamAquatic;
import net.minecraft.entity.ai.AiGoalDolphinSwim;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.PathfinerMob;
import net.minecraft.entity.ai.passive.AiGoalWaterJump;
import net.minecraft.nbt.NBTCompound;
import javax.annotation.Nullable;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.entity.mob.EntityMob;
import net.minecraft.entity.ai.action.ActionDolphinLook;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.EntityItem;
import java.util.function.Predicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.tracker.TrackedData;
import net.minecraft.entity.mob.EntityAquatic;

public class EntityDolphin extends EntityAquatic
{
    private static final TrackedData<BlockPos> b;
    private static final TrackedData<Boolean> c;
    private static final TrackedData<Integer> bC;
    public static final Predicate<EntityItem> a;
    
    public EntityDolphin(final World aWorld) {
        super(EntityType.DOLPHIN, aWorld);
        this.setSize(0.9f, 0.6f);
        this.actionMove = new a(this);
        this.actionLook = new ActionDolphinLook(this, 10);
        this.setCanPickUpLoot(true);
    }
    
    @Nullable
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable final IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        this.setBreath(this.bh());
        this.pitch = 0.0f;
        return super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
    }
    
    @Override
    public boolean cc() {
        return false;
    }
    
    @Override
    protected void a(final int aInteger) {
    }
    
    public void g(final BlockPos aBlockPos) {
        this.dataTracker.<BlockPos>set(EntityDolphin.b, aBlockPos);
    }
    
    public BlockPos getTreasurePos() {
        return this.dataTracker.<BlockPos>get(EntityDolphin.b);
    }
    
    public boolean dz() {
        return this.dataTracker.<Boolean>get(EntityDolphin.c);
    }
    
    public void a(final boolean aBoolean) {
        this.dataTracker.<Boolean>set(EntityDolphin.c, aBoolean);
    }
    
    public int dA() {
        return this.dataTracker.<Integer>get(EntityDolphin.bC);
    }
    
    public void b(final int aInteger) {
        this.dataTracker.<Integer>set(EntityDolphin.bC, aInteger);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<BlockPos>startTracking(EntityDolphin.b, BlockPos.ORIGIN);
        this.dataTracker.<Boolean>startTracking(EntityDolphin.c, false);
        this.dataTracker.<Integer>startTracking(EntityDolphin.bC, 2400);
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setInt("TreasurePosX", this.getTreasurePos().getX());
        aNBTCompound.setInt("TreasurePosY", this.getTreasurePos().getY());
        aNBTCompound.setInt("TreasurePosZ", this.getTreasurePos().getZ());
        aNBTCompound.setBoolean("GotFish", this.dz());
        aNBTCompound.setInt("Moistness", this.dA());
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        final int vInteger2 = aNBTCompound.getInt("TreasurePosX");
        final int vInteger3 = aNBTCompound.getInt("TreasurePosY");
        final int vInteger4 = aNBTCompound.getInt("TreasurePosZ");
        this.g(new BlockPos(vInteger2, vInteger3, vInteger4));
        super.deserializeCustomData(aNBTCompound);
        this.a(aNBTCompound.getBoolean("GotFish"));
        this.b(aNBTCompound.getInt("Moistness"));
    }
    
    @Override
    protected void n() {
        this.goalSelector.add(0, new AiGoalWaterJump(this));
        this.goalSelector.add(0, new AiGoalDolphinSwim(this));
        this.goalSelector.add(1, new b(this));
        this.goalSelector.add(2, new c(this, 4.0));
        this.goalSelector.add(4, new AiGoalRoamAquatic(this, 1.0, 10));
        this.goalSelector.add(4, new AiGoalIdleGaze(this));
        this.goalSelector.add(5, new AiGoalWatchClosest(this, EntityPlayer.class, 6.0f));
        this.goalSelector.add(5, new AiGoalDolphinJump(this, 10));
        this.goalSelector.add(6, new AiGoalMeleeAttack(this, 1.2000000476837158, true));
        this.goalSelector.add(8, new d());
        this.goalSelector.add(8, new AiGoalFollowEntityOnBoat(this));
        this.goalSelector.add(9, new AiAvoidEntity<Object>(this, EntityGuardian.class, 8.0f, 1.0, 1.0));
        this.targetSelector.add(1, new AiGoalGangAttack(this, (Class<?>[])new Class[] { EntityGuardian.class }).a((Class<?>[])new Class[0]));
    }
    
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(AttributeManager.MAX_HEALTH).setBaseValue(10.0);
        this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(1.2000000476837158);
        this.getAttributeContainer().register(AttributeManager.ATTACK_DAMAGE);
        this.getAttributeInstance(AttributeManager.ATTACK_DAMAGE).setBaseValue(3.0);
    }
    
    @Override
    protected EntityNavigation createNavigation(final World aWorld) {
        return new EntityNavigationAquatic(this, aWorld);
    }
    
    @Override
    public boolean attackEntity(final Entity aEntity) {
        final boolean vBoolean2 = aEntity.applyDamage(DamageSource.byMob(this), (int)this.getAttributeInstance(AttributeManager.ATTACK_DAMAGE).getValue());
        if (vBoolean2) {
            this.a(this, aEntity);
            this.playSoundAtEntity(Sounds.ENTITY_DOLPHIN_ATTACK, 1.0f, 1.0f);
        }
        return vBoolean2;
    }
    
    @Override
    public int bh() {
        return 4800;
    }
    
    @Override
    protected int l(final int aInteger) {
        return this.bh();
    }
    
    @Override
    public float getEyeHeight() {
        return 0.3f;
    }
    
    @Override
    public int L() {
        return 1;
    }
    
    @Override
    public int M() {
        return 1;
    }
    
    @Override
    protected boolean canStartRiding(final Entity aEntity) {
        return true;
    }
    
    @Override
    protected void a(final EntityItem aEntityItem) {
        if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
            final ItemStack vItemStack2 = aEntityItem.getStack();
            if (this.d(vItemStack2)) {
                this.setEquippedStack(EquipmentSlot.MAINHAND, vItemStack2);
                this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 2.0f;
                this.a(aEntityItem, vItemStack2.getAmount());
                aEntityItem.invalidate();
            }
        }
    }
    
    @Override
    public void update() {
        super.update();
        if (this.isAiDisabled()) {
            return;
        }
        if (this.ar()) {
            this.b(2400);
        }
        else {
            this.b(this.dA() - 1);
            if (this.dA() <= 0) {
                this.applyDamage(DamageSource.DRYOUT, 1.0f);
            }
            if (this.onGround) {
                this.s += 0.5;
                this.r += (this.rand.nextFloat() * 2.0f - 1.0f) * 0.2f;
                this.t += (this.rand.nextFloat() * 2.0f - 1.0f) * 0.2f;
                this.yaw = this.rand.nextFloat() * 360.0f;
                this.onGround = false;
                this.velocityDirty = true;
            }
        }
        if (this.world.isClient && this.isSwimming() && this.r * this.r + this.s * this.s + this.t * this.t > 0.03) {
            final Vec3d vVec3d1 = this.f(0.0f);
            final float vFloat2 = MathUtils.cos(this.yaw * 0.017453292f) * 0.3f;
            final float vFloat3 = MathUtils.sin(this.yaw * 0.017453292f) * 0.3f;
            final float vFloat4 = 1.2f - this.rand.nextFloat() * 0.7f;
            for (int vInteger5 = 0; vInteger5 < 2; ++vInteger5) {
                this.world.a(Particles.DOLPHIN, this.x - vVec3d1.x * vFloat4 + vFloat2, this.y - vVec3d1.y, this.z - vVec3d1.z * vFloat4 + vFloat3, 0.0, 0.0, 0.0);
                this.world.a(Particles.DOLPHIN, this.x - vVec3d1.x * vFloat4 - vFloat2, this.y - vVec3d1.y, this.z - vVec3d1.z * vFloat4 - vFloat3, 0.0, 0.0, 0.0);
            }
        }
    }
    
    @Sided(Side.CLIENT)
    @Override
    public void a(final byte aByte) {
        if (aByte == 38) {
            this.a(Particles.HAPPY_VILLAGER);
        }
        else {
            super.a(aByte);
        }
    }
    
    @Sided(Side.CLIENT)
    private void a(final ParticleConfig aParticleConfig) {
        for (int vInteger2 = 0; vInteger2 < 7; ++vInteger2) {
            final double vDouble3 = this.rand.nextGaussian() * 0.01;
            final double vDouble4 = this.rand.nextGaussian() * 0.01;
            final double vDouble5 = this.rand.nextGaussian() * 0.01;
            this.world.a(aParticleConfig, this.x + this.rand.nextFloat() * this.width * 2.0f - this.width, this.y + 0.20000000298023224 + this.rand.nextFloat() * this.height, this.z + this.rand.nextFloat() * this.width * 2.0f - this.width, vDouble3, vDouble4, vDouble5);
        }
    }
    
    @Override
    protected boolean interactMob(final EntityPlayer aEntityPlayer1, final Hand aHand2) {
        final ItemStack vItemStack3 = aEntityPlayer1.getStackInHand(aHand2);
        if (!vItemStack3.isEmpty() && vItemStack3.getItem().a(ItemTags.FISHES)) {
            if (!this.world.isClient) {
                this.playSoundAtEntity(Sounds.ENTITY_DOLPHIN_EAT, 1.0f, 1.0f);
            }
            this.a(true);
            if (!aEntityPlayer1.abilities.creativeMode) {
                vItemStack3.subtract(1);
            }
            return true;
        }
        return super.interactMob(aEntityPlayer1, aHand2);
    }
    
    @Nullable
    public EntityItem f(final ItemStack aItemStack) {
        if (aItemStack.isEmpty()) {
            return null;
        }
        final double vDouble2 = this.y - 0.30000001192092896 + this.getEyeHeight();
        final EntityItem vEntityItem4 = new EntityItem(this.world, this.x, vDouble2, this.z, aItemStack);
        vEntityItem4.setPickupDelay(40);
        vEntityItem4.c(this.getUuid());
        float vFloat5 = 0.3f;
        vEntityItem4.r = -MathUtils.sin(this.yaw * 0.017453292f) * MathUtils.cos(this.pitch * 0.017453292f) * vFloat5;
        vEntityItem4.s = MathUtils.sin(this.pitch * 0.017453292f) * vFloat5 * 1.5f;
        vEntityItem4.t = MathUtils.cos(this.yaw * 0.017453292f) * MathUtils.cos(this.pitch * 0.017453292f) * vFloat5;
        final float vFloat6 = this.rand.nextFloat() * 6.2831855f;
        vFloat5 = 0.02f * this.rand.nextFloat();
        final EntityItem entityItem = vEntityItem4;
        entityItem.r += MathUtils.cos(vFloat6) * vFloat5;
        final EntityItem entityItem2 = vEntityItem4;
        entityItem2.t += MathUtils.sin(vFloat6) * vFloat5;
        this.world.spawnEntity(vEntityItem4);
        return vEntityItem4;
    }
    
    @Override
    public boolean canSpawnHere(final IWorld aIWorld1, final boolean aBoolean2) {
        return (this.y > 45.0 && this.y < aIWorld1.getSeaLevel() && aIWorld1.getBiome(new BlockPos(this)) != Biomes.OCEAN) || (aIWorld1.getBiome(new BlockPos(this)) != Biomes.z && super.canSpawnHere(aIWorld1, aBoolean2));
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.ENTITY_DOLPHIN_HURT;
    }
    
    @Nullable
    @Override
    protected Sound getSoundDeath() {
        return Sounds.ENTITY_DOLPHIN_DEATH;
    }
    
    @Nullable
    @Override
    protected Sound getSoundAmbient() {
        return this.isSwimming() ? Sounds.ENTITY_DOLPHIN_AMBIENT_WATER : Sounds.ENTITY_DOLPHIN_AMBIENT;
    }
    
    @Override
    protected Sound getSoundSplash() {
        return Sounds.ENTITY_DOLPHIN_SPLASH;
    }
    
    @Override
    protected Sound getSoundSwim() {
        return Sounds.ENTITY_DOLPHIN_SWIM;
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_DOLPHIN;
    }
    
    protected boolean dB() {
        final BlockPos vBlockPos1 = this.t().i();
        return vBlockPos1 != null && this.squaredDistanceTo(vBlockPos1) < 144.0;
    }
    
    @Override
    public void a(final float aFloat1, final float aFloat2, final float aFloat3) {
        if (this.cR() && this.isSwimming()) {
            this.a(aFloat1, aFloat2, aFloat3, this.cM());
            this.move(MovementType.SELF, this.r, this.s, this.t);
            this.r *= 0.8999999761581421;
            this.s *= 0.8999999761581421;
            this.t *= 0.8999999761581421;
            if (this.getTarget() == null) {
                this.s -= 0.005;
            }
        }
        else {
            super.a(aFloat1, aFloat2, aFloat3);
        }
    }
    
    @Override
    public boolean canBeLeashedBy(final EntityPlayer aEntityPlayer) {
        return true;
    }
    
    static {
        b = DataTracker.<BlockPos>registerData(EntityDolphin.class, TrackedDataHandlerRegistry.BLOCK_POS);
        c = DataTracker.<Boolean>registerData(EntityDolphin.class, TrackedDataHandlerRegistry.BOOLEAN);
        bC = DataTracker.<Integer>registerData(EntityDolphin.class, TrackedDataHandlerRegistry.INTEGER);
        a = (aEntityItem -> !aEntityItem.q() && aEntityItem.isValid() && aEntityItem.isSwimming());
    }
    
    static class a extends ActionMove
    {
        private final EntityDolphin i;
        
        public a(final EntityDolphin aEntityDolphin) {
            super(aEntityDolphin);
            this.i = aEntityDolphin;
        }
        
        @Override
        public void tick() {
            if (this.i.isSwimming()) {
                final EntityDolphin i = this.i;
                i.s += 0.005;
            }
            if (this.currentState != State.MOVE_TO || this.i.t().isInactive()) {
                this.i.o(0.0f);
                this.i.t(0.0f);
                this.i.s(0.0f);
                this.i.r(0.0f);
                return;
            }
            final double vDouble1 = this.targetX - this.i.x;
            final double vDouble2 = this.targetY - this.i.y;
            final double vDouble3 = this.targetZ - this.i.z;
            final double vDouble4 = vDouble1 * vDouble1 + vDouble2 * vDouble2 + vDouble3 * vDouble3;
            if (vDouble4 < 2.500000277905201E-7) {
                this.owner.r(0.0f);
                return;
            }
            final float vFloat9 = (float)(MathUtils.atan2(vDouble3, vDouble1) * 57.2957763671875) - 90.0f;
            this.i.yaw = this.a(this.i.yaw, vFloat9, 10.0f);
            this.i.aO = this.i.yaw;
            this.i.headPitch = this.i.yaw;
            final float vFloat10 = (float)(this.speedFactor * this.i.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).getValue());
            if (this.i.isSwimming()) {
                this.i.o(vFloat10 * 0.02f);
                float vFloat11 = -(float)(MathUtils.atan2(vDouble2, MathUtils.sqrt(vDouble1 * vDouble1 + vDouble3 * vDouble3)) * 57.2957763671875);
                vFloat11 = MathUtils.clamp(MathUtils.wrapDegrees(vFloat11), -85.0f, 85.0f);
                this.i.pitch = this.a(this.i.pitch, vFloat11, 5.0f);
                final float vFloat12 = MathUtils.cos(this.i.pitch * 0.017453292f);
                final float vFloat13 = MathUtils.sin(this.i.pitch * 0.017453292f);
                this.i.bh = vFloat12 * vFloat10;
                this.i.bg = -vFloat13 * vFloat10;
            }
            else {
                this.i.o(vFloat10 * 0.1f);
            }
        }
    }
    
    class d extends AiGoal
    {
        private int b;
        
        private d() {
        }
        
        @Override
        public boolean canStart() {
            if (this.b > EntityDolphin.this.age) {
                return false;
            }
            final List<EntityItem> vList1 = EntityDolphin.this.world.<EntityItem>a((Class<? extends EntityItem>)EntityItem.class, EntityDolphin.this.getBoundingBox().expand(8.0, 8.0, 8.0), (Predicate<? super EntityItem>)EntityDolphin.a);
            return !vList1.isEmpty() || !EntityDolphin.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
        }
        
        @Override
        public void start() {
            final List<EntityItem> vList1 = EntityDolphin.this.world.<EntityItem>a((Class<? extends EntityItem>)EntityItem.class, EntityDolphin.this.getBoundingBox().expand(8.0, 8.0, 8.0), (Predicate<? super EntityItem>)EntityDolphin.a);
            if (!vList1.isEmpty()) {
                EntityDolphin.this.t().moveTo(vList1.get(0), 1.2000000476837158);
                EntityDolphin.this.playSoundAtEntity(Sounds.ENTITY_DOLPHIN_PLAY, 1.0f, 1.0f);
            }
            this.b = 0;
        }
        
        @Override
        public void stop() {
            final ItemStack vItemStack1 = EntityDolphin.this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (!vItemStack1.isEmpty()) {
                EntityDolphin.this.f(vItemStack1);
                EntityDolphin.this.setEquippedStack(EquipmentSlot.MAINHAND, ItemStack.NULL_STACK);
                this.b = EntityDolphin.this.age + EntityDolphin.this.rand.nextInt(100);
            }
        }
        
        @Override
        public void tick() {
            final List<EntityItem> vList1 = EntityDolphin.this.world.<EntityItem>a((Class<? extends EntityItem>)EntityItem.class, EntityDolphin.this.getBoundingBox().expand(8.0, 8.0, 8.0), (Predicate<? super EntityItem>)EntityDolphin.a);
            final ItemStack vItemStack2 = EntityDolphin.this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (!vItemStack2.isEmpty()) {
                EntityDolphin.this.f(vItemStack2);
                EntityDolphin.this.setEquippedStack(EquipmentSlot.MAINHAND, ItemStack.NULL_STACK);
            }
            else if (!vList1.isEmpty()) {
                EntityDolphin.this.t().moveTo(vList1.get(0), 1.2000000476837158);
            }
        }
    }
    
    static class c extends AiGoal
    {
        private final EntityDolphin a;
        private final double b;
        private EntityPlayer c;
        
        c(final EntityDolphin aEntityDolphin1, final double aDouble2) {
            this.a = aEntityDolphin1;
            this.b = aDouble2;
            this.setCategoryBits(3);
        }
        
        @Override
        public boolean canStart() {
            this.c = this.a.world.a(this.a, 10.0);
            return this.c != null && this.c.bd();
        }
        
        @Override
        public boolean shouldContinue() {
            return this.c != null && this.c.bd() && this.a.squaredDistanceTo(this.c) < 256.0;
        }
        
        @Override
        public void start() {
            this.c.addPotionEffect(new PotionEffect(PotionEffectTypes.D, 100));
        }
        
        @Override
        public void stop() {
            this.c = null;
            this.a.t().clearPath();
        }
        
        @Override
        public void tick() {
            this.a.getActionLook().lookAt(this.c, this.a.M() + 20, this.a.L());
            if (this.a.squaredDistanceTo(this.c) < 6.25) {
                this.a.t().clearPath();
            }
            else {
                this.a.t().moveTo(this.c, this.b);
            }
            if (this.c.bd() && this.c.world.rand.nextInt(6) == 0) {
                this.c.addPotionEffect(new PotionEffect(PotionEffectTypes.D, 100));
            }
        }
    }
    
    static class b extends AiGoal
    {
        private final EntityDolphin a;
        private boolean b;
        
        b(final EntityDolphin aEntityDolphin) {
            this.a = aEntityDolphin;
            this.setCategoryBits(3);
        }
        
        @Override
        public boolean canStop() {
            return false;
        }
        
        @Override
        public boolean canStart() {
            return this.a.dz() && this.a.getBreath() >= 100;
        }
        
        @Override
        public boolean shouldContinue() {
            final BlockPos vBlockPos1 = this.a.getTreasurePos();
            return this.a.squaredDistanceTo(new BlockPos(vBlockPos1.getX(), this.a.y, vBlockPos1.getZ())) > 16.0 && !this.b && this.a.getBreath() >= 100;
        }
        
        @Override
        public void start() {
            this.b = false;
            this.a.t().clearPath();
            final World vWorld1 = this.a.world;
            final BlockPos vBlockPos2 = new BlockPos(this.a);
            final String vString3 = (vWorld1.rand.nextFloat() >= 0.5) ? "Ocean_Ruin" : "Shipwreck";
            final BlockPos vBlockPos3 = vWorld1.a(vString3, vBlockPos2, 50, false);
            if (vBlockPos3 == null) {
                final BlockPos vBlockPos4 = vWorld1.a(vString3.equals("Ocean_Ruin") ? "Shipwreck" : "Ocean_Ruin", vBlockPos2, 50, false);
                if (vBlockPos4 == null) {
                    this.b = true;
                    return;
                }
                this.a.g(vBlockPos4);
            }
            else {
                this.a.g(vBlockPos3);
            }
            vWorld1.a(this.a, (byte)38);
        }
        
        @Override
        public void stop() {
            final BlockPos vBlockPos1 = this.a.getTreasurePos();
            if (this.a.squaredDistanceTo(new BlockPos(vBlockPos1.getX(), this.a.y, vBlockPos1.getZ())) <= 16.0 || this.b) {
                this.a.a(false);
            }
        }
        
        @Override
        public void tick() {
            final BlockPos vBlockPos1 = this.a.getTreasurePos();
            final World vWorld2 = this.a.world;
            if (this.a.dB() || this.a.t().isInactive()) {
                Vec3d vVec3d3 = EntityTargetGenerator.findPositionTowards(this.a, 16, 1, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()), 0.39269909262657166);
                if (vVec3d3 == null) {
                    vVec3d3 = EntityTargetGenerator.findPositionTowards(this.a, 8, 4, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()));
                }
                if (vVec3d3 != null) {
                    final BlockPos vBlockPos2 = new BlockPos(vVec3d3);
                    if (!vWorld2.getFluidState(vBlockPos2).hasTag(FluidTags.WATER) || !vWorld2.h(vBlockPos2).a(vWorld2, vBlockPos2, ccm.WATER)) {
                        vVec3d3 = EntityTargetGenerator.findPositionTowards(this.a, 8, 5, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()));
                    }
                }
                if (vVec3d3 == null) {
                    this.b = true;
                    return;
                }
                this.a.getActionLook().lookAt(vVec3d3.x, vVec3d3.y, vVec3d3.z, this.a.M() + 20, this.a.L());
                this.a.t().moveTo(vVec3d3.x, vVec3d3.y, vVec3d3.z, 1.3);
                if (vWorld2.rand.nextInt(80) == 0) {
                    vWorld2.a(this.a, (byte)38);
                }
            }
        }
    }
}
