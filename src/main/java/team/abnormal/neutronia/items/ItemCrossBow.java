package team.abnormal.neutronia.items;

import com.google.common.collect.Lists;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.utils.NBTUtils;
import team.abnormal.neutronia.init.NItems;
import team.abnormal.neutronia.init.NSoundEvents;

import java.util.List;

public class ItemCrossBow extends ItemNeutroniaBase {
    public ItemCrossBow() {
        super("crossbow");
        this.setMaxStackSize(1);
        this.setMaxDamage(326);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.addPropertyOverride(new ResourceLocation("pull"), ((stack, worldIn, entityIn) -> entityIn == null || entityIn.getActiveItemStack().getItem() != NItems.CROSSBOW || isLoaded(stack) ? 0.0F : (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F));
        this.addPropertyOverride(new ResourceLocation("pulling"), (stack, worldIn, entityIn) -> !isLoaded(stack) && entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F);
        this.addPropertyOverride(new ResourceLocation("loaded"), (stack, worldIn, entityIn) -> isLoaded(stack) ? 1.0F : 0.0F);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 300000;
    }


    public void setIsLoaded(ItemStack stack, boolean bool) {

        NBTTagCompound tag2 = NBTUtils.getCompoundSafe(stack);

        tag2.setBoolean("isLoaded", bool);
        stack.setTagCompound(tag2);

    }

    public void setIsLoading(ItemStack stack, boolean bool) {
        NBTTagCompound tag2 = NBTUtils.getCompoundSafe(stack);

        tag2.setBoolean("isLoading", bool);
        stack.setTagCompound(tag2);

    }

    public void setLoadedItem(ItemStack stack, ItemStack stacks) {
        NBTTagCompound tag = NBTUtils.getCompoundSafe(stack);

        NBTTagList listTag_2;

        if (tag.hasKey("LoadedItem", 9)) {
            listTag_2 = tag.getTagList("LoadedItem", 10);
        } else {
            listTag_2 = new NBTTagList();
        }


        NBTTagCompound compoundTag_2 = new NBTTagCompound();
        stacks.setTagCompound(compoundTag_2);
        listTag_2.appendTag(compoundTag_2);
        stack.setTagInfo("LoadedItem", listTag_2);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack stack = playerIn.getHeldItem(hand);


        if (isLoaded(stack)) {
            List<ItemStack> crossBowItem = getLoadedItem(stack);

            this.shoot(playerIn, worldIn, stack, crossBowItem);

            setIsLoaded(stack, false);

            stack.damageItem(1, playerIn);


            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
        } else {

            ItemStack itemstack = playerIn.getHeldItem(hand);
            boolean flag = !this.findAmmo(playerIn).isEmpty();

            ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, hand, flag);
            if (ret != null) return ret;

            float rand = 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.7F * 0.5F;

            if (!playerIn.capabilities.isCreativeMode && !flag) {
                return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
            } else {
                setIsLoading(stack, true);

                worldIn.playSound(playerIn, playerIn.getPosition(), NSoundEvents.ITEM_CROSSBOW_LOADING_START, SoundCategory.PLAYERS, 1.0F, rand);
                playerIn.setActiveHand(hand);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
            }
        }
    }

