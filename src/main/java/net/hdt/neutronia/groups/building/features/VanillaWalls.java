package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaWall;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VanillaWalls extends Component {

    boolean stone, granite, diorite, andesite, sandstone, chiseledSandstone, smoothSandstone, redSandstone, chiseledRedSandstone, smoothRedSandstone, stoneBricks, bricks, quartz, prismarine, prismarineBricks, darkPrismarine, purpurBlock, endBricks;

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
        neutroniaWall.setCreativeTabs(creativeTabs);
        BlockNeutroniaWall.initWall(block, meta, neutroniaWall);
    }

    @Override
    public void setupConfig() {
        stone = loadProperty("Stone", false).get();
        granite = loadProperty("Granite", false).get();
        diorite = loadProperty("Diorite", false).get();
        andesite = loadProperty("Andesite", false).get();
        sandstone = loadProperty("Sandstone", true).get();
        redSandstone = loadProperty("Red Sandstone", true).get();
        stoneBricks = loadProperty("Stone Bricks", false).get();
        bricks = loadProperty("Bricks", false).get();
        quartz = loadProperty("Quartz", false).get();
        prismarine = loadProperty("Prismarine", false).get();
        prismarineBricks = loadProperty("Prismarine Bricks", false).get();
        darkPrismarine = loadProperty("Dark Prismarine", false).get();
        purpurBlock = loadProperty("Purpur", false).get();
        endBricks = loadProperty("End Bricks", false).get();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        add("stone", Blocks.STONE, 0, stone);
        add("stone_granite", Blocks.STONE, 1, granite);
        add("stone_diorite", Blocks.STONE, 3, diorite);
        add("stone_andesite", Blocks.STONE, 5, andesite);
        add("sandstone", Blocks.SANDSTONE, 0, sandstone);
        add("chiseled_sandstone", Blocks.SANDSTONE, 1, chiseledSandstone);
        add("smooth_sandstone", Blocks.SANDSTONE, 2, smoothSandstone);
        add("red_sandstone", Blocks.RED_SANDSTONE, 0, redSandstone);
        add("chiseled_red_sandstone", Blocks.RED_SANDSTONE, 1, chiseledRedSandstone);
        add("smooth_red_sandstone", Blocks.RED_SANDSTONE, 2, smoothRedSandstone);
        add("stonebrick", Blocks.STONEBRICK, 0, stoneBricks);
        add("brick", Blocks.BRICK_BLOCK, 0, bricks);
        add("quartz", Blocks.QUARTZ_BLOCK, 0, quartz);
        add("prismarine_rough", Blocks.PRISMARINE, 0, prismarine);
        add("prismarine_bricks", Blocks.PRISMARINE, 1, prismarineBricks);
        add("dark_prismarine", Blocks.PRISMARINE, 2, darkPrismarine);
        add("purpur_block", Blocks.PURPUR_BLOCK, 0, purpurBlock);
        add("end_bricks", Blocks.END_BRICKS, 0, endBricks);
    }

    @Override
    public String getDescription() {
        return "This adds walls for a lot more blocks";
    }

    public interface WallSupplier {
        BlockNeutroniaWall supply(String wallName, IBlockState state);
    }
}
