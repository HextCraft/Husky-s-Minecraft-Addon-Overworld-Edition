package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.state.StateHolder;
import net.minecraft.entity.ai.pathing.PathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeMakerTurtle;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.EntityNavigationAquatic;
import net.minecraft.entity.ai.action.ActionMove;
import net.minecraft.entity.ai.AiGoalRoam;
import net.minecraft.property.IProperty;
import net.minecraft.block.BlockTurtleEgg;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.ai.AiGoalMoveToBlock;
import net.minecraft.entity.player.EntityPlayerServer;
import net.minecraft.entity.EntityExperienceOrb;
import net.minecraft.reference.CriterionTriggers;
import net.minecraft.stat.Statistics;
import net.minecraft.entity.ai.animal.AiGoalAnimalMate;
import com.google.common.collect.Sets;
import net.minecraft.item.Item;
import java.util.Set;
import net.minecraft.util.math.BlockRegion;
import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.ai.EntityTargetGenerator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockView;
import net.minecraft.entity.PathfinerMob;
import net.minecraft.entity.ai.AiGoalPanicRun;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLightning;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.entity.MovementType;
import net.minecraft.util.IItemProvider;
import net.minecraft.reference.Items;
import net.minecraft.block.Block;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.IWorldReadable;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.block.state.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.reference.Sounds;
import net.minecraft.sound.Sound;
import net.minecraft.enchantment.DamageCategory;
import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.entity.mob.EntityMob;
import net.minecraft.entity.ai.AiGoalWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.AiGoal;
import javax.annotation.Nullable;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.nbt.NBTCompound;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import java.util.function.Predicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.tracker.TrackedData;

public class EntityTurtle extends EntityAnimal
{
    private static final TrackedData<BlockPos> bD;
    private static final TrackedData<Boolean> bE;
    private static final TrackedData<Boolean> bG;
    private static final TrackedData<BlockPos> bH;
    private static final TrackedData<Boolean> bI;
    private static final TrackedData<Boolean> bJ;
    private int bK;
    public static final Predicate<Entity> bC;
    
    public EntityTurtle(final World aWorld) {
        super(EntityType.TURTLE, aWorld);
        this.setSize(1.2f, 0.4f);
        this.actionMove = new e(this);
        this.spawningGround = Blocks.C;
        this.stepHeight = 1.0f;
    }
    
    public void g(final BlockPos aBlockPos) {
        this.dataTracker.<BlockPos>set(EntityTurtle.bD, aBlockPos);
    }
    
    private BlockPos dB() {
        return this.dataTracker.<BlockPos>get(EntityTurtle.bD);
    }
    
    private void h(final BlockPos aBlockPos) {
        this.dataTracker.<BlockPos>set(EntityTurtle.bH, aBlockPos);
    }
    
    private BlockPos dC() {
        return this.dataTracker.<BlockPos>get(EntityTurtle.bH);
    }
    
    public boolean dz() {
        return this.dataTracker.<Boolean>get(EntityTurtle.bE);
    }
    
    private void s(final boolean aBoolean) {
        this.dataTracker.<Boolean>set(EntityTurtle.bE, aBoolean);
    }
    
    public boolean dA() {
        return this.dataTracker.<Boolean>get(EntityTurtle.bG);
    }
    
    private void t(final boolean aBoolean) {
        this.bK = (aBoolean ? 1 : 0);
        this.dataTracker.<Boolean>set(EntityTurtle.bG, aBoolean);
    }
    
    private boolean dD() {
        return this.dataTracker.<Boolean>get(EntityTurtle.bI);
    }
    
    private void u(final boolean aBoolean) {
        this.dataTracker.<Boolean>set(EntityTurtle.bI, aBoolean);
    }
    
    private boolean dI() {
        return this.dataTracker.<Boolean>get(EntityTurtle.bJ);
    }
    
