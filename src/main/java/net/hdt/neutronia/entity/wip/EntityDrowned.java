package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.entity.ai.action.ActionMove;
import net.minecraft.entity.ai.mob.AiGoalZombieAttack;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.AiGoalMoveToBlock;
import net.minecraft.entity.ai.EntityTargetGenerator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.ai.AiGoalArrowAttack;
import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.projectile.EntityTrident;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.Entity;
import net.minecraft.world.IWorldReadable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.reference.Sounds;
import net.minecraft.sound.Sound;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.reference.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.reference.Items;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.nbt.NBTCompound;
import javax.annotation.Nullable;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.passive.EntityTurtle;
import net.minecraft.entity.passive.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import java.util.function.Predicate;
import net.minecraft.entity.ai.mob.AiGoalTrackAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.AiGoalGangAttack;
import net.minecraft.entity.ai.AiGoalRoam;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.PathfinerMob;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.pathing.EntityMobNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigationAquatic;
import net.minecraft.entity.ai.IDistantAttacker;

public class EntityDrowned extends EntityZombie implements IDistantAttacker
{
    private boolean bC;
    protected final EntityNavigationAquatic a;
    protected final EntityMobNavigation b;
    
    public EntityDrowned(final World aWorld) {
        super(EntityType.DROWNED, aWorld);
        this.stepHeight = 1.0f;
        this.actionMove = new e(this);
        this.setPathNodeTypeWeight(PathNodeType.WATER, 0.0f);
        this.a = new EntityNavigationAquatic(this, aWorld);
        this.b = new EntityMobNavigation(this, aWorld);
    }
    
    @Override
    protected void l() {
        this.goalSelector.add(1, new d(this, 1.0));
        this.goalSelector.add(2, new g(this, 1.0, 40, 10.0f));
        this.goalSelector.add(2, new a(this, 1.0, false));
        this.goalSelector.add(5, new c(this, 1.0));
        this.goalSelector.add(6, new f(this, 1.0, this.world.getSeaLevel()));
        this.goalSelector.add(7, new AiGoalRoam(this, 1.0));
        this.targetSelector.add(1, new AiGoalGangAttack(this, (Class<?>[])new Class[] { EntityDrowned.class }).a(EntityPigZombie.class));
        this.targetSelector.add(2, new AiGoalTrackAttackableTarget<Object>(this, EntityPlayer.class, 10, true, false, new b(this)));
        this.targetSelector.add(3, new AiGoalTrackAttackableTarget<Object>(this, EntityVillager.class, false));
        this.targetSelector.add(3, new AiGoalTrackAttackableTarget<Object>(this, EntityIronGolem.class, true));
        this.targetSelector.add(5, new AiGoalTrackAttackableTarget<Object>(this, EntityTurtle.class, 10, true, false, EntityTurtle.bC));
    }
    
