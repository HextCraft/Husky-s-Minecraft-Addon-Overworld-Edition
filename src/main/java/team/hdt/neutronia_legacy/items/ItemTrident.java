package team.hdt.neutronia_legacy.items;

import com.google.common.collect.Multimap;
import team.hdt.huskylib.item.ItemMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import team.hdt.neutronia_legacy.base.Neutronia;
import team.hdt.neutronia_legacy.base.items.INeutroniaItem;
import team.hdt.neutronia_legacy.entity.projectile.EntityTrident;
import team.hdt.neutronia_legacy.init.NSounds;

public class ItemTrident extends ItemMod implements INeutroniaItem {

    private EnumAction SPEAR = EnumHelper.addAction("spear");

    public ItemTrident() {
        super("trident");
        this.addPropertyOverride(new ResourceLocation("throwing"), (p_210315_0_, p_210315_1_, p_210315_2_) ->
                p_210315_2_ != null && p_210315_2_.isHandActive() && p_210315_2_.getActiveItemStack() == p_210315_0_ ? 1.0F : 0.0F);
    }

    public boolean func_195938_a(IBlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_, EntityPlayer p_195938_4_) {
        return !p_195938_4_.isCreative();
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    /**
     * Returns true if this item has an enchantment glint. By default, this returns
     * <code>stack.isItemEnchanted()</code>, but other items can override it (for instance, written books always return
     * true).
     * <p>
     * Note that if you override this method, you generally want to also call the super version (on {@link Item}) to get
     * the glint for enchanted items. Of course, that is unnecessary if the overwritten version always returns true.
     */
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            int i = this.getMaxItemUseDuration(stack) - timeLeft;

            if (i >= 10) {
                int j = Neutronia.func_203190_g(stack);

                if (j <= 0 || entityplayer.isWet()) {
                    if (!worldIn.isRemote) {
                        stack.damageItem(1, entityplayer);

                        if (j == 0) {
                            EntityTrident entitytrident = new EntityTrident(worldIn, entityplayer, stack);
                            entitytrident.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 2.5F + (float) j * 0.5F, 1.0F);

                            if (entityplayer.capabilities.isCreativeMode) {
                                entitytrident.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                            }

                            worldIn.spawnEntity(entitytrident);

                            if (!entityplayer.capabilities.isCreativeMode) {
                                entityplayer.inventory.deleteStack(stack);
                            }
                        }
                    }

//                    entityplayer.addStat(StatList.OBJECT_USE_STATS.add(this));
                    SoundEvent soundevent = NSounds.field_203274_ip;

                    if (j > 0) {
                        float f = entityplayer.rotationYaw;
                        float f1 = entityplayer.rotationPitch;
                        float f2 = -MathHelper.sin(f * 0.017453292F) * MathHelper.cos(f1 * 0.017453292F);
                        float f3 = -MathHelper.sin(f1 * 0.017453292F);
                        float f4 = MathHelper.cos(f * 0.017453292F) * MathHelper.cos(f1 * 0.017453292F);
                        float f5 = MathHelper.sqrt(f2 * f2 + f3 * f3 + f4 * f4);
                        float f6 = 3.0F * ((1.0F + (float) j) / 4.0F);
                        f2 = f2 * (f6 / f5);
                        f3 = f3 * (f6 / f5);
                        f4 = f4 * (f6 / f5);
                        entityplayer.addVelocity((double) f2, (double) f3, (double) f4);

                        if (j >= 3) {
                            soundevent = NSounds.field_203273_io;
                        } else if (j == 2) {
                            soundevent = NSounds.field_203272_in;
                        } else {
                            soundevent = NSounds.field_203271_im;
                        }

                        entityplayer.addExperience(20);

                        if (entityplayer.onGround) {
                            float f7 = 1.1999999F;
                            entityplayer.move(MoverType.SELF, 0.0D, 1.1999999284744263D, 0.0D);
                        }
                    }

                    worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        } else if (Neutronia.func_203190_g(itemstack) > 0 && !playerIn.isWet()) {
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        return true;
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if ((double) state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 8.0D, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", -2.9000000953674316D, 0));
        }

        return multimap;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability() {
        return 1;
    }
}
