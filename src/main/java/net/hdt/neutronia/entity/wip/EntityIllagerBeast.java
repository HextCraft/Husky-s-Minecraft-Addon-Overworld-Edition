package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.world.IBlockView;
import net.minecraft.entity.ai.pathing.PathNodeMakerLand;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.EntityMobNavigation;
import net.minecraft.entity.ai.mob.AiGoalMeleeAttack;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.sound.Sound;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.util.math.Vec3d;
import java.util.List;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.client.particle.config.ParticleConfig;
import net.minecraft.client.particle.Particles;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import java.util.Iterator;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.reference.Sounds;
import net.minecraft.block.BlockLeaves;
import net.minecraft.util.math.MathUtils;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import javax.annotation.Nullable;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.nbt.NBTCompound;
import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.entity.passive.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.ai.mob.AiGoalTrackAttackableTarget;
import net.minecraft.entity.ai.AiGoalGangAttack;
import net.minecraft.entity.EntityAbstractIllager;
import net.minecraft.entity.ai.AiGoalWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.AiGoalRoamAvoidWater;
import net.minecraft.entity.PathfinerMob;
import net.minecraft.entity.ai.AiAvoidEntity;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.ai.AiGoalSwim;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import java.util.function.Predicate;

public class EntityIllagerBeast extends EntityHostile
{
    private static final Predicate<Entity> a;
    private int b;
    private int c;
    private int bC;
    private BlockPos bD;
    
    public EntityIllagerBeast(final World aWorld) {
        super(EntityType.ILLAGER_BEAST, aWorld);
        this.setSize(1.95f, 2.2f);
        this.stepHeight = 1.0f;
        this.experiencePoints = 10;
    }
    
