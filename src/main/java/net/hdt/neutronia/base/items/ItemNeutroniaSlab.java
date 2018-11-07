package net.hdt.neutronia.base.items;

import net.hdt.huskylib2.item.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNeutroniaSlab extends ItemModBlock {

    private final Block doubleBlock;

    public ItemNeutroniaSlab(Block block, ResourceLocation name, Block doubleBlock) {
        super(block, name);
        this.doubleBlock = doubleBlock;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);

        if (!stack.isEmpty() && player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            IBlockState currentState = world.getBlockState(pos);

            if (currentState.getBlock() == this.block) {
                BlockSlab.EnumBlockHalf currentHalf = currentState.getValue(BlockSlab.HALF);

                if (facing == EnumFacing.UP && currentHalf == BlockSlab.EnumBlockHalf.BOTTOM || facing == EnumFacing.DOWN && currentHalf == BlockSlab.EnumBlockHalf.TOP) {
                    IBlockState doubleState = this.doubleBlock.getDefaultState();
                    AxisAlignedBB collisionBounds = doubleState.getCollisionBoundingBox(world, pos);

                    if (collisionBounds != null && world.checkNoEntityCollision(collisionBounds.offset(pos)) && world.setBlockState(pos, doubleState, 11)) {
                        SoundType soundType = doubleState.getBlock().getSoundType(doubleState, world, pos, player);
                        world.playSound(player, pos, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
                        stack.shrink(1);

                        return EnumActionResult.SUCCESS;
                    }
                }
            }

            return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
        }

        return EnumActionResult.FAIL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == this.block) {
            boolean top = state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;
            if (side == EnumFacing.UP && !top || side == EnumFacing.DOWN && top) {
                return true;
            }
        }

        IBlockState currentState = world.getBlockState(pos.offset(side));
        return currentState.getBlock() == this.block || super.canPlaceBlockOnSide(world, pos, side, player, stack);
    }
}