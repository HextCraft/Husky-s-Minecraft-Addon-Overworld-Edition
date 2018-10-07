package net.hdt.neutronia.groups.decoration.blocks;

import net.hdt.huskylib2.interf.IBlockColorProvider;
import net.hdt.huskylib2.interf.IRecipeGrouped;
import net.hdt.huskylib2.item.ItemModBlock;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockTerracottaFlowerPot extends BlockFlowerPot implements INeutroniaBlock, IBlockColorProvider, IRecipeGrouped {

    public final EnumDyeColor color;
    private final String[] variants;
    private final String bareName;

    public BlockTerracottaFlowerPot(EnumDyeColor color) {
        this.color = color;
        String name = color.getName() + "_terracotta_pot";
        variants = new String[]{name};
        bareName = name;

        setHardness(0.0F);
        setSoundType(SoundType.STONE);
        setCreativeTab(Neutronia.CREATIVE_TAB);

        setTranslationKey(name);
    }

    @Override
    public Block setTranslationKey(String name) {
        super.setTranslationKey(name);
        setRegistryName(LibMisc.PREFIX_MOD + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(LibMisc.PREFIX_MOD + name)));
        return this;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        ItemStack stack = super.getItem(worldIn, pos, state);
        if (stack.getItem() == Items.FLOWER_POT)
            stack = new ItemStack(Item.getItemFromBlock(this));

        return stack;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public String getLocalizedName() {
        return I18n.translateToLocal(getTranslationKey() + ".name");
    }

    @Override
    public String getBareName() {
        return bareName;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[]{LEGACY_DATA};
    }

    @Override
    public IProperty getVariantProp() {
        return null;
    }

    @Override
    public Class getVariantEnum() {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() {
        return (state, worldIn, pos, tintIndex) -> EnumDyeColor.values()[tintIndex].getColorValue();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor() {
        return (stack, tintIndex) -> EnumDyeColor.values()[tintIndex].getColorValue();
    }

    @Override
    public String getRecipeGroup() {
        return "terracotta_flower_pot";
    }

}