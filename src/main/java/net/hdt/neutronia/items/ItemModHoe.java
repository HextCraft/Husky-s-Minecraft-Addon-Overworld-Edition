package net.hdt.neutronia.items;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.thegaminghuskymc.huskylib2.interf.IVariantHolder;
import net.thegaminghuskymc.huskylib2.items.ItemMod;
import net.thegaminghuskymc.huskylib2.utils.ProxyRegistry;

public class ItemModHoe extends ItemHoe implements IVariantHolder {

    private final String[] variants;
    private final String bareName, modid;

    public ItemModHoe(ToolMaterial material, String name, String modid, String... variants) {
        super(material);

        if (variants.length > 1)
            setHasSubtypes(true);

        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;
        this.modid = modid;
        setUnlocalizedName(name, modid);
        ItemMod.variantHolders.add(this);
        this.toolMaterial = material;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override
    public String getPrefix() {
        return this.modid;
    }

    @Override
    public String getModNamespace() {
        return this.modid;
    }

    public Item setUnlocalizedName(String name, String modid) {
        super.setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(modid, name));
        ProxyRegistry.register(this);

        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int dmg = par1ItemStack.getItemDamage();
        String[] variants = getVariants();

        String name;
        if (dmg >= variants.length)
            name = bareName;
        else name = variants[dmg];

        return "item." + getPrefix() + name;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

}