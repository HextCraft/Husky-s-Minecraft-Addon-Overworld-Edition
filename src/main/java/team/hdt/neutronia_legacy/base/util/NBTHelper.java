package team.hdt.neutronia_legacy.base.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

class NBTHelper {

    static void setTagCompound(ItemStack stack) {
        setTagCompound(stack, new NBTTagCompound());
    }

    static void setTagCompound(ItemStack stack, NBTTagCompound compound) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(compound);
        } else {
            stack.getTagCompound().merge(compound);
        }

    }

}