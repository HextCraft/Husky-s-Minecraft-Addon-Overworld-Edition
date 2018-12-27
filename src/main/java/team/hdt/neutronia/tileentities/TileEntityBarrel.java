package team.hdt.neutronia.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.NonNullList;

import java.util.Iterator;

public class TileEntityBarrel extends TileEntityLockableLoot {

    private NonNullList<ItemStack> inventory;

    public TileEntityBarrel() {
        this.inventory = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        Iterator items = this.inventory.iterator();

        ItemStack stack;
        do {
            if(!items.hasNext()) return true;
            stack = (ItemStack) items.next();
        } while (stack.isEmpty());

        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerChest(playerInventory, this, playerIn);
    }

    @Override
    public String getGuiID() {
        return "neutronia:barrel";
    }

    @Override
    public String getName() {
        return "Barrel";
    }

}