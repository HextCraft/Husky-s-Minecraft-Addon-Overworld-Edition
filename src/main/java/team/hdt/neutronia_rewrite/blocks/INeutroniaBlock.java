package team.hdt.neutronia_rewrite.blocks;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import team.hdt.huskylib.interf.IModBlock;
import team.hdt.neutronia_rewrite.Referece;

public interface INeutroniaBlock extends IModBlock {

    @Override
    default String getModNamespace() {
        return Referece.MOD_ID;
    }

    @Override
    default EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

}
