package net.hdt.neutronia.init;

import net.hdt.huskylib2.blocks.BlockModStairs;
import net.hdt.neutronia.Main;
import net.hdt.neutronia.blocks.base.*;
import net.hdt.neutronia.blocks.nether.BlockNetherBase;
import net.hdt.neutronia.blocks.nether.BlockNetherGlowingBase;
import net.hdt.neutronia.blocks.nether.BlockNetherSponge;
import net.hdt.neutronia.blocks.nether.BlockSoulStone;
import net.hdt.neutronia.blocks.overworld.*;
import net.hdt.neutronia.properties.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.hdt.neutronia.util.Reference.MOD_ID;

public class NBlocks {

    public static final Block netherGlass, soulGlass, netherRod, netherSponge;

    // Misc blocks
    public static final Block stoneAnvil, carbonAnvil, goldenAnvil, marbleAnvil, ironAnvil;
//    public static final Block stoneCauldron, carbonCauldron, goldenCauldron, marbleCauldron, ironCauldron, glassCauldron;
//    public static final Block whiteBricks, redBricks, greenBricks;
//    public static final Block redClayBlock, greenClayBlock;

    // Sea Blocks
    public static Block[] brainCoral = new Block[5];
    public static Block deadBrainCoral;
    public static Block[] normalCoral = new Block[5];
    public static Block[] deadNormalCoral = new Block[5];
    public static Block[] coralFan = new Block[5];
    public static Block[] deadCoralFan = new Block[5];
    public static Block[] coralPlant = new Block[5];
    public static Block[] deadCoralPlant = new Block[5];
    public static Block[] pipeCoral = new Block[5];
    public static Block[] deadPipeCoral = new Block[5];
    public static Block[] seaFan = new Block[5];
    public static Block[] deadSeaFan = new Block[5];
    public static Block[] naturalAquamarine = new Block[13];
    public static Block[] aquamarine = new Block[6];
    public static Block kelp, driedKelpBlock;

    //Stone Blocks
    public static Block[] newStoneVariants = new Block[23];

    //Wood Blocks
    public static Block[] strippedLogs = new Block[6], strippedBarkBlocks = new Block[6];
    public static Block[] potterySpinner = new Block[6], potterySpinnerActive = new Block[6];
    public static Block[] barkBlocks = new Block[6], chiseledBarkBlocks = new Block[6], unnamedChiseledBarkBlock = new Block[6];
    public static Block[] logPoles = new Block[6], strippedLogPoles = new Block[6], plankPoles = new Block[6];
    public static Block[] logDowels = new Block[6], strippedLogDowels = new Block[6], plankDowels = new Block[6];
    public static Block[] barkButtons = new Block[6], plankButtons = new Block[6];
    public static Block[] barkPressurePlates = new Block[6], plankPressurePlates = new Block[6];

    //Blocks for the nether
    public static Block[] netherBlocks = new Block[2];
    public static Block[] glowingNetherBlocks = new Block[24];
    public static Block[] soulStone = new Block[4];
    public static Block[] netherPlants = new Block[3];
    public static Block[] tallNetherPlants = new Block[2];

    //Frosted versions of some blocks
    public static Block[] frostedStones = new Block[6];
    public static Block[] frostedDirts = new Block[12];
    public static Block[] frostedClay = new Block[16];

    // Some colored blocks
    public static Block[] coloredSand = new Block[16];
    public static Block[] coloredSandstone = new Block[16];
    public static BlockColoredAlt[] coloredCandles = new BlockColoredAlt[16];
    public static BlockColoredAlt[] coloredLitCandles = new BlockColoredAlt[16];
    public static BlockColoredAlt[] coloredLanterns = new BlockColoredAlt[16];
    public static BlockColoredAlt[] coloredLitLanterns = new BlockColoredAlt[16];
    public static BlockColoredAlt[] coloredRedstoneLamp = new BlockColoredAlt[16];
    public static BlockColoredAlt[] coloredLitRedstoneLamp = new BlockColoredAlt[16];
    public static Block[] coloredVases = new Block[16];
    public static Block[] terracottaPots = new Block[16];

    public static final Block blackSand;

    private static Block[] coffins = new Block[13];
    private static Block slumpedWitherSkeleton, slumpedSkeleton;
    private static Block tombstoneBig, tombstoneBigDark, tombstoneMedium, tombstoneMediumDark, tombstoneSmall, tombstoneSmallDark;
//    public static final Block smoothQuartz, smoothSandstone, smoothStone, smoothRedSandstone;

