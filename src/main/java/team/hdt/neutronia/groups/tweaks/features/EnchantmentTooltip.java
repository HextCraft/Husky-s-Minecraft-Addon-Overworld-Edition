package team.hdt.neutronia.groups.tweaks.features;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;
import team.hdt.neutronia.base.groups.Component;

import javax.annotation.Nullable;

public class EnchantmentTooltip extends Component {

    @SideOnly(Side.CLIENT)
    public static String getTranslatedEnchantment(@Nullable Enchantment enchantment) {
        if (enchantment != null && enchantment.type != null) {
            String name = enchantment.type.name().toLowerCase();
            String key = String.format("neutronia.enchantment.type.%s", name);
            if (I18n.hasKey(key)) {
                return I18n.format(key);
            }
            return StringUtils.capitalize(name.replace("_", " "));
        }
        return null;
    }

    @Override
    public boolean hasSubscriptions() {
        return isClient();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ItemEnchantedBook) {
            NBTTagList nbttaglist = ItemEnchantedBook.getEnchantments(stack);
            for (int i = 0; i < nbttaglist.tagCount(); ++i) {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                int j = nbttagcompound.getShort("id");
                Enchantment enchantment = Enchantment.getEnchantmentByID(j);
                String tooltip = getTranslatedEnchantment(enchantment);
                if (tooltip != null) {
                    event.getToolTip().add(tooltip);
                }
            }
        }
    }

    @Override
    public String getFeatureDescription() {
        return "Add a tooltip to Enchantment source items (Scrolls, Books) to show what type of tool the enchantment can be used on.";
    }

}