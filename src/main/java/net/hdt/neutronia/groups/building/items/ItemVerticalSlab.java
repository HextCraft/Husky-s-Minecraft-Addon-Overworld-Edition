package net.hdt.neutronia.groups.building.items;

import net.hdt.neutronia.groups.building.blocks.BlockVerticalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemVerticalSlab extends ItemBlock {

    private BlockVerticalSlab verticalSlab;

    public ItemVerticalSlab(BlockVerticalSlab block, ResourceLocation resourceLocation) {
        super(block);
        this.verticalSlab = block;
        setMaxDamage(0);
        setHasSubtypes(true);
        setRegistryName(resourceLocation);
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, 
            float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        if(!itemstack.isEmpty() && player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            if(iblockstate.getBlock() == verticalSlab && verticalSlab.isDouble())
            {
                EnumFacing face = iblockstate.getValue(BlockVerticalSlab.PROPERTYFACING);
                if(facing == EnumFacing.NORTH && face == EnumFacing.NORTH || facing == EnumFacing.SOUTH && face == EnumFacing.SOUTH || facing == EnumFacing.EAST && face == EnumFacing.EAST || facing == EnumFacing.WEST && face == EnumFacing.WEST)
                {
                    BlockVerticalSlab doubleSlab = verticalSlab;
                    IBlockState iblockstate1 = doubleSlab.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);
                    if(axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        SoundType soundtype = this.verticalSlab.getSoundType(iblockstate1, worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);
                    }
                    return EnumActionResult.SUCCESS;
                } else
                {
                    EnumActionResult result = super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
                    return result;
                }
            } else
            {
                EnumActionResult result = super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
                return result;
            }
        } else
        {
            return EnumActionResult.FAIL;
        }
    }

    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        BlockPos blockpos = pos;
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if(iblockstate.getBlock() == verticalSlab && !verticalSlab.isDouble() && (side == EnumFacing.NORTH && iblockstate.getValue(BlockVerticalSlab.PROPERTYFACING) == EnumFacing.NORTH || side == EnumFacing.SOUTH && iblockstate.getValue(BlockVerticalSlab.PROPERTYFACING) == EnumFacing.SOUTH || side == EnumFacing.EAST && iblockstate.getValue(BlockVerticalSlab.PROPERTYFACING) == EnumFacing.EAST || side == EnumFacing.WEST && iblockstate.getValue(BlockVerticalSlab.PROPERTYFACING) == EnumFacing.WEST)) {
            return true;
        } else {
            pos = pos.offset(side);
            IBlockState iblockstate1 = worldIn.getBlockState(pos);
            return iblockstate1.getBlock() == verticalSlab || super.canPlaceBlockOnSide(worldIn, blockpos, side, player, stack);
        }
    }

}
