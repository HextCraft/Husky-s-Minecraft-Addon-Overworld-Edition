package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BlockNeutroniaSlab extends BlockSlab {

    public static final PropertyEnum<BlockNeutroniaSlab.Variant> VARIANT = PropertyEnum.create("variant", BlockNeutroniaSlab.Variant.class);

    public BlockNeutroniaSlab()
    {
        super(Material.ROCK, MapColor.MAGENTA);
        IBlockState iblockstate = this.blockState.getBaseState();

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, BlockNeutroniaSlab.Variant.DEFAULT));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(new Half());
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(new Half());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockNeutroniaSlab.Variant.DEFAULT);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    /**
     * Returns the slab block name with the type associated with it
     */
    public String getTranslationKey(int meta)
    {
        return super.getTranslationKey();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return BlockNeutroniaSlab.Variant.DEFAULT;
    }

    public static class Double extends BlockNeutroniaSlab
    {
        public boolean isDouble()
        {
            return true;
        }
    }

    public static class Half extends BlockNeutroniaSlab
    {
        public boolean isDouble()
        {
            return false;
        }
    }

    public enum Variant implements IStringSerializable
    {
        DEFAULT;

        public String getName()
        {
            return "default";
        }
    }

}
