package net.hdt.neutronia.groups.world.blocks;

import net.hdt.huskylib2.item.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockSpeleothem extends ItemModBlock {

    private boolean up;

    public ItemBlockSpeleothem(Block block, ResourceLocation res, boolean up) {
        super(block, res);
        this.up = up;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
        return up ? side == EnumFacing.UP : side == EnumFacing.DOWN;
    }

}