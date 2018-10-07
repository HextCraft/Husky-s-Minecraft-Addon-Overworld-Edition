package net.hdt.neutronia.groups.building.blocks;

import net.hdt.neutronia.blocks.base.BlockRodBase;
import net.hdt.neutronia.groups.building.features.LogBlocks;
import net.hdt.neutronia.init.NItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLogPole extends BlockRodBase {

    private BlockPlanks.EnumType type;
    private boolean stripped;

    public BlockLogPole(BlockPlanks.EnumType type, boolean stripped) {
        super(stripped ? String.format("stripped_%s_log_pole", type.getName()) : String.format("%s_log_pole", type.getName()), false);
        this.type = type;
        this.stripped = stripped;
    }

    protected static boolean isExcepBlockForAttachWithPiston(Block p_194142_0_) {
        return Block.isExceptBlockForAttachWithPiston(p_194142_0_) || p_194142_0_ == Blocks.BARRIER || p_194142_0_ == Blocks.MELON_BLOCK || p_194142_0_ == Blocks.PUMPKIN || p_194142_0_ == Blocks.LIT_PUMPKIN;
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.updateState(worldIn, pos, state);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        this.updateState(worldIn, pos, state);
    }

    public void updateState(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (!this.stripped) {
                worldIn.setBlockState(pos, LogBlocks.logPoles[type.getMetadata()].getDefaultState(), 2);
            } else {
                worldIn.setBlockState(pos, LogBlocks.strippedLogPoles[type.getMetadata()].getDefaultState(), 2);
            }
        }
    }

    /**
     * Determines if an entity can path through this block
     */
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
        Block block = iblockstate.getBlock();
        boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE && (iblockstate.getMaterial() == this.material);
        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || flag;
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (!this.stripped) {
                worldIn.scheduleUpdate(pos, this, 4);
            } else {
                worldIn.setBlockState(pos, LogBlocks.strippedLogPoles[type.getMetadata()].getDefaultState(), 2);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.getActiveItemStack() == new ItemStack(Items.WOODEN_AXE) || playerIn.getActiveItemStack() == new ItemStack(Items.STONE_AXE) || playerIn.getActiveItemStack() == new ItemStack(Items.IRON_AXE) || playerIn.getActiveItemStack() == new ItemStack(Items.GOLDEN_AXE) || playerIn.getActiveItemStack() == new ItemStack(Items.DIAMOND_AXE) || playerIn.getActiveItemStack() == new ItemStack(NItems.OBSIDIAN_AXE) && hand == EnumHand.MAIN_HAND) {
            if (worldIn.getBlockState(pos) == LogBlocks.logPoles[type.getMetadata()].getDefaultState()) {
                worldIn.setBlockState(pos, LogBlocks.strippedLogPoles[type.getMetadata()].getDefaultState(), 2);
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(NItems.barkItem[type.getMetadata()], 4)));
                stripped = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        return canConnectTo(world, pos.offset(facing), facing.getOpposite());
    }

    private boolean canFenceConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        BlockPos other = pos.offset(facing);
        Block block = world.getBlockState(other).getBlock();
        return block.canBeConnectedTo(world, other, facing.getOpposite()) || canConnectTo(world, other, facing.getOpposite());
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
    }

}
