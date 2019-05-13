package team.abnormal.neutronia.base.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import team.abnormal.neutronia.base.Reference;

public class RegistryUtils {

    public static Block register(Block block, ResourceLocation name) {
        return register(block, name, CreativeTabs.DECORATIONS);
    }

    public static Block register(Block block, String name) {
        return register(block, new ResourceLocation(Reference.MOD_ID, name), CreativeTabs.DECORATIONS);
    }

    public static Block register(String modId, Block block, String name) {
        return register(block, new ResourceLocation(modId, name), CreativeTabs.DECORATIONS);
    }

    public static Block register(Block block, ResourceLocation name, CreativeTabs itemGroup) {
        block.setRegistryName(name);
        block.setTranslationKey(name.getNamespace() + "." + name.getPath());
        block.setCreativeTab(itemGroup);
        ForgeRegistries.BLOCKS.register(block);
        Item itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(name);
        itemBlock.setTranslationKey(name.getNamespace() + "." + name.getPath());
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block registerDoor(Block block, ResourceLocation name, CreativeTabs itemGroup) {
        block.setRegistryName(name);
        block.setTranslationKey(name.getNamespace() + "." + name.getPath());
        block.setCreativeTab(itemGroup);
        ForgeRegistries.BLOCKS.register(block);
        Item itemBlock = new ItemDoor(block);
        itemBlock.setRegistryName(name);
        itemBlock.setTranslationKey(name.getNamespace() + "." + name.getPath());
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block register(Block block, String name, CreativeTabs itemGroup) {
        register(block, new ResourceLocation(Reference.MOD_ID, name), itemGroup);
        return block;
    }

    public static Block register(String modId, Block block, String name, CreativeTabs itemGroup) {
        register(block, new ResourceLocation(modId, name), itemGroup);
        return block;
    }

    public static Block registerNoBI(Block block, String name) {
        block.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
        block.setTranslationKey(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    public static Block registerNoBI(Block block, ResourceLocation name) {
        block.setRegistryName(name);
        block.setTranslationKey(name.getNamespace() + "." + name.getPath());
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    public static Item registerItem(Item item, String name) {
        item.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
        item.setTranslationKey(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

    public static Item registerItem(Item item, ResourceLocation name) {
        item.setRegistryName(name);
        item.setTranslationKey(name.getNamespace() + "." + name.getPath());
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

}