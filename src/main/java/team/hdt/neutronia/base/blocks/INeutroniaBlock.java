package team.hdt.neutronia.base.blocks;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import team.hdt.huskylib.interf.IModBlock;
import team.hdt.neutronia.base.lib.LibMisc;

public interface INeutroniaBlock extends IModBlock {

    @Override
    default String getModNamespace() {
        return LibMisc.MOD_ID;
    }

    @Override
    default EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

}
