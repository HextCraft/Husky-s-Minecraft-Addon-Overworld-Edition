package net.hdt.neutronia.groups.building.features;

import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.neutronia.base.blocks.BlockNeutroniaDoubleSlab;
import net.hdt.neutronia.base.blocks.BlockNeutroniaSlab;
import net.hdt.neutronia.base.blocks.BlockNeutroniaStairs;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VanillaStairsAndSlabs extends Component {

    boolean stone, granite, diorite, andesite, polishedGranite, polishedDiorite, polishedAndesite, endBricks, prismarine,
            prismarineBricks, darkPrismarine, redNetherBricks, mossyStoneBrick, crackedStoneBrick, smoothSandstone,
    chiseledSandstone, cutSandstone, smoothRedSandstone, chiseledRedSandstone, cutRedSandstone, mossyCobblestone;

    public static void add(String name, Block block, int meta, boolean doit) {
        add(name, block, meta, true, true, doit);
    }

    public static void add(String name, Block block, int meta, boolean slab, boolean stairs, boolean doit) {
        if (!doit)
            return;

        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";

        if (stairs)
            BlockModStairs.initStairs(block, meta, new BlockNeutroniaStairs(stairsName, state));
        if (slab) {
            Block singleSlab;
            Block doubleSlab = Blocks.AIR;
            singleSlab = new BlockNeutroniaSlab(doubleSlab,name + "_slab", block::getDefaultState);
            doubleSlab = new BlockNeutroniaDoubleSlab( name + "_slab_double", () -> singleSlab);
        }
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
        endBricks = loadPropBool("End Bricks", "", true);
        prismarine = loadPropBool("Prismarine", "", true);
        prismarineBricks = loadPropBool("Prismarine Bricks", "", true);
        darkPrismarine = loadPropBool("Dark Prismarine", "", true);
        redNetherBricks = loadPropBool("Red Nether Brick", "", true);
        mossyStoneBrick = loadPropBool("Red Nether Brick", "", true);
        crackedStoneBrick = loadPropBool("Red Nether Brick", "", true);
        smoothSandstone = loadPropBool("Smooth Sandstone", "", true);
        chiseledSandstone = loadPropBool("Chiseled Sandstone", "", true);
        cutSandstone = loadPropBool("Cut Sandstone", "", true);
        smoothRedSandstone = loadPropBool("Smooth Red Sandstone", "", true);
        chiseledRedSandstone = loadPropBool("Chiseled Red Sandstone", "", true);
        cutRedSandstone = loadPropBool("Cut Red Sandstone", "", true);
        mossyCobblestone = loadPropBool("Mossy Cobblestone", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (!GlobalConfig.enableVariants)
            return;

        add("stone", Blocks.STONE, 0, false, true, stone);
        add("granite", Blocks.STONE, 1, granite);
        add("diorite", Blocks.STONE, 3, diorite);
        add("andesite", Blocks.STONE, 5, andesite);
        add("polished_granite", Blocks.STONE, 2, polishedGranite);
        add("polished_diorite", Blocks.STONE, 4, polishedDiorite);
        add("polished_andesite", Blocks.STONE, 6, polishedAndesite);
        add("end_bricks", Blocks.END_BRICKS, 0, endBricks);
        add("prismarine", Blocks.PRISMARINE, 0, prismarine);
        add("prismarine_bricks", Blocks.PRISMARINE, 1, prismarineBricks);
        add("dark_prismarine", Blocks.PRISMARINE, 2, darkPrismarine);
        add("red_nether_brick", Blocks.RED_NETHER_BRICK, 0, redNetherBricks);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}

