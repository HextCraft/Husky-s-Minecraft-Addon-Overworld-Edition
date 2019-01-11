//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.blocks;

import net.minecraft.class_3914;
import net.minecraft.block.Block.Settings;
import net.minecraft.client.network.ClientDummyContainerProvider;
import net.minecraft.container.ContainerProvider;
import net.minecraft.container.LoomContainer;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.state.property.Property;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class LoomBlock extends HorizontalFacingBlock {
    private static final TranslatableTextComponent field_17373 = new TranslatableTextComponent("container.loom", new Object[0]);

    protected LoomBlock(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, Direction direction_1, float float_1, float float_2, float float_3) {
        if (world_1.isClient) {
            return true;
        } else {
            playerEntity_1.method_17355(blockState_1.method_17526(world_1, blockPos_1));
            return true;
        }
    }

    public NameableContainerProvider method_17454(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
        return new ClientDummyContainerProvider((int_1, playerInventory_1, playerEntity_1) -> {
            return new LoomContainer(int_1, playerInventory_1, class_3914.method_17392(world_1, blockPos_1.add(0.5D, 0.0D, 0.5D)));
        }, field_17373);
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return (BlockState)this.getDefaultState().with(field_11177, itemPlacementContext_1.getPlayerHorizontalFacing());
    }

    protected void appendProperties(Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(new Property[]{field_11177});
    }
}
