package net.hdt.neutronia.items;

import net.hdt.huskylib2.item.ItemModSword;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.hdt.neutronia.base.util.ColorUtilsIdk;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemAncientSword extends ItemModSword implements INeutroniaItem {

    private static Item.ToolMaterial ancientToolMaterial = EnumHelper.addToolMaterial("egyptian", 1, 131, 8.0F, 3.5F, 22);

    public ItemAncientSword() {
        super("khopesh", ancientToolMaterial);
        setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GOLD + "Evolved from the Battle Axe, this Egyptian Sword is known as a 'sickle-sword' in part due to its shape.");
        tooltip.add(TextFormatting.GOLD + "Originally crafted from bronze, it transitioned to Iron during the period of Egyptian history known as 'the New Kingdom' period.");
        tooltip.add(TextFormatting.GOLD + "Along with being used in battle, the Khopesh was frequently depicted held by pharaohs as a representation of their martial power, though in these cases the blades were unsharpened.");
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