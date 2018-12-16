package team.hdt.neutronia_legacy.groups.tweaks.features;

import com.google.common.collect.ImmutableSet;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;

public class StackableItems extends Component {

    int potions, minecarts, writableBooks, records;

    @Override
    public void setupConfig() {
        potions = loadPropInt("Potions", "", 8);
        minecarts = loadPropInt("Minecarts", "", 16);
        writableBooks = loadPropInt("Writable Books", "", 16);
        records = loadPropInt("Records", "", 8);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ImmutableSet.<Item>of(Items.POTIONITEM, Items.SPLASH_POTION, Items.LINGERING_POTION)
                .forEach(item -> item.setMaxStackSize(potions));

        ImmutableSet.<Item>of(Items.MINECART, Items.CHEST_MINECART, Items.COMMAND_BLOCK_MINECART, Items.FURNACE_MINECART, Items.HOPPER_MINECART, Items.TNT_MINECART)
                .forEach(item -> item.setMaxStackSize(minecarts));

        ImmutableSet.of(Items.RECORD_11, Items.RECORD_13, Items.RECORD_BLOCKS, Items.RECORD_CAT, Items.RECORD_CHIRP, Items.RECORD_FAR, Items.RECORD_MALL, Items.RECORD_MELLOHI,
                Items.RECORD_STAL, Items.RECORD_STRAD, Items.RECORD_WAIT, Items.RECORD_WARD)
                .forEach(item -> item.setMaxStackSize(records));

        ImmutableSet.of(Items.WRITABLE_BOOK)
                .forEach(item -> item.setMaxStackSize(writableBooks));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
