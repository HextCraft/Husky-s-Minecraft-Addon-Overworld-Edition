package team.hdt.neutronia_revamped.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import team.hdt.neutronia_legacy.properties.EnumVanillaWoodTypes;
import team.hdt.neutronia_revamped.blocks.*;
import team.hdt.neutronia_revamped.properties.EnumGTPVariants;

public class NBlocks {

    public static Block[] bookshelfs = new Block[5], chests = new Block[5], trappedChests = new Block[5], ladder = new Block[5], gtp = new Block[13];
    public static BlockChiseled chiseledPrismarine;
    public static BlockChiseled chiseledNetherbrick;
    public static BlockChiseled chiseledPurpur;
    public static BlockChiseled chiseledBricks;
    public static BlockDarkPrismarineChiseled chiseledDarkPrismarine;
    public static BlockChiseled chiseledEndStoneBrick;
    public static BlockChiseled chiseledRedNetherBrick;
    public static BlockNeutroniaBase smoothEndBrick;
    public static BlockNeutroniaBase smoothPrismarineBrick;
    public static BlockNeutroniaBase smoothBrick;
    public static BlockNeutroniaBase smoothPurpurBlock;
    public static BlockNeutroniaBase smoothNetherBrick;
    public static BlockNeutroniaBase smoothRedNetherBrick;
    public static BlockNeutroniaPillar diamondBrickPillar;
    public static BlockNeutroniaPillar emeraldBrickPillar;
    public static BlockNeutroniaPillar ironBrickPillar;
    public static BlockNeutroniaPillar goldBrickPillar;
    public static BlockNeutroniaBase diamondBricks;
    public static BlockNeutroniaBase emeraldBricks;
    public static BlockNeutroniaBase ironBricks;
    public static BlockNeutroniaBase goldBricks;
    public static BlockNeutroniaPillar redNetherBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "red_nether_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
    public static BlockNeutroniaPillar endStoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "end_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 15.0F, SoundType.STONE);
    public static BlockNeutroniaPillar brickPillar = new BlockNeutroniaPillar(Material.ROCK, "brick_pillar", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, SoundType.STONE);
    public static BlockNeutroniaPillar darkPrismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "dark_prismarine_column", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, SoundType.STONE);

    public static void init() {
        for(EnumVanillaWoodTypes woodType : EnumVanillaWoodTypes.values()) {
            bookshelfs[woodType.getMetadata()] = new BlockNeutroniaBase(String.format("%s_bookshelf", woodType.getName()), Material.WOOD);
//            chests[woodType.getMetadata()] = new BlockCustomChest(String.format("%s_chest", woodType.getName()), BlockChest.Type.BASIC);
//            trappedChests[woodType.getMetadata()] = new BlockCustomChest(String.format("trapped_%s_chest", woodType.getName()), BlockChest.Type.TRAP);
            ladder[woodType.getMetadata()] = new BlockCustomLadder(woodType.getName());
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

        diamondBricks = new BlockNeutroniaBase(Material.IRON, "diamond_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        emeraldBricks = new BlockNeutroniaBase(Material.IRON, "emerald_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        ironBricks = new BlockNeutroniaBase(Material.IRON, "iron_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        goldBricks = new BlockNeutroniaBase(Material.IRON, "gold_bricks", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
        
        diamondBrickPillar = new BlockNeutroniaPillar(Material.IRON, "diamond_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        emeraldBrickPillar = new BlockNeutroniaPillar(Material.IRON, "emerald_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        ironBrickPillar = new BlockNeutroniaPillar(Material.IRON, "iron_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 5.0F, 10.0F, SoundType.STONE);
        goldBrickPillar = new BlockNeutroniaPillar(Material.IRON, "gold_brick_pillar", CreativeTabs.BUILDING_BLOCKS, 3.0F, 10.0F, SoundType.STONE);
    }

}
