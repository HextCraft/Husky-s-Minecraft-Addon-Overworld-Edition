package team.abnormal.neutronia.base.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static net.minecraft.item.ItemStack.EMPTY;

public class NBTUtils {
    public static NBTTagCompound getCompoundSafe(ItemStack stack) {
        if (!stack.isEmpty() && stack.hasTagCompound()) {
            return stack.getTagCompound();
        }

        return new NBTTagCompound();
    }

    public static ItemStack fromTag(NBTTagCompound compoundTag_1) {
        try {
            return new ItemStack(compoundTag_1);
        } catch (RuntimeException var2) {
            return EMPTY;
        }
    }
}
