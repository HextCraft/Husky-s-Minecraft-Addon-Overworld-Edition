package team.hdt.neutronia_revamped.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import team.hdt.huskylib.item.ItemModBlock;
import team.hdt.huskylib.util.ProxyRegistry;
import team.hdt.neutronia_revamped.blocks.INeutroniaBlock;

public class BlockModBush extends BlockBush implements INeutroniaBlock {

    private final String[] variants;
    private final String bareName;

    public BlockModBush(String name, Material material) {
        super(material);
        variants = new String[]{name};
        bareName = name;

        setTranslationKey(name);
        setCreativeTab(CreativeTabs.SEARCH);
    }

    public Block setTranslationKey(String name) {
        super.setTranslationKey(name);
        this.setRegistryName(getPrefix() + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(createItemBlock(new ResourceLocation(getPrefix() + name)));
        return this;
    }

    private ItemBlock createItemBlock(ResourceLocation res) {
        return new ItemModBlock(this, res);
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
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    @Override
    public IProperty getVariantProp() {
        return null;
    }

    @Override
    public Class getVariantEnum() {
        return null;
    }

}