package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.ai.AiGoalPanicRun;
import net.minecraft.entity.ai.AiGoalGangAttack;
import net.minecraft.entity.EntityPredicates;
import net.minecraft.entity.ai.AiAvoidEntity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.Vec3i;
import net.minecraft.entity.ai.animal.AiGoalAnimalMate;
import net.minecraft.entity.ai.action.ActionMove;
import java.util.Comparator;
import java.util.Arrays;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.block.state.BlockState;
import net.minecraft.sound.Sound;
import net.minecraft.item.ItemEntitySpawnEgg;
import net.minecraft.util.Hand;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.village.VillageProperties;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.damage.DamageSource;
import java.util.Iterator;
import java.util.List;
import net.minecraft.reference.Items;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.client.particle.config.ParticleConfig;
import net.minecraft.client.particle.config.ParticleConfigStackDust;
import net.minecraft.client.particle.Particles;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.reference.Sounds;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathUtils;
import net.minecraft.world.IWorld;
import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.entity.ai.AiGoalRoamAvoidWater;
import net.minecraft.entity.ai.AiGoalFollowParent;
import net.minecraft.entity.ai.AiGoalIdleGaze;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.AiGoalWatchClosest;
import net.minecraft.entity.mob.EntityHostile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.AiGoalTempt;
import net.minecraft.item.ItemIngredient;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.entity.PathfinerMob;
import net.minecraft.entity.ai.mob.AiGoalMeleeAttack;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.mob.EntityMob;
import net.minecraft.entity.ai.AiGoalSwim;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTCompound;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.EntityItem;
import java.util.function.Predicate;
import net.minecraft.entity.tracker.TrackedData;

public class EntityPanda extends EntityAnimal
{
    private static final TrackedData<Integer> bD;
    private static final TrackedData<Integer> bE;
    private static final TrackedData<Integer> bG;
    private static final TrackedData<Byte> bH;
    private static final TrackedData<Byte> bI;
    private static final TrackedData<Byte> bJ;
    private boolean leashed;
    private boolean bL;
    public int inLove;
    private double bM;
    private double bN;
    private float bO;
    private float bP;
    private float bQ;
    private float bR;
    private float bS;
    private float bT;
    private static final Predicate<EntityItem> bU;
    
    public EntityPanda(final World aWorld) {
        super(EntityType.PANDA, aWorld);
        this.setSize(1.3f, 1.25f);
        this.actionMove = new f(this);
        if (!this.isChild()) {
            this.setCanPickUpLoot(true);
        }
    }
    
    public int dz() {
        return this.dataTracker.<Integer>get(EntityPanda.bD);
    }
    
    public void p(final int aInteger) {
        this.dataTracker.<Integer>set(EntityPanda.bD, aInteger);
    }
    
    public boolean dA() {
        return this.s(2);
    }
    
    public boolean dB() {
        return this.s(8);
    }
    
    public void s(final boolean aBoolean) {
        this.d(8, aBoolean);
    }
    
    public boolean dC() {
        return this.s(16);
    }
    
    public void t(final boolean aBoolean) {
        this.d(16, aBoolean);
    }
    
    public boolean dD() {
        return this.dataTracker.<Integer>get(EntityPanda.bG) > 0;
    }
    
    public void u(final boolean aBoolean) {
        this.dataTracker.<Integer>set(EntityPanda.bG, aBoolean ? 1 : 0);
    }
    
    private int dW() {
        return this.dataTracker.<Integer>get(EntityPanda.bG);
    }
    
    private void r(final int aInteger) {
        this.dataTracker.<Integer>set(EntityPanda.bG, aInteger);
    }
    
    public void v(final boolean aBoolean) {
        this.d(2, aBoolean);
        if (!aBoolean) {
            this.q(0);
        }
    }
    
    public int dI() {
        return this.dataTracker.<Integer>get(EntityPanda.bE);
    }
    
    public void q(final int aInteger) {
        this.dataTracker.<Integer>set(EntityPanda.bE, aInteger);
    }
    
    public c dJ() {
        return c.a(this.dataTracker.<Byte>get(EntityPanda.bH));
    }
    
    public void a(c aEntityPandac) {
        if (aEntityPandac.a() > 6) {
            aEntityPandac = this.dX();
        }
        this.dataTracker.<Byte>set(EntityPanda.bH, (byte)aEntityPandac.a());
    }
    
