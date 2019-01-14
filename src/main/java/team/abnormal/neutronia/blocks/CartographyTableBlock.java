/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.blocks;

import javax.annotation.Nullable;
import net.minecraft.class_3910;
import net.minecraft.class_3914;
import net.minecraft.block.Block.Settings;
import net.minecraft.client.network.ClientDummyContainerProvider;
import net.minecraft.container.ContainerProvider;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CartographyTableBlock extends Block {
    private static final TranslatableTextComponent field_17355 = new TranslatableTextComponent("container.cartography_table", new Object[0]);

    protected CartographyTableBlock(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, Direction direction_1, float float_1, float float_2, float float_3) {
        playerEntity_1.method_17355(blockState_1.method_17526(world_1, blockPos_1));
        playerEntity_1.increaseStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
        return true;
    }

    @Nullable
    public NameableContainerProvider method_17454(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
        return new ClientDummyContainerProvider((int_1, playerInventory_1, playerEntity_1) -> {
            return new class_3910(int_1, playerInventory_1, class_3914.method_17392(world_1, blockPos_1));
        }, field_17355);
    }
}
*/
