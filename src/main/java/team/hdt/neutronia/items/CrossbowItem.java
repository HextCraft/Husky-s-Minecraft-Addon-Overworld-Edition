/*
package team.hdt.neutronia.items;

import NEnchantments;
import NSounds;
import net.minecraft.advancement.criterion.CriterionTriggers;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.audio.Sound;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.HelperEnchantment;
import net.minecraft.entity.FireworkEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity.PickupType;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.TagCompound;
import net.minecraft.nbt.TagList;
import net.minecraft.sound.Sounds;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossbowItem extends ItemBow {
    private boolean field_7937 = false;
    private boolean field_7936 = false;

    public CrossbowItem() {
        this.addPropertyOverride(new ResourceLocation("pull"), (var1x, var2, var3) -> {
            if (var3 != null && var1x.getItem() == this) {
                return method_7781(var1x) ? 0.0F : (float)(var1x.getMaxUseTime() - var3.method_6014()) / (float)method_7775(var1x);
            } else {
                return 0.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), (var0, var1x, var2) -> {
            return var2 != null && var2.method_6115() && var2.getActiveItem() == var0 && !method_7781(var0) ? 1.0F : 0.0F;
        });
        this.addPropertyOverride(new ResourceLocation("charged"), (var0, var1x, var2) -> {
            return var2 != null && method_7781(var0) ? 1.0F : 0.0F;
        });
        this.addPropertyOverride(new ResourceLocation("firework"), (var1x, var2, var3) -> {
            return var3 != null && method_7781(var1x) && this.method_7772(var1x, Items.FIREWORKS) ? 1.0F : 0.0F;
        });
    }

    public ActionResult<ItemStack> use(World var1, EntityPlayer var2, EnumHand var3) {
        ItemStack var4 = var2.getHeldItem(var3);
        boolean var5 = !this.method_7774(var2).isEmpty();
        ItemStack var6 = this.findArrowStack(var2);
        if (method_7781(var4)) {
            this.method_7777(var1, var2, var4);
            method_7782(var4, false);
            return new TypedActionResult(ActionResult.SUCCESS, var4);
        } else if (!var2.abilities.creativeMode && var6.isEmpty() && !var5) {
            return var6.isEmpty() && !var5 ? new TypedActionResult(ActionResult.FAILURE, var4) : new TypedActionResult(ActionResult.PASS, var4);
        } else {
            if (!method_7781(var4)) {
                this.field_7937 = false;
                this.field_7936 = false;
                var2.setCurrentHand(var3);
            }

            return new TypedActionResult(ActionResult.SUCCESS, var4);
        }
    }

    public void onItemStopUsing(ItemStack var1, World var2, LivingEntity var3, int var4) {
        if (var3 instanceof EntityPlayer) {
            int var5 = this.getMaxUseTime(var1) - var4;
            float var6 = method_7770(var5, var1);
            if (var6 >= 1.0F && !method_7781(var1)) {
                this.method_7767((EntityPlayer)var3, var1);
                method_7782(var1, true);
                var2.playSound((EntityPlayer)null, var3.x, var3.y, var3.z, Sounds.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYER, 1.0F, 1.0F / (rand.nextFloat() * 0.5F + 1.0F) + 0.2F);
            }

        }
    }

    private void method_7767(EntityPlayer var1, ItemStack var2) {
        ItemStack var3 = this.method_7774(var1);
        boolean var4 = var1.abilities.creativeMode;
        int var5 = HelperEnchantment.getLevel(Enchantments.MULTISHOT, var2);
        int var6 = var5 == 0 ? 1 : 3;
        ItemStack var7 = var3.isEmpty() ? this.findArrowStack(var1) : var3;
        ItemStack var8 = var7.copy();

        for(int var9 = 0; var9 < var6; ++var9) {
            if (var9 > 0) {
                var7 = var8.copy();
            }

            if (var7.isEmpty() && var4) {
                var7 = new ItemStack(Items.ARROW);
                var8 = var7.copy();
            }

            this.method_7765(var1, var2, var7, var9 > 0);
        }

    }

    private void method_7765(EntityPlayer var1, ItemStack var2, ItemStack var3, boolean var4) {
        boolean var5 = var1.abilities.creativeMode;
        boolean var6 = var5 && var3.getItem() == Items.ARROW;
        ItemStack var7;
        if (!var6 && !var5 && !var4) {
            var7 = var3.split(1);
            if (var3.isEmpty()) {
                var1.inventory.removeOne(var3);
            }
        } else {
            var7 = var3.copy();
        }

        this.method_7778(var2, var7);
    }

    public static boolean method_7781(ItemStack var0) {
        TagCompound var1 = var0.getTag();
        return var1 != null ? var1.getBoolean("Charged") : false;
    }

    public static TagCompound method_7782(ItemStack var0, boolean var1) {
        TagCompound var2 = var0.getOrCreateTag();
        var2.setBoolean("Charged", var1);
        return var2;
    }

    private void method_7778(ItemStack var1, ItemStack var2) {
        TagCompound var3 = var1.getOrCreateTag();
        TagList var4;
        if (var3.containsKey("ChargedProjectiles", 9)) {
            var4 = var3.getTagList("ChargedProjectiles", 10);
        } else {
            var4 = new TagList();
        }

        TagCompound var5 = new TagCompound();
        var2.serialize(var5);
        var4.add(var5);
        var3.setTag("ChargedProjectiles", var4);
    }

    private List<ItemStack> method_7785(ItemStack var1) {
        List<ItemStack> var2 = new ArrayList();
        TagCompound var3 = var1.getTag();
        if (var3 != null && var3.containsKey("ChargedProjectiles", 9)) {
            TagList var4 = var3.getTagList("ChargedProjectiles", 10);
            if (var4 != null) {
                for(int var5 = 0; var5 < var4.size(); ++var5) {
                    TagCompound var6 = var4.getTagCompound(var5);
                    var2.add(ItemStack.fromTag(var6));
                }
            }
        }

        return var2;
    }

    private void method_7766(ItemStack var1) {
        TagCompound var2 = var1.getTag();
        if (var2 != null) {
            TagList var3 = var2.getTagList("ChargedProjectiles", 9);
            var3.clear();
            var2.setTag("ChargedProjectiles", var3);
        }

    }

    private boolean method_7772(ItemStack var1, Item var2) {
        return this.method_7785(var1).stream().anyMatch((var1x) -> {
            return var1x.getItem() == var2;
        });
    }

    private void method_7763(World var1, EntityPlayer var2, ItemStack var3, ItemStack var4, float var5, float var6, boolean var7) {
        if (!this.method_7764(var1, var2, var3, var4, var5) && !var4.isEmpty()) {
            boolean var8 = var2.abilities.creativeMode;
            boolean var9 = var8 && var4.getItem() == Items.ARROW;
            if (!var1.isRemote) {
                ArrowItem var10 = (ArrowItem)((ArrowItem)(var4.getItem() instanceof ArrowItem ? var4.getItem() : Items.ARROW));
                ProjectileEntity var11 = var10.createEntityArrow(var1, var4, var2);
                var11.method_7437(var2, var2.pitch, var5, 0.0F, 3.15F, 1.0F);
                var11.setCritical(true);
                var11.setSound(Sounds.ITEM_CROSSBOW_HIT);
                var11.setShotFromCrossbow(true);
                int var12 = HelperEnchantment.getLevel(Enchantments.PIERCING, var3);
                if (var12 > 0) {
                    var11.setFlagByte((byte)var12);
                }

                var3.applyDamage(1, var2);
                if (var9 || var7 || var2.abilities.creativeMode && (var4.getItem() == Items.SPECTRAL_ARROW || var4.getItem() == Items.TIPPED_ARROW)) {
                    var11.pickupType = PickupType.CREATIVE_PICKUP;
                }

                var1.spawnEntity(var11);
            }

            var1.playSound((EntityPlayer)null, var2.x, var2.y, var2.z, Sounds.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYER, 1.0F, var6);
        }
    }

    private boolean method_7764(World var1, EntityPlayer var2, ItemStack var3, ItemStack var4, float var5) {
        if (var4.getItem() != Items.FIREWORK_ROCKET) {
            return false;
        } else if (!var4.isEmpty()) {
            if (!var1.isRemote) {
                FireworkEntity var6 = new FireworkEntity(var1, var4, var2.x, var2.y + (double)var2.getEyeHeight() - 0.15000000596046448D, var2.z, true);
                var6.method_7474(var2, var5, 1.0F);
                var3.applyDamage(3, var2);
                var1.spawnEntity(var6);
                var1.playSound((EntityPlayer)null, var2.x, var2.y, var2.z, Sounds.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYER, 1.0F, 1.0F);
            }

            this.method_7769(var1, var2, var3);
            return true;
        } else {
            return false;
        }
    }

    private void method_7777(World var1, EntityPlayer var2, ItemStack var3) {
        List<ItemStack> var4 = this.method_7785(var3);
        float var5 = 10.0F;
        float[] var6 = this.method_7780(var2.getRand());

        for(int var7 = 0; var7 < var4.size(); ++var7) {
            ItemStack var8 = (ItemStack)var4.get(var7);
            if (!var8.isEmpty()) {
                if (var7 == 0) {
                    this.method_7763(var1, var2, var3, var8, var2.yaw, var6[var7], false);
                } else if (var7 == 1) {
                    this.method_7763(var1, var2, var3, var8, var2.yaw - 10.0F, var6[var7], true);
                } else if (var7 == 2) {
                    this.method_7763(var1, var2, var3, var8, var2.yaw + 10.0F, var6[var7], true);
                }
            }
        }

        this.method_7769(var1, var2, var3);
    }

    private float[] method_7780(Random var1) {
        boolean var2 = var1.nextBoolean();
        return new float[]{1.0F, this.method_7784(var2), this.method_7784(!var2)};
    }

    private float method_7784(boolean var1) {
        float var2 = var1 ? 0.63F : 0.43F;
        return 1.0F / (rand.nextFloat() * 0.5F + 1.8F) + var2;
    }

    private void method_7769(World var1, EntityPlayer var2, ItemStack var3) {
        if (!var1.isRemote) {
            CriteriaTriggers..method_9115((EntityPlayerMP)var2, var3);
        }

        var2.incrementStat(Stats.USED.method_14956(this));
        this.method_7766(var3);
    }

    public void method_7852(World var1, LivingEntity var2, ItemStack var3, int var4) {
        if (!var1.isRemote) {
            int var5 = EnchantmentHelper.getEnchantmentLevel(NEnchantments.QUICK_CHARGE, var3);
            Sound var6 = this.method_7773(var5);
            Sound var7 = var5 == 0 ? Sounds.ITEM_CROSSBOW_LOADING_MIDDLE : null;
            float var8 = (float)(var3.getMaxUseTime() - var4) / (float)method_7775(var3);
            if (var8 < 0.2F) {
                this.field_7937 = false;
                this.field_7936 = false;
            }

            if (var8 >= 0.2F && !this.field_7937) {
                this.field_7937 = true;
                var1.playSound((EntityPlayer)null, var2.x, var2.y, var2.z, var6, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }

            if (var8 >= 0.5F && var7 != null && !this.field_7936) {
                this.field_7936 = true;
                var1.playSound((EntityPlayer)null, var2.x, var2.y, var2.z, var7, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }
        }

    }

    public int getMaxUseTime(ItemStack var1) {
        return method_7775(var1) + 3;
    }

    public static int method_7775(ItemStack var0) {
        int var1 = HelperEnchantment.getLevel(Enchantments.QUICK_CHARGE, var0);
        return var1 == 0 ? 25 : 25 - 5 * var1;
    }

    public UseAction getUseAction(ItemStack var1) {
        return UseAction.CROSSBOW;
    }

    private ItemStack method_7774(EntityPlayer var1) {
        ItemStack var2 = var1.getHeldItemOffhand();
        if (!var2.isEmpty() && var2.getItem() instanceof ItemFirework) {
            return var2;
        } else {
            ItemStack var3 = var1.getHeldItemMainhand();
            return !var3.isEmpty() && var3.getItem() instanceof ItemFirework ? var3 : ItemStack.EMPTY;
        }
    }

    private Sound method_7773(int var1) {
        switch(var1) {
        case 1:
            return NSounds.ITEM_CROSSBOW_QUICK_CHARGE_1;
        case 2:
            return NSounds.ITEM_CROSSBOW_QUICK_CHARGE_2;
        case 3:
            return NSounds.ITEM_CROSSBOW_QUICK_CHARGE_3;
        default:
            return NSounds.ITEM_CROSSBOW_LOADING_START;
        }
    }

    public static float method_7770(int var0, ItemStack var1) {
        float var2 = (float)var0 / (float)method_7775(var1);
        if (var2 > 1.0F) {
            var2 = 1.0F;
        }

        return var2;
    }
}
*/
