package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import team.abnormal.abnormalib.item.ItemModBlock;
import team.abnormal.abnormalib.util.ProxyRegistry;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.blocks.INeutroniaBlock;

public class BlockNeutroniaBush extends BlockBush implements INeutroniaBlock {

    private final String[] variants;
    private final String bareName;

    public BlockNeutroniaBush(String name) {
        variants = new String[]{name};
        bareName = name;

        setTranslationKey(name);
    }

    @Override
    public Block setTranslationKey(String name) {
        super.setTranslationKey(name);
        setRegistryName(Reference.PREFIX_MOD + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(Reference.PREFIX_MOD + name)));
        return this;
    }

    @Override
    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
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