    @Override
    protected void n() {
        super.n();
        this.goalSelector.add(0, new AiGoalSwim(this));
        this.goalSelector.add(1, new AiAvoidEntity<Object>(this, EntityRabbit.class, 6.0f, 1.0, 1.2));
        this.goalSelector.add(4, new a());
        this.goalSelector.add(5, new AiGoalRoamAvoidWater(this, 0.4));
        this.goalSelector.add(6, new AiGoalWatchClosest(this, EntityPlayer.class, 6.0f));
        this.goalSelector.add(10, new AiGoalWatchClosest(this, EntityMob.class, 8.0f));
        this.targetSelector.add(2, new AiGoalGangAttack(this, (Class<?>[])new Class[] { EntityAbstractIllager.class }).a((Class<?>[])new Class[0]));
        this.targetSelector.add(3, new AiGoalTrackAttackableTarget<Object>(this, EntityPlayer.class, true));
        this.targetSelector.add(4, new AiGoalTrackAttackableTarget<Object>(this, EntityVillager.class, true));
        this.targetSelector.add(4, new AiGoalTrackAttackableTarget<Object>(this, EntityIronGolem.class, true));
    }
    
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(AttributeManager.MAX_HEALTH).setBaseValue(100.0);
        this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(0.3);
        this.getAttributeInstance(AttributeManager.KNOCKBACK_RESISTANCE).setBaseValue(0.5);
        this.getAttributeInstance(AttributeManager.ATTACK_DAMAGE).setBaseValue(12.0);
        this.getAttributeInstance(AttributeManager.g).setBaseValue(1.5);
        this.getAttributeInstance(AttributeManager.FOLLOW_RANGE).setBaseValue(32.0);
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setInt("AttackTick", this.b);
        aNBTCompound.setInt("StunTick", this.c);
        aNBTCompound.setInt("RoarTick", this.bC);
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        this.b = aNBTCompound.getInt("AttackTick");
        this.c = aNBTCompound.getInt("StunTick");
        this.bC = aNBTCompound.getInt("RoarTick");
    }
    
    @Override
    protected EntityNavigation createNavigation(final World aWorld) {
        return new b(this, aWorld);
    }
    
    @Override
    public int M() {
        return 45;
    }
    
    @Override
    public double getMountedHeightOffset() {
        return 2.1;
    }
    
    @Override
    public boolean di() {
        return true;
    }
    
    @Nullable
    @Override
    public Entity bQ() {
        if (this.getPassengerList().isEmpty()) {
            return null;
        }
        return this.getPassengerList().get(0);
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.aP;
    }
    
    @Override
    public void updateMovement() {
        super.updateMovement();
        if (this.healthDepleted()) {
            this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(0.0);
        }
        else {
            final double vDouble1 = (this.getTarget() != null) ? 0.35 : 0.3;
            final double vDouble2 = this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).getBaseValue();
            this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(vDouble2 + (vDouble1 - vDouble2) * 0.1);
        }
        if (this.z) {
            boolean vBoolean1 = false;
            final BoundingBox vBoundingBox2 = this.getBoundingBox().expand(0.2);
            for (final BlockPos.Mutable vBlockPosMutable4 : BlockPos.iterateBoxPositionsMutable(MathUtils.floor(vBoundingBox2.a), MathUtils.floor(vBoundingBox2.minY), MathUtils.floor(vBoundingBox2.c), MathUtils.floor(vBoundingBox2.d), MathUtils.floor(vBoundingBox2.e), MathUtils.floor(vBoundingBox2.f))) {
                final BlockState vBlockState5 = this.world.h(vBlockPosMutable4);
                final Block vBlock6 = vBlockState5.getBlock();
                if (vBlock6 instanceof BlockLeaves) {
                    vBoolean1 = (this.world.breakBlock(vBlockPosMutable4, true) || vBoolean1);
                }
            }
            if (!vBoolean1 && this.onGround) {
                this.cJ();
            }
        }
        if (this.bC > 0) {
            --this.bC;
            if (this.bC == 10) {
                this.dG();
            }
        }
        if (this.b > 0) {
            --this.b;
        }
        if (this.c > 0) {
            --this.c;
            this.dF();
            if (this.c == 0) {
                this.playSoundAtEntity(Sounds.eF, 1.0f, 1.0f);
                this.bC = 20;
            }
        }
    }
    
    private void dF() {
        if (this.rand.nextInt(6) == 0) {
            final double vDouble1 = this.x - this.width * Math.sin(this.aO * 0.017453292f) + (this.rand.nextDouble() * 0.6 - 0.3);
            final double vDouble2 = this.y + this.height - 0.3;
            final double vDouble3 = this.z + this.width * Math.cos(this.aO * 0.017453292f) + (this.rand.nextDouble() * 0.6 - 0.3);
            this.world.a(Particles.ENTITY_EFFECT, vDouble1, vDouble2, vDouble3, 0.4980392156862745, 0.5137254901960784, 0.5725490196078431);
        }
    }
    
    @Override
    protected boolean healthDepleted() {
        return super.healthDepleted() || this.b > 0 || this.c > 0 || this.bC > 0;
    }
    
    @Override
    public boolean canSee(final Entity aEntity) {
        return this.c <= 0 && this.bC <= 0 && super.canSee(aEntity);
    }
    
    @Override
    protected void d(final EntityLiving aEntityLiving) {
        if (this.bC == 0) {
            if (this.rand.nextDouble() < 0.5) {
                this.c = 40;
                this.playSoundAtEntity(Sounds.eE, 1.0f, 1.0f);
                this.world.a(this, (byte)39);
                aEntityLiving.pushAwayFrom(this);
            }
            else {
                this.a(aEntityLiving);
            }
            aEntityLiving.C = true;
        }
    }
    
    private void dG() {
        if (this.isValid()) {
            final List<Entity> vList1 = this.world.<Entity>a((Class<? extends Entity>)EntityLiving.class, this.getBoundingBox().expand(4.0), (Predicate<? super Entity>)EntityIllagerBeast.a);
            for (final Entity vEntity3 : vList1) {
                if (!vEntity3.equals(this)) {
                    vEntity3.applyDamage(DamageSource.byMob(this), 6.0f);
                    this.a(vEntity3);
                }
            }
            final Vec3d vVec3d2 = this.getBoundingBox().getCenter();
            for (int vInteger3 = 0; vInteger3 < 40; ++vInteger3) {
                final double vDouble4 = this.rand.nextGaussian() * 0.2;
                final double vDouble5 = this.rand.nextGaussian() * 0.2;
                final double vDouble6 = this.rand.nextGaussian() * 0.2;
                this.world.a(Particles.I, vVec3d2.x, vVec3d2.y, vVec3d2.z, vDouble4, vDouble5, vDouble6);
            }
        }
    }
    
    private void a(final Entity aEntity) {
        final double vDouble2 = aEntity.x - this.x;
        final double vDouble3 = aEntity.z - this.z;
        final double vDouble4 = vDouble2 * vDouble2 + vDouble3 * vDouble3;
        aEntity.addVelocity(vDouble2 / vDouble4 * 4.0, 0.2, vDouble3 / vDouble4 * 4.0);
    }
    
    @Sided(Side.CLIENT)
    @Override
    public void a(final byte aByte) {
        if (aByte == 4) {
            this.b = 10;
            this.playSoundAtEntity(Sounds.eA, 1.0f, 1.0f);
        }
        else if (aByte == 39) {
            this.c = 40;
        }
        super.a(aByte);
    }
    
    @Sided(Side.CLIENT)
    public int l() {
        return this.b;
    }
    
    @Sided(Side.CLIENT)
    public int dA() {
        return this.c;
    }
    
    @Sided(Side.CLIENT)
    public int dB() {
        return this.bC;
    }
    
    @Override
    public boolean attackEntity(final Entity aEntity) {
        this.b = 10;
        this.world.a(this, (byte)4);
        this.playSoundAtEntity(Sounds.eA, 1.0f, 1.0f);
        return super.attackEntity(aEntity);
    }
    
    @Nullable
    @Override
    protected Sound getSoundAmbient() {
        return Sounds.ez;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.eC;
    }
    
    @Override
    protected Sound getSoundDeath() {
        return Sounds.eB;
    }
    
    @Override
    protected void playStepSound(final BlockPos aBlockPos1, final BlockState aBlockState2) {
        this.playSoundAtEntity(Sounds.eD, 0.15f, 1.0f);
    }
    
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable final IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        this.bD = new BlockPos(this).add(-500 + this.rand.nextInt(1000), 0, -500 + this.rand.nextInt(1000));
        return super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
    }
    
    static {
        a = (aEntity -> aEntity.isValid() && !(aEntity instanceof EntityIllagerBeast));
    }
    
    class a extends AiGoalMeleeAttack
    {
        public a() {
            super(EntityIllagerBeast.this, 1.0, true);
        }
        
        @Override
        protected double getAttackRangeSquared(final EntityLiving aEntityLiving) {
            final float vFloat2 = EntityIllagerBeast.this.width - 0.1f;
            return vFloat2 * 2.0f * (vFloat2 * 2.0f) + aEntityLiving.width;
        }
    }
    
    static class b extends EntityMobNavigation
    {
        public b(final EntityMob aEntityMob1, final World aWorld2) {
            super(aEntityMob1, aWorld2);
        }
        
        @Override
        protected PathNodeNavigator createPathNodeNavigator() {
            this.o = new c();
            return new PathNodeNavigator(this.o);
        }
    }
    
    static class c extends PathNodeMakerLand
    {
        private c() {
        }
        
        @Override
        protected PathNodeType a(final IBlockView aIBlockView1, final boolean aBoolean2, final boolean aBoolean3, final BlockPos aBlockPos4, final PathNodeType aPathNodeType5) {
            if (aPathNodeType5 == PathNodeType.t) {
                return PathNodeType.OPEN;
            }
            return super.a(aIBlockView1, aBoolean2, aBoolean3, aBlockPos4, aPathNodeType5);
        }
    }
}
