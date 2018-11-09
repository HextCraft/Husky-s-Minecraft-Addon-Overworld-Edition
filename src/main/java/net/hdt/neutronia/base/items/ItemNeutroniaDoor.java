package net.hdt.neutronia.base.items;

import net.hdt.huskylib2.item.ItemModBlock;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNeutroniaDoor extends ItemModBlock {

    public ItemNeutroniaDoor(Block block, String name) {
        super(block, new ResourceLocation(LibMisc.PREFIX_MOD + name));
        this.setCreativeTab(CreativeTabs.REDSTONE);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        }

        Block currentBlock = world.getBlockState(pos).getBlock();
        if (!currentBlock.isReplaceable(world, pos)) {
            pos = pos.offset(facing);
        }

        ItemStack heldItem = player.getHeldItem(hand);
        if (player.canPlayerEdit(pos, facing, heldItem) && this.block.canPlaceBlockAt(world, pos)) {
            EnumFacing direction = EnumFacing.fromAngle(player.rotationYaw);
            int xOffset = direction.getXOffset();
            int zOffset = direction.getZOffset();
            boolean rightHinge = xOffset < 0 && hitZ < 0.5F || xOffset > 0 && hitZ > 0.5F || zOffset < 0 && hitX > 0.5F || zOffset > 0 && hitX < 0.5F;

            this.placeDoor(world, pos, direction, this.block, rightHinge);
            this.applyPlaceEffect(player, world, pos, heldItem);

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }

    private void placeDoor(World world, BlockPos pos, EnumFacing facing, Block door, boolean rightHinge) {
        BlockDoor.EnumHingePosition hinge = this.computeHinge(world, pos, facing, rightHinge);

        BlockPos upperPos = pos.up();
        boolean powered = world.isBlockPowered(pos) || world.isBlockPowered(upperPos);

        IBlockState placeState = door.getDefaultState()
                .withProperty(BlockDoor.FACING, facing)
                .withProperty(BlockDoor.HINGE, hinge)
                .withProperty(BlockDoor.POWERED, powered)
                .withProperty(BlockDoor.OPEN, powered);
        world.setBlockState(pos, placeState.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
        world.setBlockState(upperPos, placeState.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);

        world.notifyNeighborsOfStateChange(pos, door, false);
        world.notifyNeighborsOfStateChange(upperPos, door, false);
    }

    private BlockDoor.EnumHingePosition computeHinge(World world, BlockPos pos, EnumFacing facing, boolean rightHinge) {
        BlockPos posRight = pos.offset(facing.rotateY());
        BlockPos posLeft = pos.offset(facing.rotateYCCW());

        int leftWeight = (world.getBlockState(posLeft).isNormalCube() ? 1 : 0) + (world.getBlockState(posLeft.up()).isNormalCube() ? 1 : 0);
        int rightWeight = (world.getBlockState(posRight).isNormalCube() ? 1 : 0) + (world.getBlockState(posRight.up()).isNormalCube() ? 1 : 0);
        boolean doorLeft = this.isDoor(world, posLeft) || this.isDoor(world, posLeft.up());
        boolean doorRight = this.isDoor(world, posRight) || this.isDoor(world, posRight.up());

        if ((!doorLeft || doorRight) && rightWeight <= leftWeight) {
            if (doorRight && !doorLeft || rightWeight < leftWeight) {
                return BlockDoor.EnumHingePosition.LEFT;
            }
        } else {
            return BlockDoor.EnumHingePosition.RIGHT;
        }

        return rightHinge ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT;
    }

    private void applyPlaceEffect(EntityPlayer player, World world, BlockPos pos, ItemStack heldItem) {
        IBlockState state = world.getBlockState(pos);
        SoundType soundType = state.getBlock().getSoundType(state, world, pos, player);
        world.playSound(player, pos, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
        heldItem.shrink(1);
    }

    private boolean isDoor(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() instanceof BlockDoor;
    }

}