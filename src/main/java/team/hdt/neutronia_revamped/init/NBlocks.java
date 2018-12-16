package team.hdt.neutronia_revamped.init;

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
import team.hdt.neutronia_legacy.properties.EnumSoulStoneVariants;
import team.hdt.neutronia_legacy.properties.EnumVanillaWoodTypes;
import team.hdt.neutronia_revamped.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_revamped.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia_revamped.blocks.*;
import team.hdt.neutronia_revamped.properties.EnumGTPVariants;
import team.hdt.neutronia_revamped.utils.BlockRegisteringUtils;

public class NBlocks {

    public static Block[] bookshelfs = new Block[5], patternedPlanks = new Block[6], carvedPlanks = new Block[6], lanterns = new Block[6], ladder = new Block[5], gtp = new Block[13], soulStone = new Block[4];
    public static BlockChiseled chiseledPrismarine, chiseledNetherbrick, chiseledPurpur, chiseledBricks, chiseledEndStoneBrick, chiseledRedNetherBrick;
    public static BlockDarkPrismarineChiseled chiseledDarkPrismarine;
    public static BlockNeutroniaBase smoothEndBrick, smoothPrismarineBrick, smoothBrick, smoothPurpurBlock, smoothNetherBrick, smoothRedNetherBrick, smoothStone;
    public static BlockNeutroniaPillar diamondBrickPillar, emeraldBrickPillar, ironBrickPillar, goldBrickPillar;
    public static BlockNeutroniaBase diamondBricks, emeraldBricks, ironBricks, goldBricks;
    public static BlockNeutroniaPillar redNetherBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "red_nether_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
    public static BlockNeutroniaPillar endStoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "end_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 15.0F, SoundType.STONE);
    public static BlockNeutroniaPillar brickPillar;
    public static BlockNeutroniaPillar darkPrismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "dark_prismarine_column", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
    public static Block sandstonePillar, redSandstonePillar, stonePillar, polishedAndesitePillar, polishedGranitePillar, polishedDioritePillar,
            stoneBrickPillar, prismarineColumn, endStonePillar, netherBrickPillar;
    public static Block dirtyBricks, crackedBricks, mossyBricks, kitchenTiles, smallKitchenTiles;
    public static Block stoneTile, smallStoneTile, andesiteTile, smallAndesiteTile, dioriteTile, smallDioriteTile, graniteTile, smallGraniteTile,
            stoneBrickTile, smallStoneBrickTile, brickTile, smallBrickTile;
    public static Block ropeCoil;
    public static Block chiseledIce, packedIceBricks, packedIcePillar, smallSnowBricks, snowBricks;
    public static Block chiseledBrick;
    public static Block frosted_glass;
    public static Block frosted_glass_pane;

