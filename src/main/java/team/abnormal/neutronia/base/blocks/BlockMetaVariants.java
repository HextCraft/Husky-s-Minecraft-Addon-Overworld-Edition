package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import team.abnormal.abnormalib.block.BlockMod;
import team.abnormal.abnormalib.interf.IVariantEnumHolder;

import java.util.Locale;

public abstract class BlockMetaVariants<T extends Enum<T> & IStringSerializable> extends BlockMod {

    public static Class temporaryVariantsEnum; // This is a massive hack, but such is life with constructors
    public static PropertyEnum temporaryVariantProp;

    public final Class<T> variantsEnum;
    public final PropertyEnum<T> variantProp;

    public BlockMetaVariants(String name, Material materialIn, Class<T> variantsEnum) {
        super(name, materialIn, asVariantArray(variantsEnum));

        this.variantsEnum = variantsEnum;
        this.variantProp = temporaryVariantProp;
        setDefaultState(blockState.getBaseState().withProperty(variantProp, variantsEnum.getEnumConstants()[0]));

        register(name);
    }

    public static String[] asVariantArray(Class e) {
        temporaryVariantsEnum = e;
        temporaryVariantProp = PropertyEnum.create(IVariantEnumHolder.HEADER, e);
        Enum[] values = (Enum[]) e.getEnumConstants();
        String[] variants = new String[values.length];

        for (int i = 0; i < values.length; i++)
            variants[i] = values[i].name().toLowerCase(Locale.ENGLISH);
        return variants;
    }

    @Override
    public boolean registerInConstruction() {
        return false;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, temporaryVariantProp);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((Enum<T>) state.getValue(variantProp == null ? temporaryVariantProp : variantProp)).ordinal();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta >= variantsEnum.getEnumConstants().length)
            meta = 0;

        return getDefaultState().withProperty(variantProp, variantsEnum.getEnumConstants()[meta]);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public Class<T> getVariantEnum() {
        return variantsEnum;
    }

    @Override
    public IProperty getVariantProp() {
        return variantProp;
    }

    public interface EnumBase extends IStringSerializable {

        @Override
        default String getName() {
            return ((Enum) this).name().toLowerCase(Locale.ENGLISH);
        }

    }

}