    static {

        for (EnumCoralColor coralColor : EnumCoralColor.values()) {
            brainCoral[coralColor.getMetadata()] = new BlockColoredWaterBlockBase(coralColor, "brain_coral");
            normalCoral[coralColor.getMetadata()] = new BlockColoredWaterBlockBase(coralColor, "coral");
            deadNormalCoral[coralColor.getMetadata()] = new BlockColoredWaterBlockBase(coralColor, "dead_coral");
            coralFan[coralColor.getMetadata()] = new BlockColoredWaterPlantBase(coralColor, "coral_fan");
            /*deadCoralFan[coralColor.getMetadata()] = new BlockColoredWaterPlantBase(coralColor, "dead_coral_fan");
            coralPlant[coralColor.getMetadata()] = new BlockColoredWaterPlantBase(coralColor, "coral_plant");
            deadCoralPlant[coralColor.getMetadata()] = new BlockColoredWaterPlantBase(coralColor, "dead_coral_plant");*/
            /*pipeCoral[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "pipe_coral");
            deadPipeCoral[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "dead_pipe_coral");
            seaFan[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "sea_fan");
            deadSeaFan[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "dead_sea_fan");*/
        }
        deadBrainCoral = new BlockWaterBlockBase("dead_brain_coral");

        for (EnumAquamarineVariants aquamarineVariants : EnumAquamarineVariants.values()) {
            aquamarine[aquamarineVariants.ordinal()] = new BlockOverworldBase(Material.ROCK, aquamarineVariants.getName(), false);
            add(aquamarineVariants.getName(), aquamarine[aquamarineVariants.getID()], 0, true, false, Main.OVERWORLD_EXPANSION_TAB);
        }
        for (EnumNaturalAquamarineVariants naturalAquamarineVariants : EnumNaturalAquamarineVariants.values()) {
            naturalAquamarine[naturalAquamarineVariants.getID()] = new BlockOverworldBase(Material.ROCK, naturalAquamarineVariants.getName(), false);
        }
//        driedKelpBlock = new BlockOverworldBase(Material.PLANTS, "dried_kelp_block");
//        kelp = new BlockKelp();

        blackSand = new BlockFalling("black_sand");

        // Nether Blocks
        for (EnumNetherBlocks netherBlockTypes : EnumNetherBlocks.values()) {
            netherBlocks[netherBlockTypes.getMetadata()] = new BlockNetherBase(Material.ROCK, netherBlockTypes.getName());
        }
        for (EnumSoulStoneTypes soulStoneTypes : EnumSoulStoneTypes.values()) {
            soulStone[soulStoneTypes.getMetadata()] = new BlockSoulStone(soulStoneTypes.getName());
            add(soulStoneTypes.getName(), soulStone[soulStoneTypes.getMetadata()], 0, true, false, Main.NETHER_EXPANSION_TAB);
        }
        for (EnumGlowingNetherBlocks enumGlowingNetherBlocks : EnumGlowingNetherBlocks.values()) {
            glowingNetherBlocks[enumGlowingNetherBlocks.getMetadata()] = new BlockNetherGlowingBase(Material.GLASS, enumGlowingNetherBlocks.getName());
        }
        /*for (EnumNetherPlantTypes netherPlantTypes : EnumNetherPlantTypes.values()) {
            netherPlants[netherPlantTypes.getID()] = new BlockNetherPlantBase(netherPlantTypes.getName());
        }
        for (EnumTallNetherPlantTypes netherPlantTypes : EnumTallNetherPlantTypes.values()) {
            tallNetherPlants[netherPlantTypes.getID()] = new BlockNetherDoublePlantBase(netherPlantTypes.getName());
        }*/
        netherGlass = new BlockGlassBase("nether_glass").setCreativeTab(Main.NETHER_EXPANSION_TAB);
        soulGlass = new BlockGlassBase("soul_glass").setCreativeTab(Main.NETHER_EXPANSION_TAB);
        netherRod = new BlockRodBase("nether_rod", Main.NETHER_EXPANSION_TAB, true);
        netherSponge = new BlockNetherSponge();

        // New Stone Blocks
        for (EnumNewStoneVariants newStoneVariant : EnumNewStoneVariants.values()) {
            newStoneVariants[newStoneVariant.getMetadata()] = new BlockOverworldBase(Material.ROCK, newStoneVariant.getName(), true);
            add(newStoneVariant.getName(), newStoneVariants[newStoneVariant.getMetadata()], 0, true, false, Main.OVERWORLD_EXPANSION_TAB);
        }

        //Wood Blocks
        for(BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
            strippedLogs[enumType.getMetadata()] = new BlockModPillar(String.format("stripped_%s_log", enumType.getName()), Material.WOOD);
            strippedBarkBlocks[enumType.getMetadata()] = new BlockOverworldBase(Material.WOOD, String.format("stripped_%s_bark", enumType.getName()), true);
//            potterySpinner[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine", enumType.getName()), false).setCreativeTab(Main.OVERWORLD_EXPANSION_TAB);
//            potterySpinnerActive[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine_active", enumType.getName()), true).setCreativeTab(null);
            barkBlocks[enumType.getMetadata()] = new BlockOverworldBase(Material.WOOD, String.format("%s_bark", enumType.getName()), true);
            chiseledBarkBlocks[enumType.getMetadata()] = new BlockOverworldBase(Material.WOOD, String.format("%s_bark_chiseled", enumType.getName()), true);
            unnamedChiseledBarkBlock[enumType.getMetadata()] = new BlockOverworldBase(Material.WOOD, String.format("unnamed_%s_bark_chiseled", enumType.getName()), true);
//            add(String.format("stripped_%s_log", enumType.getName()),strippedLogs[enumType.getMetadata()], 0, true, true, Main.OVERWORLD_EXPANSION_TAB);
//            coffins[enumType.getMetadata()] = new BlockOverworldBase(Material.WOOD, enumType.getName() + "_coffin");
//            logDowels[enumType.getMetadata()] = new BlockRodBase(String.format("%s_log_dowel", enumType.getName()), Main.OVERWORLD_EXPANSION_TAB, false);
//            strippedLogDowels[enumType.getMetadata()] = new BlockRodBase(String.format("stripped_%s_log_dowel", enumType.getName()), Main.OVERWORLD_EXPANSION_TAB, false);
//            plankDowels[enumType.getMetadata()] = new BlockRodBase(String.format("%s_plank_dowel", enumType.getName()), Main.OVERWORLD_EXPANSION_TAB, false);
            logPoles[enumType.getMetadata()] = new BlockRodBase(String.format("%s_log_pole", enumType.getName()), Main.OVERWORLD_EXPANSION_TAB, false);
            strippedLogPoles[enumType.getMetadata()] = new BlockRodBase(String.format("stripped_%s_log_pole", enumType.getName()), Main.OVERWORLD_EXPANSION_TAB, false);
            plankPoles[enumType.getMetadata()] = new BlockRodBase(String.format("%s_plank_pole", enumType.getName()), Main.OVERWORLD_EXPANSION_TAB, false);
            addWall(String.format("%s_planks", enumType.getName()), Blocks.PLANKS, enumType.getMetadata());
        }
        addWall("oak_bark", Blocks.LOG, 0);
        addWall("spruce_bark", Blocks.LOG, 1);
        addWall("birch_bark", Blocks.LOG, 2);
        addWall("jungle_bark", Blocks.LOG, 3);
        addWall("acacia_bark", Blocks.LOG2, 0);
        addWall("dark_oak_bark", Blocks.LOG2, 1);
        addFenceAndFenceGate(BlockPlanks.EnumType.OAK, "oak_bark", Blocks.LOG, 0, true, true, Main.OVERWORLD_EXPANSION_TAB);
        addFenceAndFenceGate(BlockPlanks.EnumType.SPRUCE, "spruce_bark", Blocks.LOG, 1, true, true, Main.OVERWORLD_EXPANSION_TAB);
        addFenceAndFenceGate(BlockPlanks.EnumType.BIRCH, "birch_bark", Blocks.LOG, 2, true, true, Main.OVERWORLD_EXPANSION_TAB);
        addFenceAndFenceGate(BlockPlanks.EnumType.JUNGLE, "jungle_bark", Blocks.LOG, 3, true, true, Main.OVERWORLD_EXPANSION_TAB);
        addFenceAndFenceGate(BlockPlanks.EnumType.ACACIA, "acacia_bark", Blocks.LOG2, 0, true, true, Main.OVERWORLD_EXPANSION_TAB);
        addFenceAndFenceGate(BlockPlanks.EnumType.DARK_OAK, "dark_oak_bark", Blocks.LOG2, 1, true, true, Main.OVERWORLD_EXPANSION_TAB);

        // Frosted versions of vanilla stones & dirt
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            frostedClay[dyeColor.getMetadata()] = new BlockOverworldBase(Material.ROCK, String.format("frozen_%s_terracotta", dyeColor.getName()), false);
            add(String.format("frozen_%s_terracotta", dyeColor.getName()), frostedClay[dyeColor.getMetadata()], 0, true, false, Main.OVERWORLD_EXPANSION_TAB);
//            coloredVases[dyeColor.getMetadata()] = new BlockColoredVase(EnumDyeColor.byMetadata(dyeColor.getMetadata()));
//            terracottaPots[dyeColor.getMetadata()] = new BlockColoredFlowerPot(EnumDyeColor.byMetadata(dyeColor.getMetadata()));
//            glazedTerracottaPillar[dyeColor.getMetadata()] = new BlockModPillar(String.format("%s_glazed_terracotta_pillar", dyeColor.getName()), Material.ROCK);
//            terracottaPillar[dyeColor.getMetadata()] = new BlockModPillar(String.format("%s_terracotta_pillar", dyeColor.getName()), Material.ROCK);
            add(String.format("%s_terracotta", dyeColor.getName()), Blocks.STAINED_HARDENED_CLAY, dyeColor.getMetadata(), true, true, Main.OVERWORLD_EXPANSION_TAB);
            add(String.format("%s_glazed_terracotta", dyeColor.getName()), Block.getBlockFromName(String.format("minecraft:%s_glazed_terracotta", dyeColor.getName())), dyeColor.getMetadata(), true, false, Main.OVERWORLD_EXPANSION_TAB);
//            coloredCandles[dyeColor.getMetadata()] = new BlockColoredCandles(dyeColor, false);
//            coloredLitCandles[dyeColor.getMetadata()] = new BlockColoredCandles(dyeColor, true);
//            coloredLanterns[dyeColor.getMetadata()] = new BlockColoredLanterns(dyeColor, false);
//            coloredLitLanterns[dyeColor.getMetadata()] = new BlockColoredLanterns(dyeColor, true);
//            coloredRedstoneLamp[dyeColor.getMetadata()] = new BlockColoredRedstoneLamp(dyeColor, false);
//            coloredLitRedstoneLamp[dyeColor.getMetadata()] = new BlockColoredRedstoneLamp(dyeColor, true);
        }

