package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaWall;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VanillaWalls extends Component {

    boolean stone, granite, diorite, andesite, polishedGranite, polishedDiorite, polishedAndesite, sandstone,
            chiseledSandstone, smoothSandstone, redSandstone, chiseledRedSandstone,
            smoothRedSandstone, stoneBricks,
            bricks, quartz, prismarine, prismarineBricks, darkPrismarine, purpurBlock, endBricks, mossyStoneBrick,
            crackedStoneBrick, chiseledStoneBrick, mossyCobblestone;

    public static void add(String name, Block block, int meta, boolean doit) {
        add(name, block, meta, doit, BlockNeutroniaWall::new);
    }

    public static void add(String name, Block block, int meta, boolean doit, WallSupplier supplier) {
        if (!doit)
            return;

        IBlockState state = block.getStateFromMeta(meta);
        String wallName = name + "_wall";
        BlockNeutroniaWall.initWall(block, meta, supplier.supply(wallName, state));
    }

    public static void add(String name, Block block, int meta, boolean doit, CreativeTabs creativeTabs) {
        add(name, block, meta, doit, BlockNeutroniaWall::new, creativeTabs);
    }

    public static void add(String name, Block block, int meta, boolean doit, WallSupplier supplier, CreativeTabs creativeTabs) {
        if (!doit)
            return;

        IBlockState state = block.getStateFromMeta(meta);
        String wallName = name + "_wall";
        BlockNeutroniaWall neutroniaWall = supplier.supply(wallName, state);
        neutroniaWall.setCreativeTab(creativeTabs);
        BlockNeutroniaWall.initWall(block, meta, neutroniaWall);
    }

    @Override
    public void setupConfig() {
        stone = loadPropBool("Stone", "", true);
        granite = loadPropBool("Granite", "", true);
        diorite = loadPropBool("Diorite", "", true);
        andesite = loadPropBool("Andesite", "", true);
        polishedGranite = loadPropBool("Polished Granite", "", true);
        polishedDiorite = loadPropBool("Polished Diorite", "", true);
        polishedAndesite = loadPropBool("Polished Andesite", "", true);
        sandstone = loadPropBool("Sandstone", "", true);
        smoothSandstone = loadPropBool("Smooth Sandstone", "", true);
        chiseledSandstone = loadPropBool("Chiseled Sandstone", "", true);
        redSandstone = loadPropBool("Red Sandstone", "", true);
        smoothRedSandstone = loadPropBool("Smooth Red Sandstone", "", true);
        chiseledRedSandstone = loadPropBool("Chiseled Red Sandstone", "", true);
        stoneBricks = loadPropBool("Stone Bricks", "", true);
        mossyStoneBrick = loadPropBool("Mossy Stone Bricks", "", true);
        crackedStoneBrick = loadPropBool("Cracked Stone Bricks", "", true);
        bricks = loadPropBool("Bricks", "", true);
        quartz = loadPropBool("Quartz", "", true);
        prismarine = loadPropBool("Prismarine", "", true);
        prismarineBricks = loadPropBool("Prismarine Bricks", "", true);
        darkPrismarine = loadPropBool("Dark Prismarine", "", true);
        purpurBlock = loadPropBool("Purpur", "", true);
        endBricks = loadPropBool("End Bricks", "", true);
        mossyCobblestone = loadPropBool("Mossy Cobblestone", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        add("stone", Blocks.STONE, 0, stone);
        add("granite", Blocks.STONE, 1, granite);
        add("diorite", Blocks.STONE, 3, diorite);
        add("andesite", Blocks.STONE, 5, andesite);
        add("polished_granite", Blocks.STONE, 2, polishedGranite);
        add("polished_diorite", Blocks.STONE, 4, polishedDiorite);
        add("polished_andesite", Blocks.STONE, 6, polishedAndesite);
        add("sandstone", Blocks.SANDSTONE, 0, sandstone);
        add("chiseled_sandstone", Blocks.SANDSTONE, 1, chiseledSandstone);
        add("smooth_sandstone", Blocks.SANDSTONE, 2, smoothSandstone);
        add("red_sandstone", Blocks.RED_SANDSTONE, 0, redSandstone);
        add("chiseled_red_sandstone", Blocks.RED_SANDSTONE, 1, chiseledRedSandstone);
        add("smooth_red_sandstone", Blocks.RED_SANDSTONE, 2, smoothRedSandstone);
        add("stone_brick", Blocks.STONEBRICK, 0, stoneBricks);
        add("mossy_stone_brick", Blocks.STONEBRICK, 1, stoneBricks);
        add("cracked_stone_brick", Blocks.STONEBRICK, 2, crackedStoneBrick);
        add("chiseled_stone_brick", Blocks.STONEBRICK, 2, chiseledStoneBrick);
        add("brick", Blocks.BRICK_BLOCK, 0, bricks);
        add("quartz", Blocks.QUARTZ_BLOCK, 0, quartz);
        add("prismarine_rough", Blocks.PRISMARINE, 0, prismarine);
        add("prismarine_bricks", Blocks.PRISMARINE, 1, prismarineBricks);
        add("dark_prismarine", Blocks.PRISMARINE, 2, darkPrismarine);
        add("purpur_block", Blocks.PURPUR_BLOCK, 0, purpurBlock);
        add("end_bricks", Blocks.END_BRICKS, 0, endBricks);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    public interface WallSupplier {
        BlockNeutroniaWall supply(String wallName, IBlockState state);
    }

}