package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoreBuildingBlocks extends Component {

    public static Block dirtyBricks, crackedBricks, mossyBricks, kitchenTiles, smallKitchenTiles;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        dirtyBricks = new BlockNeutroniaBase(Material.ROCK, "dirty_bricks");
        crackedBricks = new BlockNeutroniaBase(Material.ROCK, "cracked_bricks");
        mossyBricks = new BlockNeutroniaBase(Material.ROCK, "mossy_bricks");
        kitchenTiles = new BlockNeutroniaBase(Material.ROCK, "kitchen_tiles");
        smallKitchenTiles = new BlockNeutroniaBase(Material.ROCK, "small_kitchen_tiles");
    }

}