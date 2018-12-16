package team.hdt.neutronia_legacy.items;

import team.hdt.huskylib.interf.IModBlock;
import team.hdt.huskylib.interf.IVariantHolder;
import team.hdt.huskylib.item.ItemMod;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia_legacy.blocks.base.BlockModColoredSlab;

public class ItemModBlockColoredSlab extends ItemSlab implements IVariantHolder {

    private IModBlock modBlock;

    public ItemModBlockColoredSlab(BlockModColoredSlab block, ResourceLocation res) {
        super(block, block.getSingleBlock(), block.getFullBlock());
        modBlock = block;

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

        return "tile." + name;
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
    public String getModNamespace() {
        return modBlock.getModNamespace();
    }

}