package team.hdt.neutronia.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.blocks.BlockNeutroniaDoor;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.blocks.BlockNeutroniaTrapdoor;
import team.hdt.neutronia.blocks.*;
import team.hdt.neutronia.properties.EnumGTPVariants;
import team.hdt.neutronia.properties.SoulStoneVariants;
import team.hdt.neutronia.properties.VanillaWoodTypes;

public class NBlocks {

    public static Block[] bookshelfs = new Block[5], patternedPlanks = new Block[6], carvedPlanks = new Block[6],
            barrels = new Block[6], fluidBarrels = new Block[6], woodLanterns = new Block[6], ladder = new Block[5],
            gtp = new Block[13], soulStone = new Block[4];
    public static BlockChiseled chiseledPrismarine, chiseledNetherbrick, chiseledPurpur, chiseledBricks, chiseledEndStoneBrick, chiseledRedNetherBrick;
    public static BlockDarkPrismarineChiseled chiseledDarkPrismarine;
    public static Block chiseledPrismarineBricks, cutPrismarine, cutPrismarineBricks, engravedPrismarine, engravedPrismarineBricks;
    public static Block obsidianBricks, obsidianCobble, obsidianTiles;
    public static Block smoothEndBrick, smoothPrismarineBrick, smoothPrismarine, smoothObsidian, smoothBrick, smoothPurpurBlock, smoothNetherBrick, smoothRedNetherBrick, smoothStone, smoothStoneBrick;
    public static BlockNeutroniaPillar diamondBrickPillar, emeraldBrickPillar, ironBrickPillar, goldBrickPillar;
    public static Block diamondBricks, emeraldBricks, ironBricks, goldBricks;
    public static Block sandstonePillar, redSandstonePillar, stonePillar, polishedAndesitePillar, polishedGranitePillar, polishedDioritePillar,
            stoneBrickPillar, prismarineColumn, prismarinePillar, prismarineBrickPillar, redNetherBrickPillar, endStoneBrickPillar,
            brickPillar, darkPrismarineColumn;
    public static Block dirtyBricks, crackedBricks, mossyBricks, brickPath, kitchenTiles, smallKitchenTiles;
    public static Block stoneTile, smallStoneTile, andesiteTile, smallAndesiteTile, dioriteTile, smallDioriteTile, graniteTile, smallGraniteTile,
            stoneBrickTile, smallStoneBrickTile, brickTile, smallBrickTile;
    public static Block ropeCoil;
    public static Block darkAndesite, darkAndesiteBricks, polishedDarkAndesite;
    public static Block mud, mudBricks, driedMud, driedMudBricks;
    public static Block packedIceBricks, packedIcePillar, smallSnowBricks, snowBricks, iceTiles, iceRod, iceDoor, iceTrapdoor;
    public static Block chiseledBrick;
    public static Block frosted_glass;
    public static Block frosted_glass_pane;
    public static Block ironLantern, goldLantern, prismarineLantern, ironChain, goldChain, prismarineChain;
    public static BlockLanternRedstone redstoneIronLantern, redstoneIronLanternOff, redstoneGoldLantern, redstoneGoldLanternOff;
    public static Block stickBundle, chorusBundle, sugarCaneBundle, bambooBundle, netherWartSack, cocoaBeanSack, gunpowderSack,
                        eggCrate, beetrootCrate, potatoCrate, carrotCrate, appleCrate, goldenAppleCrate, cactusBundle;
    public static Block BAMBOO_SAPLING;
    public static Block BAMBOO;
    public static Block BLAST_FURNACE;
    public static Block LIT_BLAST_FURNACE;
    public static Block SMOKER;
    public static Block LIT_SMOKER;

    public static Block SWEET_BERRY_BUSH;
    public static Block GOOSEBERRY_BUSH;
    public static Block BLUEBERRY_BUSH;
    public static Block RED_GRAPE_BUSH;
    public static Block GREEN_GRAPE_BUSH;
    public static Block BLACKBERRY_BUSH;
    public static Block POISON_BERRY_BUSH;
    public static Block WITHER_BERRY_BUSH;
    public static Block RASPBERRY_BUSH;
    public static Block SOUR_BERRY_BUSH;
    public static Block CURRANT_BUSH;

