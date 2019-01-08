package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import team.abnormal.abnormalib.recipe.RecipeHandler;
import team.abnormal.abnormalib.util.ProxyRegistry;

public abstract class MRSlab extends BlockSlab {
    public static final PropertyEnum<MRSlab.Variant> VARIANT = PropertyEnum.create("variant", MRSlab.Variant.class);
    protected String name;
    protected Block pickBlock;

    public MRSlab(String name, Material material, IBlockState state, float hardness, float resistance, SoundType soundType, Block pickBlock) {
        super(material);
        setRegistryName(name);
        setTranslationKey(name);
        setDefaultState(state);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
        this.name = name;
        this.pickBlock = pickBlock;
        useNeighborBrightness = !this.isDouble();
        IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        this.setDefaultState(iblockstate);
        if (!this.isDouble()) {
            iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
    }

    public MRSlab(String name, Material material, IBlockState state, float hardness, float resistance, SoundType soundType) {
        super(material);
        setRegistryName(name);
        setTranslationKey(name);
        setDefaultState(state);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(soundType);
        this.name = name;
        useNeighborBrightness = !this.isDouble();
        IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        this.setDefaultState(iblockstate);
        if (!this.isDouble()) {
            iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
    }

    public static void registerSlab(Block base, int meta, BlockSlab halfSlab, BlockSlab doubleSlab) {
        ForgeRegistries.BLOCKS.register(halfSlab);
        Item halfSlabItem = (new ItemSlab(halfSlab, halfSlab, doubleSlab)).setRegistryName(halfSlab.getRegistryName());
        ForgeRegistries.ITEMS.register(halfSlabItem);
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(halfSlabItem, 0, new ModelResourceLocation(halfSlab.getRegistryName(), "inventory"));
        }

        ForgeRegistries.BLOCKS.register(doubleSlab);
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(halfSlab, 6), "BBB", 'B', ProxyRegistry.newStack(base, 1, meta));
    }

    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack) {
        return MRSlab.Variant.DEFAULT;
    }

    public final IBlockState getStateFromMeta(int meta) {
        IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, MRSlab.Variant.DEFAULT);
        if (!this.isDouble()) {
            blockstate = blockstate.withProperty(HALF, (meta & 8) != 0 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        }

        return blockstate;
    }

    public final int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
            meta |= 8;
        }

        return meta;
    }

    protected BlockStateContainer createBlockState() {
        return !this.isDouble() ? new BlockStateContainer(this, VARIANT, HALF) : new BlockStateContainer(this, VARIANT);
    }

    public enum Variant implements IStringSerializable {
        DEFAULT;

        Variant() {
        }

        public String getName() {
            return "default";
        }
    }

    public static class Half extends MRSlab {

        public Half(String name, Material material, IBlockState state, float hardness, float resistance, SoundType soundType, Block pickBlock) {
            super(name, material, state, hardness, resistance, soundType, pickBlock);
            setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }

        public Half(String name, Material material, IBlockState state) {
            super(name, material, state, 2.0F, 10.0F, SoundType.STONE, null);
            setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }

        public boolean isDouble() {
            return false;
        }
    }

    public static class Double extends MRSlab {
        public float slipperiness;

        public Double(String name, Material material, IBlockState state, float hardness, float resistance, SoundType soundType, Block pickBlock) {
            super(name, material, state, hardness, resistance, soundType, pickBlock);
            setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }

        public Double(String name, IBlockState state, Material material) {
            super(name, material, state, 2.0F, 10.0F, SoundType.STONE, null);
            setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }

        public boolean isDouble() {
            return true;
        }

        public void setSlipperiness(float slipperiness) {
            this.slipperiness = slipperiness;
        }
    }
}
