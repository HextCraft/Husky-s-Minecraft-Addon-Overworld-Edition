/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.hdt.neutronia.entity;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.MobEffectInstance;
import net.minecraft.entity.effect.MobEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.TagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillageProperties;
import net.minecraft.world.World;
import net.minecraft.world.WorldVillageManager;

public abstract class class_3763 extends class_3732 {
    private static final Predicate<ItemEntity> field_16600 = (var0) -> {
        return !var0.method_6977() && var0.isValid() && ItemStack.areEqual(var0.getStack(), class_3765.field_16609);
    };
    protected class_3765 field_16599;
    private int field_16601;
    private boolean field_16602;

    protected class_3763(EntityType<?> var1, World var2) {
        super(var1, var2);
    }

    public abstract void method_16484(int var1, boolean var2);

    public boolean method_16481() {
        return this.field_16602;
    }

    public void method_16480(boolean var1) {
        this.field_16602 = var1;
    }

    public void onDeath(DamageSource var1) {
        if (this.method_16478() != null) {
            if (this.method_16219()) {
                this.method_16478().method_16500(this.method_16486());
            }

            this.method_16478().method_16510(this);
        }

        WorldVillageManager var2 = this.world.getVillageManager();
        class_3765 var3 = null;
        if (var2 != null) {
            VillageProperties var4 = var2.getNearestVillage(new BlockPos(this.x, this.y, this.z), 0);
            if (var4 != null) {
                var3 = var4.method_16469();
            }
        }

        if (this.method_16219() && this.method_16478() == null && var3 == null) {
            ItemStack var9 = this.getEquippedStack(EquipmentSlot.HEAD);
            if (!var9.isEmpty() && ItemStack.areEqual(var9, class_3765.field_16609) && var1.getAttacker() instanceof PlayerEntity) {
                PlayerEntity var5 = (PlayerEntity)var1.getAttacker();
                MobEffectInstance var6 = var5.getPotionEffect(MobEffects.BAD_OMEN);
                int var7 = 0;
                if (var6 != null) {
                    var7 = var6.getAmplifier();
                    if (var7 < 8) {
                        ++var7;
                    }

                    var5.removePotionEffect(MobEffects.BAD_OMEN);
                }

                MobEffectInstance var8 = new MobEffectInstance(MobEffects.BAD_OMEN, 120000, var7, false, false, true);
                var5.addPotionEffect(var8);
            }
        }

        super.onDeath(var1);
    }

    protected boolean method_16485() {
        return true;
    }

    protected void method_5959() {
        super.method_5959();
        this.goalSelector.add(2, new class_3763.class_3764(this));
        this.goalSelector.add(3, new class_3759(this));
    }

    public boolean method_16472() {
        return this.method_16482();
    }

    public void method_16476(class_3765 var1) {
        this.field_16599 = var1;
    }

    public class_3765 method_16478() {
        return this.field_16599;
    }

    public boolean method_16482() {
        return this.method_16478() != null && this.method_16478().method_16504();
    }

    public void method_16477(int var1) {
        this.field_16601 = var1;
    }

    public int method_16486() {
        return this.field_16601;
    }

    public void serializeCustomData(TagCompound var1) {
        super.serializeCustomData(var1);
        var1.setInt("Wave", this.field_16601);
        var1.setBoolean("HasRaidGoal", this.field_16602);
        if (this.field_16599 != null) {
            var1.setInt("RaidId", this.field_16599.method_16494());
        }

    }

    public void deserializeCustomData(TagCompound var1) {
        super.deserializeCustomData(var1);
        this.field_16601 = var1.getInt("Wave");
        this.field_16602 = var1.getBoolean("HasRaidGoal");
        if (var1.containsKey("RaidId", 3)) {
            this.field_16599 = this.world.method_16542().method_16541(var1.getInt("RaidId"));
            if (this.field_16599 != null) {
                this.field_16599.method_16487(this.field_16601, this, false);
                if (this.method_16219()) {
                    this.field_16599.method_16491(this.field_16601, this);
                }
            }
        }

    }

    protected void method_5949(ItemEntity var1) {
        ItemStack var2 = var1.getStack();
        boolean var3 = this.method_16482() && this.method_16478().method_16496(this.method_16486()) != null;
        if (this.method_16482() && !var3 && ItemStack.areEqual(var2, class_3765.field_16609)) {
            EquipmentSlot var4 = EquipmentSlot.HEAD;
            ItemStack var5 = this.getEquippedStack(var4);
            double var6 = (double)this.method_5929(var4);
            if (!var5.isEmpty() && (double)(this.rand.nextFloat() - 0.1F) < var6) {
                this.dropStack(var5);
            }

            this.setEquippedStack(var4, var2);
            this.method_6103(var1, var2.getAmount());
            var1.invalidate();
            this.method_16478().method_16491(this.method_16486(), this);
            this.method_16217(true);
        } else {
            super.method_5949(var1);
        }

    }

    public boolean isPersistent() {
        return this.method_16478() != null;
    }

    public class class_3764<T extends class_3763> extends AiGoal {
        private final T field_16603;

        public class_3764(T var2) {
            this.field_16603 = var2;
            this.setCategoryBits(1);
        }

        public boolean canStart() {
            class_3765 var1 = this.field_16603.method_16478();
            if (class_3763.this.method_16482() && this.field_16603.method_16485() && !ItemStack.areEqual(this.field_16603.getEquippedStack(EquipmentSlot.HEAD), class_3765.field_16609)) {
                if (var1.method_16496(this.field_16603.method_16486()) != null && var1.method_16496(this.field_16603.method_16486()).isValid()) {
                    return false;
                } else {
                    List<ItemEntity> var2 = this.field_16603.world.getEntities(ItemEntity.class, this.field_16603.getBoundingBox().expand(16.0D, 8.0D, 16.0D), class_3763.field_16600);
                    if (!var2.isEmpty()) {
                        this.field_16603.method_5942().method_6335((Entity)var2.get(0), 1.2000000476837158D);
                    }

                    return !var2.isEmpty();
                }
            } else {
                return false;
            }
        }

        public void tick() {
            if (this.field_16603.squaredDistanceTo(this.field_16603.method_5942().method_6355()) < 2.0D) {
                List<ItemEntity> var1 = this.field_16603.world.getEntities(ItemEntity.class, this.field_16603.getBoundingBox().expand(4.0D, 4.0D, 4.0D), class_3763.field_16600);
                if (!var1.isEmpty()) {
                    this.field_16603.method_5949((ItemEntity)var1.get(0));
                }
            }

        }
    }
}
*/