    public c dK() {
        return c.a(this.dataTracker.<Byte>get(EntityPanda.bI));
    }
    
    public void b(c aEntityPandac) {
        if (aEntityPandac.a() > 6) {
            aEntityPandac = this.dX();
        }
        this.dataTracker.<Byte>set(EntityPanda.bI, (byte)aEntityPandac.a());
    }
    
    public boolean dL() {
        return this.s(4);
    }
    
    public void w(final boolean aBoolean) {
        this.d(4, aBoolean);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Integer>startTracking(EntityPanda.bD, 0);
        this.dataTracker.<Integer>startTracking(EntityPanda.bE, 0);
        this.dataTracker.<Byte>startTracking(EntityPanda.bH, (byte)0);
        this.dataTracker.<Byte>startTracking(EntityPanda.bI, (byte)0);
        this.dataTracker.<Byte>startTracking(EntityPanda.bJ, (byte)0);
        this.dataTracker.<Integer>startTracking(EntityPanda.bG, 0);
    }
    
    private boolean s(final int aInteger) {
        return (this.dataTracker.<Byte>get(EntityPanda.bJ) & aInteger) != 0x0;
    }
    
    private void d(final int aInteger1, final boolean aBoolean2) {
        final byte vByte3 = this.dataTracker.<Byte>get(EntityPanda.bJ);
        if (aBoolean2) {
            this.dataTracker.<Byte>set(EntityPanda.bJ, (byte)(vByte3 | aInteger1));
        }
        else {
            this.dataTracker.<Byte>set(EntityPanda.bJ, (byte)(vByte3 & ~aInteger1));
        }
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setString("MainGene", this.dJ().b());
        aNBTCompound.setString("HiddenGene", this.dK().b());
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        this.a(c.a(aNBTCompound.getString("MainGene")));
        this.b(c.a(aNBTCompound.getString("HiddenGene")));
    }
    
    @Nullable
    @Override
    public EntityPassive createChild(final EntityPassive aEntityPassive) {
        final EntityPanda vEntityPanda2 = new EntityPanda(this.world);
        if (aEntityPassive instanceof EntityPanda) {
            vEntityPanda2.a(this, (EntityPanda)aEntityPassive);
        }
        vEntityPanda2.dU();
        return vEntityPanda2;
    }
    
