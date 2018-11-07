package net.hdt.neutronia.groups.craftingExtension.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class NewCraftingBlocks extends Component {

    public static Block grindstone, stoneCutter, smoker, blastFurnace, loom;
    public static Block[] smithingTables = new Block[6], fletchingTables = new Block[6], cartographerTables = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        grindstone = new BlockNeutroniaBase(Material.ROCK, "grindstone");
        stoneCutter = new BlockNeutroniaBase(Material.ROCK, "stone_cutter");
        for(BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            smithingTables[type.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_smithing_table", type.getName()));
            fletchingTables[type.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_fletching_table", type.getName()));
            cartographerTables[type.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_cartographer_table", type.getName()));
        }
        smoker = new BlockNeutroniaBase(Material.ROCK, "smoker");
        blastFurnace = new BlockNeutroniaBase(Material.ROCK, "blast_furnace");
        loom = new BlockNeutroniaBase(Material.ROCK, "loom");
    }
}