    public static void init() {
        for(EnumVanillaWoodTypes woodType : EnumVanillaWoodTypes.values()) {
            bookshelfs[woodType.getMetadata()] = new BlockNeutroniaBase(String.format("%s_bookshelf", woodType.getName()), Material.WOOD);
//            chests[woodType.getMetadata()] = new BlockCustomChest(String.format("%s_chest", woodType.getName()), BlockChest.Type.BASIC);
//            trappedChests[woodType.getMetadata()] = new BlockCustomChest(String.format("trapped_%s_chest", woodType.getName()), BlockChest.Type.TRAP);
            ladder[woodType.getMetadata()] = new BlockCustomLadder(woodType.getName());
        }
        for(BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
            patternedPlanks[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("patterned_%s_planks", woodType.getName()));
            carvedPlanks[woodType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("carved_%s_planks", woodType.getName()));
            lanterns[woodType.getMetadata()] = new BlockWoodenLantern(woodType);
        }

        for(EnumGTPVariants color : EnumGTPVariants.values()) {
            gtp[color.getId()] = new BlockNeutroniaPillar(Material.ROCK, String.format("%s_glazed_terracotta_pillar", color.getName()));
        }

        chiseledPrismarine = new BlockChiseled(Material.ROCK, "chiseled_prismarine", 1.5F, 10.0F, SoundType.STONE, Items.PRISMARINE_CRYSTALS);
        chiseledNetherbrick = new BlockChiseled(Material.ROCK, "chiseled_nether_brick", 1.5F, 10.0F, SoundType.STONE, Items.LAVA_BUCKET);
        chiseledPurpur = new BlockChiseled(Material.ROCK, "chiseled_purpur", 1.5F, 10.0F, SoundType.STONE, Items.ENDER_PEARL);
        chiseledBricks = new BlockChiseled(Material.ROCK, "chiseled_bricks", 1.5F, 10.0F, SoundType.STONE, Item.getItemFromBlock(Blocks.STONE_SLAB));
        chiseledDarkPrismarine = new BlockDarkPrismarineChiseled("chiseled_dark_prismarine");
        chiseledEndStoneBrick = new BlockChiseled(Material.ROCK, "chiseled_end_brick", 3.0F, 15.0F, SoundType.STONE, Items.CHORUS_FRUIT_POPPED);
        chiseledRedNetherBrick = new BlockChiseled(Material.ROCK, "chiseled_red_nether_brick", 1.5F, 10.0F, SoundType.STONE, Item.getItemFromBlock(Blocks.GLOWSTONE));

        smoothEndBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_end_brick", CreativeTabs.BUILDING_BLOCKS, 0.8F, 10.0F, SoundType.STONE);
        smoothPrismarineBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_prismarine_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        smoothBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        smoothPurpurBlock = new BlockNeutroniaBase(Material.ROCK, "smooth_purpur_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        smoothNetherBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_nether_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
        smoothRedNetherBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_red_nether_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);
        smoothStone = new BlockNeutroniaBase(Material.ROCK, "smooth_stone", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);

        diamondBricks = new BlockNeutroniaBase(Material.IRON, "diamond_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        emeraldBricks = new BlockNeutroniaBase(Material.IRON, "emerald_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        ironBricks = new BlockNeutroniaBase(Material.IRON, "iron_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        goldBricks = new BlockNeutroniaBase(Material.IRON, "gold_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        
        diamondBrickPillar = new BlockNeutroniaPillar(Material.IRON, "diamond_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        emeraldBrickPillar = new BlockNeutroniaPillar(Material.IRON, "emerald_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        ironBrickPillar = new BlockNeutroniaPillar(Material.IRON, "iron_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        goldBrickPillar = new BlockNeutroniaPillar(Material.IRON, "gold_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);

        dirtyBricks = new BlockNeutroniaBase(Material.ROCK, "dirty_bricks");
        crackedBricks = new BlockNeutroniaBase(Material.ROCK, "cracked_bricks");
        mossyBricks = new BlockNeutroniaBase(Material.ROCK, "mossy_bricks");
        kitchenTiles = new BlockNeutroniaBase(Material.ROCK, "checkered_floor");
        smallKitchenTiles = new BlockNeutroniaBase(Material.ROCK, "small_checkered_floor");
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

        chiseledIce = new BlockChiseledIce();
        packedIceBricks = new BlockNeutroniaBase(Material.PACKED_ICE, "packed_ice_bricks");
        packedIcePillar = new BlockNeutroniaPillar(Material.PACKED_ICE, "packed_ice_pillar");
        smallSnowBricks = new BlockNeutroniaBase(Material.SNOW, "small_snow_bricks");
        snowBricks = new BlockNeutroniaBase(Material.PACKED_ICE, "snow_bricks");

        sandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        redSandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "red_sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        stonePillar = new BlockNeutroniaPillar(Material.ROCK, "stone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedAndesitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_andesite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedDioritePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_diorite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedGranitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_granite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        stoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "stone_brick_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        brickPillar = new BlockNeutroniaPillar(Material.ROCK, "brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);

        frosted_glass = new BlockFrostedGlass();
        frosted_glass_pane = new BlockFrostedGlassPane();

        for (EnumSoulStoneVariants soulStoneTypes : EnumSoulStoneVariants.values()) {
            soulStone[soulStoneTypes.getMetadata()] = new BlockNeutroniaBase(Material.ROCK, soulStoneTypes.getName());
            BlockRegisteringUtils.addSlabAndStair(soulStoneTypes.getName(), soulStone[soulStoneTypes.getMetadata()], 0, true);
        }

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[0]),
                "SS", "SS",
                'S', ProxyRegistry.newStack(Blocks.SOUL_SAND));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[2], 4),
                "SS", "SS",
                'S', ProxyRegistry.newStack(soulStone[0], 1));
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soulStone[1], 1),
                "S", "S",
                'S', ProxyRegistry.newStack(Block.getBlockFromName("neutronia_revamped:normal_soulstone_slab"), 1));
    }

}