    @Override
    protected void n() {
        this.goalSelector.add(0, new AiGoalSwim(this));
        this.goalSelector.add(2, new g(this, 2.0));
        this.goalSelector.add(2, new b(this, 1.0));
        this.goalSelector.add(3, new AiGoalMeleeAttack(this, 1.2000000476837158, true));
        this.goalSelector.add(4, new AiGoalTempt(this, 1.0, ItemIngredient.a(new IItemProvider[] { Blocks.kO.getItem() }), false));
        this.goalSelector.add(6, new a<Object>(this, EntityPlayer.class, 8.0f, 2.0, 2.0));
        this.goalSelector.add(6, new a<Object>(this, EntityHostile.class, 4.0f, 2.0, 2.0));
        this.goalSelector.add(7, new i());
        this.goalSelector.add(8, new e(this));
        this.goalSelector.add(8, new j(this));
        this.goalSelector.add(9, new AiGoalWatchClosest(this, EntityPlayer.class, 6.0f));
        this.goalSelector.add(10, new AiGoalIdleGaze(this));
        this.goalSelector.add(12, new h(this));
        this.goalSelector.add(13, new AiGoalFollowParent(this, 1.25));
        this.goalSelector.add(14, new AiGoalRoamAvoidWater(this, 1.0));
        this.targetSelector.add(1, new d(this, (Class<?>[])new Class[0]).a((Class<?>[])new Class[0]));
    }
    
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(0.15000000596046448);
        this.getAttributeContainer().register(AttributeManager.ATTACK_DAMAGE).setBaseValue(6.0);
    }
    
    public c dM() {
        return b(this.dJ(), this.dK());
    }
    
    public boolean dN() {
        return this.dM() == c.b;
    }
    
    public boolean dO() {
        return this.dM() == c.c;
    }
    
    public boolean dP() {
        return this.dM() == c.d;
    }
    
    public boolean dR() {
        return this.dM() == c.f;
    }
    
    public boolean dS() {
        return this.dM() == c.g;
    }
    
    private c dX() {
        final int vInteger1 = this.rand.nextInt(16);
        if (vInteger1 == 0) {
            return c.b;
        }
        if (vInteger1 == 1) {
            return c.c;
        }
        if (vInteger1 == 2) {
            return c.d;
        }
        if (vInteger1 == 4) {
            return c.g;
        }
        if (vInteger1 < 9) {
            return c.f;
        }
        if (vInteger1 < 11) {
            return c.e;
        }
        return c.a;
    }
    
    @Override
    public boolean canSpawnHere(final IWorld aIWorld1, final boolean aBoolean2) {
        final int vInteger3 = MathUtils.floor(this.x);
        final int vInteger4 = MathUtils.floor(this.getBoundingBox().minY);
        final int vInteger5 = MathUtils.floor(this.z);
        final BlockPos vBlockPos6 = new BlockPos(vInteger3, vInteger4, vInteger5);
        final Block vBlock7 = aIWorld1.h(vBlockPos6.down()).getBlock();
        return (vBlock7.hasTag(BlockTags.LEAVES) || vBlock7 == Blocks.aQ) && aIWorld1.a(vBlockPos6, 0) > 8;
    }
    
    @Override
    public boolean canBeLeashedBy(final EntityPlayer aEntityPlayer) {
        return false;
    }
    
    @Override
    public boolean attackEntity(final Entity aEntity) {
        this.playSoundAtEntity(Sounds.gw, 1.0f, 1.0f);
        if (!this.dS()) {
            this.bL = true;
        }
        return super.attackEntity(aEntity);
    }
    
    @Override
    public void update() {
        super.update();
        if (this.dO()) {
            if (this.world.W()) {
                this.s(true);
                this.u(false);
            }
            else if (!this.dD()) {
                this.s(false);
            }
        }
        if (this.getTarget() == null) {
            this.leashed = false;
            this.bL = false;
        }
        if (this.dz() > 0) {
            if (this.getTarget() != null) {
                this.a(this.getTarget(), 90.0f, 90.0f);
            }
            if (this.dz() == 29 || this.dz() == 14) {
                this.playSoundAtEntity(Sounds.gs, 1.0f, 1.0f);
            }
            this.p(this.dz() - 1);
        }
        if (this.dA()) {
            this.q(this.dI() + 1);
            if (this.dI() > 20) {
                this.v(false);
                this.ee();
            }
            else if (this.dI() == 1) {
                this.playSoundAtEntity(Sounds.gm, 1.0f, 1.0f);
            }
        }
        if (this.dL()) {
            this.ed();
        }
        else {
            this.inLove = 0;
        }
        if (this.dB()) {
            this.pitch = 0.0f;
        }
        this.ea();
        this.dY();
        this.eb();
        this.ec();
    }
    
    public boolean dT() {
        return this.dO() && this.world.W();
    }
    
    private void dY() {
        if (!this.dD() && this.dB() && !this.dT() && !this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() && this.rand.nextInt(80) == 1) {
            this.u(true);
        }
        else if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() || !this.dB()) {
            this.u(false);
        }
        if (this.dD()) {
            this.dZ();
            if (!this.world.isClient && this.dW() > 80 && this.rand.nextInt(20) == 1) {
                if (this.dW() > 100 && this.f(this.getEquippedStack(EquipmentSlot.MAINHAND))) {
                    if (!this.world.isClient) {
                        this.setEquippedStack(EquipmentSlot.MAINHAND, ItemStack.NULL_STACK);
                    }
                    this.s(false);
                }
                this.u(false);
                return;
            }
            this.r(this.dW() + 1);
        }
    }
    
    private void dZ() {
        if (this.dW() % 5 == 0) {
            this.playSoundAtEntity(Sounds.gq, 0.5f + 0.5f * this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            for (int vInteger1 = 0; vInteger1 < 6; ++vInteger1) {
                Vec3d vVec3d2 = new Vec3d((this.rand.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, (this.rand.nextFloat() - 0.5) * 0.1);
                vVec3d2 = vVec3d2.rotateX(-this.pitch * 0.017453292f);
                vVec3d2 = vVec3d2.rotateY(-this.yaw * 0.017453292f);
                final double vDouble3 = -this.rand.nextFloat() * 0.6 - 0.3;
                Vec3d vVec3d3 = new Vec3d((this.rand.nextFloat() - 0.5) * 0.8, vDouble3, 1.0 + (this.rand.nextFloat() - 0.5) * 0.4);
                vVec3d3 = vVec3d3.rotateY(-this.aO * 0.017453292f);
                vVec3d3 = vVec3d3.add(this.x, this.y + this.getEyeHeight() + 1.0, this.z);
                this.world.a(new ParticleConfigStackDust(Particles.ITEM, this.getEquippedStack(EquipmentSlot.MAINHAND)), vVec3d3.x, vVec3d3.y, vVec3d3.z, vVec3d2.x, vVec3d2.y + 0.05, vVec3d2.z);
            }
        }
    }
    
    private void ea() {
        this.bP = this.bO;
        if (this.dB()) {
            this.bO = Math.min(1.0f, this.bO + 0.15f);
        }
        else {
            this.bO = Math.max(0.0f, this.bO - 0.19f);
        }
    }
    
    private void eb() {
        this.bR = this.bQ;
        if (this.dC()) {
            this.bQ = Math.min(1.0f, this.bQ + 0.15f);
        }
        else {
            this.bQ = Math.max(0.0f, this.bQ - 0.19f);
        }
    }
    
    private void ec() {
        this.bT = this.bS;
        if (this.dL()) {
            this.bS = Math.min(1.0f, this.bS + 0.15f);
        }
        else {
            this.bS = Math.max(0.0f, this.bS - 0.19f);
        }
    }
    
    @Sided(Side.CLIENT)
    public float v(final float aFloat) {
        return this.b(this.bP, this.bO, aFloat);
    }
    
    @Sided(Side.CLIENT)
    public float w(final float aFloat) {
        return this.b(this.bR, this.bQ, aFloat);
    }
    
    @Sided(Side.CLIENT)
    public float x(final float aFloat) {
        return this.b(this.bT, this.bS, aFloat);
    }
    
    private void ed() {
        ++this.inLove;
        if (this.inLove > 32) {
            this.w(false);
            return;
        }
        if (!this.world.isClient) {
            if (this.inLove == 1) {
                this.s = 0.27000001072883606;
                final float vFloat1 = this.yaw * 0.017453292f;
                final float vFloat2 = this.isChild() ? 0.1f : 0.2f;
                this.r -= MathUtils.sin(vFloat1) * vFloat2;
                this.t += MathUtils.cos(vFloat1) * vFloat2;
                this.bM = this.r;
                this.bN = this.t;
            }
            else if (this.inLove == 7.0f || this.inLove == 15.0f || this.inLove == 23.0f) {
                if (this.onGround) {
                    this.s = 0.27000001072883606;
                }
                this.r = 0.0;
                this.t = 0.0;
            }
            else {
                this.r = this.bM;
                this.t = this.bN;
            }
        }
    }
    
    private void ee() {
        this.world.a(Particles.M, this.x - (this.width + 1.0f) * 0.5 * MathUtils.sin(this.aO * 0.017453292f), this.y + this.getEyeHeight() - 0.10000000149011612, this.z + (this.width + 1.0f) * 0.5 * MathUtils.cos(this.aO * 0.017453292f), this.r, 0.0, this.t);
        this.playSoundAtEntity(Sounds.gn, 1.0f, 1.0f);
        final List<EntityPanda> vList1 = this.world.<EntityPanda>a((Class<? extends EntityPanda>)EntityPanda.class, this.getBoundingBox().expand(10.0));
        for (final EntityPanda vEntityPanda3 : vList1) {
            if (!vEntityPanda3.isChild() && vEntityPanda3.onGround && !vEntityPanda3.isSwimming() && !vEntityPanda3.dC()) {
                vEntityPanda3.cJ();
            }
        }
        if (this.rand.nextInt(700) == 0 && this.world.getGameRules().getBoolean("doMobLoot")) {
            this.a(Items.kO);
        }
    }
    
    @Override
    protected void a(final EntityItem aEntityItem) {
        if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty() && EntityPanda.bU.test(aEntityItem)) {
            final ItemStack vItemStack2 = aEntityItem.getStack();
            this.setEquippedStack(EquipmentSlot.MAINHAND, vItemStack2);
            this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 2.0f;
            this.a(aEntityItem, vItemStack2.getAmount());
            aEntityItem.invalidate();
        }
    }
    
    @Override
    public boolean applyDamage(final DamageSource aDamageSource1, final float aFloat2) {
        this.s(false);
        return super.applyDamage(aDamageSource1, aFloat2);
    }
    
    @Override
    public void setAttacker(@Nullable final EntityLiving aEntityLiving) {
        super.setAttacker(aEntityLiving);
        if (aEntityLiving instanceof EntityPlayer) {
            final List<EntityVillager> vList2 = this.world.<EntityVillager>a((Class<? extends EntityVillager>)EntityVillager.class, this.getBoundingBox().expand(10.0));
            boolean vBoolean3 = false;
            for (final EntityVillager vEntityVillager5 : vList2) {
                if (vEntityVillager5.isValid()) {
                    this.world.a(vEntityVillager5, (byte)13);
                    if (vBoolean3) {
                        continue;
                    }
                    final VillageProperties vVillageProperties6 = vEntityVillager5.dC();
                    if (vVillageProperties6 == null) {
                        continue;
                    }
                    vBoolean3 = true;
                    vVillageProperties6.addAttacker(aEntityLiving);
                    int vInteger7 = -1;
                    if (this.isChild()) {
                        vInteger7 = -3;
                    }
                    vVillageProperties6.a(((EntityPlayer)aEntityLiving).getGameProfile().getName(), vInteger7);
                }
            }
        }
    }
    
    @Nullable
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable final IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        this.a(this.dX());
        this.b(this.dX());
        this.dU();
        return super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
    }
    
    public void a(final EntityPanda aEntityPanda1, @Nullable final EntityPanda aEntityPanda2) {
        if (aEntityPanda2 == null) {
            if (this.rand.nextBoolean()) {
                this.a(aEntityPanda1.ef());
                this.b(this.dX());
            }
            else {
                this.a(this.dX());
                this.b(aEntityPanda1.ef());
            }
        }
        else if (this.rand.nextBoolean()) {
            this.a(aEntityPanda1.ef());
            this.b(aEntityPanda2.ef());
        }
        else {
            this.a(aEntityPanda2.ef());
            this.b(aEntityPanda1.ef());
        }
        if (this.rand.nextInt(32) == 0) {
            this.a(this.dX());
        }
        if (this.rand.nextInt(32) == 0) {
            this.b(this.dX());
        }
    }
    
    private c ef() {
        if (this.rand.nextBoolean()) {
            return this.dJ();
        }
        return this.dK();
    }
    
    public void dU() {
        if (this.dR()) {
            this.getAttributeInstance(AttributeManager.MAX_HEALTH).setBaseValue(10.0);
        }
        if (this.dN()) {
            this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(0.07000000029802322);
        }
    }
    
    @Override
    public boolean interactMob(final EntityPlayer aEntityPlayer1, final Hand aHand2) {
        final ItemStack vItemStack3 = aEntityPlayer1.getStackInHand(aHand2);
        if (vItemStack3.getItem() instanceof ItemEntitySpawnEgg) {
            return super.interactMob(aEntityPlayer1, aHand2);
        }
        if (this.dT()) {
            return false;
        }
        if (this.dC()) {
            this.t(false);
            return true;
        }
        if (this.f(vItemStack3)) {
            if (this.getTarget() != null) {
                this.leashed = true;
            }
            if (this.isChild()) {
                this.a(aEntityPlayer1, vItemStack3);
                this.a((int)(-this.getBreedingAge() / 20 * 0.1f), true);
            }
            else if (!this.world.isClient && this.getBreedingAge() == 0 && this.dE()) {
                this.a(aEntityPlayer1, vItemStack3);
                this.f(aEntityPlayer1);
            }
            else {
                if (this.world.isClient || this.dB()) {
                    return false;
                }
                this.s(true);
                this.u(true);
                final ItemStack vItemStack4 = this.getEquippedStack(EquipmentSlot.MAINHAND);
                if (!vItemStack4.isEmpty() && !aEntityPlayer1.abilities.creativeMode) {
                    this.a_(vItemStack4);
                }
                this.setEquippedStack(EquipmentSlot.MAINHAND, new ItemStack(vItemStack3.getItem(), 1));
                this.a(aEntityPlayer1, vItemStack3);
            }
            return true;
        }
        return false;
    }
    
    @Nullable
    @Override
    protected Sound getSoundAmbient() {
        if (this.dS()) {
            return Sounds.gt;
        }
        if (this.dO()) {
            return Sounds.gu;
        }
        return Sounds.go;
    }
    
    @Override
    protected void playStepSound(final BlockPos aBlockPos1, final BlockState aBlockState2) {
        this.playSoundAtEntity(Sounds.gr, 0.15f, 1.0f);
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.K;
    }
    
    @Override
    public boolean f(final ItemStack aItemStack) {
        return aItemStack.getItem() == Blocks.kO.getItem();
    }
    
    @Nullable
    @Override
    protected Sound getSoundDeath() {
        return Sounds.gp;
    }
    
    @Nullable
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.gv;
    }
    
    static {
        bD = DataTracker.<Integer>registerData(EntityPanda.class, TrackedDataHandlerRegistry.INTEGER);
        bE = DataTracker.<Integer>registerData(EntityPanda.class, TrackedDataHandlerRegistry.INTEGER);
        bG = DataTracker.<Integer>registerData(EntityPanda.class, TrackedDataHandlerRegistry.INTEGER);
        bH = DataTracker.<Byte>registerData(EntityPanda.class, TrackedDataHandlerRegistry.BYTE);
        bI = DataTracker.<Byte>registerData(EntityPanda.class, TrackedDataHandlerRegistry.BYTE);
        bJ = DataTracker.<Byte>registerData(EntityPanda.class, TrackedDataHandlerRegistry.BYTE);
        final Item vItem2;
        bU = (aEntityItem -> {
            vItem2 = aEntityItem.getStack().getItem();
            return (vItem2 == Blocks.kO.getItem() || vItem2 == Blocks.cP.getItem()) && aEntityItem.isValid() && !aEntityItem.q();
        });
    }
    
    public enum c
    {
        a(0, "normal", false, "textures/entity/panda/panda.png"), 
        b(1, "lazy", false, "textures/entity/panda/lazy_panda.png"), 
        c(2, "worried", false, "textures/entity/panda/worried_panda.png"), 
        d(3, "playful", false, "textures/entity/panda/playful_panda.png"), 
        e(4, "brown", true, "textures/entity/panda/brown_panda.png"), 
        f(5, "weak", true, "textures/entity/panda/weak_panda.png"), 
        g(6, "aggressive", false, "textures/entity/panda/aggressive_panda.png");
        
        private static final c[] h;
        private final int i;
        private final String j;
        private final boolean k;
        private final Identifier l;
        
        private c(final int aInteger1, final String aString2, final boolean aBoolean3, final String aString4) {
            this.i = aInteger1;
            this.j = aString2;
            this.k = aBoolean3;
            this.l = new Identifier(aString4);
        }
        
        public int a() {
            return this.i;
        }
        
        public String b() {
            return this.j;
        }
        
        public boolean c() {
            return this.k;
        }
        
        private static c b(final c aEntityPandac1, final c aEntityPandac2) {
            if (!aEntityPandac1.c()) {
                return aEntityPandac1;
            }
            if (aEntityPandac1 == aEntityPandac2) {
                return aEntityPandac1;
            }
            return c.a;
        }
        
        @Sided(Side.CLIENT)
        public Identifier d() {
            return this.l;
        }
        
        public static c a(int aInteger) {
            if (aInteger < 0 || aInteger >= c.h.length) {
                aInteger = 0;
            }
            return c.h[aInteger];
        }
        
        public static c a(final String aString) {
            for (final c vEntityPandac5 : values()) {
                if (vEntityPandac5.j.equals(aString)) {
                    return vEntityPandac5;
                }
            }
            return c.a;
        }
        
        static {
            h = Arrays.<c>stream(values()).sorted(Comparator.<? super c>comparingInt(c::a)).<c>toArray(c[]::new);
        }
    }
    
    static class f extends ActionMove
    {
        private final EntityPanda i;
        
        public f(final EntityPanda aEntityPanda) {
            super(aEntityPanda);
            this.i = aEntityPanda;
        }
        
        @Override
        public void tick() {
            if (this.i.dB() || this.i.dC() || this.i.dT()) {
                return;
            }
            super.tick();
        }
    }
    
    static class h extends AiGoal
    {
        private final EntityPanda a;
        
        public h(final EntityPanda aEntityPanda) {
            this.a = aEntityPanda;
            this.setCategoryBits(7);
        }
        
        @Override
        public boolean canStart() {
            if ((!this.a.isChild() && !this.a.dP()) || !this.a.onGround || this.a.dC() || this.a.dA()) {
                return false;
            }
            final float vFloat1 = this.a.yaw * 0.017453292f;
            int vInteger2 = 0;
            int vInteger3 = 0;
            final float vFloat2 = -MathUtils.sin(vFloat1);
            final float vFloat3 = MathUtils.cos(vFloat1);
            if (Math.abs(vFloat2) > 0.5) {
                vInteger2 += (int)(vFloat2 / Math.abs(vFloat2));
            }
            if (Math.abs(vFloat3) > 0.5) {
                vInteger3 += (int)(vFloat3 / Math.abs(vFloat3));
            }
            return this.a.world.h(new BlockPos(this.a).add(vInteger2, -1, vInteger3)).isAir() || (this.a.dP() && this.a.rand.nextInt(60) == 1) || this.a.rand.nextInt(500) == 1;
        }
        
        @Override
        public boolean shouldContinue() {
            return false;
        }
        
        @Override
        public void start() {
            this.a.w(true);
        }
        
        @Override
        public boolean canStop() {
            return false;
        }
    }
    
    static class j extends AiGoal
    {
        private final EntityPanda a;
        
        public j(final EntityPanda aEntityPanda) {
            this.a = aEntityPanda;
        }
        
        @Override
        public boolean canStart() {
            return this.a.isChild() && !this.a.dL() && ((this.a.dR() && this.a.rand.nextInt(500) == 1) || this.a.rand.nextInt(6000) == 1);
        }
        
        @Override
        public boolean shouldContinue() {
            return false;
        }
        
        @Override
        public void start() {
            this.a.v(true);
        }
    }
    
    static class b extends AiGoalAnimalMate
    {
        private final EntityPanda d;
        private int timer;
        
        public b(final EntityPanda aEntityPanda1, final double aDouble2) {
            super(aEntityPanda1, aDouble2);
            this.d = aEntityPanda1;
        }
        
        @Override
        public boolean canStart() {
            if (!super.canStart() || this.d.dz() != 0) {
                return false;
            }
            if (!this.i()) {
                if (this.timer <= this.d.age) {
                    this.d.p(32);
                    this.timer = this.d.age + 600;
                    if (this.d.cR()) {
                        final EntityPlayer vEntityPlayer1 = this.world.a(this.d, 8.0);
                        this.d.setTarget(vEntityPlayer1);
                    }
                }
                return false;
            }
            return true;
        }
        
        private boolean i() {
            final BlockPos vBlockPos1 = new BlockPos(this.d);
            final BlockPos.Mutable vBlockPosMutable2 = new BlockPos.Mutable();
            for (int vInteger3 = 0; vInteger3 < 3; ++vInteger3) {
                for (int vInteger4 = 0; vInteger4 < 8; ++vInteger4) {
                    for (int vInteger5 = 0; vInteger5 <= vInteger4; vInteger5 = ((vInteger5 > 0) ? (-vInteger5) : (1 - vInteger5))) {
                        for (int vInteger6 = (vInteger5 < vInteger4 && vInteger5 > -vInteger4) ? vInteger4 : 0; vInteger6 <= vInteger4; vInteger6 = ((vInteger6 > 0) ? (-vInteger6) : (1 - vInteger6))) {
                            vBlockPosMutable2.set(vBlockPos1).e(vInteger5, vInteger3, vInteger6);
                            if (this.world.h(vBlockPosMutable2).getBlock() == Blocks.kO) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
    
    static class a<T extends Entity> extends AiAvoidEntity<T>
    {
        private final EntityPanda c;
        
        public a(final EntityPanda aEntityPanda1, final Class<T> aClass2, final float aFloat3, final double aDouble4, final double vDouble6) {
            super(aEntityPanda1, aClass2, aFloat3, aDouble4, vDouble6, EntityPredicates.f);
            this.c = aEntityPanda1;
        }
        
        @Override
        public boolean canStart() {
            return this.c.dO() && super.canStart();
        }
    }
    
    class i extends AiGoal
    {
        private int b;
        
        public i() {
            this.setCategoryBits(1);
        }
        
        @Override
        public boolean canStart() {
            if (this.b > EntityPanda.this.age || EntityPanda.this.isChild() || EntityPanda.this.dB() || EntityPanda.this.isSwimming() || EntityPanda.this.dC() || EntityPanda.this.dz() > 0) {
                return false;
            }
            final List<EntityItem> vList1 = EntityPanda.this.world.<EntityItem>a((Class<? extends EntityItem>)EntityItem.class, EntityPanda.this.getBoundingBox().expand(6.0, 6.0, 6.0), (Predicate<? super EntityItem>)EntityPanda.bU);
            return !vList1.isEmpty() || !EntityPanda.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
        }
        
        @Override
        public boolean shouldContinue() {
            return !EntityPanda.this.isSwimming() && (EntityPanda.this.dN() || EntityPanda.this.rand.nextInt(600) != 1) && EntityPanda.this.rand.nextInt(2000) != 1;
        }
        
        @Override
        public void start() {
            final List<EntityItem> vList1 = EntityPanda.this.world.<EntityItem>a((Class<? extends EntityItem>)EntityItem.class, EntityPanda.this.getBoundingBox().expand(8.0, 8.0, 8.0), (Predicate<? super EntityItem>)EntityPanda.bU);
            if (!vList1.isEmpty() && EntityPanda.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                EntityPanda.this.t().moveTo(vList1.get(0), 1.2000000476837158);
            }
            else if (!EntityPanda.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                EntityPanda.this.s(true);
            }
            this.b = 0;
        }
        
        @Override
        public void stop() {
            final ItemStack vItemStack1 = EntityPanda.this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (!vItemStack1.isEmpty()) {
                EntityPanda.this.a_(vItemStack1);
                EntityPanda.this.setEquippedStack(EquipmentSlot.MAINHAND, ItemStack.NULL_STACK);
                final int vInteger2 = EntityPanda.this.dN() ? (EntityPanda.this.rand.nextInt(50) + 10) : (EntityPanda.this.rand.nextInt(150) + 10);
                this.b = EntityPanda.this.age + vInteger2 * 20;
            }
            EntityPanda.this.s(false);
        }
    }
    
    static class e extends AiGoal
    {
        private final EntityPanda a;
        private int b;
        
        public e(final EntityPanda aEntityPanda) {
            this.a = aEntityPanda;
        }
        
        @Override
        public boolean canStart() {
            return this.b < this.a.age && this.a.dN() && !this.a.dB() && !this.a.dL() && this.a.rand.nextInt(400) == 1;
        }
        
        @Override
        public boolean shouldContinue() {
            return !this.a.isSwimming() && (this.a.dN() || this.a.rand.nextInt(600) != 1) && this.a.rand.nextInt(2000) != 1;
        }
        
        @Override
        public void start() {
            this.a.t(true);
            this.b = 0;
        }
        
        @Override
        public void stop() {
            this.a.t(false);
            this.b = this.a.age + 200;
        }
    }
    
    static class d extends AiGoalGangAttack
    {
        final EntityPanda a;
        
        public d(final EntityPanda aEntityPanda1, final Class<?>... aArr2) {
            super(aEntityPanda1, aArr2);
            this.a = aEntityPanda1;
        }
        
        @Override
        public boolean shouldContinue() {
            if (this.a.leashed || this.a.bL) {
                this.a.setTarget(null);
                return false;
            }
            return super.shouldContinue();
        }
        
        @Override
        protected void setTargetTo(final PathfinerMob aPathfinerMob1, final EntityLiving aEntityLiving2) {
            if (aPathfinerMob1 instanceof EntityPanda && ((EntityPanda)aPathfinerMob1).dS()) {
                aPathfinerMob1.setTarget(aEntityLiving2);
            }
        }
    }
    
    static class g extends AiGoalPanicRun
    {
        private final EntityPanda f;
        
        public g(final EntityPanda aEntityPanda1, final double aDouble2) {
            super(aEntityPanda1, aDouble2);
            this.f = aEntityPanda1;
        }
        
        @Override
        public boolean canStart() {
            return this.a.isOnFire();
        }
        
        @Override
        public boolean shouldContinue() {
            if (this.f.dB()) {
                this.f.t().clearPath();
                return false;
            }
            return super.shouldContinue();
        }
    }
}
