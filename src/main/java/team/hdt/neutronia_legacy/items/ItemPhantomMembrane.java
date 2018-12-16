package team.hdt.neutronia_legacy.items;

import net.minecraft.item.ItemStack;
import team.hdt.neutronia_legacy.base.util.ColorUtilsIdk;

public class ItemPhantomMembrane extends ItemNeutroniaBase {

    public ItemPhantomMembrane() {
        super("phantom_membrane");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return ColorUtilsIdk.getClosest(0xd8cda9) + super.getItemStackDisplayName(stack);
    }

}
