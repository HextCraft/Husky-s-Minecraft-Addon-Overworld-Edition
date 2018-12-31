package team.hdt.neutronia.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import team.hdt.neutronia.base.Reference;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.blocks.BlockNeutroniaDoor;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.blocks.BlockNeutroniaTrapdoor;
import team.hdt.neutronia.blocks.*;
import team.hdt.neutronia.properties.EnumGTPVariants;
import team.hdt.neutronia.properties.SoulStoneVariants;
import team.hdt.neutronia.properties.VanillaWoodTypes;
import team.hdt.neutronia.properties.WoodTypes;

public class NBlocks {

    public static final BlockChest.Type CUSTOM_TYPE_NEUTRONIA = EnumHelper.addEnum(BlockChest.Type.class, "NEUTRONIA", new Class[0]);
    public static final BlockChest.Type CUSTOM_TYPE_NEUTRONIA_TRAP = EnumHelper.addEnum(BlockChest.Type.class, "NEUTRONIA_TRAP", new Class[0]);

    public static final ResourceLocation TRAP_RESOURCE = new ResourceLocation(Reference.PREFIX_MOD + "textures/entity/chest/trap.png");
    public static final ResourceLocation TRAP_DOUBLE_RESOURCE = new ResourceLocation(Reference.PREFIX_MOD + "textures/entity/chest/trap_double.png");

    public static Block[] BOOKSHELVES = new Block[9], PATTERNED_PLANKS = new Block[6], CARVED_PLANKS = new Block[10],
            BARRELS = new Block[10], FLUID_BARRELS = new Block[10], WOOD_LANTERNS = new Block[10], LADDERS = new Block[9],
            GLAZED_TERRACOTTA_PILLAR = new Block[13], SOUL_STONE = new Block[4];
    public static BlockCustomChest CUSTOM_CHEST;
    public static BlockCustomChest CUSTOM_TRAPPED_CHEST;
    public static BlockChiseled CHISELED_PRISMARINE, CHISELED_NETHER_BRICK, CHISELED_PURPUR, CHISELED_BRICKS, CHISELED_END_BRICK, CHISELED_RED_NETHER_BRICK;
    public static BlockDarkPrismarineChiseled CHISELED_DARK_PRISMARINE;
    public static Block CHISELED_PRISMARINE_BRICKS, CUT_PRISMARINE, CUT_PRISMARINE_BRICKS, ENGRAVED_PRISMARINE, ENGRAVED_PRISMARINE_BRICKS;
    public static Block OBSIDIAN_BRICKS, OBSIDIAN_COBBLE, OBSIDIAN_PILLAR, CHISELED_OBSIDIAN, CRYING_OBSIDIAN;
    public static Block SMOOTH_END_BRICK, SMOOTH_PRISMARINE_BRICKS, SMOOTH_PRISMARINE, SMOOTH_OBSIDIAN, SMOOTH_BRICK, SMOOTH_PURPUR_BRICK, SMOOTH_NETHER_BRICK, SMOOTH_RED_NETHER_BRICK, SMOOTH_STONE, SMOOTH_STONE_BRICK;
    public static BlockNeutroniaPillar DIAMOND_PILLAR, EMERALD_PILLAR, IRON_PILLAR, GOLD_PILLAR;
    public static Block DIAMOND_BRICKS, EMERALD_BRICKS, IRON_BRICKS, GOLD_BRICKS;
    public static Block SANDSTONE_PILLAR, RED_SANDSTONE_PILLAR, STONE_PILLAR, POLISHED_ANDESITE_PILLAR, POLISHED_GRANITE_PILLAR, POLISHED_DIORITE_PILLAR,
            STONE_BRICK_PILLAR, PRISMARINE_COLUMN, PRISMARINE_PILLAR, PRISMARINE_BRICK_PILLAR, RED_NETHER_BRICK_PILLAR, END_BRICK_PILLAR,
            BRICK_PILLAR, DARK_PRISMARINE_COLUMN;
    public static Block DIRTY_BRICKS, CRACKED_BRICKS, MOSSY_BRICKS, BRICK_PATH, CHECKERED_TILE, SMALL_CHECKERED_TILE;
    public static Block STONE_TILE, SMALL_STONE_TILE, ANDESITE_TILE, SMALL_ANDESITE_TILE, DIORITE_TILE, SMALL_DIORITE_TILE, GRANITE_TILE, SMALL_GRANITE_TILE,
            STONE_BRICK_TILE, SMALL_STONE_BRICK_TILE, BRICK_TILE, SMALL_BRICK_TILE, OBSIDIAN_TILES, SMALL_OBSIDIAN_TILES;
    public static Block ROPE_COIL;
    public static Block DARK_ANDESITE, DARK_ANDESITE_BRICKS, POLISHED_DARK_ANDESITE;
    public static Block MUD, MUD_BRICKS, DRIED_MUD, DRIED_MUD_BRICKS;
    public static Block PACKED_ICE_BRICKS, PACKED_ICE_PILLAR, SMALL_SNOW_BRICKS, SNOW_BRICKS, ICE_TILES, ICE_ROD, ICE_DOOR, ICE_TRAPDOOR;
    public static Block CHISELED_BRICK;
    public static Block FROSTED_GLASS;
    public static Block FROSTED_GLASS_PANE;
    public static Block IRON_LANTERN, GOLDEN_LANTERN, PRISMARINE_LANTERN, IRON_CHAIN, GOLD_CHAIN, PRISMARINE_CHAIN;
    public static BlockLanternRedstone IRON_REDSTONE_LANTERN, IRON_REDSTONE_LANTERN_OFF, GOLDEN_REDSTONE_LANTERN, GOLDEN_REDSTONE_LANTERN_OFF;
    public static Block STICK_BUNDLE, CHORUS_BUNDLE, SUGAR_CANE_BUNDLE, BAMBOO_BUNDLE, NETHER_WART_SACK, COCOA_BEAN_SACK, GUNPOWDER_SACK,
            EGG_CRATE, BEETROOT_CRATE, POTATO_CRATE, CARROT_CRATE, APPLE_CRATE, GOLDEN_APPLE_CRATE, CACTUS_BUNDLE;
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

