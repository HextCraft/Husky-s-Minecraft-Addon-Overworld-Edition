package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoreBuildingBlocks extends Component {

    public static Block dirtyBricks, crackedBricks, mossyBricks, kitchenTiles, smallKitchenTiles;
    public static Block stoneTile, smallStoneTile, andesiteTile, smallAndesiteTile, dioriteTile, smallDioriteTile, graniteTile, smallGraniteTile,
            stoneBrickTile, smallStoneBrickTile, brickTile, smallBrickTile;

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
    }

}