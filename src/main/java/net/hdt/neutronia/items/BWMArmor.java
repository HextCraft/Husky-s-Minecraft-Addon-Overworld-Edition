package net.hdt.neutronia.items;

import net.hdt.neutronia.api.client.IColorable;
import net.hdt.neutronia.base.items.INeutroniaItem;
import net.hdt.neutronia.client.ColorHandlers;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BWMArmor extends ItemModArmor implements IColorable, INeutroniaItem {
    private String name;
    private ArmorMaterial material;

    public BWMArmor(ArmorMaterial material, EntityEquipmentSlot equipmentSlotIn, String name) {
        super(name, material, 2, equipmentSlotIn);
        this.name = name;
        this.setCreativeTab(NCreativeTabs.NEUTRONIA_MAIN);
    }

    @Override
    public int getColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound != null && nbttagcompound.hasKey("display")) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            if (nbttagcompound1.hasKey("color", 3)) {
                return nbttagcompound1.getInteger("color");
            }
        }
        return 0x00FFFFFF;
//        return 0xa06540;
    }

    @Override
    public void removeColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound != null) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            if (nbttagcompound1.hasKey("color")) {
                nbttagcompound1.removeTag("color");
            }
        }
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            stack.setTagCompound(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
        if (!nbttagcompound.hasKey("display", 10)) {
            nbttagcompound.setTag("display", nbttagcompound1);
        }
        nbttagcompound1.setInteger("color", color);
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        return (nbttagcompound != null && nbttagcompound.hasKey("display", 10)) && nbttagcompound.getCompoundTag("display").hasKey("color", 3);
    }

    @Override
    public boolean hasOverlay(ItemStack stack) {
        return getColor(stack) != 0x00FFFFFF;
    }

    @Override
    public IItemColor getColorHandler() {
        return ColorHandlers.armor;
    }
}
