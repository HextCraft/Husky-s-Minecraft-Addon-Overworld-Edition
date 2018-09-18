package net.hdt.neutronia.init;

import net.hdt.huskylib2.block.BlockModSlab;
import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.blocks.BlockNeutroniaOre;
import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.blocks.*;
import net.hdt.neutronia.blocks.base.BlockColoredAlt;
import net.hdt.neutronia.blocks.base.BlockFalling;
import net.hdt.neutronia.blocks.base.BlockGlassBase;
import net.hdt.neutronia.blocks.base.BlockRodBase;
import net.hdt.neutronia.blocks.nether.BlockAsh;
import net.hdt.neutronia.blocks.nether.BlockBurnedBones;
import net.hdt.neutronia.blocks.nether.BlockNetherGlowingBase;
import net.hdt.neutronia.blocks.nether.BlockNetherSponge;
import net.hdt.neutronia.blocks.overworld.*;
import net.hdt.neutronia.properties.EnumGlowingNetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

import static net.hdt.neutronia.init.NCreativeTabs.*;

public class NBlocks {

    // Misc
    public static final Block blackSand;
    public static final Block brassBlock, steelBlock, copperBlock/*, bronzeBlock*/;
    public static final Block brassOre, steelOre, copperOre/*, bronzeOre, tinOre*/, zincOre;
    public static final BlockPrismarineChiseled chiseledPrismarine;
    public static final BlockPrismarineChiseled chiseledPrismarineFilled;
    //Wood Blocks
    public static final Block[] potterySpinner = new Block[6], potterySpinnerActive = new Block[6];
    public static final Block[] logPoles = new Block[6];
    public static final Block[] strippedLogPoles = new Block[6];
    //Blocks for the nether
    public static final Block[] glowingNetherBlocks = new Block[24];
    public static final BlockNetherbrickChiseled chiseledNetherbrick, chiseledNetherbrickFilled;
    public static final BlockColoredAlt[] coloredCandles = new BlockColoredAlt[16];
    public static final BlockColoredAlt[] coloredLitCandles = new BlockColoredAlt[16];
//    public static final BlockColoredAlt[] coloredLanterns = new BlockColoredAlt[16];
//    public static final BlockColoredAlt[] coloredLitLanterns = new BlockColoredAlt[16];
    public static final BlockColoredAlt[] coloredRedstoneLamp = new BlockColoredAlt[16];
    public static final BlockColoredAlt[] coloredLitRedstoneLamp = new BlockColoredAlt[16];
    public static final BlockColoredAlt[] coloredSlimeBlock = new BlockColoredAlt[16];
    public static final Block fireflyBulbOff, fireflyBulbOn;
    public static final Block sandstonePillar;
    public static final Block redSandstonePillar;
    public static final BlockPurpurChiseled chiseledPurpur;
    public static final BlockPurpurChiseled chiseledPurpurFilled;
    public static final BlockBrickChiseled chiseledBricks;
    public static final BlockBrickChiseled chiseledBricksFilled;
    public static final Block[] closet = new Block[6];
//    private static final Block smoothQuartz, smoothSandstone, smoothRedSandstone;
//    private static final Block quartzBricks, sandstoneBricks, redSandstoneBricks;
    // Sea Blocks
    private static final Block[] aquamarine = new Block[6];
    private static final Block driedKelpBlock;
    private static final Block wrautnaut, wrautnautOld, wrautnautPorthole;
    private static final Block prismarineColumn;
    private static final Block netherGlass, netherRod, netherSponge, ash, burnedBones;
    private static final Block netherbrickPillar;
    private static final Block[] centeredGlazedTerracottaBlocks = new Block[16];
    public static Block seaPickle, turtleEgg;
    public static Block[] netherPlants = new Block[3];
    public static Block[] tallNetherPlants = new Block[2];
    //Frosted versions of some blocks
    public static Block[] frostedStones = new Block[6];
    public static Block[] frostedDirts = new Block[12];
    public static Block[] frostedClay = new Block[16];
//    public static final Block stoneAnvil, carbonAnvil, goldenAnvil, marbleAnvil, ironAnvil, darkIronAnvil;
//    public static final Block stoneCauldron, carbonCauldron, goldenCauldron, marbleCauldron, ironCauldron, glassCauldron;
//    public static final Block whiteBricks, redBricks, greenBricks;
//    public static final Block redClayBlock, greenClayBlock;
    // Some colored blocks
    public static Block[] coloredSand = new Block[16];
    public static Block[] coloredSandstone = new Block[16];
    public static Block[] coloredPlanksStair = new Block[16];
    public static Block[] coloredVases = new Block[16];
    public static Block[] terracottaPots = new Block[16];
    private static Block[] coffins = new Block[13];
    private static Block slumpedWitherSkeleton, slumpedSkeleton;
    private static Block tombstoneBig, tombstoneBigDark, tombstoneMedium, tombstoneMediumDark, tombstoneSmall, tombstoneSmallDark;

