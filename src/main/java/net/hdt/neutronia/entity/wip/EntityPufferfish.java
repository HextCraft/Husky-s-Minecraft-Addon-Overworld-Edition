package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.Entity;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import net.minecraft.enchantment.DamageCategory;
import net.minecraft.sound.Sound;
import net.minecraft.network.IPacketHandler;
import net.minecraft.network.packet.client.CPacketGameStateChange;
import net.minecraft.entity.player.EntityPlayerServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.reference.PotionEffectTypes;
import net.minecraft.entity.damage.DamageSource;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.mob.EntityMob;
import net.minecraft.reference.Sounds;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.util.IItemProvider;
import net.minecraft.reference.Items;
import net.minecraft.item.ItemStack;
import javax.annotation.Nullable;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.nbt.NBTCompound;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;
import java.util.function.Predicate;
import net.minecraft.entity.tracker.TrackedData;

public class EntityPufferfish extends EntityFish
{
    private static final TrackedData<Integer> MOB_FLAGS;
    private int b;
    private int c;
    private static final Predicate<EntityLiving> bC;
    private float bD;
    private float bE;
    
    public EntityPufferfish(final World aWorld) {
        super(EntityType.PUFFERFISH, aWorld);
        this.bD = -1.0f;
        this.setSize(0.7f, 0.7f);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Integer>startTracking(EntityPufferfish.MOB_FLAGS, 0);
    }
    
    public int getPuffState() {
        return this.dataTracker.<Integer>get(EntityPufferfish.MOB_FLAGS);
    }
    
    public void b(final int aInteger) {
        this.dataTracker.<Integer>set(EntityPufferfish.MOB_FLAGS, aInteger);
        this.d(aInteger);
    }
    
    private void d(final int aInteger) {
        float vFloat2 = 1.0f;
        if (aInteger == 1) {
            vFloat2 = 0.7f;
        }
        else if (aInteger == 0) {
            vFloat2 = 0.5f;
        }
        this.a(vFloat2);
    }
    
    @Override
    protected final void setSize(final float aFloat1, final float aFloat2) {
        final boolean vBoolean3 = this.bD > 0.0f;
        this.bD = aFloat1;
        this.bE = aFloat2;
        if (!vBoolean3) {
            this.a(1.0f);
        }
    }
    
    private void a(final float aFloat) {
        super.setSize(this.bD * aFloat, this.bE * aFloat);
    }
    
    @Override
    public void onTrackedDataSet(final TrackedData<?> aTrackedData) {
        this.d(this.getPuffState());
        super.onTrackedDataSet(aTrackedData);
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setInt("PuffState", this.getPuffState());
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        this.b(aNBTCompound.getInt("PuffState"));
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_PUFFERFISH;
    }
    
    @Override
    protected ItemStack l() {
        return new ItemStack(Items.kC);
    }
    
    @Override
    protected void n() {
        super.n();
        this.goalSelector.add(1, new a(this));
    }
    
    @Override
    public void update() {
        if (this.isValid() && !this.world.isClient) {
            if (this.b > 0) {
                if (this.getPuffState() == 0) {
                    this.playSoundAtEntity(Sounds.ENTITY_PUFFER_FISH_BLOW_UP, this.getSoundVolume(), this.getSoundPitch());
                    this.b(1);
                }
                else if (this.b > 40 && this.getPuffState() == 1) {
                    this.playSoundAtEntity(Sounds.ENTITY_PUFFER_FISH_BLOW_UP, this.getSoundVolume(), this.getSoundPitch());
                    this.b(2);
                }
                ++this.b;
            }
            else if (this.getPuffState() != 0) {
                if (this.c > 60 && this.getPuffState() == 2) {
                    this.playSoundAtEntity(Sounds.ENTITY_PUFFER_FISH_BLOW_OUT, this.getSoundVolume(), this.getSoundPitch());
                    this.b(1);
                }
                else if (this.c > 100 && this.getPuffState() == 1) {
                    this.playSoundAtEntity(Sounds.ENTITY_PUFFER_FISH_BLOW_OUT, this.getSoundVolume(), this.getSoundPitch());
                    this.b(0);
                }
                ++this.c;
            }
        }
        super.update();
    }
    