    public static Block BLACK_SAND;
    public static Block WHITE_SAND;

    public static void init() {

        for (VanillaWoodTypes woodType : VanillaWoodTypes.values()) {
            BOOKSHELVES[woodType.getMetadata()] = new BlockNeutroniaBase(String.format("%s_bookshelf", woodType.getName()), Material.WOOD);
            LADDERS[woodType.getMetadata()] = new BlockCustomLadder(woodType.getName());
        }

        CUSTOM_CHEST = new BlockCustomChest("wooden_chest", BlockChest.Type.BASIC);
        CUSTOM_TRAPPED_CHEST = new BlockCustomChest("trapped_wooden_chest", BlockChest.Type.TRAP);

        for(WoodTypes woodTypes : WoodTypes.values()) {
            WOOD_LANTERNS[woodTypes.getMetadata()] = new BlockWoodenLantern(woodTypes);
            BARRELS[woodTypes.getMetadata()] = new BlockBarrel(woodTypes);
            FLUID_BARRELS[woodTypes.getMetadata()] = new BlockFluidBarrel(woodTypes);
            CARVED_PLANKS[woodTypes.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("carved_%s_planks", woodTypes.getName()));
        }

        for (BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
            PATTERNED_PLANKS[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("patterned_%s_planks", woodType.getName()));
        }

        for (EnumGTPVariants color : EnumGTPVariants.values()) {
            GLAZED_TERRACOTTA_PILLAR[color.getId()] = new BlockNeutroniaPillar(Material.ROCK, String.format("%s_glazed_terracotta_pillar", color.getName()));
        }

        CHISELED_PRISMARINE = new BlockChiseled(Material.ROCK, "chiseled_prismarine", 1.5F, 10.0F, SoundType.STONE, Items.PRISMARINE_CRYSTALS);
        CHISELED_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.ROCK, "chiseled_prismarine_bricks");
        CHISELED_NETHER_BRICK = new BlockChiseled(Material.ROCK, "chiseled_nether_brick", 1.5F, 10.0F, SoundType.STONE, Items.LAVA_BUCKET);
        CHISELED_PURPUR = new BlockChiseled(Material.ROCK, "chiseled_purpur", 1.5F, 10.0F, SoundType.STONE, Items.ENDER_PEARL);
        CHISELED_BRICKS = new BlockChiseled(Material.ROCK, "chiseled_bricks", 1.5F, 10.0F, SoundType.STONE, Item.getItemFromBlock(Blocks.STONE_SLAB));
        CHISELED_DARK_PRISMARINE = new BlockDarkPrismarineChiseled("chiseled_dark_prismarine");
        CHISELED_END_BRICK = new BlockChiseled(Material.ROCK, "chiseled_end_brick", 3.0F, 15.0F, SoundType.STONE, Items.CHORUS_FRUIT_POPPED);
        CHISELED_RED_NETHER_BRICK = new BlockChiseled(Material.ROCK, "chiseled_red_nether_brick", 1.5F, 10.0F, SoundType.STONE, Item.getItemFromBlock(Blocks.GLOWSTONE));