    static {
        driedKelpBlock = new BlockOverworldBase(Material.PLANTS, "dried_kelp_block", false).setCreativeTab(OCEAN_EXPANSION_TAB);
        wrautnaut = new BlockOverworldBase(Material.IRON, "wrautnaut", false).setCreativeTab(OCEAN_EXPANSION_TAB);
        wrautnautOld = new BlockOverworldBase(Material.IRON, "old_wrautnaut", false).setCreativeTab(OCEAN_EXPANSION_TAB);
        wrautnautPorthole = new BlockOverworldBase(Material.IRON, "wrautnaut_porthole", false).setCreativeTab(OCEAN_EXPANSION_TAB);
        prismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "prismarine_column").setCreativeTab(OCEAN_EXPANSION_TAB);
        chiseledPrismarine = new BlockPrismarineChiseled("chiseled_prismarine", false);
        chiseledPrismarineFilled = new BlockPrismarineChiseled("chiseled_prismarine_filled", true);

        // Nether Blocks
        netherGlass = new BlockGlassBase("nether_glass").setCreativeTab(NETHER_EXPANSION_TAB);
        netherRod = new BlockRodBase("nether_rod", NETHER_EXPANSION_TAB, true);
        netherSponge = new BlockNetherSponge();
        burnedBones = new BlockBurnedBones();
        ash = new BlockAsh();

        for (EnumGlowingNetherBlocks enumGlowingNetherBlocks : EnumGlowingNetherBlocks.values()) {
            glowingNetherBlocks[enumGlowingNetherBlocks.getMetadata()] = new BlockNetherGlowingBase(Material.GLASS, enumGlowingNetherBlocks.getName());
        }

        netherbrickPillar = new BlockNeutroniaPillar(Material.ROCK, "netherbrick_pillar").setCreativeTab(NETHER_EXPANSION_TAB);
        chiseledNetherbrick = new BlockNetherbrickChiseled("chiseled_netherbrick", false);
        chiseledNetherbrickFilled = new BlockNetherbrickChiseled("chiseled_netherbrick_filled", true);

        //Wood Blocks
        /*for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
//            potterySpinner[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine", enumType.getName()), false).setCreativeTab(WOOD_EXPANSION_TAB);
//            potterySpinnerActive[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine_active", enumType.getName()), true).setCreativeTab(null);
        }*/

