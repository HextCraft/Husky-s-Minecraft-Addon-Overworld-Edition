package net.hdt.neutronia.base.items;

import net.hdt.huskylib2.interf.IModBlock;
import net.hdt.huskylib2.interf.IVariantHolder;
import net.hdt.huskylib2.item.ItemMod;
import net.hdt.neutronia.base.blocks.BlockNeutroniaDoubleSlab;
import net.hdt.neutronia.base.blocks.BlockNeutroniaSlab;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemModBlockSlab extends ItemSlab implements IVariantHolder {

    private IModBlock modBlock;

    public ItemModBlockSlab(Block block, ResourceLocation res) {
        super(block, ((BlockNeutroniaSlab) block), ((BlockNeutroniaDoubleSlab) block));
        modBlock = (IModBlock) block;

        ItemMod.variantHolders.add(this);
        if (getVariants().length > 1)
            setHasSubtypes(true);
        setRegistryName(res);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public ItemBlock setTranslationKey(String par1Str) {
        return (ItemBlock) super.setTranslationKey(par1Str);
    }

    @Override
    public String getTranslationKey(ItemStack par1ItemStack) {
        int dmg = par1ItemStack.getItemDamage();
        String[] variants = getVariants();

        String name;
        if (dmg >= variants.length)
            name = modBlock.getBareName();
        else name = variants[dmg];

        return "tile." + getPrefix() + name;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        String[] variants = getVariants();
        if (isInCreativeTab(tab))
            for (int i = 0; i < variants.length; i++)
                subItems.add(new ItemStack(this, 1, i));
    }

    @Override
    public String[] getVariants() {
        return modBlock.getVariants();
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return modBlock.getCustomMeshDefinition();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return modBlock.getBlockRarity(stack);
    }

    @Override
    public String getModNamespace() {
        return modBlock.getModNamespace();
    }

}