        CUT_PRISMARINE = new BlockNeutroniaBase(Material.ROCK, "cut_prismarine");
        CUT_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.ROCK, "cut_prismarine_bricks");

        ENGRAVED_PRISMARINE = new BlockNeutroniaBase(Material.ROCK, "engraved_prismarine");
        ENGRAVED_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.ROCK, "engraved_prismarine_bricks");

        OBSIDIAN_BRICKS = new BlockNeutroniaBase(Material.ROCK, "obsidian_bricks").setHardness(50.0F).setResistance(2000.0F);
        OBSIDIAN_COBBLE = new BlockNeutroniaBase(Material.ROCK, "obsidian_cobble").setHardness(50.0F).setResistance(2000.0F);
        OBSIDIAN_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "obsidian_pillar").setHardness(50.0F).setResistance(2000.0F);
        CHISELED_OBSIDIAN = new BlockNeutroniaBase(Material.ROCK, "chiseled_obsidian").setHardness(50.0F).setResistance(2000.0F);
        CRYING_OBSIDIAN = new BlockNeutroniaBase(Material.ROCK, "crying_obsidian").setHardness(50.0F).setResistance(2000.0F);

        SMOOTH_END_BRICK = new BlockNeutroniaBase(Material.ROCK, "smooth_end_brick", CreativeTabs.BUILDING_BLOCKS, 0.8F, 10.0F, SoundType.STONE);
        SMOOTH_PRISMARINE_BRICKS = new BlockNeutroniaBase(Material.ROCK, "smooth_prismarine_bricks", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        SMOOTH_PRISMARINE = new BlockNeutroniaBase(Material.ROCK, "smooth_prismarine");
        SMOOTH_OBSIDIAN = new BlockNeutroniaBase(Material.ROCK, "smooth_obsidian").setHardness(50.0F).setResistance(2000.0F);
        SMOOTH_BRICK = new BlockNeutroniaBase(Material.ROCK, "smooth_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        SMOOTH_PURPUR_BRICK = new BlockNeutroniaBase(Material.ROCK, "smooth_purpur_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        SMOOTH_NETHER_BRICK = new BlockNeutroniaBase(Material.ROCK, "smooth_nether_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        SMOOTH_RED_NETHER_BRICK = new BlockNeutroniaBase(Material.ROCK, "smooth_red_nether_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        SMOOTH_STONE = new BlockNeutroniaBase(Material.ROCK, "smooth_stone", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        SMOOTH_STONE_BRICK = new BlockNeutroniaBase(Material.ROCK, "smooth_stone_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);

        DIAMOND_BRICKS = new BlockNeutroniaBase(Material.IRON, "diamond_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        EMERALD_BRICKS = new BlockNeutroniaBase(Material.IRON, "emerald_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        IRON_BRICKS = new BlockNeutroniaBase(Material.IRON, "iron_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        GOLD_BRICKS = new BlockNeutroniaBase(Material.IRON, "gold_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);

        DIAMOND_PILLAR = new BlockNeutroniaPillar(Material.IRON, "diamond_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        EMERALD_PILLAR = new BlockNeutroniaPillar(Material.IRON, "emerald_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        IRON_PILLAR = new BlockNeutroniaPillar(Material.IRON, "iron_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        GOLD_PILLAR = new BlockNeutroniaPillar(Material.IRON, "gold_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);

        DIRTY_BRICKS = new BlockNeutroniaBase(Material.ROCK, "dirty_bricks");
        CRACKED_BRICKS = new BlockNeutroniaBase(Material.ROCK, "cracked_bricks");
        MOSSY_BRICKS = new BlockNeutroniaBase(Material.ROCK, "mossy_bricks");
        BRICK_PATH = new BlockNeutroniaBase(Material.ROCK, "brick_path");

        CHECKERED_TILE = new BlockNeutroniaBase(Material.ROCK, "checkered_tiles");
        SMALL_CHECKERED_TILE = new BlockNeutroniaBase(Material.ROCK, "small_checkered_tiles");
        STONE_TILE = new BlockNeutroniaBase(Material.ROCK, "stone_tile");
        SMALL_STONE_TILE = new BlockNeutroniaBase(Material.ROCK, "small_stone_tile");
        ANDESITE_TILE = new BlockNeutroniaBase(Material.ROCK, "andesite_tile");
        SMALL_ANDESITE_TILE = new BlockNeutroniaBase(Material.ROCK, "small_andesite_tile");
        DIORITE_TILE = new BlockNeutroniaBase(Material.ROCK, "diorite_tile");
        SMALL_DIORITE_TILE = new BlockNeutroniaBase(Material.ROCK, "small_diorite_tile");
        GRANITE_TILE = new BlockNeutroniaBase(Material.ROCK, "granite_tile");
        SMALL_GRANITE_TILE = new BlockNeutroniaBase(Material.ROCK, "small_granite_tile");
        STONE_BRICK_TILE = new BlockNeutroniaBase(Material.ROCK, "stone_brick_tile");
        SMALL_STONE_BRICK_TILE = new BlockNeutroniaBase(Material.ROCK, "small_stone_brick_tile");
        BRICK_TILE = new BlockNeutroniaBase(Material.ROCK, "brick_tile");
        SMALL_BRICK_TILE = new BlockNeutroniaBase(Material.ROCK, "small_brick_tile");
        SMALL_OBSIDIAN_TILES = new BlockNeutroniaBase(Material.ROCK, "small_obsidian_tiles").setHardness(50.0F).setResistance(2000.0F);
        OBSIDIAN_TILES = new BlockNeutroniaBase(Material.ROCK, "obsidian_tiles").setHardness(50.0F).setResistance(2000.0F);

        CHISELED_BRICK = new BlockNeutroniaBase(Material.ROCK, "chiseled_brick");

        ROPE_COIL = new BlockNeutroniaPillar(Material.CLOTH, "rope_coil");

        DARK_ANDESITE = new BlockNeutroniaBase(Material.ROCK, "dark_andesite");
        DARK_ANDESITE_BRICKS = new BlockNeutroniaBase(Material.ROCK, "dark_andesite_bricks");
        POLISHED_DARK_ANDESITE = new BlockNeutroniaBase(Material.ROCK, "polished_dark_andesite");

        MUD = new BlockMud();
        MUD_BRICKS = new BlockMud("mud_bricks");
        DRIED_MUD = new BlockNeutroniaBase(Material.GROUND, "dried_mud");
        DRIED_MUD_BRICKS = new BlockNeutroniaBase(Material.GROUND, "dried_mud_bricks");

        PACKED_ICE_BRICKS = new BlockNeutroniaBase(Material.PACKED_ICE, "packed_ice_bricks");
        PACKED_ICE_PILLAR = new BlockNeutroniaPillar(Material.PACKED_ICE, "packed_ice_pillar");
        SMALL_SNOW_BRICKS = new BlockNeutroniaBase(Material.SNOW, "small_snow_bricks");
        SNOW_BRICKS = new BlockNeutroniaBase(Material.PACKED_ICE, "snow_bricks");
        ICE_TILES = new BlockNeutroniaBase(Material.PACKED_ICE, "ice_tiles");
        ICE_ROD = new BlockRodBase("ice_rod", true);
        ICE_DOOR = new BlockNeutroniaDoor(Material.ICE, "ice_door");
        ICE_TRAPDOOR = new BlockNeutroniaTrapdoor(Material.ICE, "ice_trapdoor", SoundType.SNOW);

        SANDSTONE_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        RED_SANDSTONE_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "red_sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        STONE_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "stone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        POLISHED_ANDESITE_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "polished_andesite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        POLISHED_DIORITE_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "polished_diorite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        POLISHED_GRANITE_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "polished_granite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        STONE_BRICK_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "stone_brick_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        BRICK_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        PRISMARINE_COLUMN = new BlockNeutroniaPillar(Material.ROCK, "prismarine_column");
        PRISMARINE_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "prismarine_pillar");
        PRISMARINE_BRICK_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "prismarine_brick_pillar");
        RED_NETHER_BRICK_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "red_nether_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        END_BRICK_PILLAR = new BlockNeutroniaPillar(Material.ROCK, "end_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 15.0F, SoundType.STONE);
        DARK_PRISMARINE_COLUMN = new BlockNeutroniaPillar(Material.ROCK, "dark_prismarine_column", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);

        FROSTED_GLASS = new BlockFrostedGlass();
        FROSTED_GLASS_PANE = new BlockFrostedGlassPane();

        IRON_LANTERN = new BlockLantern("iron");
        GOLDEN_LANTERN = new BlockLantern("gold");
        PRISMARINE_LANTERN = new BlockLantern("prismarine");
        IRON_REDSTONE_LANTERN = new BlockLanternRedstone("iron", true);
        IRON_REDSTONE_LANTERN_OFF = new BlockLanternRedstone("iron", false);
        IRON_REDSTONE_LANTERN.setOffBlock(IRON_REDSTONE_LANTERN_OFF.getDefaultState());
        IRON_REDSTONE_LANTERN_OFF.setOnBlock(IRON_REDSTONE_LANTERN.getDefaultState());
        GOLDEN_REDSTONE_LANTERN = new BlockLanternRedstone("gold", true);
        GOLDEN_REDSTONE_LANTERN_OFF = new BlockLanternRedstone("gold", false);
        GOLDEN_REDSTONE_LANTERN.setOffBlock(GOLDEN_REDSTONE_LANTERN_OFF.getDefaultState());
        GOLDEN_REDSTONE_LANTERN_OFF.setOnBlock(GOLDEN_REDSTONE_LANTERN.getDefaultState());
        IRON_CHAIN = new BlockChain("iron");
        GOLD_CHAIN = new BlockChain("gold");
        PRISMARINE_CHAIN = new BlockChain("prismarine");

        for (SoulStoneVariants soulStoneTypes : SoulStoneVariants.values()) {
            SOUL_STONE[soulStoneTypes.getMetadata()] = new BlockNeutroniaBase(Material.ROCK, soulStoneTypes.getName());
//            BlockRegisteringUtils.addSlabAndStair(soulStoneTypes.getName(), SOUL_STONE[soulStoneTypes.getMetadata()], 0, true);
        }

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(SOUL_STONE[0]),
                "SS", "SS",
                'S', ProxyRegistry.newStack(Blocks.SOUL_SAND));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(SOUL_STONE[2], 4),
                "SS", "SS",
                'S', ProxyRegistry.newStack(SOUL_STONE[0], 1));
        /*RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(SOUL_STONE[1], 1),
                "S", "S",
                'S', ProxyRegistry.newStack(Block.getBlockFromName("neutronia:normal_soulstone_slab"), 1));*/

        STICK_BUNDLE = new BlockNeutroniaPillar(Material.WOOD, "stick_bundle");
        CHORUS_BUNDLE = new BlockNeutroniaPillar(Material.PLANTS, "chorus_bundle");
        SUGAR_CANE_BUNDLE = new BlockNeutroniaPillar(Material.PLANTS, "sugar_cane_bundle");
        BAMBOO_BUNDLE = new BlockNeutroniaPillar(Material.PLANTS, "bamboo_bundle");
        CACTUS_BUNDLE = new BlockCactusBundle();

        NETHER_WART_SACK = new BlockNeutroniaPillar(Material.CLOTH, "nether_wart_sack");
        COCOA_BEAN_SACK = new BlockNeutroniaPillar(Material.CLOTH, "cocoa_bean_sack");
        GUNPOWDER_SACK = new BlockNeutroniaPillar(Material.CLOTH, "gunpowder_sack");

        EGG_CRATE = new BlockNeutroniaPillar(Material.WOOD, "egg_crate");
        BEETROOT_CRATE = new BlockNeutroniaPillar(Material.WOOD, "beetroot_crate");
        POTATO_CRATE = new BlockNeutroniaPillar(Material.WOOD, "potato_crate");
        CARROT_CRATE = new BlockNeutroniaPillar(Material.WOOD, "carrot_crate");
        APPLE_CRATE = new BlockNeutroniaPillar(Material.WOOD, "apple_crate");
        GOLDEN_APPLE_CRATE = new BlockNeutroniaPillar(Material.WOOD, "golden_apple_crate");

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

        BLACK_SAND = new BlockNeutroniaBase(Material.SAND, "black_sand");
        WHITE_SAND = new BlockNeutroniaBase(Material.SAND, "white_sand");
    }

}
