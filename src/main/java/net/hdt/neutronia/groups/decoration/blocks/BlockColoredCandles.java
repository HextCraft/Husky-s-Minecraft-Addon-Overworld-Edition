package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.neutronia.blocks.base.BlockColoredAlt;
import net.hdt.neutronia.groups.decoration.features.ColoredCandles;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockColoredCandles extends BlockColoredAlt {

    public EnumDyeColor color;
    private boolean lit;

    public BlockColoredCandles(EnumDyeColor color, boolean lit) {
        super(lit ? "lit_candle" : "candle", color);
        this.color = color;
        this.lit = lit;
        setCreativeTab(!lit ? NCreativeTabs.OVERWORLD_EXPANSION_TAB : null);
        this.setLightLevel(lit ? 1.0F : 0.0F);
        this.setTickRandomly(lit);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (lit) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + 0.55D;
            double d2 = (double) pos.getZ() + 0.5D;

            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (!lit && playerIn.getHeldItem(hand).getItem() == Items.FLINT_AND_STEEL) {
                lit = true;
                worldIn.scheduleUpdate(pos, this, 4);
                playerIn.getHeldItem(hand).damageItem(1, playerIn);
                return true;
            } else if (lit && playerIn.getHeldItem(hand).isEmpty() && playerIn.isSneaking()) {
                lit = false;
                return true;
            }
        }

        return false;
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (this.lit) {
                worldIn.scheduleUpdate(pos, this, 4);
            } else {
                worldIn.setBlockState(pos, ColoredCandles.coloredLitCandles[color.getMetadata()].getDefaultState(), 2);
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ColoredCandles.coloredCandles[color.getMetadata()]);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ColoredCandles.coloredCandles[color.getMetadata()]);
    }

    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(ColoredCandles.coloredCandles[color.getMetadata()]);
    }

}