    private void shoot(EntityPlayer playerIn, World worldIn, ItemStack stack, List<ItemStack> crossbowStack) {

        if (!worldIn.isRemote) {
            for (int int_1 = 0; int_1 < 1; ++int_1) {
                ItemStack item = crossbowStack.get(int_1);
                ItemArrow itemarrow = (ItemArrow) (item.getItem() instanceof ItemArrow ? item.getItem() : Items.ARROW);
                EntityArrow entityArrow = itemarrow.createArrow(worldIn, item, playerIn);
                entityArrow.pickupStatus = playerIn.isCreative() ? EntityArrow.PickupStatus.CREATIVE_ONLY : EntityArrow.PickupStatus.ALLOWED;

                entityArrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.5F + 0F * 0.5F, 0.5F);
                entityArrow.setIsCritical(true);
                worldIn.spawnEntity(entityArrow);

                entityArrow.playSound(NSoundEvents.ITEM_CROSSBOW_FIRE, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.7F * 0.5F);
                boolean flag1 = playerIn.capabilities.isCreativeMode || (item.getItem() instanceof ItemArrow && isInfinite(item, stack));
                if (!flag1 && !playerIn.capabilities.isCreativeMode) {
                    item.shrink(1);
                    if (item.isEmpty()) {
                        playerIn.inventory.deleteStack(item);
                    }
                }

            }
        }
    }

    public ItemStack findAmmo(EntityPlayer player) { // From ItemBow
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
            return player.getHeldItem(EnumHand.OFF_HAND);
        } else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        } else if (!player.isCreative()) {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack)) {
                    return itemstack;
                }
            }
            return ItemStack.EMPTY;
        } else {
            return new ItemStack(Items.ARROW);
        }
    }

    public boolean isArrow(ItemStack stack) { // From Arrow
        return stack.getItem() instanceof ItemArrow;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            float rand = 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.7F * 0.5F;
            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            if (i >= 28.0F && !isLoaded(stack)) {
                if (!this.findAmmo(playerIn).isEmpty() || playerIn.isCreative()) {
                    ItemStack item;
                    if (playerIn.isCreative()) {
                        item = new ItemStack(Items.ARROW);
                    } else {
                        item = findAmmo(playerIn);
                    }
                    setIsLoaded(stack, true);


                    setLoadedItem(stack, item);
                    worldIn.playSound((EntityPlayer) entityLiving, entityLiving.getPosition(), NSoundEvents.ITEM_CROSSBOW_CHARGE, SoundCategory.PLAYERS, 1.0F, rand);
                }
            }
            setIsLoading(stack, false);
        }
    }


    private void setupStock(ItemStack stack, ItemStack item, EntityLivingBase entityLiving, boolean flag) {
        boolean boolean_3 = flag && item.getItem() instanceof ItemArrow;
        ItemStack itemStack_4;
        if (!boolean_3 && !flag) {
            itemStack_4 = item.splitStack(1);
            if (item.isEmpty() && entityLiving instanceof EntityPlayer) {
                ((EntityPlayer) entityLiving).inventory.storeItemStack(item);
            }
        } else {
            itemStack_4 = item.copy();
        }


    }

    /**
     * Null-safe check to check if the stack (crossbow) is loaded
     */
    private static boolean isLoaded(ItemStack stack) {
        NBTTagCompound compoundTag_1 = NBTUtils.getCompoundSafe(stack);
        if (stack.getTagCompound() != null) {

            return compoundTag_1.getBoolean("isLoaded");
        } else {
            return false;
        }
    }

    private static List<ItemStack> getLoadedItem(ItemStack stack) {


        List<ItemStack> list_1 = Lists.newArrayList();
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound!= null && nbttagcompound.hasKey("LoadedItem", 9)) {
           NBTTagList listTag_1 = nbttagcompound.getTagList("LoadedItem", 10);
            if (listTag_1 != null) {
                for(int int_1 = 0; int_1 < listTag_1.tagCount(); ++int_1) {
                    NBTTagCompound compoundTag_2 = listTag_1.getCompoundTagAt(int_1);
                    list_1.add(NBTUtils.fromTag(compoundTag_2));
                }
            }
        }

        return list_1;
    }

    private static boolean hasProjectile(ItemStack itemStack_1, Item item_1) {
        return getLoadedItem(itemStack_1).stream().anyMatch((itemStack_1x) -> {
            return itemStack_1x.getItem() == item_1;
        });
    }

    /**
     * Null-safe check to check if the player is loading the weapon
     */
    private static boolean isLoading(ItemStack stack) {
        NBTTagCompound compoundTag_1 = NBTUtils.getCompoundSafe(stack);
        if (stack.getTagCompound() == null) {
            return false;
        }
        return compoundTag_1.getBoolean("isLoading");
    }

    public static boolean isInfinite(ItemStack stack, ItemStack bow) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant > 0 && stack.getItem() instanceof ItemArrow;
    }


    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }
}