    private void v(final boolean aBoolean) {
        this.dataTracker.<Boolean>set(EntityTurtle.bJ, aBoolean);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<BlockPos>startTracking(EntityTurtle.bD, BlockPos.ORIGIN);
        this.dataTracker.<Boolean>startTracking(EntityTurtle.bE, false);
        this.dataTracker.<BlockPos>startTracking(EntityTurtle.bH, BlockPos.ORIGIN);
        this.dataTracker.<Boolean>startTracking(EntityTurtle.bI, false);
        this.dataTracker.<Boolean>startTracking(EntityTurtle.bJ, false);
        this.dataTracker.<Boolean>startTracking(EntityTurtle.bG, false);
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setInt("HomePosX", this.dB().getX());
        aNBTCompound.setInt("HomePosY", this.dB().getY());
        aNBTCompound.setInt("HomePosZ", this.dB().getZ());
        aNBTCompound.setBoolean("HasEgg", this.dz());
        aNBTCompound.setInt("TravelPosX", this.dC().getX());
        aNBTCompound.setInt("TravelPosY", this.dC().getY());
        aNBTCompound.setInt("TravelPosZ", this.dC().getZ());
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        final int vInteger2 = aNBTCompound.getInt("HomePosX");
        final int vInteger3 = aNBTCompound.getInt("HomePosY");
        final int vInteger4 = aNBTCompound.getInt("HomePosZ");
        this.g(new BlockPos(vInteger2, vInteger3, vInteger4));
        super.deserializeCustomData(aNBTCompound);
        this.s(aNBTCompound.getBoolean("HasEgg"));
        final int vInteger5 = aNBTCompound.getInt("TravelPosX");
        final int vInteger6 = aNBTCompound.getInt("TravelPosY");
        final int vInteger7 = aNBTCompound.getInt("TravelPosZ");
        this.h(new BlockPos(vInteger5, vInteger6, vInteger7));
    }
    
