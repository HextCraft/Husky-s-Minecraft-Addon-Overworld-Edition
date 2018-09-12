package net.hdt.neutronia.blocks.overworld;

import java.util.Random;

import net.hdt.huskylib2.interf.IBlockColorProvider;
import net.hdt.huskylib2.interf.IRecipeGrouped;
import net.hdt.huskylib2.item.ItemModBlock;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
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

public class BlockColoredVase extends BlockFlowerPot implements INeutroniaBlock, IBlockColorProvider, IRecipeGrouped {

    private final String[] variants;
    private final String bareName;

    public BlockColoredVase(EnumDyeColor color) {
        String name = String.format("%s_colored_vase", color.getName());
        variants = new String[]{name};
        bareName = name;

        setHardness(0.0F);
        setSoundType(SoundType.STONE);
        setCreativeTab(NCreativeTabs.OVERWORLD_EXPANSION_TAB);

        register(name);
    }

    public Block register(String name) {
        setTranslationKey(name);
        setRegistryName(getPrefix(), name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(getPrefix(), name)));
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
    public String getPrefix() {
        return Reference.MOD_ID;
    }

    @Override
    public String getModNamespace() {
        return Reference.MOD_ID;
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
    public IItemColor getItemColor() {
        return (stack, i) -> 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() {
        return (state, world, pos, i) -> Minecraft.getMinecraft().getBlockColors().colorMultiplier(Blocks.FLOWER_POT.getDefaultState(), world, pos, i);
    }

    @Override
    public String getRecipeGroup() {
        return "colored_vase";
    }

}