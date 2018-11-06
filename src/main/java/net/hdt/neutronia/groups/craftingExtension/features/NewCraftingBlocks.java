package net.hdt.neutronia.groups.craftingExtension.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class NewCraftingBlocks extends Component {

    public static Block grindstone, stoneCutter, smithingTable, smoker, blastFurnace, loom;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        grindstone = new BlockNeutroniaBase(Material.ROCK, "grindstone");
        stoneCutter = new BlockNeutroniaBase(Material.ROCK, "stone_cutter");
        smithingTable = new BlockNeutroniaBase(Material.ROCK, "smithing_table");
        smoker = new BlockNeutroniaBase(Material.ROCK, "smoker");
        blastFurnace = new BlockNeutroniaBase(Material.ROCK, "blast_furnace");
        loom = new BlockNeutroniaBase(Material.ROCK, "loom");
    }
}