        // Frosted versions of vanilla stones & dirt
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            frostedClay[dyeColor.getMetadata()] = new BlockOverworldBase(Material.ROCK, String.format("frozen_%s_terracotta", dyeColor.getName()), false);
//            coloredVases[dyeColor.getMetadata()] = new BlockColoredVase(EnumDyeColor.byMetadata(dyeColor.getMetadata()));
//            terracottaPots[dyeColor.getMetadata()] = new BlockTerracottaFlowerPot(EnumDyeColor.byMetadata(dyeColor.getMetadata()));
//            glazedTerracottaPillar[dyeColor.getMetadata()] = new BlockModPillar(String.format("%s_glazed_terracotta_pillar", dyeColor.getName()), Material.ROCK);
//            terracottaPillar[dyeColor.getMetadata()] = new BlockModPillar(String.format("%s_terracotta_pillar", dyeColor.getName()), Material.ROCK);
            add(String.format("frozen_%s_terracotta", dyeColor.getName()), frostedClay[dyeColor.getMetadata()], Material.ROCK, 0, true, false, OVERWORLD_EXPANSION_TAB);
            add(String.format("%s_terracotta", dyeColor.getName()), Blocks.STAINED_HARDENED_CLAY, Material.ROCK, dyeColor.getMetadata(), true, false, OVERWORLD_EXPANSION_TAB);
            add(String.format("%s_glazed_terracotta", dyeColor.getName()), Objects.requireNonNull(Block.getBlockFromName(String.format("minecraft:%s_glazed_terracotta", dyeColor.getName()))), Material.ROCK, dyeColor.getMetadata(), true, false, OVERWORLD_EXPANSION_TAB);
            add(String.format("%s_terracotta", dyeColor.getName()), Blocks.STAINED_HARDENED_CLAY, Material.ROCK, dyeColor.getMetadata(), false, true, OVERWORLD_EXPANSION_TAB);
            coloredCandles[dyeColor.getMetadata()] = new BlockColoredCandles(dyeColor, false);
            coloredLitCandles[dyeColor.getMetadata()] = new BlockColoredCandles(dyeColor, true);
//            coloredLanterns[dyeColor.getMetadata()] = new BlockColoredLanterns(dyeColor, false);
//            coloredLitLanterns[dyeColor.getMetadata()] = new BlockColoredLanterns(dyeColor, true);
            coloredRedstoneLamp[dyeColor.getMetadata()] = new BlockColoredRedstoneLamp(dyeColor, false);
            coloredLitRedstoneLamp[dyeColor.getMetadata()] = new BlockColoredRedstoneLamp(dyeColor, true);
            coloredSlimeBlock[dyeColor.getMetadata()] = new BlockColoredSlime(dyeColor);
            add(String.format("%s_glass", dyeColor.getName()), Blocks.STAINED_GLASS, Material.GLASS, dyeColor.getMetadata(), true, false, NCreativeTabs.OVERWORLD_EXPANSION_TAB);
//            centeredGlazedTerracottaBlocks[dyeColor.getMetadata()] = new BlockOverworldBase(Material.ROCK, String.format("centered_glazed_terracotta_%s", dyeColor.getName()), false);
        }

        //Misc
        fireflyBulbOff = new BlockFireflyBulb(false);
        fireflyBulbOn = new BlockFireflyBulb(true);

        /*smoothQuartz = new BlockOverworldBase(Material.ROCK, "smooth_quartz", false);
        smoothRedSandstone = new BlockOverworldBase(Material.ROCK, "smooth_red_sandstone", false);
        smoothSandstone = new BlockOverworldBase(Material.ROCK, "smooth_sandstone", false);

        quartzBricks = new BlockOverworldBase(Material.ROCK, "quartz_bricks", false);
        redSandstoneBricks = new BlockOverworldBase(Material.ROCK, "red_sandstone_bricks", false);
        sandstoneBricks = new BlockOverworldBase(Material.ROCK, "sandstone_bricks", false);

        add("smooth_quartz", smoothQuartz, Material.ROCK, 0, true, false, NCreativeTabs.OVERWORLD_EXPANSION_TAB);
        add("smooth_red_sandstone", smoothRedSandstone, Material.ROCK, 0, true, false, OVERWORLD_EXPANSION_TAB);
        add("smooth_sandstone", smoothSandstone, Material.ROCK, 0, true, false, OVERWORLD_EXPANSION_TAB);

        add("quartz_bricks", quartzBricks, Material.ROCK, 0, true, false, OVERWORLD_EXPANSION_TAB);
        add("red_sandstone_bricks", redSandstoneBricks, Material.ROCK, 0, true, false, OVERWORLD_EXPANSION_TAB);
        add("sandstone_bricks", sandstoneBricks, Material.ROCK, 0, true, false, OVERWORLD_EXPANSION_TAB);

        add("smooth_quartz", smoothQuartz, Material.ROCK, 0, false, true, OVERWORLD_EXPANSION_TAB);
        add("smooth_red_sandstone", smoothRedSandstone, Material.ROCK, 0, false, true, OVERWORLD_EXPANSION_TAB);
        add("smooth_sandstone", smoothSandstone, Material.ROCK, 0, false, true, OVERWORLD_EXPANSION_TAB);

        add("quartz_bricks", quartzBricks, Material.ROCK, 0, false, true, OVERWORLD_EXPANSION_TAB);
        add("red_sandstone_bricks", redSandstoneBricks, Material.ROCK, 0, false, true, OVERWORLD_EXPANSION_TAB);
        add("sandstone_bricks", sandstoneBricks, Material.ROCK, 0, false, true, OVERWORLD_EXPANSION_TAB);

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(smoothSandstone, 8),
                "SSS", "S S", "SSS",
                'S', ProxyRegistry.newStack(Blocks.SANDSTONE));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(sandstoneBricks, 4),
                "SS", "SS",
                'S', ProxyRegistry.newStack(smoothSandstone, 1));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(smoothRedSandstone, 8),
                "SSS", "S S", "SSS",
                'S', ProxyRegistry.newStack(Blocks.RED_SANDSTONE));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(redSandstoneBricks, 4),
                "SS", "SS",
                'S', ProxyRegistry.newStack(smoothRedSandstone, 1));*/

        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Blocks.SAND, 4),
                ProxyRegistry.newStack(Blocks.SANDSTONE, 1));

        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Blocks.SAND, 4),
                ProxyRegistry.newStack(Blocks.SANDSTONE, 1));

        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND).getBlock(), 4),
                ProxyRegistry.newStack(Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.CHISELED).getBlock(), 1));

