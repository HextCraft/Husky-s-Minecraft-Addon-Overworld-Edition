package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.hdt.neutronia.properties.EnumNewStoneVariants;
import net.hdt.neutronia.properties.EnumNewStoneVariantsSlabsAndStairs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MoreStoneBlocks extends Component {

    //Stone Blocks
    public static final Block[] newStoneVariants = new Block[26];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // New Stone Blocks
        for (EnumNewStoneVariants newStoneVariant : EnumNewStoneVariants.values()) {
            newStoneVariants[newStoneVariant.getMetadata()] = new BlockOverworldBase(Material.ROCK, newStoneVariant.getName(), false);
        }
        for (EnumNewStoneVariantsSlabsAndStairs newStoneVariantsSlabsAndStairs : EnumNewStoneVariantsSlabsAndStairs.values()) {
            VanillaStairsAndSlabs.add(newStoneVariantsSlabsAndStairs.getName(), newStoneVariants[newStoneVariantsSlabsAndStairs.getMetadata()], 0, true, false, true);
        }
        GameRegistry.addSmelting(newStoneVariants[4], new ItemStack(newStoneVariants[1], 4), 2F);
        VanillaStairsAndSlabs.add("andesite_bricks", newStoneVariants[EnumNewStoneVariants.ANDESITE_BRICKS.getMetadata()], 0, false, true, true);
        VanillaStairsAndSlabs.add("andesite_cobble", newStoneVariants[EnumNewStoneVariants.ANDESITE_COBBLE.getMetadata()], 0, false, true, true);
        VanillaStairsAndSlabs.add("granite_bricks", newStoneVariants[EnumNewStoneVariants.GRANITE_BRICKS.getMetadata()], 0, false, true, true);
        VanillaStairsAndSlabs.add("granite_cobble", newStoneVariants[EnumNewStoneVariants.GRANITE_COBBLE.getMetadata()], 0, false, true, true);
    }

}
