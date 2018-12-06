package team.hdt.neutronia.groups.building.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.building.blocks.BlockChiseledIce;

public class MoreBuildingBlocks extends Component {

    public static Block dirtyBricks, crackedBricks, mossyBricks, kitchenTiles, smallKitchenTiles;
    public static Block stoneTile, smallStoneTile, andesiteTile, smallAndesiteTile, dioriteTile, smallDioriteTile, graniteTile, smallGraniteTile,
            stoneBrickTile, smallStoneBrickTile, brickTile, smallBrickTile;
    public static Block ropeCoil, darkThatch, thatch;
    public static Block blackRoof, blueRoof, brownRoof, greenRoof, grayRoof, lightBlueRoof, redRoof, whiteRoof;
    public static Block chiseledIce, packedIceBricks, packedIcePillar, smallSnowBricks, snowBricks;
    public static Block fancyAcaciaPlanks, fancyBirchPlanks, fancyDarkOakPlanks, fancyJunglePlanks, fancyOakPlanks, fancySprucePlanks;
    public static Block chiseledBrick;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        dirtyBricks = new BlockNeutroniaBase(Material.ROCK, "dirty_bricks");
        crackedBricks = new BlockNeutroniaBase(Material.ROCK, "cracked_bricks");
        mossyBricks = new BlockNeutroniaBase(Material.ROCK, "mossy_bricks");
        kitchenTiles = new BlockNeutroniaBase(Material.ROCK, "kitchen_tiles");
        smallKitchenTiles = new BlockNeutroniaBase(Material.ROCK, "small_kitchen_tiles");
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

        ropeCoil = new BlockNeutroniaPillar(Material.CLOTH, "rope_coil");
        darkThatch = new BlockNeutroniaBase(Material.GRASS, "dark_thatch");
        thatch = new BlockNeutroniaBase(Material.GRASS, "thatch");

        blackRoof = new BlockNeutroniaBase(Material.ROCK, "black_roof");
        blueRoof = new BlockNeutroniaBase(Material.ROCK, "blue_roof");
        brownRoof = new BlockNeutroniaBase(Material.ROCK, "brown_roof");
        greenRoof = new BlockNeutroniaBase(Material.ROCK, "green_roof");
        grayRoof = new BlockNeutroniaBase(Material.ROCK, "gray_roof");
        lightBlueRoof = new BlockNeutroniaBase(Material.ROCK, "light_blue_roof");
        redRoof = new BlockNeutroniaBase(Material.ROCK, "red_roof");
        whiteRoof = new BlockNeutroniaBase(Material.ROCK, "white_roof");

        chiseledIce = new BlockChiseledIce();
        packedIceBricks = new BlockNeutroniaBase(Material.PACKED_ICE, "packed_ice_bricks");
        packedIcePillar = new BlockNeutroniaPillar(Material.PACKED_ICE, "packed_ice_pillar");
        smallSnowBricks = new BlockNeutroniaBase(Material.SNOW, "small_snow_bricks");
        snowBricks = new BlockNeutroniaBase(Material.PACKED_ICE, "snow_bricks");

        fancyAcaciaPlanks = new BlockNeutroniaBase(Material.WOOD, "fancy_acacia_planks");
        fancyBirchPlanks = new BlockNeutroniaBase(Material.WOOD, "fancy_birch_planks");
        fancyDarkOakPlanks = new BlockNeutroniaBase(Material.WOOD, "fancy_dark_oak_planks");
        fancyJunglePlanks = new BlockNeutroniaBase(Material.WOOD, "fancy_jungle_planks");
        fancyOakPlanks = new BlockNeutroniaBase(Material.WOOD, "fancy_oak_planks");
        fancySprucePlanks = new BlockNeutroniaBase(Material.WOOD, "fancy_spruce_planks");

        chiseledBrick = new BlockNeutroniaBase(Material.ROCK, "chiseled_brick");
    }

}