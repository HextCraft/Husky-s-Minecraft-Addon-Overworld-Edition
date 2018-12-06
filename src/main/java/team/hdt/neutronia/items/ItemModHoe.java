package team.hdt.neutronia.items;

import team.hdt.huskylib.interf.IVariantHolder;
import team.hdt.huskylib.item.ItemMod;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import team.hdt.neutronia.base.items.INeutroniaItem;

public class ItemModHoe extends ItemHoe implements IVariantHolder, INeutroniaItem {

    private final String[] variants;
    private final String bareName;

    public ItemModHoe(ToolMaterial material, String name, String... variants) {
        super(material);

        if (variants.length > 1)
            setHasSubtypes(true);

        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;
        setTranslationKey(getPrefix() + name);
        ItemMod.variantHolders.add(this);
        this.toolMaterial = material;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    public Item setTranslationKey(String name) {
        super.setTranslationKey(name);
        setRegistryName(new ResourceLocation(name));
        ProxyRegistry.register(this);
        return this;
    }

    @Override
    public String getTranslationKey(ItemStack par1ItemStack) {
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