    @Override
    public void updateMovement() {
        super.updateMovement();
        if (this.getPuffState() > 0) {
            final List<EntityMob> vList1 = this.world.<EntityMob>a((Class<? extends EntityMob>)EntityMob.class, this.getBoundingBox().expand(0.3), (Predicate<? super EntityMob>)EntityPufferfish.bC);
            for (final EntityMob vEntityMob3 : vList1) {
                if (vEntityMob3.isValid()) {
                    this.a(vEntityMob3);
                }
            }
        }
    }
    
    private void a(final EntityMob aEntityMob) {
        final int vInteger2 = this.getPuffState();
        if (aEntityMob.applyDamage(DamageSource.byMob(this), 1 + vInteger2)) {
            aEntityMob.addPotionEffect(new PotionEffect(PotionEffectTypes.s, 60 * vInteger2, 0));
            this.playSoundAtEntity(Sounds.ENTITY_PUFFER_FISH_STING, 1.0f, 1.0f);
        }
    }
    
    @Override
    public void d(final EntityPlayer aEntityPlayer) {
        final int vInteger2 = this.getPuffState();
        if (aEntityPlayer instanceof EntityPlayerServer && vInteger2 > 0 && aEntityPlayer.applyDamage(DamageSource.byMob(this), 1 + vInteger2)) {
            ((EntityPlayerServer)aEntityPlayer).networkHandler.a(new CPacketGameStateChange(9, 0.0f));
            aEntityPlayer.addPotionEffect(new PotionEffect(PotionEffectTypes.s, 60 * vInteger2, 0));
        }
    }
    
    @Override
    protected Sound getSoundAmbient() {
        return Sounds.ENTITY_PUFFER_FISH_AMBIENT;
    }
    
    @Override
    protected Sound getSoundDeath() {
        return Sounds.ENTITY_PUFFER_FISH_DEATH;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.ENTITY_PUFFER_FISH_HURT;
    }
    
    @Override
    protected Sound dA() {
        return Sounds.ENTITY_PUFFER_FISH_FLOP;
    }
    
    static {
        MOB_FLAGS = DataTracker.<Integer>registerData(EntityPufferfish.class, TrackedDataHandlerRegistry.INTEGER);
        bC = (aEntityLiving -> {
            if (aEntityLiving == null) {
                return false;
            }
            else if (aEntityLiving instanceof EntityPlayer && (aEntityLiving.isSpectator() || aEntityLiving.isCreative())) {
                return false;
            }
            else {
                return aEntityLiving.getEntityAttribute() != DamageCategory.AQUATIC;
            }
        });
    }
    
    static class a extends AiGoal
    {
        private final EntityPufferfish a;
        
        public a(final EntityPufferfish aEntityPufferfish) {
            this.a = aEntityPufferfish;
        }
        
        @Override
        public boolean canStart() {
            final List<EntityLiving> vList1 = this.a.world.<EntityLiving>a((Class<? extends EntityLiving>)EntityLiving.class, this.a.getBoundingBox().expand(2.0), (Predicate<? super EntityLiving>)EntityPufferfish.bC);
            return !vList1.isEmpty();
        }
        
        @Override
        public void start() {
            this.a.b = 1;
            this.a.c = 0;
        }
        
        @Override
        public void stop() {
            this.a.b = 0;
        }
        
        @Override
        public boolean shouldContinue() {
            final List<EntityLiving> vList1 = this.a.world.<EntityLiving>a((Class<? extends EntityLiving>)EntityLiving.class, this.a.getBoundingBox().expand(2.0), (Predicate<? super EntityLiving>)EntityPufferfish.bC);
            return !vList1.isEmpty();
        }
    }
}
