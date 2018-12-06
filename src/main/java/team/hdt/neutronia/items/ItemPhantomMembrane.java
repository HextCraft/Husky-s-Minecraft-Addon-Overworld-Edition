package team.hdt.neutronia.items;

import net.minecraft.item.ItemStack;
import team.hdt.neutronia.base.util.ColorUtilsIdk;

public class ItemPhantomMembrane extends ItemNeutroniaBase {

    public ItemPhantomMembrane() {
        super("phantom_membrane");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return ColorUtilsIdk.getClosest(0xd8cda9) + super.getItemStackDisplayName(stack);
    }

}
