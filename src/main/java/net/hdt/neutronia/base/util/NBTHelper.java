package net.hdt.neutronia.base.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
    public static NBTTagCompound setTagCompound(ItemStack stack) {
        return setTagCompound(stack, new NBTTagCompound());
    }

    public static NBTTagCompound setTagCompound(ItemStack stack, NBTTagCompound compound) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(compound);
        } else {
            stack.getTagCompound().merge(compound);
        }

        return stack.getTagCompound();
    }

    public static NBTTagCompound setTagCompound(NBTTagCompound base, String key, NBTTagCompound value) {
        if (!base.hasKey(key)) {
            base.setTag(key, value);
        }

        return base.getCompoundTag(key);
    }
}