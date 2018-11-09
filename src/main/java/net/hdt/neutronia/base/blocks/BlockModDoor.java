package net.hdt.neutronia.base.blocks;

import net.hdt.huskylib2.interf.IModBlock;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.items.ItemNeutroniaDoor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockModDoor extends BlockDoor implements IModBlock {

    private final String[] variants;
    private final String bareName;

    public BlockModDoor(Material materialIn, String name, String... variants) {
        super(materialIn);

        if (variants.length == 0) {
            variants = new String[]{name};
        }

        this.bareName = name;
        this.variants = variants;
        if (this.registerInConstruction()) {
            this.setTranslationKey(name);
        }
    }

    public Block setTranslationKey(String name) {
        super.setTranslationKey(name);
        this.setRegistryName(this.getPrefix() + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemNeutroniaDoor(this, name));
        return this;
    }

    public boolean registerInConstruction() {
        return true;
    }

    public String getBareName() {
        return this.bareName;
    }

    public String[] getVariants() {
        return this.variants;
    }

    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    public IProperty getVariantProp() {
        return null;
    }

    public Class getVariantEnum() {
        return null;
    }

}
