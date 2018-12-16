/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hdt.neutronia_legacy.entity;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.class_1310;
import net.minecraft.class_1361;
import net.minecraft.class_1379;
import net.minecraft.class_1383;
import net.minecraft.class_1399;
import net.minecraft.class_1400;
import net.minecraft.class_1543;
import net.minecraft.class_3730;
import net.minecraft.class_3745;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.class_1543.class_1544;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.HelperEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.AiGoalSwim;
import net.minecraft.entity.ai.DistantAttacker;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedData;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import net.minecraft.inventory.ImplInventory;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.block.BannerItem;
import net.minecraft.nbt.TagCompound;
import net.minecraft.nbt.TagList;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.text.TextComponentString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class PillagerEntity extends class_1543 implements class_3745, DistantAttacker {
    private static final TrackedData<Boolean> field_7334;
    private final ImplInventory field_7335 = new ImplInventory(new TextComponentString("Inventory"), 5);

    public PillagerEntity(World var1) {
        super(EntityType.PILLAGER, var1);
        this.setSize(0.6F, 1.95F);
    }

    protected void method_5959() {
        super.method_5959();
        this.goalSelector.add(0, new AiGoalSwim(this));
        this.goalSelector.add(3, new class_1383(this, 1.0D, 8.0F));
        this.goalSelector.add(8, new class_1379(this, 0.6D));
        this.goalSelector.add(9, new class_1361(this, PlayerEntity.class, 15.0F, 1.0F));
        this.goalSelector.add(10, new class_1361(this, MobEntity.class, 15.0F));
        this.targetSelector.add(1, (new class_1399(this, new Class[]{class_1543.class})).method_6318(new Class[0]));
        this.targetSelector.add(2, new class_1400(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new class_1400(this, VillagerEntity.class, false));
        this.targetSelector.add(3, new class_1400(this, IronGolemEntity.class, true));
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.3499999940395355D);
        this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(24.0D);
        this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(field_7334, false);
    }

    @Sided(Side.CLIENT)
    public boolean method_7108() {
        return (Boolean)this.dataTracker.get(field_7334);
    }

    public void method_7110(boolean var1) {
        this.dataTracker.set(field_7334, var1);
    }

    @Sided(Side.CLIENT)
    public boolean method_7109() {
        return this.method_6991(1);
    }

    public void setArmsRaised(boolean var1) {
        this.method_6992(1, var1);
    }

    public void serializeCustomData(TagCompound var1) {
        super.serializeCustomData(var1);
        TagList var2 = new TagList();

        for(int var3 = 0; var3 < this.field_7335.getInvSize(); ++var3) {
            ItemStack var4 = this.field_7335.getInvStack(var3);
            if (!var4.isEmpty()) {
                var2.add(var4.serialize(new TagCompound()));
            }
        }

        var1.setTag("Inventory", var2);
    }

    @Sided(Side.CLIENT)
    public class_1544 method_6990() {
        if (this.method_7109()) {
            return class_1544.ATTACKING;
        } else if (this.method_7108()) {
            return class_1544.CROSSBOW_CHARGE;
        } else {
            return !this.getMainHandStack().isEmpty() && this.getMainHandStack().getItem() == Items.CROSSBOW ? class_1544.CROSSBOW_HOLD : class_1544.CROSSED;
        }
    }

    public void deserializeCustomData(TagCompound var1) {
        super.deserializeCustomData(var1);
        TagList var2 = var1.getTagList("Inventory", 10);

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            ItemStack var4 = ItemStack.fromTag(var2.getTagCompound(var3));
            if (!var4.isEmpty()) {
                this.field_7335.method_5491(var4);
            }
        }

        this.setCanPickUpLoot(true);
    }

    public float method_6144(BlockPos var1, ViewableWorld var2) {
        Block var3 = var2.getBlockState(var1.down()).getBlock();
        return var3 != Blocks.GRASS_BLOCK && var3 != Blocks.SAND ? var2.method_8610(var1) - 0.5F : 10.0F;
    }

    protected boolean method_7075() {
        return true;
    }

    public boolean method_5979(IWorld var1, class_3730 var2) {
        int var3 = MathHelper.floor(this.x);
        int var4 = MathHelper.floor(this.getBoundingBox().minY);
        int var5 = MathHelper.floor(this.z);
        BlockPos var6 = new BlockPos(var3, var4, var5);
        Block var7 = var1.getBlockState(var6.down()).getBlock();
        if (var7 != Blocks.GRASS_BLOCK && var7 != Blocks.SAND) {
            return false;
        } else {
            return var1.getLightLevel(LightType.BLOCK, var6) <= 8 && var1.getLightLevel(LightType.SKY, var6) >= 10 ? super.method_5979(var1, var2) : false;
        }
    }

    @Nullable
    public EntityData method_5943(IWorld var1, LocalDifficulty var2, class_3730 var3, @Nullable EntityData var4, @Nullable TagCompound var5) {
        this.initEquipment(var2);
        this.method_5984(var2);
        return super.method_5943(var1, var2, var3, var4, var5);
    }

    protected void initEquipment(LocalDifficulty var1) {
        ItemStack var2 = new ItemStack(Items.CROSSBOW);
        if (this.rand.nextInt(300) == 0) {
            Map<Enchantment, Integer> var3 = new HashMap();
            var3.put(Enchantments.PIERCING, 1);
            HelperEnchantment.set(var3, var2);
        }

        this.setEquippedStack(EquipmentSlot.HAND_MAIN, var2);
    }

    public boolean isTeammate(Entity var1) {
        if (super.isTeammate(var1)) {
            return true;
        } else if (var1 instanceof LivingEntity && ((LivingEntity)var1).method_6046() == class_1310.field_6291) {
            return this.getScoreboardTeam() == null && var1.getScoreboardTeam() == null;
        } else {
            return false;
        }
    }

    protected Sound getSoundAmbient() {
        return Sounds.ENTITY_PILLAGER_AMBIENT;
    }

    protected Sound getSoundDeath() {
        return Sounds.ENTITY_PILLAGER_DEATH;
    }

    protected Sound getSoundHurt(DamageSource var1) {
        return Sounds.ENTITY_PILLAGER_HURT;
    }

    public void attackDistant(LivingEntity var1, float var2) {
        ProjectileEntity var3 = this.method_7107(var2);
        double var4 = var1.x - this.x;
        double var6 = var1.getBoundingBox().minY + (double)(var1.height / 3.0F) - var3.y;
        double var8 = var1.z - this.z;
        double var10 = (double)MathHelper.sqrt(var4 * var4 + var8 * var8);
        var3.setVelocity(var4, var6 + var10 * 0.20000000298023224D, var8, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.playSoundAtEntity(Sounds.ITEM_CROSSBOW_SHOOT, 1.0F, 1.0F / (this.getRand().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(var3);
        CrossbowItem.method_7782(this.getMainHandStack(), false);
    }

    protected ProjectileEntity method_7107(float var1) {
        ArrowEntity var2 = new ArrowEntity(this.world, this);
        var2.setShotFromCrossbow(true);
        var2.setSound(Sounds.ITEM_CROSSBOW_HIT);
        var2.method_7435(this, var1);
        return var2;
    }

    public ImplInventory method_16473() {
        return this.field_7335;
    }

    protected void method_5949(ItemEntity var1) {
        ItemStack var2 = var1.getStack();
        if (var2.getItem() instanceof BannerItem) {
            super.method_5949(var1);
        } else {
            Item var3 = var2.getItem();
            if (this.method_7111(var3)) {
                ItemStack var4 = this.field_7335.method_5491(var2);
                if (var4.isEmpty()) {
                    var1.invalidate();
                } else {
                    var2.setAmount(var4.getAmount());
                }
            }
        }

    }

    private boolean method_7111(Item var1) {
        return this.method_16482() ? var1 == Items.WHITE_BANNER : false;
    }

    public boolean method_5758(int var1, ItemStack var2) {
        if (super.method_5758(var1, var2)) {
            return true;
        } else {
            int var3 = var1 - 300;
            if (var3 >= 0 && var3 < this.field_7335.getInvSize()) {
                this.field_7335.setInvStack(var3, var2);
                return true;
            } else {
                return false;
            }
        }
    }

    public void method_16484(int var1, boolean var2) {
        if (var1 > 3) {
            int var3 = true;
            if (this.rand.nextInt(Math.max(10 - var1, 1)) == 0) {
                ItemStack var4 = new ItemStack(Items.CROSSBOW);
                Map<Enchantment, Integer> var5 = new HashMap();
                if (var1 > 6) {
                    var5.put(Enchantments.QUICK_CHARGE, 2);
                }

                var5.put(Enchantments.MULTISHOT, 1);
                HelperEnchantment.set(var5, var4);
                this.setEquippedStack(EquipmentSlot.HAND_MAIN, var4);
            }
        }

    }

    public boolean isPersistent() {
        return super.isPersistent() && this.method_16473().isInvEmpty();
    }

    static {
        field_7334 = DataTracker.registerData(PillagerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}
*/
