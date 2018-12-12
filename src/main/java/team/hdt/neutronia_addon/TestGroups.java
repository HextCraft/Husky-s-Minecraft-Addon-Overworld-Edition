package team.hdt.neutronia_addon;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import team.hdt.neutronia.base.groups.Group;

public class TestGroups {

    public static void init() {
        Group.createGroup(new Group.Builder()
                .name("Test")
                .description("This is a test")
                .enabledByDefault(true)
                .enabled(true)
                .iconStack(new ItemStack(Blocks.LIT_PUMPKIN)));
    }

}
