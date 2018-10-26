package net.hdt.neutronia.entity.wip;

import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.world.chunk.HeightMap;
import net.minecraft.entity.ai.AiGoalTrackTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3i;
import net.minecraft.entity.ai.action.ActionLook;
import net.minecraft.entity.ai.action.ActionMove;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import java.util.Random;
import net.minecraft.enchantment.DamageCategory;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.Sound;
import net.minecraft.client.audio.SoundCategory;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTCompound;
import javax.annotation.Nullable;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.util.Difficulty;
import net.minecraft.client.particle.config.ParticleConfig;
import net.minecraft.client.particle.Particles;
import net.minecraft.reference.Sounds;
import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.tracker.TrackedData;
import net.minecraft.entity.sortme.IMob;

public class EntityPhantom extends EntityFlying implements IMob
{
    private static final TrackedData<Integer> MOB_FLAGS;
    private Vec3d b;
    private BlockPos ax;
    private a bC;
    
    public EntityPhantom(final World aWorld) {
        super(EntityType.PHANTOM, aWorld);
        this.b = Vec3d.ZERO;
        this.ax = BlockPos.ORIGIN;
        this.bC = a.CIRCLE;
        this.experiencePoints = 5;
        this.setSize(0.9f, 0.5f);
        this.actionMove = new g(this);
        this.actionLook = new f(this);
    }
    
    @Override
    protected EntityHelper o() {
        return new d(this);
    }
    
