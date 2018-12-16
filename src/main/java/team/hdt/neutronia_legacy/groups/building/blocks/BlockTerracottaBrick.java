package team.hdt.neutronia_legacy.groups.building.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockTerracottaBrick extends Block {

    public BlockTerracottaBrick(EnumDyeColor color) {
        super(Material.ROCK);
        setHardness(5.0F);
        register(String.format("%s_terracotta_brick", color.getName()));
    }

    public Block register(String name) {
        setTranslationKey(name);
        setRegistryName("modid", name);
        ForgeRegistries.BLOCKS.register(this);
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName("modid", name));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(name, "inventory"));
        return this;
    }

}