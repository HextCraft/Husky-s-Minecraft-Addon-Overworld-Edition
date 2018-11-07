package net.hdt.neutronia.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class BlockNeutroniaDoubleSlab extends BlockNeutroniaSlab {
    private final Supplier<Block> singleSlabSupplier;

    public BlockNeutroniaDoubleSlab(String name, Supplier<Block> singleSlabSupplier) {
        super(new BlockNeutroniaDoubleSlab(name, singleSlabSupplier), name, () -> singleSlabSupplier.get().getDefaultState());
        this.singleSlabSupplier = singleSlabSupplier;
        this.setCreativeTab(null);
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    protected IBlockState buildDefaultState(IBlockState baseState) {
        return baseState;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this.singleSlabSupplier.get());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this);
    }

}