package team.hdt.neutronia.groups.decoration.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia.base.blocks.BlockNeutroniaColored;
import team.hdt.neutronia.groups.decoration.features.ColoredRedstoneLamps;

import java.util.Random;

public class BlockColoredRedstoneLamp extends BlockNeutroniaColored {

    private final boolean isOn;
    public EnumDyeColor color;

    public BlockColoredRedstoneLamp(EnumDyeColor color, boolean isOn) {
        super(Material.REDSTONE_LIGHT, isOn ? "colored_lit_redstone_lamp" : "colored_redstone_lamp", color);
        this.color = color;
        this.isOn = isOn;
        setCreativeTab(isOn ? CreativeTabs.SEARCH : CreativeTabs.REDSTONE);
        this.setLightLevel(isOn ? 1.0F : 0.0F);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.updateState(worldIn, pos, state);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        this.updateState(worldIn, pos, state);
    }

    public void updateState(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, ColoredRedstoneLamps.coloredRedstoneLamp[color.getMetadata()].getDefaultState(), 2);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, ColoredRedstoneLamps.coloredLitRedstoneLamp[color.getMetadata()].getDefaultState(), 2);
            }
        }
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.scheduleUpdate(pos, this, 4);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, ColoredRedstoneLamps.coloredLitRedstoneLamp[color.getMetadata()].getDefaultState(), 2);
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ColoredRedstoneLamps.coloredRedstoneLamp[color.getMetadata()]);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ColoredRedstoneLamps.coloredRedstoneLamp[color.getMetadata()]);
    }

    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(ColoredRedstoneLamps.coloredRedstoneLamp[color.getMetadata()]);
    }

}
