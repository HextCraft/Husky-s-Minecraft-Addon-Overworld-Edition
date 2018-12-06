package team.hdt.neutronia.items;

import team.hdt.huskylib.interf.IVariantHolder;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public abstract class ItemModFood extends ItemFood implements IVariantHolder {

    public static final ArrayList<IVariantHolder> variantHolders = new ArrayList<>();

    private final String[] variants;
    private final String bareName;

    public ItemModFood(String name, CreativeTabs creativeTabs, int amount, float saturation, boolean isWolfFood, String... variants) {
        super(amount, saturation, isWolfFood);
        setCreativeTab(creativeTabs);
        if (variants.length > 1)
            setHasSubtypes(true);

        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;
        setTranslationKey(name);
        variantHolders.add(this);
    }

    public ItemModFood(String name, CreativeTabs creativeTabs, int amount, boolean isWolfFood, String... variants) {
        this(name, creativeTabs, amount, 0.6F, isWolfFood, variants);
    }

    public Item setTranslationKey(String name) {
        super.setTranslationKey(name);
        this.setRegistryName(new ResourceLocation(getPrefix(), name));
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

        return "item." + name;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab))
            for (int i = 0; i < getVariants().length; i++)
                subItems.add(new ItemStack(this, 1, i));
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