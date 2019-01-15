/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class class_3916 extends Container {
    private final InventoryBasic field_17313;
    private final class_3913 field_17314;

    public class_3916(InventoryBasic inventory_1, class_3913 class_3913_1) {
        getSlotFromInventory(inventory_1, 1);
        method_17361(class_3913_1, 1);
        this.field_17313 = inventory_1;
        this.field_17314 = class_3913_1;
        this.addSlotToContainer(new Slot(inventory_1, 0, 0, 0) {
            public void markDirty() {
                this.inventory.markDirty();
            }
        });
        this.addSlotToContainer(class_3913_1);
    }

    public boolean onButtonClick(EntityPlayer playerEntity_1, int int_1) {
        int int_2;
        switch(int_1) {
        case 1:
            int_2 = this.field_17314.method_17390(0);
            this.updateProgressBar(0, int_2 - 1);
            return true;
        case 2:
            int_2 = this.field_17314.method_17390(0);
            this.updateProgressBar(0, int_2 + 1);
            return true;
        case 3:
            if (!playerEntity_1.isCreative()) {
                return false;
            }

            ItemStack itemStack_1 = this.field_17313.removeStackFromSlot(0);
            this.field_17313.markDirty();
            playerEntity_1.inventory.setItemStack(itemStack_1);
            return true;
        default:
            return false;
        }
    }

    public void updateProgressBar(int int_1, int int_2) {
        super.updateProgressBar(int_1, int_2);
        this.detectAndSendChanges();
    }

    public boolean canInteractWith(EntityPlayer playerEntity_1) {
        return this.field_17313.isUsableByPlayer(playerEntity_1);
    }

    protected static void method_17361(class_3913 class_3913_1, int int_1) {
        int int_2 = class_3913_1.method_17389();
        if (int_2 < int_1) {
            throw new IllegalArgumentException("Container data count " + int_2 + " is smaller than expected " + int_1);
        }
    }

    @SideOnly(Side.CLIENT)
    public ItemStack method_17418() {
        return this.field_17313.getStackInSlot(0);
    }

    @SideOnly(Side.CLIENT)
    public int method_17419() {
        return this.field_17314.method_17390(0);
    }
}
*/
