package net.hdt.neutronia.base.handler.server;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public final class ModIntegrationHandler {

    public static void registerChiselVariant(String group, ItemStack stack) {
        NBTTagCompound cmp = new NBTTagCompound();
        cmp.setString("group", group);
        NBTTagCompound stackCmp = new NBTTagCompound();
        stack.writeToNBT(stackCmp);
        cmp.setTag("stack", stackCmp);

        FMLInterModComms.sendMessage("chisel", "add_variation", cmp);
    }

}
