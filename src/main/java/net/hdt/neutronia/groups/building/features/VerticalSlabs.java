package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.blocks.BlockVerticalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VerticalSlabs extends Component {

    private static Block verticalSlab, verticleSlabDouble;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        verticalSlab = new BlockVerticalSlab("vertical_stone_brick_slab", Material.ROCK, false);
        verticleSlabDouble = new BlockVerticalSlab("vertical_stone_brick_slab", Material.ROCK, true);
    }

}