//        stoneAnvil = new BlockModAnvil("stone_anvil", OVERWORLD_EXPANSION_TAB);
//        carbonAnvil = new BlockModAnvil("carbon_anvil", OVERWORLD_EXPANSION_TAB);
//        goldenAnvil = new BlockModAnvil("golden_anvil", OVERWORLD_EXPANSION_TAB);
//        marbleAnvil = new BlockModAnvil("marble_anvil", OVERWORLD_EXPANSION_TAB);
//        ironAnvil = new BlockModAnvil("iron_anvil", OVERWORLD_EXPANSION_TAB);
//        darkIronAnvil = new BlockModAnvil("dark_iron_anvil", OVERWORLD_EXPANSION_TAB);

        blackSand = new BlockFalling("black_sand", OVERWORLD_EXPANSION_TAB);
        brassBlock = new BlockOverworldBase(Material.IRON, "brass_block", false);
        steelBlock = new BlockOverworldBase(Material.IRON, "steel_block", false);
        copperBlock = new BlockOverworldBase(Material.IRON, "copper_block", false);
//        bronzeBlock = new BlockOverworldBase(Material.IRON, "bronze_block", false);

        brassOre = new BlockNeutroniaOre("brass_ore").setIngot(NItems.brassIngot);
        steelOre = new BlockNeutroniaOre("steel_ore").setIngot(NItems.steelIngot);
        copperOre = new BlockNeutroniaOre("copper_ore").setIngot(NItems.copperIngot);
//        bronzeOre = new BlockNeutroniaOre("bronze_ore").setIngot(NItems.bronzeIngot);
//        tinOre = new BlockNeutroniaOre("tin_ore").setIngot(NItems.tinIngot);
        zincOre = new BlockNeutroniaOre("zinc_ore").setIngot(NItems.zincChunk);

        sandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "sandstone_pillar").setCreativeTab(OVERWORLD_EXPANSION_TAB);
        redSandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "red_sandstone_pillar").setCreativeTab(OVERWORLD_EXPANSION_TAB);
        chiseledPurpur = new BlockPurpurChiseled("purpur_chiseled", false);
        chiseledPurpurFilled = new BlockPurpurChiseled("purpur_chiseled_filled", true);
        chiseledBricks = new BlockBrickChiseled("chiseled_bricks", false);
        chiseledBricksFilled = new BlockBrickChiseled("chiseled_bricks_filled", true);

        for (BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
            closet[woodType.getMetadata()] = new BlockRandom(woodType);
        }

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    public static void add(String name, Block block, Material material, int meta, CreativeTabs creativeTabs) {
        add(name, block, material, meta, true, true, creativeTabs);
    }

    public static void add(String name, Block block, Material material, int meta, boolean slabs, boolean stairs, CreativeTabs creativeTabs) {
        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";

        if (stairs) {
            BlockModStairs.initStairs(block, meta, new BlockOverworldStairBase(stairsName, state, creativeTabs));
        }
        if (slabs) {
            BlockModSlab.initSlab(block, meta, new BlockOverworldSlabBase(name, material, false, creativeTabs), new BlockOverworldSlabBase(name, material, true, creativeTabs));
        }
    }

}