        //Misc
//        whiteBricks = new BlockOverworldBase(Material.ROCK, "white_bricks");
//        redBricks = new BlockOverworldBase(Material.ROCK, "red_bricks");
//        greenBricks = new BlockOverworldBase(Material.ROCK, "green_bricks");
//        redClayBlock = new BlockOverworldBase(Material.CLAY, "red_clay_block");
//        greenClayBlock = new BlockOverworldBase(Material.CLAY, "green_clay_block");

        add("stone", Blocks.STONE, 0, false, true, Main.OVERWORLD_EXPANSION_TAB);
        add("stone_granite", Blocks.STONE, 1, true, true, Main.OVERWORLD_EXPANSION_TAB);
        add("stone_diorite", Blocks.STONE, 3, true, true, Main.OVERWORLD_EXPANSION_TAB);
        add("stone_andesite", Blocks.STONE, 5, true, true, Main.OVERWORLD_EXPANSION_TAB);
        add("andesite_bricks", NBlocks.newStoneVariants[0], 0, false, true, Main.OVERWORLD_EXPANSION_TAB);
        add("andesite_cobble", NBlocks.newStoneVariants[1], 0, false, true, Main.OVERWORLD_EXPANSION_TAB);
        add("diorite_bricks", NBlocks.newStoneVariants[8], 0, false, true, Main.OVERWORLD_EXPANSION_TAB);
        add("diorite_cobble", NBlocks.newStoneVariants[9], 0, false, true, Main.OVERWORLD_EXPANSION_TAB);
        add("granite_bricks", NBlocks.newStoneVariants[11], 0, false, true, Main.OVERWORLD_EXPANSION_TAB);
        add("granite_cobble", NBlocks.newStoneVariants[12], 0, false, true, Main.OVERWORLD_EXPANSION_TAB);
        add("end_bricks", Blocks.END_BRICKS, 0, true, true, Main.END_EXPANSION_TAB);
        add("prismarine", Blocks.PRISMARINE, 0, true, true, Main.OVERWORLD_EXPANSION_TAB);
        add("prismarine_bricks", Blocks.PRISMARINE, 1, true, true, Main.OVERWORLD_EXPANSION_TAB);
        add("prismarine_dark", Blocks.PRISMARINE, 2, true, true, Main.OVERWORLD_EXPANSION_TAB);
        add("red_nether_brick", Blocks.RED_NETHER_BRICK, 0, true, true, Main.NETHER_EXPANSION_TAB);

//        addWall("sandstone", Blocks.SANDSTONE, 0);
//        addWall("chiseled_sandstone", Blocks.SANDSTONE, 1);
//        addWall("smooth_sandstone", Blocks.SANDSTONE, 2);
//        addWall("red_sandstone", Blocks.RED_SANDSTONE, 0);
//        addWall("chiseled_red_sandstone", Blocks.RED_SANDSTONE, 1);
//        addWall("smooth_red_sandstone", Blocks.RED_SANDSTONE, 2);