    @Override
    protected EntityNavigation createNavigation(final World aWorld) {
        return super.createNavigation(aWorld);
    }
    
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        aIEntityData3 = super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
        if (this.getEquippedStack(EquipmentSlot.OFFHAND).isEmpty() && this.rand.nextFloat() < 0.03f) {
            this.setEquippedStack(EquipmentSlot.OFFHAND, new ItemStack(Items.pm));
            this.handDropChances[EquipmentSlot.OFFHAND.getEntitySlotId()] = 2.0f;
        }
        return aIEntityData3;
    }
    
    @Override
    public boolean canSpawnHere(final IWorld aIWorld1, final boolean aBoolean2) {
        final Biome vBiome3 = aIWorld1.getBiome(new BlockPos(this.x, this.y, this.z));
        if (vBiome3 == Biomes.i || vBiome3 == Biomes.m) {
            return this.rand.nextInt(15) == 0 && super.canSpawnHere(aIWorld1, aBoolean2);
        }
        return this.rand.nextInt(40) == 0 && this.dG() && super.canSpawnHere(aIWorld1, aBoolean2);
    }
    
    private boolean dG() {
        return this.getBoundingBox().minY < this.world.getSeaLevel() - 5;
    }
    
    @Override
    protected boolean dA() {
        return false;
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_DROWNED;
    }
    
    @Override
    protected Sound getSoundAmbient() {
        if (this.isSwimming()) {
            return Sounds.ENTITY_DROWNED_AMBIENT_WATER;
        }
        return Sounds.ENTITY_DROWNED_AMBIENT;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        if (this.isSwimming()) {
            return Sounds.ENTITY_DROWNED_HURT_WATER;
        }
        return Sounds.ENTITY_DROWNED_HURT;
    }
    
    @Override
    protected Sound getSoundDeath() {
        if (this.isSwimming()) {
            return Sounds.ENTITY_DROWNED_DEATH_WATER;
        }
        return Sounds.ENTITY_DROWNED_DEATH;
    }
    
    @Override
    protected Sound getSoundStep() {
        return Sounds.ENTITY_DROWNED_STEP;
    }
    
    @Override
    protected Sound getSoundSwim() {
        return Sounds.ENTITY_DROWNED_SWIM;
    }
    
    @Override
    protected ItemStack getSkull() {
        return ItemStack.NULL_STACK;
    }
    
    @Override
    protected void initEquipment(final LocalDifficulty aLocalDifficulty) {
        if (this.rand.nextFloat() > 0.9) {
            final int vInteger2 = this.rand.nextInt(16);
            if (vInteger2 < 10) {
                this.setEquippedStack(EquipmentSlot.MAINHAND, new ItemStack(Items.pk));
            }
            else {
                this.setEquippedStack(EquipmentSlot.MAINHAND, new ItemStack(Items.kT));
            }
        }
    }
    
    @Override
    protected boolean a(final ItemStack aItemStack1, final ItemStack aItemStack2, final EquipmentSlot aEquipmentSlot3) {
        if (aItemStack2.getItem() == Items.pm) {
            return false;
        }
        if (aItemStack2.getItem() == Items.pk) {
            return aItemStack1.getItem() == Items.pk && aItemStack1.g() < aItemStack2.g();
        }
        return aItemStack1.getItem() == Items.pk || super.a(aItemStack1, aItemStack2, aEquipmentSlot3);
    }
    
    @Override
    protected boolean dD() {
        return false;
    }
    
    @Override
    public boolean a(final IWorldReadable aIWorldReadable) {
        return aIWorldReadable.a_(this, this.getBoundingBox()) && aIWorldReadable.c(this, this.getBoundingBox());
    }
    
    public boolean g(@Nullable final EntityLiving aEntityLiving) {
        return aEntityLiving != null && (!this.world.isDaylight() || aEntityLiving.isSwimming());
    }
    
    @Override
    public boolean by() {
        return !this.bd();
    }
    
    private boolean dJ() {
        if (this.bC) {
            return true;
        }
        final EntityLiving vEntityLiving1 = this.getTarget();
        return vEntityLiving1 != null && vEntityLiving1.isSwimming();
    }
    
    @Override
    public void a(final float aFloat1, final float aFloat2, final float aFloat3) {
        if (this.cR() && this.isSwimming() && this.dJ()) {
            this.a(aFloat1, aFloat2, aFloat3, 0.01f);
            this.move(MovementType.SELF, this.r, this.s, this.t);
            this.r *= 0.8999999761581421;
            this.s *= 0.8999999761581421;
            this.t *= 0.8999999761581421;
        }
        else {
            super.a(aFloat1, aFloat2, aFloat3);
        }
    }
    
    @Override
    public void au() {
        if (!this.world.isClient) {
            if (this.cR() && this.isSwimming() && this.dJ()) {
                this.navigation = this.a;
                this.g(true);
            }
            else {
                this.navigation = this.b;
                this.g(false);
            }
        }
    }
    
    protected boolean dE() {
        final Path vPath1 = this.t().getCurrentPath();
        if (vPath1 != null) {
            final PathNode vPathNode2 = vPath1.i();
            if (vPathNode2 != null) {
                final double vDouble3 = this.squaredDistanceTo(vPathNode2.a, vPathNode2.b, vPathNode2.c);
                if (vDouble3 < 4.0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public void attackDistant(final EntityLiving aEntityLiving1, final float aFloat2) {
        final EntityTrident vEntityTrident3 = new EntityTrident(this.world, this, new ItemStack(Items.pk));
        final double vDouble4 = aEntityLiving1.x - this.x;
        final double vDouble5 = aEntityLiving1.getBoundingBox().minY + aEntityLiving1.height / 3.0f - vEntityTrident3.y;
        final double vDouble6 = aEntityLiving1.z - this.z;
        final double vDouble7 = MathUtils.sqrt(vDouble4 * vDouble4 + vDouble6 * vDouble6);
        vEntityTrident3.setVelocity(vDouble4, vDouble5 + vDouble7 * 0.20000000298023224, vDouble6, 1.6f, 14 - this.world.getDifficulty().getId() * 4);
        this.playSoundAtEntity(Sounds.ENTITY_DROWNED_SHOOT, 1.0f, 1.0f / (this.getRand().nextFloat() * 0.4f + 0.8f));
        this.world.spawnEntity(vEntityTrident3);
    }
    
    public void a(final boolean aBoolean) {
        this.bC = aBoolean;
    }
    
    static class g extends AiGoalArrowAttack
    {
        private final EntityDrowned a;
        
        public g(final IDistantAttacker aIDistantAttacker1, final double aDouble2, final int aInteger4, final float vFloat5) {
            super(aIDistantAttacker1, aDouble2, aInteger4, vFloat5);
            this.a = (EntityDrowned)aIDistantAttacker1;
        }
        
        @Override
        public boolean canStart() {
            return super.canStart() && this.a.getMainHandStack().getItem() == Items.pk;
        }
        
        @Override
        public void start() {
            super.start();
            this.a.setArmsRaised(true);
        }
        
        @Override
        public void stop() {
            super.stop();
            this.a.setArmsRaised(false);
        }
    }
    
    static class f extends AiGoal
    {
        private final EntityDrowned a;
        private final double b;
        private final int c;
        private boolean d;
        
        public f(final EntityDrowned aEntityDrowned1, final double aDouble2, final int vInteger4) {
            this.a = aEntityDrowned1;
            this.b = aDouble2;
            this.c = vInteger4;
        }
        
        @Override
        public boolean canStart() {
            return !this.a.world.isDaylight() && this.a.isSwimming() && this.a.y < this.c - 2;
        }
        
        @Override
        public boolean shouldContinue() {
            return this.canStart() && !this.d;
        }
        
        @Override
        public void tick() {
            if (this.a.y < this.c - 1 && (this.a.t().isInactive() || this.a.dE())) {
                final Vec3d vVec3d1 = EntityTargetGenerator.findPositionTowards(this.a, 4, 8, new Vec3d(this.a.x, this.c - 1, this.a.z));
                if (vVec3d1 == null) {
                    this.d = true;
                    return;
                }
                this.a.t().moveTo(vVec3d1.x, vVec3d1.y, vVec3d1.z, this.b);
            }
        }
        
        @Override
        public void start() {
            this.a.a(true);
            this.d = false;
        }
        
        @Override
        public void stop() {
            this.a.a(false);
        }
    }
    
    static class c extends AiGoalMoveToBlock
    {
        private final EntityDrowned f;
        
        public c(final EntityDrowned aEntityDrowned1, final double aDouble2) {
            super(aEntityDrowned1, aDouble2, 8, 2);
            this.f = aEntityDrowned1;
        }
        
        @Override
        public boolean canStart() {
            return super.canStart() && !this.f.world.isDaylight() && this.f.isSwimming() && this.f.y >= this.f.world.getSeaLevel() - 3;
        }
        
        @Override
        public boolean shouldContinue() {
            return super.shouldContinue();
        }
        
        @Override
        protected boolean a(final IWorldReadable aIWorldReadable1, final BlockPos aBlockPos2) {
            final BlockPos vBlockPos3 = aBlockPos2.up();
            return aIWorldReadable1.isAir(vBlockPos3) && aIWorldReadable1.isAir(vBlockPos3.up()) && aIWorldReadable1.h(aBlockPos2).isTopSolid();
        }
        
        @Override
        public void start() {
            this.f.a(false);
            this.f.navigation = this.f.b;
            super.start();
        }
        
        @Override
        public void stop() {
            super.stop();
        }
    }
    
    static class d extends AiGoal
    {
        private final PathfinerMob a;
        private double b;
        private double c;
        private double d;
        private final double e;
        private final World f;
        
        public d(final PathfinerMob aPathfinerMob1, final double aDouble2) {
            this.a = aPathfinerMob1;
            this.e = aDouble2;
            this.f = aPathfinerMob1.world;
            this.setCategoryBits(1);
        }
        
        @Override
        public boolean canStart() {
            if (!this.f.isDaylight()) {
                return false;
            }
            if (this.a.isSwimming()) {
                return false;
            }
            final Vec3d vVec3d1 = this.g();
            if (vVec3d1 == null) {
                return false;
            }
            this.b = vVec3d1.x;
            this.c = vVec3d1.y;
            this.d = vVec3d1.z;
            return true;
        }
        
        @Override
        public boolean shouldContinue() {
            return !this.a.t().isInactive();
        }
        
        @Override
        public void start() {
            this.a.t().moveTo(this.b, this.c, this.d, this.e);
        }
        
        @Nullable
        private Vec3d g() {
            final Random vRandom1 = this.a.getRand();
            final BlockPos vBlockPos2 = new BlockPos(this.a.x, this.a.getBoundingBox().minY, this.a.z);
            for (int vInteger3 = 0; vInteger3 < 10; ++vInteger3) {
                final BlockPos vBlockPos3 = vBlockPos2.add(vRandom1.nextInt(20) - 10, 2 - vRandom1.nextInt(8), vRandom1.nextInt(20) - 10);
                if (this.f.h(vBlockPos3).getBlock() == Blocks.A) {
                    return new Vec3d(vBlockPos3.getX(), vBlockPos3.getY(), vBlockPos3.getZ());
                }
            }
            return null;
        }
    }
    
    static class a extends AiGoalZombieAttack
    {
        private final EntityDrowned d;
        
        public a(final EntityDrowned aEntityDrowned1, final double aDouble2, final boolean vBoolean4) {
            super(aEntityDrowned1, aDouble2, vBoolean4);
            this.d = aEntityDrowned1;
        }
        
        @Override
        public boolean canStart() {
            return super.canStart() && this.d.g(this.d.getTarget());
        }
        
        @Override
        public boolean shouldContinue() {
            return super.shouldContinue() && this.d.g(this.d.getTarget());
        }
    }
    
    static class e extends ActionMove
    {
        private final EntityDrowned i;
        
        public e(final EntityDrowned aEntityDrowned) {
            super(aEntityDrowned);
            this.i = aEntityDrowned;
        }
        
        @Override
        public void tick() {
            final EntityLiving vEntityLiving1 = this.i.getTarget();
            if (this.i.dJ() && this.i.isSwimming()) {
                if ((vEntityLiving1 != null && vEntityLiving1.y > this.i.y) || this.i.bC) {
                    final EntityDrowned i = this.i;
                    i.s += 0.002;
                }
                if (this.currentState != State.MOVE_TO || this.i.t().isInactive()) {
                    this.i.o(0.0f);
                    return;
                }
                final double vDouble2 = this.targetX - this.i.x;
                double vDouble3 = this.targetY - this.i.y;
                final double vDouble4 = this.targetZ - this.i.z;
                final double vDouble5 = MathUtils.sqrt(vDouble2 * vDouble2 + vDouble3 * vDouble3 + vDouble4 * vDouble4);
                vDouble3 /= vDouble5;
                final float vFloat10 = (float)(MathUtils.atan2(vDouble4, vDouble2) * 57.2957763671875) - 90.0f;
                this.i.yaw = this.a(this.i.yaw, vFloat10, 90.0f);
                this.i.aO = this.i.yaw;
                final float vFloat11 = (float)(this.speedFactor * this.i.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).getValue());
                this.i.o(this.i.cM() + (vFloat11 - this.i.cM()) * 0.125f);
                final EntityDrowned j = this.i;
                j.s += this.i.cM() * vDouble3 * 0.1;
                final EntityDrowned k = this.i;
                k.r += this.i.cM() * vDouble2 * 0.005;
                final EntityDrowned l = this.i;
                l.t += this.i.cM() * vDouble4 * 0.005;
            }
            else {
                if (!this.i.onGround) {
                    final EntityDrowned m = this.i;
                    m.s -= 0.008;
                }
                super.tick();
            }
        }
    }
    
    static class b implements Predicate<EntityPlayer>
    {
        private final EntityDrowned a;
        
        public b(final EntityDrowned aEntityDrowned) {
            this.a = aEntityDrowned;
        }
        
        @Override
        public boolean test(@Nullable final EntityPlayer aEntityPlayer) {
            return this.a.g(aEntityPlayer);
        }
    }
}