    public static void init() {
        for (VanillaWoodTypes woodType : VanillaWoodTypes.values()) {
            bookshelfs[woodType.getMetadata()] = new BlockNeutroniaBase(String.format("%s_bookshelf", woodType.getName()), Material.WOOD);
//            chests[woodType.getMetadata()] = new BlockCustomChest(String.format("%s_chest", woodType.getName()), BlockChest.Type.BASIC);
//            trappedChests[woodType.getMetadata()] = new BlockCustomChest(String.format("trapped_%s_chest", woodType.getName()), BlockChest.Type.TRAP);
            ladder[woodType.getMetadata()] = new BlockCustomLadder(woodType.getName());
        }
        for (BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
            patternedPlanks[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("patterned_%s_planks", woodType.getName()));
            carvedPlanks[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("carved_%s_planks", woodType.getName()));
            woodLanterns[woodType.getMetadata()] = new BlockWoodenLantern(woodType);
            barrels[woodType.getMetadata()] = new BlockBarrel(woodType);
            fluidBarrels[woodType.getMetadata()] = new BlockFluidBarrel(woodType);
        }

        for (EnumGTPVariants color : EnumGTPVariants.values()) {
            gtp[color.getId()] = new BlockNeutroniaPillar(Material.ROCK, String.format("%s_glazed_terracotta_pillar", color.getName()));
        }

        chiseledPrismarine = new BlockChiseled(Material.ROCK, "chiseled_prismarine", 1.5F, 10.0F, SoundType.STONE, Items.PRISMARINE_CRYSTALS);
        chiseledPrismarineBricks = new BlockNeutroniaBase(Material.ROCK, "chiseled_prismarine_bricks");
        chiseledNetherbrick = new BlockChiseled(Material.ROCK, "chiseled_nether_brick", 1.5F, 10.0F, SoundType.STONE, Items.LAVA_BUCKET);
        chiseledPurpur = new BlockChiseled(Material.ROCK, "chiseled_purpur", 1.5F, 10.0F, SoundType.STONE, Items.ENDER_PEARL);
        chiseledBricks = new BlockChiseled(Material.ROCK, "chiseled_bricks", 1.5F, 10.0F, SoundType.STONE, Item.getItemFromBlock(Blocks.STONE_SLAB));
        chiseledDarkPrismarine = new BlockDarkPrismarineChiseled("chiseled_dark_prismarine");
        chiseledEndStoneBrick = new BlockChiseled(Material.ROCK, "chiseled_end_brick", 3.0F, 15.0F, SoundType.STONE, Items.CHORUS_FRUIT_POPPED);
        chiseledRedNetherBrick = new BlockChiseled(Material.ROCK, "chiseled_red_nether_brick", 1.5F, 10.0F, SoundType.STONE, Item.getItemFromBlock(Blocks.GLOWSTONE));

        cutPrismarine = new BlockNeutroniaBase(Material.ROCK, "cut_prismarine");
        cutPrismarineBricks = new BlockNeutroniaBase(Material.ROCK, "cut_prismarine_bricks");

        engravedPrismarine = new BlockNeutroniaBase(Material.ROCK, "engraved_prismarine");
        engravedPrismarineBricks = new BlockNeutroniaBase(Material.ROCK, "engraved_prismarine_bricks");

        obsidianBricks = new BlockNeutroniaBase(Material.ROCK, "obsidian_bricks").setHardness(50.0F).setResistance(2000.0F);
        obsidianCobble = new BlockNeutroniaBase(Material.ROCK, "obsidian_cobble").setHardness(50.0F).setResistance(2000.0F);
        obsidianTiles = new BlockNeutroniaBase(Material.ROCK, "obsidian_tiles").setHardness(50.0F).setResistance(2000.0F);

        smoothEndBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_end_brick", CreativeTabs.BUILDING_BLOCKS, 0.8F, 10.0F, SoundType.STONE);
        smoothPrismarineBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_prismarine_bricks", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        smoothPrismarine = new BlockNeutroniaBase(Material.ROCK, "smooth_prismarine");
        smoothObsidian = new BlockNeutroniaBase(Material.ROCK, "smooth_obsidian").setHardness(50.0F).setResistance(2000.0F);
        smoothBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        smoothPurpurBlock = new BlockNeutroniaBase(Material.ROCK, "smooth_purpur_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        smoothNetherBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_nether_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        smoothRedNetherBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_red_nether_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        smoothStone = new BlockNeutroniaBase(Material.ROCK, "smooth_stone", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        smoothStoneBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_stone_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);

        diamondBricks = new BlockNeutroniaBase(Material.IRON, "diamond_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        emeraldBricks = new BlockNeutroniaBase(Material.IRON, "emerald_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        ironBricks = new BlockNeutroniaBase(Material.IRON, "iron_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        goldBricks = new BlockNeutroniaBase(Material.IRON, "gold_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);

        diamondBrickPillar = new BlockNeutroniaPillar(Material.IRON, "diamond_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        emeraldBrickPillar = new BlockNeutroniaPillar(Material.IRON, "emerald_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        ironBrickPillar = new BlockNeutroniaPillar(Material.IRON, "iron_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        goldBrickPillar = new BlockNeutroniaPillar(Material.IRON, "gold_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);

        dirtyBricks = new BlockNeutroniaBase(Material.ROCK, "dirty_bricks");
        crackedBricks = new BlockNeutroniaBase(Material.ROCK, "cracked_bricks");
        mossyBricks = new BlockNeutroniaBase(Material.ROCK, "mossy_bricks");
        brickPath = new BlockNeutroniaBase(Material.ROCK, "brick_path");
        kitchenTiles = new BlockNeutroniaBase(Material.ROCK, "checkered_tiles");
        smallKitchenTiles = new BlockNeutroniaBase(Material.ROCK, "small_checkered_tiles");
        stoneTile = new BlockNeutroniaBase(Material.ROCK, "stone_tile");
        smallStoneTile = new BlockNeutroniaBase(Material.ROCK, "small_stone_tile");
        andesiteTile = new BlockNeutroniaBase(Material.ROCK, "andesite_tile");
        smallAndesiteTile = new BlockNeutroniaBase(Material.ROCK, "small_andesite_tile");
        dioriteTile = new BlockNeutroniaBase(Material.ROCK, "diorite_tile");
        smallDioriteTile = new BlockNeutroniaBase(Material.ROCK, "small_diorite_tile");
        graniteTile = new BlockNeutroniaBase(Material.ROCK, "granite_tile");
        smallGraniteTile = new BlockNeutroniaBase(Material.ROCK, "small_granite_tile");
        stoneBrickTile = new BlockNeutroniaBase(Material.ROCK, "stone_brick_tile");
        smallStoneBrickTile = new BlockNeutroniaBase(Material.ROCK, "small_stone_brick_tile");
        brickTile = new BlockNeutroniaBase(Material.ROCK, "brick_tile");
        smallBrickTile = new BlockNeutroniaBase(Material.ROCK, "small_brick_tile");

        chiseledBrick = new BlockNeutroniaBase(Material.ROCK, "chiseled_brick");

        ropeCoil = new BlockNeutroniaPillar(Material.CLOTH, "rope_coil");

        darkAndesite = new BlockNeutroniaBase(Material.ROCK, "dark_andesite");
        darkAndesiteBricks = new BlockNeutroniaBase(Material.ROCK, "dark_anesite_bricks");
        polishedDarkAndesite = new BlockNeutroniaBase(Material.ROCK, "polished_dark_andesite");

        mud = new BlockMud();
        mudBricks = new BlockMud("mud_bricks");
        driedMud = new BlockNeutroniaBase(Material.GROUND, "dried_mud");
        driedMudBricks = new BlockNeutroniaBase(Material.GROUND, "dried_mud_bricks");

        packedIceBricks = new BlockNeutroniaBase(Material.PACKED_ICE, "packed_ice_bricks");
        packedIcePillar = new BlockNeutroniaPillar(Material.PACKED_ICE, "packed_ice_pillar");
        smallSnowBricks = new BlockNeutroniaBase(Material.SNOW, "small_snow_bricks");
        snowBricks = new BlockNeutroniaBase(Material.PACKED_ICE, "snow_bricks");
        iceTiles = new BlockNeutroniaBase(Material.PACKED_ICE, "ice_tiles");
        iceRod = new BlockRodBase("ice_rod", true);
        iceDoor = new BlockNeutroniaDoor(Material.ICE, "ice_door");
        iceTrapdoor = new BlockNeutroniaTrapdoor(Material.ICE, "ice_trapdoor", SoundType.SNOW);

        sandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        redSandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "red_sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        stonePillar = new BlockNeutroniaPillar(Material.ROCK, "stone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedAndesitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_andesite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedDioritePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_diorite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedGranitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_granite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        stoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "stone_brick_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        brickPillar = new BlockNeutroniaPillar(Material.ROCK, "brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        prismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "prismarine_column");
        prismarinePillar = new BlockNeutroniaPillar(Material.ROCK, "prismarine_pillar");
        prismarineBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "prismarine_brick_pillar");
        redNetherBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "red_nether_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        endStoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "end_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 15.0F, SoundType.STONE);
        darkPrismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "dark_prismarine_column", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);