    @Override
    protected void n() {
        this.goalSelector.add(1, new c());
        this.goalSelector.add(2, new i());
        this.goalSelector.add(3, new e());
        this.targetSelector.add(1, new b());
    }
    
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeContainer().register(AttributeManager.ATTACK_DAMAGE);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Integer>startTracking(EntityPhantom.MOB_FLAGS, 0);
    }
    
    public void a(int aInteger) {
        if (aInteger < 0) {
            aInteger = 0;
        }
        else if (aInteger > 64) {
            aInteger = 64;
        }
        this.dataTracker.<Integer>set(EntityPhantom.MOB_FLAGS, aInteger);
        this.l();
    }
    
    public void l() {
        final int vInteger1 = this.dataTracker.<Integer>get(EntityPhantom.MOB_FLAGS);
        this.setSize(0.9f + 0.2f * vInteger1, 0.5f + 0.1f * vInteger1);
        this.getAttributeInstance(AttributeManager.ATTACK_DAMAGE).setBaseValue(6 + vInteger1);
    }
    
    public int ds() {
        return this.dataTracker.<Integer>get(EntityPhantom.MOB_FLAGS);
    }
    
    @Override
    public float getEyeHeight() {
        return this.height * 0.35f;
    }
    
    @Override
    public void onTrackedDataSet(final TrackedData<?> aTrackedData) {
        if (EntityPhantom.MOB_FLAGS.equals(aTrackedData)) {
            this.l();
        }
        super.onTrackedDataSet(aTrackedData);
    }
    
    @Override
    public void update() {
        super.update();
        if (this.world.isClient) {
            final float vFloat1 = MathUtils.cos((this.getEntityId() * 3 + this.age) * 0.13f + 3.1415927f);
            final float vFloat2 = MathUtils.cos((this.getEntityId() * 3 + this.age + 1) * 0.13f + 3.1415927f);
            if (vFloat1 > 0.0f && vFloat2 <= 0.0f) {
                this.world.playSound(this.x, this.y, this.z, Sounds.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95f + this.rand.nextFloat() * 0.05f, 0.95f + this.rand.nextFloat() * 0.05f, false);
            }
            final int vInteger3 = this.ds();
            final float vFloat3 = MathUtils.cos(this.yaw * 0.017453292f) * (1.3f + 0.21f * vInteger3);
            final float vFloat4 = MathUtils.sin(this.yaw * 0.017453292f) * (1.3f + 0.21f * vInteger3);
            final float vFloat5 = (0.3f + vFloat1 * 0.45f) * (vInteger3 * 0.2f + 1.0f);
            this.world.a(Particles.MYCELIUM, this.x + vFloat3, this.y + vFloat5, this.z + vFloat4, 0.0, 0.0, 0.0);
            this.world.a(Particles.MYCELIUM, this.x - vFloat3, this.y + vFloat5, this.z - vFloat4, 0.0, 0.0, 0.0);
        }
        if (!this.world.isClient && this.world.getDifficulty() == Difficulty.PEACEFUL) {
            this.invalidate();
        }
    }
    
    @Override
    public void updateMovement() {
        if (this.dr()) {
            this.setOnFireFor(8);
        }
        super.updateMovement();
    }
    
    @Override
    protected void mobTick() {
        super.mobTick();
    }
    
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable final IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        this.ax = new BlockPos(this).up(5);
        this.a(0);
        return super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        if (aNBTCompound.hasKey("AX")) {
            this.ax = new BlockPos(aNBTCompound.getInt("AX"), aNBTCompound.getInt("AY"), aNBTCompound.getInt("AZ"));
        }
        this.a(aNBTCompound.getInt("Size"));
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setInt("AX", this.ax.getX());
        aNBTCompound.setInt("AY", this.ax.getY());
        aNBTCompound.setInt("AZ", this.ax.getZ());
        aNBTCompound.setInt("Size", this.ds());
    }
    
    @Sided(Side.CLIENT)
    @Override
    public boolean shouldRender(final double aDouble) {
        return true;
    }
    
    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }
    
    @Override
    protected Sound getSoundAmbient() {
        return Sounds.ENTITY_PHANTOM_AMBIENT;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.ENTITY_PHANTOM_HURT;
    }
    
    @Override
    protected Sound getSoundDeath() {
        return Sounds.ENTITY_PHANTOM_DEATH;
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_PHANTOM;
    }
    
    @Override
    public DamageCategory getEntityAttribute() {
        return DamageCategory.UNDEAD;
    }
    
    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }
    
    @Override
    public boolean canTrack(final Class<? extends EntityLiving> aClass) {
        return true;
    }
    
    static {
        MOB_FLAGS = DataTracker.<Integer>registerData(EntityPhantom.class, TrackedDataHandlerRegistry.INTEGER);
    }
    
    enum a
    {
        CIRCLE, 
        SWOOP;
    }
    
    class g extends ActionMove
    {
        private float j;
        
        public g(final EntityMob aEntityMob2) {
            super(aEntityMob2);
            this.j = 0.1f;
        }
        
        @Override
        public void tick() {
            if (EntityPhantom.this.z) {
                final EntityPhantom i = EntityPhantom.this;
                i.yaw += 180.0f;
                this.j = 0.1f;
            }
            float vFloat1 = (float)(EntityPhantom.this.b.x - EntityPhantom.this.x);
            final float vFloat2 = (float)(EntityPhantom.this.b.y - EntityPhantom.this.y);
            float vFloat3 = (float)(EntityPhantom.this.b.z - EntityPhantom.this.z);
            double vDouble4 = MathUtils.sqrt(vFloat1 * vFloat1 + vFloat3 * vFloat3);
            final double vDouble5 = 1.0 - MathUtils.abs(vFloat2 * 0.7f) / vDouble4;
            vFloat1 *= (float)vDouble5;
            vFloat3 *= (float)vDouble5;
            vDouble4 = MathUtils.sqrt(vFloat1 * vFloat1 + vFloat3 * vFloat3);
            final double vDouble6 = MathUtils.sqrt(vFloat1 * vFloat1 + vFloat3 * vFloat3 + vFloat2 * vFloat2);
            final float vFloat4 = EntityPhantom.this.yaw;
            final float vFloat5 = (float)MathUtils.atan2(vFloat3, vFloat1);
            final float vFloat6 = MathUtils.wrapDegrees(EntityPhantom.this.yaw + 90.0f);
            final float vFloat7 = MathUtils.wrapDegrees(vFloat5 * 57.295776f);
            EntityPhantom.this.yaw = MathUtils.c(vFloat6, vFloat7, 4.0f) - 90.0f;
            EntityPhantom.this.aO = EntityPhantom.this.yaw;
            if (MathUtils.d(vFloat4, EntityPhantom.this.yaw) < 3.0f) {
                this.j = MathUtils.b(this.j, 1.8f, 0.005f * (1.8f / this.j));
            }
            else {
                this.j = MathUtils.b(this.j, 0.2f, 0.025f);
            }
            final float vFloat8 = (float)(-(MathUtils.atan2(-vFloat2, vDouble4) * 57.2957763671875));
            EntityPhantom.this.pitch = vFloat8;
            final float vFloat9 = EntityPhantom.this.yaw + 90.0f;
            final double vDouble7 = this.j * MathUtils.cos(vFloat9 * 0.017453292f) * Math.abs(vFloat1 / vDouble6);
            final double vDouble8 = this.j * MathUtils.sin(vFloat9 * 0.017453292f) * Math.abs(vFloat3 / vDouble6);
            final double vDouble9 = this.j * MathUtils.sin(vFloat8 * 0.017453292f) * Math.abs(vFloat2 / vDouble6);
            final EntityPhantom j = EntityPhantom.this;
            j.r += (vDouble7 - EntityPhantom.this.r) * 0.2;
            final EntityPhantom k = EntityPhantom.this;
            k.s += (vDouble9 - EntityPhantom.this.s) * 0.2;
            final EntityPhantom l = EntityPhantom.this;
            l.t += (vDouble8 - EntityPhantom.this.t) * 0.2;
        }
    }
    
    class d extends EntityHelper
    {
        public d(final EntityLiving aEntityLiving2) {
            super(aEntityLiving2);
        }
        
        @Override
        public void a() {
            EntityPhantom.this.headPitch = EntityPhantom.this.aO;
            EntityPhantom.this.aO = EntityPhantom.this.yaw;
        }
    }
    
    class f extends ActionLook
    {
        public f(final EntityMob aEntityMob2) {
            super(aEntityMob2);
        }
        
        @Override
        public void tick() {
        }
    }
    
    abstract class h extends AiGoal
    {
        public h() {
            this.setCategoryBits(1);
        }
        
        protected boolean g() {
            return EntityPhantom.this.b.squaredDistanceTo(EntityPhantom.this.x, EntityPhantom.this.y, EntityPhantom.this.z) < 4.0;
        }
    }
    
    class e extends h
    {
        private float c;
        private float d;
        private float e;
        private float f;
        
        private e() {
        }
        
        @Override
        public boolean canStart() {
            return EntityPhantom.this.getTarget() == null || EntityPhantom.this.bC == a.CIRCLE;
        }
        
        @Override
        public void start() {
            this.d = 5.0f + EntityPhantom.this.rand.nextFloat() * 10.0f;
            this.e = -4.0f + EntityPhantom.this.rand.nextFloat() * 9.0f;
            this.f = (EntityPhantom.this.rand.nextBoolean() ? 1.0f : -1.0f);
            this.i();
        }
        
        @Override
        public void tick() {
            if (EntityPhantom.this.rand.nextInt(350) == 0) {
                this.e = -4.0f + EntityPhantom.this.rand.nextFloat() * 9.0f;
            }
            if (EntityPhantom.this.rand.nextInt(250) == 0) {
                ++this.d;
                if (this.d > 15.0f) {
                    this.d = 5.0f;
                    this.f = -this.f;
                }
            }
            if (EntityPhantom.this.rand.nextInt(450) == 0) {
                this.c = EntityPhantom.this.rand.nextFloat() * 2.0f * 3.1415927f;
                this.i();
            }
            if (this.g()) {
                this.i();
            }
            if (EntityPhantom.this.b.y < EntityPhantom.this.y && !EntityPhantom.this.world.isAir(new BlockPos(EntityPhantom.this).down(1))) {
                this.e = Math.max(1.0f, this.e);
                this.i();
            }
            if (EntityPhantom.this.b.y > EntityPhantom.this.y && !EntityPhantom.this.world.isAir(new BlockPos(EntityPhantom.this).up(1))) {
                this.e = Math.min(-1.0f, this.e);
                this.i();
            }
        }
        
        private void i() {
            if (BlockPos.ORIGIN.equals(EntityPhantom.this.ax)) {
                EntityPhantom.this.ax = new BlockPos(EntityPhantom.this);
            }
            this.c += this.f * 15.0f * 0.017453292f;
            EntityPhantom.this.b = new Vec3d(EntityPhantom.this.ax).add(this.d * MathUtils.cos(this.c), -4.0f + this.e, this.d * MathUtils.sin(this.c));
        }
    }
    
    class i extends h
    {
        private i() {
        }
        
        @Override
        public boolean canStart() {
            return EntityPhantom.this.getTarget() != null && EntityPhantom.this.bC == a.SWOOP;
        }
        
        @Override
        public boolean shouldContinue() {
            final EntityLiving vEntityLiving1 = EntityPhantom.this.getTarget();
            return vEntityLiving1 != null && vEntityLiving1.isValid() && (!(vEntityLiving1 instanceof EntityPlayer) || (!((EntityPlayer)vEntityLiving1).isSpectator() && !((EntityPlayer)vEntityLiving1).isCreative())) && this.canStart();
        }
        
        @Override
        public void start() {
        }
        
        @Override
        public void stop() {
            EntityPhantom.this.setTarget(null);
            EntityPhantom.this.bC = a.CIRCLE;
        }
        
        @Override
        public void tick() {
            final EntityLiving vEntityLiving1 = EntityPhantom.this.getTarget();
            EntityPhantom.this.b = new Vec3d(vEntityLiving1.x, vEntityLiving1.y + vEntityLiving1.height * 0.5, vEntityLiving1.z);
            if (EntityPhantom.this.getBoundingBox().expand(0.20000000298023224).intersects(vEntityLiving1.getBoundingBox())) {
                EntityPhantom.this.attackEntity(vEntityLiving1);
                EntityPhantom.this.bC = a.CIRCLE;
                EntityPhantom.this.world.fireWorldEvent(1039, new BlockPos(EntityPhantom.this), 0);
            }
            else if (EntityPhantom.this.z || EntityPhantom.this.hurtTime > 0) {
                EntityPhantom.this.bC = a.CIRCLE;
            }
        }
    }
    
    class c extends AiGoal
    {
        private int b;
        
        private c() {
        }
        
        @Override
        public boolean canStart() {
            return AiGoalTrackTarget.canTrack(EntityPhantom.this, EntityPhantom.this.getTarget(), false, false);
        }
        
        @Override
        public void start() {
            this.b = 10;
            EntityPhantom.this.bC = a.CIRCLE;
            this.g();
        }
        
        @Override
        public void stop() {
            EntityPhantom.this.ax = EntityPhantom.this.world.a(HeightMap.Type.MOTION_BLOCKING, EntityPhantom.this.ax).up(10 + EntityPhantom.this.rand.nextInt(20));
        }
        
        @Override
        public void tick() {
            if (EntityPhantom.this.bC == a.CIRCLE) {
                --this.b;
                if (this.b <= 0) {
                    EntityPhantom.this.bC = a.SWOOP;
                    this.g();
                    this.b = (8 + EntityPhantom.this.rand.nextInt(4)) * 20;
                    EntityPhantom.this.playSoundAtEntity(Sounds.ENTITY_PHANTOM_SWOOP, 10.0f, 0.95f + EntityPhantom.this.rand.nextFloat() * 0.1f);
                }
            }
        }
        
        private void g() {
            EntityPhantom.this.ax = new BlockPos(EntityPhantom.this.getTarget()).up(20 + EntityPhantom.this.rand.nextInt(20));
            if (EntityPhantom.this.ax.getY() < EntityPhantom.this.world.getSeaLevel()) {
                EntityPhantom.this.ax = new BlockPos(EntityPhantom.this.ax.getX(), EntityPhantom.this.world.getSeaLevel() + 1, EntityPhantom.this.ax.getZ());
            }
        }
    }
    
    class b extends AiGoal
    {
        private int b;
        
        private b() {
            this.b = 20;
        }
        
        @Override
        public boolean canStart() {
            if (this.b > 0) {
                --this.b;
                return false;
            }
            this.b = 60;
            final BoundingBox vBoundingBox1 = EntityPhantom.this.getBoundingBox().expand(16.0, 64.0, 16.0);
            final List<EntityPlayer> vList2 = EntityPhantom.this.world.<EntityPlayer>a((Class<? extends EntityPlayer>)EntityPlayer.class, vBoundingBox1);
            if (!vList2.isEmpty()) {
                vList2.sort((aEntityPlayer1, aEntityPlayer2) -> (aEntityPlayer1.y > aEntityPlayer2.y) ? -1 : 1);
                for (final EntityPlayer vEntityPlayer4 : vList2) {
                    if (AiGoalTrackTarget.canTrack(EntityPhantom.this, vEntityPlayer4, false, false)) {
                        EntityPhantom.this.setTarget(vEntityPlayer4);
                        return true;
                    }
                }
            }
            return false;
        }
        
        @Override
        public boolean shouldContinue() {
            return AiGoalTrackTarget.canTrack(EntityPhantom.this, EntityPhantom.this.getTarget(), false, false);
        }
    }
}
