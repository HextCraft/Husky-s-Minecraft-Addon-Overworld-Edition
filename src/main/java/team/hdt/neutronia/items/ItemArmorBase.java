package team.hdt.neutronia.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import team.hdt.neutronia.base.items.INeutroniaItem;

public class ItemArmorBase extends ItemModArmor implements INeutroniaItem {

    private boolean hasOverlay, hasColor;

    public ItemArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(setName(name, equipmentSlotIn), materialIn, renderIndexIn, equipmentSlotIn);
    }

    public static String setName(String name, EntityEquipmentSlot equipmentSlot) {
        if (equipmentSlot == EntityEquipmentSlot.HEAD) return name + "_helmet";
        if (equipmentSlot == EntityEquipmentSlot.CHEST) return name + "_chestplate";
        if (equipmentSlot == EntityEquipmentSlot.LEGS) return name + "_leggings";
        if (equipmentSlot == EntityEquipmentSlot.FEET) return name + "_boots";
        return name;
    }

    @Override
    public boolean hasOverlay(ItemStack stack) {
        return isHasOverlay();
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        return isHasColor();
    }

    public boolean isHasOverlay() {
        return hasOverlay;
    }

    public void setHasOverlay(boolean hasOverlay) {
        this.hasOverlay = hasOverlay;
    }

    public boolean isHasColor() {
        return hasColor;
    }

    public void setHasColor(boolean hasColor) {
        this.hasColor = hasColor;
    }

}