    @Nullable
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable final IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        this.g(new BlockPos(this.x, this.y, this.z));
        this.h(BlockPos.ORIGIN);
        return super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
    }
    
    @Override
    public boolean canSpawnHere(final IWorld aIWorld1, final boolean aBoolean2) {
        final BlockPos vBlockPos3 = new BlockPos(this.x, this.getBoundingBox().minY, this.z);
        return vBlockPos3.getY() < aIWorld1.getSeaLevel() + 4 && super.canSpawnHere(aIWorld1, aBoolean2);
    }
    
    @Override
    protected void n() {
        this.goalSelector.add(0, new f(this, 1.2));
        this.goalSelector.add(1, new a(this, 1.0));
        this.goalSelector.add(1, new d(this, 1.0));
        this.goalSelector.add(2, new i(this, 1.1, Blocks.aT.getItem()));
        this.goalSelector.add(3, new c(this, 1.0));
        this.goalSelector.add(4, new b(this, 1.0));
        this.goalSelector.add(7, new j(this, 1.0));
        this.goalSelector.add(8, new AiGoalWatchClosest(this, EntityPlayer.class, 8.0f));
        this.goalSelector.add(9, new h(this, 1.0, 100));
    }
    
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(AttributeManager.MAX_HEALTH).setBaseValue(30.0);
        this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(0.25);
    }
    
    @Override
    public boolean by() {
        return false;
    }
    
    @Override
    public boolean cc() {
        return true;
    }
    
    @Override
    public DamageCategory getEntityAttribute() {
        return DamageCategory.AQUATIC;
    }
    
    @Override
    public int z() {
        return 200;
    }
    
    @Nullable
    @Override
    protected Sound getSoundAmbient() {
        if (!this.isSwimming() && this.onGround && !this.isChild()) {
            return Sounds.ENTITY_TURTLE_AMBIENT_LAND;
        }
        return super.getSoundAmbient();
    }
    
    @Override
    protected void d(final float aFloat) {
        super.d(aFloat * 1.5f);
    }
    
    @Override
    protected Sound getSoundSwim() {
        return Sounds.ENTITY_TURTLE_SWIM;
    }
    
    @Nullable
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        if (this.isChild()) {
            return Sounds.ENTITY_TURTLE_HURT_BABY;
        }
        return Sounds.ENTITY_TURTLE_HURT;
    }
    
    @Nullable
    @Override
    protected Sound getSoundDeath() {
        if (this.isChild()) {
            return Sounds.ENTITY_TURTLE_DEATH_BABY;
        }
        return Sounds.ENTITY_TURTLE_DEATH;
    }
    
    @Override
    protected void playStepSound(final BlockPos aBlockPos1, final BlockState aBlockState2) {
        final Sound vSound3 = this.isChild() ? Sounds.ENTITY_TURTLE_SHAMBLE_BABY : Sounds.ENTITY_TURTLE_SHAMBLE;
        this.playSoundAtEntity(vSound3, 0.15f, 1.0f);
    }
    
    @Override
    public boolean dE() {
        return super.dE() && !this.dz();
    }
    
    @Override
    protected float ad() {
        return this.J + 0.15f;
    }
    
    @Override
    public void a(final boolean aBoolean) {
        this.a(aBoolean ? 0.3f : 1.0f);
    }
    
    @Override
    protected EntityNavigation createNavigation(final World aWorld) {
        return new g(this, aWorld);
    }
    
    @Nullable
    @Override
    public EntityPassive createChild(final EntityPassive aEntityPassive) {
        return new EntityTurtle(this.world);
    }
    
    @Override
    public boolean f(final ItemStack aItemStack) {
        return aItemStack.getItem() == Blocks.aT.getItem();
    }
    
    @Override
    public float getPathWeight(final BlockPos aBlockPos1, final IWorldReadable aIWorldReadable2) {
        if (!this.dD() && aIWorldReadable2.getFluidState(aBlockPos1).hasTag(FluidTags.WATER)) {
            return 10.0f;
        }
        return super.getPathWeight(aBlockPos1, aIWorldReadable2);
    }
    
    @Override
    public void updateMovement() {
        super.updateMovement();
        if (this.dA() && this.bK >= 1 && this.bK % 5 == 0) {
            final BlockPos vBlockPos1 = new BlockPos(this);
            if (this.world.h(vBlockPos1.down()).getBlock() == Blocks.C) {
                this.world.fireWorldEvent(2001, vBlockPos1, Block.getStateId(Blocks.C.getDefaultState()));
            }
        }
    }
    
    @Override
    protected void l() {
        super.l();
        if (this.world.getGameRules().getBoolean("doMobLoot")) {
            this.a(Items.iU, 1);
        }
    }
    
    @Override
    public void a(final float aFloat1, final float aFloat2, final float aFloat3) {
        if (this.cR() && this.isSwimming()) {
            this.a(aFloat1, aFloat2, aFloat3, 0.1f);
            this.move(MovementType.SELF, this.r, this.s, this.t);
            this.r *= 0.8999999761581421;
            this.s *= 0.8999999761581421;
            this.t *= 0.8999999761581421;
            if (this.getTarget() == null && (!this.dD() || this.squaredDistanceTo(this.dB()) >= 400.0)) {
                this.s -= 0.005;
            }
        }
        else {
            super.a(aFloat1, aFloat2, aFloat3);
        }
    }
    
    @Override
    public boolean canBeLeashedBy(final EntityPlayer aEntityPlayer) {
        return false;
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_TURTLE;
    }
    
    @Override
    public void onStruckByLightning(final EntityLightning aEntityLightning) {
        this.applyDamage(DamageSource.LIGHTNING_BOLT, Float.MAX_VALUE);
    }
    
    static {
        bD = DataTracker.<BlockPos>registerData(EntityTurtle.class, TrackedDataHandlerRegistry.BLOCK_POS);
        bE = DataTracker.<Boolean>registerData(EntityTurtle.class, TrackedDataHandlerRegistry.BOOLEAN);
        bG = DataTracker.<Boolean>registerData(EntityTurtle.class, TrackedDataHandlerRegistry.BOOLEAN);
        bH = DataTracker.<BlockPos>registerData(EntityTurtle.class, TrackedDataHandlerRegistry.BLOCK_POS);
        bI = DataTracker.<Boolean>registerData(EntityTurtle.class, TrackedDataHandlerRegistry.BOOLEAN);
        bJ = DataTracker.<Boolean>registerData(EntityTurtle.class, TrackedDataHandlerRegistry.BOOLEAN);
        bC = (aEntity -> {
            if (aEntity instanceof EntityLiving) {
                return aEntity.isChild() && !aEntity.isSwimming();
            }
            else {
                return false;
            }
        });
    }
    
    static class f extends AiGoalPanicRun
    {
        f(final EntityTurtle aEntityTurtle1, final double aDouble2) {
            super(aEntityTurtle1, aDouble2);
        }
        
        @Override
        public boolean canStart() {
            if (this.a.getAttacker() == null && !this.a.isOnFire()) {
                return false;
            }
            final BlockPos vBlockPos1 = this.a(this.a.world, this.a, 7, 4);
            if (vBlockPos1 != null) {
                this.c = vBlockPos1.getX();
                this.d = vBlockPos1.getY();
                this.e = vBlockPos1.getZ();
                return true;
            }
            return this.g();
        }
    }
    
    static class j extends AiGoal
    {
        private final EntityTurtle a;
        private final double b;
        private boolean c;
        
        j(final EntityTurtle aEntityTurtle1, final double aDouble2) {
            this.a = aEntityTurtle1;
            this.b = aDouble2;
        }
        
        @Override
        public boolean canStart() {
            return !this.a.dD() && !this.a.dz() && this.a.isSwimming();
        }
        
        @Override
        public void start() {
            final int vInteger1 = 512;
            final int vInteger2 = 4;
            final Random vRandom3 = this.a.rand;
            final int vInteger3 = vRandom3.nextInt(1025) - 512;
            int vInteger4 = vRandom3.nextInt(9) - 4;
            final int vInteger5 = vRandom3.nextInt(1025) - 512;
            if (vInteger4 + this.a.y > this.a.world.getSeaLevel() - 1) {
                vInteger4 = 0;
            }
            final BlockPos vBlockPos7 = new BlockPos(vInteger3 + this.a.x, vInteger4 + this.a.y, vInteger5 + this.a.z);
            this.a.h(vBlockPos7);
            this.a.v(true);
            this.c = false;
        }
        
        @Override
        public void tick() {
            if (this.a.t().isInactive()) {
                final BlockPos vBlockPos1 = this.a.dC();
                Vec3d vVec3d2 = EntityTargetGenerator.findPositionTowards(this.a, 16, 3, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()), 0.3141592741012573);
                if (vVec3d2 == null) {
                    vVec3d2 = EntityTargetGenerator.findPositionTowards(this.a, 8, 7, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()));
                }
                if (vVec3d2 != null) {
                    final int vInteger3 = MathUtils.floor(vVec3d2.x);
                    final int vInteger4 = MathUtils.floor(vVec3d2.z);
                    final int vInteger5 = 34;
                    final BlockRegion vBlockRegion6 = new BlockRegion(vInteger3 - 34, 0, vInteger4 - 34, vInteger3 + 34, 0, vInteger4 + 34);
                    if (!this.a.world.a(vBlockRegion6)) {
                        vVec3d2 = null;
                    }
                }
                if (vVec3d2 == null) {
                    this.c = true;
                    return;
                }
                this.a.t().moveTo(vVec3d2.x, vVec3d2.y, vVec3d2.z, this.b);
            }
        }
        
        @Override
        public boolean shouldContinue() {
            return !this.a.t().isInactive() && !this.c && !this.a.dD() && !this.a.dG() && !this.a.dz();
        }
        
        @Override
        public void stop() {
            this.a.v(false);
            super.stop();
        }
    }
    
    static class b extends AiGoal
    {
        private final EntityTurtle a;
        private final double b;
        private boolean c;
        private int d;
        
        b(final EntityTurtle aEntityTurtle1, final double aDouble2) {
            this.a = aEntityTurtle1;
            this.b = aDouble2;
        }
        
        @Override
        public boolean canStart() {
            return !this.a.isChild() && (this.a.dz() || (this.a.getRand().nextInt(700) == 0 && this.a.squaredDistanceTo(this.a.dB()) >= 4096.0));
        }
        
        @Override
        public void start() {
            this.a.u(true);
            this.c = false;
            this.d = 0;
        }
        
        @Override
        public void stop() {
            this.a.u(false);
        }
        
        @Override
        public boolean shouldContinue() {
            return this.a.squaredDistanceTo(this.a.dB()) >= 49.0 && !this.c && this.d <= 600;
        }
        
        @Override
        public void tick() {
            final BlockPos vBlockPos1 = this.a.dB();
            final boolean vBoolean2 = this.a.squaredDistanceTo(vBlockPos1) <= 256.0;
            if (vBoolean2) {
                ++this.d;
            }
            if (this.a.t().isInactive()) {
                Vec3d vVec3d3 = EntityTargetGenerator.findPositionTowards(this.a, 16, 3, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()), 0.3141592741012573);
                if (vVec3d3 == null) {
                    vVec3d3 = EntityTargetGenerator.findPositionTowards(this.a, 8, 7, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()));
                }
                if (vVec3d3 != null && !vBoolean2 && this.a.world.h(new BlockPos(vVec3d3)).getBlock() != Blocks.A) {
                    vVec3d3 = EntityTargetGenerator.findPositionTowards(this.a, 16, 5, new Vec3d(vBlockPos1.getX(), vBlockPos1.getY(), vBlockPos1.getZ()));
                }
                if (vVec3d3 == null) {
                    this.c = true;
                    return;
                }
                this.a.t().moveTo(vVec3d3.x, vVec3d3.y, vVec3d3.z, this.b);
            }
        }
    }
    
    static class i extends AiGoal
    {
        private final EntityTurtle a;
        private final double b;
        private EntityPlayer c;
        private int d;
        private final Set<Item> e;
        
        i(final EntityTurtle aEntityTurtle1, final double aDouble2, final Item vItem4) {
            this.a = aEntityTurtle1;
            this.b = aDouble2;
            this.e = Sets.<Item>newHashSet(vItem4);
            this.setCategoryBits(3);
        }
        
        @Override
        public boolean canStart() {
            if (this.d > 0) {
                --this.d;
                return false;
            }
            this.c = this.a.world.a(this.a, 10.0);
            return this.c != null && (this.a(this.c.getMainHandStack()) || this.a(this.c.getOffHandStack()));
        }
        
        private boolean a(final ItemStack aItemStack) {
            return this.e.contains(aItemStack.getItem());
        }
        
        @Override
        public boolean shouldContinue() {
            return this.canStart();
        }
        
        @Override
        public void stop() {
            this.c = null;
            this.a.t().clearPath();
            this.d = 100;
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
        }
    }
    
    static class a extends AiGoalAnimalMate
    {
        private final EntityTurtle d;
        
        a(final EntityTurtle aEntityTurtle1, final double aDouble2) {
            super(aEntityTurtle1, aDouble2);
            this.d = aEntityTurtle1;
        }
        
        @Override
        public boolean canStart() {
            return super.canStart() && !this.d.dz();
        }
        
        @Override
        protected void g() {
            EntityPlayerServer vEntityPlayerServer1 = this.owner.dF();
            if (vEntityPlayerServer1 == null && this.mate.dF() != null) {
                vEntityPlayerServer1 = this.mate.dF();
            }
            if (vEntityPlayerServer1 != null) {
                vEntityPlayerServer1.a(Statistics.ANIMALS_BRED);
                CriterionTriggers.BRED_ANIMALS.handle(vEntityPlayerServer1, this.owner, this.mate, null);
            }
            this.d.s(true);
            this.owner.dH();
            this.mate.dH();
            final Random vRandom2 = this.owner.getRand();
            if (this.world.getGameRules().getBoolean("doMobLoot")) {
                this.world.spawnEntity(new EntityExperienceOrb(this.world, this.owner.x, this.owner.y, this.owner.z, vRandom2.nextInt(7) + 1));
            }
        }
    }
    
    static class d extends AiGoalMoveToBlock
    {
        private final EntityTurtle f;
        
        d(final EntityTurtle aEntityTurtle1, final double aDouble2) {
            super(aEntityTurtle1, aDouble2, 16);
            this.f = aEntityTurtle1;
        }
        
        @Override
        public boolean canStart() {
            return this.f.dz() && this.f.squaredDistanceTo(this.f.dB()) < 81.0 && super.canStart();
        }
        
        @Override
        public boolean shouldContinue() {
            return super.shouldContinue() && this.f.dz() && this.f.squaredDistanceTo(this.f.dB()) < 81.0;
        }
        
        @Override
        public void tick() {
            super.tick();
            final BlockPos vBlockPos1 = new BlockPos(this.f);
            if (!this.f.isSwimming() && this.k()) {
                if (this.f.bK < 1) {
                    this.f.t(true);
                }
                else if (this.f.bK > 200) {
                    final World vWorld2 = this.f.world;
                    vWorld2.a(null, vBlockPos1, Sounds.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3f, 0.9f + vWorld2.rand.nextFloat() * 0.2f);
                    vWorld2.setBlockState(this.d.up(), ((StateHolder<O, BlockState>)Blocks.jV.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockTurtleEgg.b, this.f.rand.nextInt(4) + 1), 3);
                    this.f.s(false);
                    this.f.t(false);
                    this.f.d(600);
                }
                if (this.f.dA()) {
                    this.f.bK++;
                }
            }
        }
        
        @Override
        protected boolean a(final IWorldReadable aIWorldReadable1, final BlockPos aBlockPos2) {
            if (!aIWorldReadable1.isAir(aBlockPos2.up())) {
                return false;
            }
            final Block vBlock3 = aIWorldReadable1.h(aBlockPos2).getBlock();
            return vBlock3 == Blocks.C;
        }
    }
    
    static class h extends AiGoalRoam
    {
        private final EntityTurtle h;
        
        private h(final EntityTurtle aEntityTurtle1, final double aDouble2, final int vInteger4) {
            super(aEntityTurtle1, aDouble2, vInteger4);
            this.h = aEntityTurtle1;
        }
        
        @Override
        public boolean canStart() {
            return !this.goalOwner.isSwimming() && !this.h.dD() && !this.h.dz() && super.canStart();
        }
    }
    
    static class c extends AiGoalMoveToBlock
    {
        private final EntityTurtle f;
        
        private c(final EntityTurtle aEntityTurtle1, final double aDouble2) {
            super(aEntityTurtle1, aEntityTurtle1.isChild() ? 2.0 : aDouble2, 24);
            this.f = aEntityTurtle1;
            this.e = -1;
        }
        
        @Override
        public boolean shouldContinue() {
            return !this.f.isSwimming() && this.c <= 1200 && this.a(this.f.world, this.d);
        }
        
        @Override
        public boolean canStart() {
            if (this.f.isChild() && !this.f.isSwimming()) {
                return super.canStart();
            }
            return !this.f.dD() && !this.f.isSwimming() && !this.f.dz() && super.canStart();
        }
        
        @Override
        public boolean j() {
            return this.c % 160 == 0;
        }
        
        @Override
        protected boolean a(final IWorldReadable aIWorldReadable1, final BlockPos aBlockPos2) {
            final Block vBlock3 = aIWorldReadable1.h(aBlockPos2).getBlock();
            return vBlock3 == Blocks.A;
        }
    }
    
    static class e extends ActionMove
    {
        private final EntityTurtle i;
        
        e(final EntityTurtle aEntityTurtle) {
            super(aEntityTurtle);
            this.i = aEntityTurtle;
        }
        
        private void g() {
            if (this.i.isSwimming()) {
                final EntityTurtle i = this.i;
                i.s += 0.005;
                if (this.i.squaredDistanceTo(this.i.dB()) > 256.0) {
                    this.i.o(Math.max(this.i.cM() / 2.0f, 0.08f));
                }
                if (this.i.isChild()) {
                    this.i.o(Math.max(this.i.cM() / 3.0f, 0.06f));
                }
            }
            else if (this.i.onGround) {
                this.i.o(Math.max(this.i.cM() / 2.0f, 0.06f));
            }
        }
        
        @Override
        public void tick() {
            this.g();
            if (this.currentState != State.MOVE_TO || this.i.t().isInactive()) {
                this.i.o(0.0f);
                return;
            }
            final double vDouble1 = this.targetX - this.i.x;
            double vDouble2 = this.targetY - this.i.y;
            final double vDouble3 = this.targetZ - this.i.z;
            final double vDouble4 = MathUtils.sqrt(vDouble1 * vDouble1 + vDouble2 * vDouble2 + vDouble3 * vDouble3);
            vDouble2 /= vDouble4;
            final float vFloat9 = (float)(MathUtils.atan2(vDouble3, vDouble1) * 57.2957763671875) - 90.0f;
            this.i.yaw = this.a(this.i.yaw, vFloat9, 90.0f);
            this.i.aO = this.i.yaw;
            final float vFloat10 = (float)(this.speedFactor * this.i.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).getValue());
            this.i.o(this.i.cM() + (vFloat10 - this.i.cM()) * 0.125f);
            final EntityTurtle i = this.i;
            i.s += this.i.cM() * vDouble2 * 0.1;
        }
    }
    
    static class g extends EntityNavigationAquatic
    {
        g(final EntityTurtle aEntityTurtle1, final World aWorld2) {
            super(aEntityTurtle1, aWorld2);
        }
        
        @Override
        protected boolean isAtValidPosition() {
            return true;
        }
        
        @Override
        protected PathNodeNavigator createPathNodeNavigator() {
            return new PathNodeNavigator(new PathNodeMakerTurtle());
        }
        
        @Override
        public boolean isValidPosition(final BlockPos aBlockPos) {
            if (this.entity instanceof EntityTurtle) {
                final EntityTurtle vEntityTurtle2 = (EntityTurtle)this.entity;
                if (vEntityTurtle2.dI()) {
                    return this.world.h(aBlockPos).getBlock() == Blocks.A;
                }
            }
            return !this.world.h(aBlockPos.down()).isAir();
        }
    }
}
