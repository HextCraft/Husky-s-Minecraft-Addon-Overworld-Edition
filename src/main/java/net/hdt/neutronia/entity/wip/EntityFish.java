package net.hdt.neutronia.entity.wip;

import net.minecraft.util.math.MathUtils;
import net.minecraft.tag.FluidTags;
import net.minecraft.entity.ai.action.ActionMove;
import net.minecraft.entity.ai.AiGoalRoamAquatic;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import net.minecraft.sound.Sound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerServer;
import net.minecraft.reference.CriterionTriggers;
import net.minecraft.reference.Sounds;
import net.minecraft.reference.Items;
import net.minecraft.util.Hand;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.mob.EntityMob;
import net.minecraft.entity.ai.pathing.EntityNavigationAquatic;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.AiAvoidEntity;
import net.minecraft.entity.EntityPredicates;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.PathfinerMob;
import net.minecraft.entity.ai.AiGoalPanicRun;
import net.minecraft.nbt.NBTCompound;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.tracker.TrackedData;
import net.minecraft.entity.sortme.ILiving;
import net.minecraft.entity.mob.EntityAquatic;

public abstract class EntityFish extends EntityAquatic implements ILiving
{
    private static final TrackedData<Boolean> MOB_FLAGS;
    
    public EntityFish(final EntityType<?> aEntityType1, final World aWorld2) {
        super(aEntityType1, aWorld2);
        this.actionMove = new a(this);
    }
    
    @Override
    public float getEyeHeight() {
        return this.height * 0.65f;
    }
    
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(AttributeManager.MAX_HEALTH).setBaseValue(3.0);
    }
    
    @Override
    public boolean isPersistent() {
        return this.isFromBucket() || super.isPersistent();
    }
    
    @Override
    public boolean canSpawnHere(final IWorld aIWorld1, final boolean aBoolean2) {
        final BlockPos vBlockPos3 = new BlockPos(this);
        return aIWorld1.h(vBlockPos3).getBlock() == Blocks.A && aIWorld1.h(vBlockPos3.up()).getBlock() == Blocks.A && super.canSpawnHere(aIWorld1, aBoolean2);
    }
    
    @Override
    public boolean I() {
        return !this.isFromBucket() && !this.hasCustomName();
    }
    
    @Override
    public int getLimitPerChunk() {
        return 8;
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Boolean>startTracking(EntityFish.MOB_FLAGS, false);
    }
    
    private boolean isFromBucket() {
        return this.dataTracker.<Boolean>get(EntityFish.MOB_FLAGS);
    }
    
    public void setFromBucket(final boolean aBoolean) {
        this.dataTracker.<Boolean>set(EntityFish.MOB_FLAGS, aBoolean);
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setBoolean("FromBucket", this.isFromBucket());
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        this.setFromBucket(aNBTCompound.getBoolean("FromBucket"));
    }
    
    @Override
    protected void n() {
        super.n();
        this.goalSelector.add(0, new AiGoalPanicRun(this, 1.25));
        this.goalSelector.add(2, new AiAvoidEntity<Object>(this, EntityPlayer.class, 8.0f, 1.6, 1.4, EntityPredicates.f));
        this.goalSelector.add(4, new b(this));
    }
    
    @Override
    protected EntityNavigation createNavigation(final World aWorld) {
        return new EntityNavigationAquatic(this, aWorld);
    }
    
    @Override
    public void a(final float aFloat1, final float aFloat2, final float aFloat3) {
        if (this.cR() && this.isSwimming()) {
            this.a(aFloat1, aFloat2, aFloat3, 0.01f);
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
    public void updateMovement() {
        if (!this.isSwimming() && this.onGround && this.A) {
            this.s += 0.4000000059604645;
            this.r += (this.rand.nextFloat() * 2.0f - 1.0f) * 0.05f;
            this.t += (this.rand.nextFloat() * 2.0f - 1.0f) * 0.05f;
            this.onGround = false;
            this.velocityDirty = true;
            this.playSoundAtEntity(this.dA(), this.getSoundVolume(), this.getSoundPitch());
        }
        super.updateMovement();
    }
    
    @Override
    protected boolean interactMob(final EntityPlayer aEntityPlayer1, final Hand aHand2) {
        final ItemStack vItemStack3 = aEntityPlayer1.getStackInHand(aHand2);
        if (vItemStack3.getItem() == Items.kt && this.isValid()) {
            this.playSoundAtEntity(Sounds.ITEM_BUCKET_FILL_FISH, 1.0f, 1.0f);
            vItemStack3.subtract(1);
            final ItemStack vItemStack4 = this.l();
            this.f(vItemStack4);
            if (!this.world.isClient) {
                CriterionTriggers.FILLED_BUCKET.a((EntityPlayerServer)aEntityPlayer1, vItemStack4);
            }
            if (vItemStack3.isEmpty()) {
                aEntityPlayer1.setStackInHand(aHand2, vItemStack4);
            }
            else if (!aEntityPlayer1.inventory.insertStack(vItemStack4)) {
                aEntityPlayer1.dropItem(vItemStack4, false);
            }
            this.invalidate();
            return true;
        }
        return super.interactMob(aEntityPlayer1, aHand2);
    }
    
    protected void f(final ItemStack aItemStack) {
        if (this.hasCustomName()) {
            aItemStack.a(this.e());
        }
    }
    
    protected abstract ItemStack l();
    
    protected boolean dz() {
        return true;
    }
    
    protected abstract Sound dA();
    
    @Override
    protected Sound getSoundSwim() {
        return Sounds.ENTITY_FISH_SWIM;
    }
    
    static {
        MOB_FLAGS = DataTracker.<Boolean>registerData(EntityFish.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
    
    static class b extends AiGoalRoamAquatic
    {
        private final EntityFish h;
        
        public b(final EntityFish aEntityFish) {
            super(aEntityFish, 1.0, 40);
            this.h = aEntityFish;
        }
        
        @Override
        public boolean canStart() {
            return this.h.dz() && super.canStart();
        }
    }
    
    static class a extends ActionMove
    {
        private final EntityFish i;
        
        a(final EntityFish aEntityFish) {
            super(aEntityFish);
            this.i = aEntityFish;
        }
        
        @Override
        public void tick() {
            if (this.i.a(FluidTags.WATER)) {
                final EntityFish i = this.i;
                i.s += 0.005;
            }
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
            final EntityFish j = this.i;
            j.s += this.i.cM() * vDouble2 * 0.1;
        }
    }
}