        frosted_glass = new BlockFrostedGlass();
        frosted_glass_pane = new BlockFrostedGlassPane();

        ironLantern = new BlockLantern("iron");
        goldLantern = new BlockLantern("gold");
        prismarineLantern = new BlockLantern("prismarine");
        redstoneIronLantern = new BlockLanternRedstone("iron", true);
        redstoneIronLanternOff = new BlockLanternRedstone("iron", false);
        redstoneIronLantern.setOffBlock(redstoneIronLanternOff.getDefaultState());
        redstoneIronLanternOff.setOnBlock(redstoneIronLantern.getDefaultState());
        redstoneGoldLantern = new BlockLanternRedstone("gold", true);
        redstoneGoldLanternOff = new BlockLanternRedstone("gold", false);
        redstoneGoldLantern.setOffBlock(redstoneGoldLanternOff.getDefaultState());
        redstoneGoldLanternOff.setOnBlock(redstoneGoldLantern.getDefaultState());
        ironChain = new BlockChain("iron");
        goldChain = new BlockChain("gold");
        prismarineChain = new BlockChain("prismarine");

        for (SoulStoneVariants soulStoneTypes : SoulStoneVariants.values()) {
            soulStone[soulStoneTypes.getMetadata()] = new BlockNeutroniaBase(Material.ROCK, soulStoneTypes.getName());
//            BlockRegisteringUtils.addSlabAndStair(soulStoneTypes.getName(), soulStone[soulStoneTypes.getMetadata()], 0, true);
        }

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[0]),
                "SS", "SS",
                'S', ProxyRegistry.newStack(Blocks.SOUL_SAND));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[2], 4),
                "SS", "SS",
                'S', ProxyRegistry.newStack(soulStone[0], 1));
        /*RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[1], 1),
                "S", "S",
                'S', ProxyRegistry.newStack(Block.getBlockFromName("neutronia:normal_soulstone_slab"), 1));*/

        stickBundle = new BlockNeutroniaPillar(Material.WOOD, "stick_bundle");
        chorusBundle = new BlockNeutroniaPillar(Material.PLANTS, "chorus_bundle");
        sugarCaneBundle = new BlockNeutroniaPillar(Material.PLANTS, "sugar_cane_bundle");
        bambooBundle = new BlockNeutroniaPillar(Material.PLANTS, "bamboo_bundle");
        netherWartSack = new BlockNeutroniaPillar(Material.CLOTH, "nether_wart_sack");
        cocoaBeanSack = new BlockNeutroniaPillar(Material.CLOTH, "cocoa_bean_sack");
        gunpowderSack = new BlockNeutroniaPillar(Material.CLOTH, "gunpowder_sack");

        eggCrate = new BlockNeutroniaPillar(Material.WOOD, "egg_crate");
        beetrootCrate = new BlockNeutroniaPillar(Material.WOOD, "beetroot_crate");
        potatoCrate = new BlockNeutroniaPillar(Material.WOOD, "potato_crate");
        carrotCrate = new BlockNeutroniaPillar(Material.WOOD, "carrot_crate");
        appleCrate = new BlockNeutroniaPillar(Material.WOOD, "apple_crate");
        goldenAppleCrate = new BlockNeutroniaPillar(Material.WOOD, "golden_apple_crate");
        cactusBundle = new BlockCactusBundle();

        /*BAMBOO = new BlockBamboo();
        BAMBOO_SAPLING = new BlockBambooSapling();
        BLAST_FURNACE = new BlockBlastFurnace(false);
        LIT_BLAST_FURNACE = new BlockBlastFurnace(true);
        SMOKER = new BlockSmoker(false);
        LIT_SMOKER = new BlockSmoker(true);
        SWEET_BERRY_BUSH = new BlockBerryBush("sweet_berry", NItems.SWEET_BERRIES);
        GOOSEBERRY_BUSH = new BlockBerryBush("gooseberry", NItems.GOOSEBERRIES);
        BLUEBERRY_BUSH = new BlockBerryBush("blueberry", NItems.BLUEBERRIES);
        RED_GRAPE_BUSH = new BlockGrapeBush("red", NItems.RED_GRAPES);
        GREEN_GRAPE_BUSH = new BlockGrapeBush("green", NItems.GREEN_GRAPES);
        BLACKBERRY_BUSH = new BlockBerryBush("blackberry", NItems.BLACKBERRIES);
        POISON_BERRY_BUSH = new BlockBerryBush("poison_berry", NItems.POISON_BERRIES);
        WITHER_BERRY_BUSH = new BlockBerryBush("wither_berry", NItems.WITHER_BERRIES);
        RASPBERRY_BUSH = new BlockBerryBush("raspberry", NItems.RASPBERRIES);
        SOUR_BERRY_BUSH = new BlockBerryBush("sour_berry", NItems.RASPBERRIES);
        CURRANT_BUSH = new BlockBerryBush("currant", NItems.CURRANTS);*/
    }

}