        stoneAnvil = new BlockModAnvil("stone_anvil", Main.OVERWORLD_EXPANSION_TAB);
        carbonAnvil = new BlockModAnvil("carbon_anvil", Main.OVERWORLD_EXPANSION_TAB);
        goldenAnvil = new BlockModAnvil("golden_anvil", Main.OVERWORLD_EXPANSION_TAB);
        marbleAnvil = new BlockModAnvil("marble_anvil", Main.OVERWORLD_EXPANSION_TAB);
        ironAnvil = new BlockModAnvil("iron_anvil", Main.OVERWORLD_EXPANSION_TAB);
//
//        stoneCauldron = new BlockModCauldron("stone_cauldron", Main.OVERWORLD_EXPANSION_TAB);
//        carbonCauldron = new BlockModCauldron("carbon_cauldron", Main.OVERWORLD_EXPANSION_TAB);
//        goldenCauldron = new BlockModCauldron("golden_cauldron", Main.OVERWORLD_EXPANSION_TAB);
//        marbleCauldron = new BlockModCauldron("marble_cauldron", Main.OVERWORLD_EXPANSION_TAB);
//        ironCauldron = new BlockModCauldron("iron_cauldron", Main.OVERWORLD_EXPANSION_TAB);
//        glassCauldron = new BlockModCauldron("glass_cauldron", Main.OVERWORLD_EXPANSION_TAB);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    public static void add(String name, Block block, int meta, CreativeTabs creativeTabs) {
        add(name, block, meta, true, true, creativeTabs);
    }

    public static void add(String name, Block block, int meta, boolean slabs, boolean stairs, CreativeTabs creativeTabs) {
        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";
        String slabName = name + "_slab";

        if (stairs) {
            BlockModStairs.initStairs(block, meta, new BlockOverworldStairBase(stairsName, state, creativeTabs));
        }
        if (slabs) {
            BlockModSlab.initSlab(block, meta, new BlockOverworldSlabBase(slabName, block.getMaterial(state), false, creativeTabs), new BlockOverworldSlabBase(slabName, block.getMaterial(state), true, creativeTabs));
        }
    }

    public static void addWithColoredName(String name, Block block, int meta, CreativeTabs creativeTabs, EnumDyeColor color) {
        addWithColoredName(name, block, meta, true, true, creativeTabs, color);
    }

    public static void addWithColoredName(String name, Block block, int meta, boolean slabs, boolean stairs, CreativeTabs creativeTabs, EnumDyeColor color) {
        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";
        String slabName = name + "_slab";

        if (stairs) {
            BlockModStairs.initStairs(block, meta, new BlockOverworldStairBaseColoredName(stairsName, state, creativeTabs, color));
        }
        if (slabs) {
            BlockModSlab.initSlab(block, meta, new BlockOverworldSlabBaseColoredName(slabName, block.getMaterial(state), false, creativeTabs, color), new BlockOverworldSlabBaseColoredName(slabName, block.getMaterial(state), true, creativeTabs, color));
        }
    }

    public static void addWall(String name, Block block, int meta) {
        addWall(name, block, meta, BlockModWall::new);
    }

    public static void addWall(String name, Block block, int meta, WallSupplier supplier) {
        IBlockState state = block.getStateFromMeta(meta);
        String wallName = name + "_wall";
        BlockModWall.initWall(block, meta, supplier.supply(wallName, state));
    }

    public static void addFenceAndFenceGate(BlockPlanks.EnumType woodType, String name, Block block, int meta, boolean fence, boolean gate, CreativeTabs creativeTabs) {
        IBlockState state = block.getStateFromMeta(meta);

        if (fence) {
            new BlockModFence(block.getMaterial(state), MOD_ID, name + "_fence").setCreativeTab(creativeTabs);
        }
        if (gate) {
            new BlockModFenceGate(woodType, name + "_fence_gate").setCreativeTab(creativeTabs);
        }
    }

    public interface WallSupplier {
        BlockModWall supply(String wallName, IBlockState state);
    }

}
