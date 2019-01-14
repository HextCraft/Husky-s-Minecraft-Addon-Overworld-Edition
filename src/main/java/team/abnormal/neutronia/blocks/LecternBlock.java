/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.blocks;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block.Settings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LecternBlock extends BlockWithEntity {
    public static final DirectionProperty field_16404;
    public static final BooleanProperty field_17365;
    public static final BooleanProperty field_17366;
    public static final VoxelShape field_16406;
    public static final VoxelShape field_16405;
    public static final VoxelShape field_16403;
    public static final VoxelShape field_17367;
    public static final VoxelShape field_17368;
    public static final VoxelShape field_17369;
    public static final VoxelShape field_17370;
    public static final VoxelShape field_17371;
    public static final VoxelShape field_17372;

    protected LecternBlock(Settings block$Settings_1) {
        super(block$Settings_1);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(field_16404, Direction.NORTH)).with(field_17365, false)).with(field_17366, false));
    }

    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return (BlockState)this.getDefaultState().with(field_16404, itemPlacementContext_1.getPlayerHorizontalFacing().getOpposite());
    }

    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
        return field_17368;
    }

    public VoxelShape getBoundingShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        switch(SyntheticClass_1.field_15363[((Direction)blockState_1.get(field_16404)).ordinal()]) {
        case 1:
            return field_17370;
        case 2:
            return field_17372;
        case 3:
            return field_17371;
        case 4:
            return field_17369;
        default:
            return field_16403;
        }
    }

    public BlockState applyRotation(BlockState blockState_1, Rotation rotation_1) {
        return (BlockState)blockState_1.with(field_16404, rotation_1.method_10503((Direction)blockState_1.get(field_16404)));
    }

    public BlockState applyMirror(BlockState blockState_1, Mirror mirror_1) {
        return blockState_1.applyRotation(mirror_1.getRotation((Direction)blockState_1.get(field_16404)));
    }

    protected void appendProperties(Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(new Property[]{field_16404, field_17365, field_17366});
    }

    @Nullable
    public BlockEntity createBlockEntity(BlockView blockView_1) {
        return new LecternBlockEntity();
    }

    public static boolean method_17472(World world_1, BlockPos blockPos_1, BlockState blockState_1, ItemStack itemStack_1) {
        if (!(Boolean)blockState_1.get(field_17366)) {
            if (!world_1.isClient) {
                method_17475(world_1, blockPos_1, blockState_1, itemStack_1);
            }

            return true;
        } else {
            return false;
        }
    }

    private static void method_17475(World world_1, BlockPos blockPos_1, BlockState blockState_1, ItemStack itemStack_1) {
        BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
        if (blockEntity_1 instanceof LecternBlockEntity) {
            LecternBlockEntity lecternBlockEntity_1 = (LecternBlockEntity)blockEntity_1;
            lecternBlockEntity_1.method_17513(itemStack_1.split(1));
            method_17473(world_1, blockPos_1, blockState_1, true);
            world_1.playSound((PlayerEntity)null, blockPos_1, SoundEvents.ITEM_BOOK_PUT, SoundCategory.BLOCK, 1.0F, 1.0F);
        }

    }

    public static void method_17473(World world_1, BlockPos blockPos_1, BlockState blockState_1, boolean boolean_1) {
        world_1.setBlockState(blockPos_1, (BlockState)((BlockState)blockState_1.with(field_17365, false)).with(field_17366, boolean_1), 3);
        method_17474(world_1, blockPos_1, blockState_1);
    }

    public static void method_17471(World world_1, BlockPos blockPos_1, IBlockState blockState_1) {
        method_17476(world_1, blockPos_1, blockState_1, true);
        world_1.scheduleUpdate(blockPos_1, blockState_1.getBlock(), 2);
        world_1.fireWorldEvent(1043, blockPos_1, 0);
    }

    private static void method_17476(World world_1, BlockPos blockPos_1, IBlockState blockState_1, boolean boolean_1) {
        world_1.setBlockState(blockPos_1, (IBlockState)blockState_1.withProperty(field_17365, boolean_1), 3);
        method_17474(world_1, blockPos_1, blockState_1);
    }

    private static void method_17474(World world_1, BlockPos blockPos_1, IBlockState blockState_1) {
        world_1.updateObservingBlocksAt(blockPos_1.down(), blockState_1.getBlock());
    }

    public void scheduleUpdate(IBlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (!world_1.isRemote) {
            method_17476(world_1, blockPos_1, blockState_1, false);
        }
    }

    public void onBlockRemoved(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
        if (blockState_1.getBlock() != blockState_2.getBlock()) {
            if ((Boolean)blockState_1.get(field_17366)) {
                this.method_17477(blockState_1, world_1, blockPos_1);
            }

            if ((Boolean)blockState_1.get(field_17365)) {
                world_1.updateNeighborsAlways(blockPos_1.down(), this);
            }

            super.onBlockRemoved(blockState_1, world_1, blockPos_1, blockState_2, boolean_1);
        }
    }

    private void method_17477(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
        BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
        if (blockEntity_1 instanceof LecternBlockEntity) {
            LecternBlockEntity lecternBlockEntity_1 = (LecternBlockEntity)blockEntity_1;
            Direction direction_1 = (Direction)blockState_1.get(field_16404);
            ItemStack itemStack_1 = lecternBlockEntity_1.method_17520().copy();
            float float_1 = 0.25F * (float)direction_1.getOffsetX();
            float float_2 = 0.25F * (float)direction_1.getOffsetZ();
            ItemEntity itemEntity_1 = new ItemEntity(world_1, (double)blockPos_1.getX() + 0.5D + (double)float_1, (double)(blockPos_1.getY() + 1), (double)blockPos_1.getZ() + 0.5D + (double)float_2, itemStack_1);
            itemEntity_1.method_6988();
            world_1.spawnEntity(itemEntity_1);
            lecternBlockEntity_1.clearInv();
        }

    }

    public boolean emitsRedstonePower(BlockState blockState_1) {
        return true;
    }

    public int getWeakRedstonePower(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, Direction direction_1) {
        return (Boolean)blockState_1.get(field_17365) ? 15 : 0;
    }

    public int getStrongRedstonePower(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, Direction direction_1) {
        return direction_1 == Direction.UP && (Boolean)blockState_1.get(field_17365) ? 15 : 0;
    }

    public boolean hasComparatorOutput(BlockState blockState_1) {
        return true;
    }

    public int getComparatorOutput(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
        if ((Boolean)blockState_1.get(field_17366)) {
            BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
            if (blockEntity_1 instanceof LecternBlockEntity) {
                return ((LecternBlockEntity)blockEntity_1).method_17524();
            }
        }

        return 0;
    }

    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, Direction direction_1, float float_1, float float_2, float float_3) {
        if ((Boolean)blockState_1.get(field_17366)) {
            if (!world_1.isClient) {
                this.method_17470(world_1, blockPos_1, playerEntity_1);
            }

            return true;
        } else {
            return false;
        }
    }

    @Nullable
    public NameableContainerProvider method_17454(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
        return !(Boolean)blockState_1.get(field_17366) ? null : super.method_17454(blockState_1, world_1, blockPos_1);
    }

    private void method_17470(World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1) {
        BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
        if (blockEntity_1 instanceof LecternBlockEntity) {
            playerEntity_1.method_17355((LecternBlockEntity)blockEntity_1);
            playerEntity_1.increaseStat(Stats.INTERACT_WITH_LECTERN);
        }

    }

    static {
        field_16404 = HorizontalFacingBlock.field_11177;
        field_17365 = Properties.POWERED;
        field_17366 = Properties.field_17393;
        field_16406 = Block.createCubeShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        field_16405 = Block.createCubeShape(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D);
        field_16403 = VoxelShapes.union(field_16406, field_16405);
        field_17367 = Block.createCubeShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        field_17368 = VoxelShapes.union(field_16403, field_17367);
        field_17369 = VoxelShapes.union(VoxelShapes.union(Block.createCubeShape(1.0D, 10.0D, 0.0D, 5.333333D, 14.0D, 16.0D), Block.createCubeShape(5.333333D, 12.0D, 0.0D, 9.666667D, 16.0D, 16.0D)), VoxelShapes.union(Block.createCubeShape(9.666667D, 14.0D, 0.0D, 14.0D, 18.0D, 16.0D), field_16403));
        field_17370 = VoxelShapes.union(VoxelShapes.union(Block.createCubeShape(0.0D, 10.0D, 1.0D, 16.0D, 14.0D, 5.333333D), Block.createCubeShape(0.0D, 12.0D, 5.333333D, 16.0D, 16.0D, 9.666667D)), VoxelShapes.union(Block.createCubeShape(0.0D, 14.0D, 9.666667D, 16.0D, 18.0D, 14.0D), field_16403));
        field_17371 = VoxelShapes.union(VoxelShapes.union(Block.createCubeShape(15.0D, 10.0D, 0.0D, 10.666667D, 14.0D, 16.0D), Block.createCubeShape(10.666667D, 12.0D, 0.0D, 6.333333D, 16.0D, 16.0D)), VoxelShapes.union(Block.createCubeShape(6.333333D, 14.0D, 0.0D, 2.0D, 18.0D, 16.0D), field_16403));
        field_17372 = VoxelShapes.union(VoxelShapes.union(Block.createCubeShape(0.0D, 10.0D, 15.0D, 16.0D, 14.0D, 10.666667D), Block.createCubeShape(0.0D, 12.0D, 10.666667D, 16.0D, 16.0D, 6.333333D)), VoxelShapes.union(Block.createCubeShape(0.0D, 14.0D, 6.333333D, 16.0D, 18.0D, 2.0D), field_16403));
    }
}
*/
