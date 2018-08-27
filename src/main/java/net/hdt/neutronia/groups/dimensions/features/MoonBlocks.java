package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.blocks.BlockMoonBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoonBlocks extends Component {

    public static Block CARVED_MOON_STONE, CARVED_MOON_STONE_CRACKED, CHISELED_MOON_STONE, CHISELED_MOON_STONE_CRACKED, MOON_DUST, MOON_STONE, SOLID_MOON_STONE;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        CARVED_MOON_STONE = new BlockMoonBase("carved_moon_stone", Material.ROCK, CreativeTabs.BUILDING_BLOCKS);
        CARVED_MOON_STONE_CRACKED = new BlockMoonBase("carved_moon_stone_cracked", Material.ROCK, CreativeTabs.BUILDING_BLOCKS);
        CHISELED_MOON_STONE = new BlockMoonBase("chiseled_moon_stone", Material.ROCK, CreativeTabs.BUILDING_BLOCKS);
        CHISELED_MOON_STONE_CRACKED = new BlockMoonBase("chiseled_moon_stone_cracked", Material.ROCK, CreativeTabs.BUILDING_BLOCKS);
        MOON_DUST = new BlockMoonBase("moon_dus", Material.SAND, CreativeTabs.BUILDING_BLOCKS);
        MOON_STONE = new BlockMoonBase("moon_stone", Material.ROCK, CreativeTabs.BUILDING_BLOCKS);
        SOLID_MOON_STONE = new BlockMoonBase("solid_moon_stone", Material.ROCK, CreativeTabs.BUILDING_BLOCKS);
    }

}