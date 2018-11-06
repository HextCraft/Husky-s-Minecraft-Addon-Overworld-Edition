package net.hdt.neutronia.base.blocks;

import net.hdt.neutronia.base.items.ItemNeutroniaDoor;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class BlockNeutroniaDoor extends BlockModDoor implements INeutroniaBlock {
    private final Supplier<Item> itemSupplier;

    public BlockNeutroniaDoor(String name) {
        super(Material.WOOD, name);
        this.setHardness(3.0F);
        this.setSoundType(SoundType.WOOD);
        this.itemSupplier = () -> new ItemNeutroniaDoor(this);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.itemSupplier.get();
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(this.itemSupplier.get());
    }
}