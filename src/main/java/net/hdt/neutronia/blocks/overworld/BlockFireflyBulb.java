package net.hdt.neutronia.blocks.overworld;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.init.NBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFireflyBulb extends BlockNeutroniaBase {

    private final boolean isOn;

    public BlockFireflyBulb(boolean isOn) {
        super(Material.ROCK, isOn ? "lit_firefly_bulb" : "firefly_bulb", false);
        this.isOn = isOn;
        setCreativeTab(isOn ? CreativeTabs.SEARCH : Neutronia.NEUTRONIA_MAIN);
        this.setLightLevel(isOn ? 1.0F : 0.0F);
        this.setTickRandomly(isOn);
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
                worldIn.setBlockState(pos, NBlocks.fireflyBulbOff.getDefaultState(), 2);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, NBlocks.fireflyBulbOn.getDefaultState(), 2);
            }
        }
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.scheduleUpdate(pos, this, 4);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, NBlocks.fireflyBulbOn.getDefaultState(), 2);
            }
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(NBlocks.fireflyBulbOff);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(NBlocks.fireflyBulbOff);
    }

    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(NBlocks.fireflyBulbOff);
    }

}
