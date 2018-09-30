package net.hdt.neutronia.init;

import net.hdt.huskylib2.block.BlockModSlab;
import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.blocks.base.BlockFalling;
import net.hdt.neutronia.blocks.overworld.BlockFireflyBulb;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.hdt.neutronia.blocks.overworld.BlockOverworldStairBase;
import net.hdt.neutronia.groups.building.blocks.slab.BlockVanillaSlab;
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

import static net.hdt.neutronia.init.NCreativeTabs.OCEAN_EXPANSION_TAB;
import static net.hdt.neutronia.init.NCreativeTabs.OVERWORLD_EXPANSION_TAB;

public class NBlocks {

    // Misc
    public static final Block blackSand;
    //Wood Blocks
    public static final Block[] potterySpinner = new Block[6], potterySpinnerActive = new Block[6];
    public static final Block fireflyBulbOff, fireflyBulbOn;
    // Sea Blocks
    private static final Block[] aquamarine = new Block[6];
    private static final Block driedKelpBlock;
    private static final Block wrautnaut, wrautnautOld, wrautnautPorthole;
    private static final Block[] centeredGlazedTerracottaBlocks = new Block[16];
    public static Block seaPickle, turtleEgg;
    public static Block[] netherPlants = new Block[3];
    public static Block[] tallNetherPlants = new Block[2];
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

        //Wood Blocks
        /*for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
//            potterySpinner[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine", enumType.getName()), false).setCreativeTab(WOOD_EXPANSION_TAB);
//            potterySpinnerActive[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine_active", enumType.getName()), true).setCreativeTab(null);
        }*/

        // Frosted versions of vanilla stones & dirt
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
//            coloredVases[dyeColor.getMetadata()] = new BlockColoredVase(EnumDyeColor.byMetadata(dyeColor.getMetadata()));
//            terracottaPots[dyeColor.getMetadata()] = new BlockTerracottaFlowerPot(EnumDyeColor.byMetadata(dyeColor.getMetadata()));
            add(String.format("%s_terracotta", dyeColor.getName()), Blocks.STAINED_HARDENED_CLAY, dyeColor.getMetadata(), true, true, OVERWORLD_EXPANSION_TAB);
            add(String.format("%s_glazed_terracotta", dyeColor.getName()), Objects.requireNonNull(Block.getBlockFromName(String.format("minecraft:%s_glazed_terracotta", dyeColor.getName()))), dyeColor.getMetadata(), true, false, OVERWORLD_EXPANSION_TAB);
            add(String.format("%s_glass", dyeColor.getName()), Blocks.STAINED_GLASS, dyeColor.getMetadata(), true, false, NCreativeTabs.OVERWORLD_EXPANSION_TAB);
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

        for (BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
//            closet[woodType.getMetadata()] = new BlockRandom(woodType);
        }

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    private static void add(String name, Block block, int meta, boolean slabs, boolean stairs, CreativeTabs creativeTabs) {
        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";

        if (stairs) {
            BlockModStairs.initStairs(block, meta, new BlockOverworldStairBase(stairsName, state, creativeTabs));
        }
        if (slabs) {
            BlockModSlab.initSlab(block, meta, (BlockModSlab) new BlockVanillaSlab(name, block.getDefaultState(), false).setCreativeTab(creativeTabs), (BlockModSlab) new BlockVanillaSlab(name, block.getDefaultState(), true).setCreativeTab(creativeTabs));
        }
    }

}