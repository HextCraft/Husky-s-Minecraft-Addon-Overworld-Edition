package net.hdt.neutronia.groups.world.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockComputer extends Block {

    public BlockComputer() {
        super(Material.IRON);
        setRegistryName("modID", "computer");
        setTranslationKey("computer");
        ForgeRegistries.BLOCKS.register(this);
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

}
