package net.hdt.neutronia.groups.tweaks.features;

import com.google.common.collect.ImmutableSet;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class StackableItems extends Component {

    int potions, minecarts, writableBooks, records;

    @Override
    public void setupConfig() {
        potions = loadProperty("Potions", 8).get();
        minecarts = loadProperty("Minecarts", 16).get();
        writableBooks = loadProperty("Writable Books", 16).get();
        records = loadProperty("Records", 8).get();
    }

    @Override
    public String getDescription() {
        return "Changes the stack size for some items";
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ImmutableSet.<Item>of(Items.POTIONITEM, Items.SPLASH_POTION, Items.LINGERING_POTION).forEach(item -> item.setMaxStackSize(potions));

        ImmutableSet.<Item>of(Items.MINECART, Items.CHEST_MINECART, Items.COMMAND_BLOCK_MINECART, Items.FURNACE_MINECART, Items.HOPPER_MINECART, Items.TNT_MINECART)
                .forEach(item -> item.setMaxStackSize(minecarts));

        ImmutableSet.of(Items.RECORD_11, Items.RECORD_13, Items.RECORD_BLOCKS, Items.RECORD_CAT, Items.RECORD_CHIRP, Items.RECORD_FAR, Items.RECORD_MALL, Items.RECORD_MELLOHI,
                Items.RECORD_STAL, Items.RECORD_STRAD, Items.RECORD_WAIT, Items.RECORD_WARD).forEach(item -> item.setMaxStackSize(records));

        ImmutableSet.of(Items.WRITABLE_BOOK).forEach(item -> item.setMaxStackSize(writableBooks));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
