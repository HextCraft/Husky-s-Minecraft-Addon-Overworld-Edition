package team.abnormal.neutronia.base.blocks;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import team.abnormal.abnormalib.interf.IModBlock;

public interface IMinecraftBlock extends IModBlock {

    @Override
    default String getModNamespace() {
        return "minecraft";
    }

    @Override
    default EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

}
