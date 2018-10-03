package net.hdt.neutronia.items;

import net.hdt.huskylib2.item.ItemMod;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.hdt.neutronia.base.util.ColorUtilsIdk;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBandage extends ItemMod implements INeutroniaItem {

    public ItemBandage() {
        super("bandage");
        this.setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.getHealth() < playerIn.getMaxHealth() && !worldIn.isRemote && playerIn.getHeldItem(handIn).getItem() == this) {
            float f = playerIn.getHealth();
            playerIn.setHealth(f + 1.0F);
            playerIn.getHeldItem(handIn).shrink(1);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GOLD + "The bandage heals you each time your right-click it 1 1/2 hearts");
        tooltip.add(TextFormatting.GOLD + "It drops from the Mummy in the desert pyramids as a rare drop");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return ColorUtilsIdk.getClosest(16351261) + super.getItemStackDisplayName(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0xe3bb30;
    }

}
