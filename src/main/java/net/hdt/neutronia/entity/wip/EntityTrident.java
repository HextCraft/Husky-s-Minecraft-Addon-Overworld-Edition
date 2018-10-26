package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NamedBinaryTag;
import net.minecraft.nbt.NBTCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.sound.Sound;
import net.minecraft.entity.EntityLightning;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.HitResult;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayerServer;
import net.minecraft.entity.Entity;
import net.minecraft.reference.Sounds;
import net.minecraft.util.math.Vec3d;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IItemProvider;
import net.minecraft.reference.Items;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.tracker.TrackedData;

public class EntityTrident extends EntityArrowBase
{
    private static final TrackedData<Byte> ARROW_FLAGS;
    private ItemStack trident;
    private boolean dealtDamage;
    public int au;
    
    public EntityTrident(final World aWorld) {
        super(EntityType.TRIDENT, aWorld);
        this.trident = new ItemStack(Items.pk);
    }
    
    public EntityTrident(final World aWorld1, final EntityLiving aEntityLiving2, final ItemStack aItemStack3) {
        super(EntityType.TRIDENT, aEntityLiving2, aWorld1);
        this.trident = new ItemStack(Items.pk);
        this.trident = aItemStack3.copy();
        this.dataTracker.<Byte>set(EntityTrident.ARROW_FLAGS, (byte)EnchantmentHelper.f(aItemStack3));
    }
    
    @Sided(Side.CLIENT)
    public EntityTrident(final World aWorld1, final double aDouble2, final double aDouble4, final double vDouble6) {
        super(EntityType.TRIDENT, aDouble2, aDouble4, vDouble6, aWorld1);
        this.trident = new ItemStack(Items.pk);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Byte>startTracking(EntityTrident.ARROW_FLAGS, (byte)0);
    }
    
    @Override
    public void update() {
        if (this.c > 4) {
            this.dealtDamage = true;
        }
        final Entity vEntity1 = this.k();
        if ((this.dealtDamage || this.isInBubbleColumn()) && vEntity1 != null) {
            final int vInteger2 = this.dataTracker.<Byte>get(EntityTrident.ARROW_FLAGS);
            if (vInteger2 > 0 && !this.t()) {
                if (!this.world.isClient && this.pickupType == PickupType.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1f);
                }
                this.invalidate();
            }
            else if (vInteger2 > 0) {
                this.o(true);
                Vec3d vVec3d3 = new Vec3d(vEntity1.x - this.x, vEntity1.y + vEntity1.getEyeHeight() - this.y, vEntity1.z - this.z);
                this.y += vVec3d3.y * 0.015 * vInteger2;
                if (this.world.isClient) {
                    this.M = this.y;
                }
                vVec3d3 = vVec3d3.normalize();
                final double vDouble4 = 0.05 * vInteger2;
                this.r += vVec3d3.x * vDouble4 - this.r * 0.05;
                this.s += vVec3d3.y * vDouble4 - this.s * 0.05;
                this.t += vVec3d3.z * vDouble4 - this.t * 0.05;
                if (this.au == 0) {
                    this.playSoundAtEntity(Sounds.ITEM_TRIDENT_RETURN, 10.0f, 1.0f);
                }
                ++this.au;
            }
        }
        super.update();
    }
    
    private boolean t() {
        final Entity vEntity1 = this.k();
        return vEntity1 != null && vEntity1.isValid() && (!(vEntity1 instanceof EntityPlayerServer) || !((EntityPlayerServer)vEntity1).isSpectator());
    }
    
    @Override
    protected ItemStack asItemStack() {
        return this.trident.copy();
    }
    
    @Nullable
    @Override
    protected Entity a(final Vec3d aVec3d1, final Vec3d aVec3d2) {
        if (this.dealtDamage) {
            return null;
        }
        return super.a(aVec3d1, aVec3d2);
    }
    
    @Override
    protected void b(final HitResult aHitResult) {
        final Entity vEntity2 = aHitResult.entity;
        float vFloat3 = 8.0f;
        if (vEntity2 instanceof EntityLiving) {
            final EntityLiving vEntityLiving4 = (EntityLiving)vEntity2;
            vFloat3 += EnchantmentHelper.a(this.trident, vEntityLiving4.getEntityAttribute());
        }
        final Entity vEntity3 = this.k();
        final DamageSource vDamageSource5 = DamageSource.byTrident(this, (vEntity3 == null) ? this : vEntity3);
        this.dealtDamage = true;
        Sound vSound6 = Sounds.ITEM_TRIDENT_HIT;
        if (vEntity2.applyDamage(vDamageSource5, vFloat3) && vEntity2 instanceof EntityLiving) {
            final EntityLiving vEntityLiving5 = (EntityLiving)vEntity2;
            if (vEntity3 instanceof EntityLiving) {
                EnchantmentHelper.a(vEntityLiving5, vEntity3);
                EnchantmentHelper.b((EntityLiving)vEntity3, vEntityLiving5);
            }
            this.onHit(vEntityLiving5);
        }
        this.r *= -0.009999999776482582;
        this.s *= -0.10000000149011612;
        this.t *= -0.009999999776482582;
        float vFloat4 = 1.0f;
        if (this.world.W() && EnchantmentHelper.h(this.trident)) {
            final BlockPos vBlockPos8 = vEntity2.bJ();
            if (this.world.g(vBlockPos8)) {
                final EntityLightning vEntityLightning9 = new EntityLightning(this.world, vBlockPos8.getX() + 0.5, vBlockPos8.getY(), vBlockPos8.getZ() + 0.5, false);
                vEntityLightning9.d((vEntity3 instanceof EntityPlayerServer) ? ((EntityPlayerServer)vEntity3) : null);
                this.world.addGlobalEntity(vEntityLightning9);
                vSound6 = Sounds.ITEM_TRIDENT_THUNDER;
                vFloat4 = 5.0f;
            }
        }
        this.playSoundAtEntity(vSound6, vFloat4, 1.0f);
    }
    
    @Override
    protected Sound i() {
        return Sounds.ITEM_TRIDENT_HIT_GROUND;
    }
    
    @Override
    public void d(final EntityPlayer aEntityPlayer) {
        final Entity vEntity2 = this.k();
        if (vEntity2 != null && vEntity2.getUuid() != aEntityPlayer.getUuid()) {
            return;
        }
        super.d(aEntityPlayer);
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        if (aNBTCompound.hasKey("Trident", 10)) {
            this.trident = ItemStack.a(aNBTCompound.getCompound("Trident"));
        }
        this.dealtDamage = aNBTCompound.getBoolean("DealtDamage");
        this.dataTracker.<Byte>set(EntityTrident.ARROW_FLAGS, (byte)EnchantmentHelper.f(this.trident));
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setTag("Trident", this.trident.serialize(new NBTCompound()));
        aNBTCompound.setBoolean("DealtDamage", this.dealtDamage);
    }
    
    @Override
    protected void f() {
        final int vInteger1 = this.dataTracker.<Byte>get(EntityTrident.ARROW_FLAGS);
        if (this.pickupType != PickupType.ALLOWED || vInteger1 <= 0) {
            super.f();
        }
    }
    
    @Override
    protected float r() {
        return 0.99f;
    }
    
    @Sided(Side.CLIENT)
    @Override
    public boolean shouldRenderFrom(final double aDouble1, final double aDouble3, final double vDouble5) {
        return true;
    }
    
    static {
        ARROW_FLAGS = DataTracker.<Byte>registerData(EntityTrident.class, TrackedDataHandlerRegistry.BYTE);
    }
}
