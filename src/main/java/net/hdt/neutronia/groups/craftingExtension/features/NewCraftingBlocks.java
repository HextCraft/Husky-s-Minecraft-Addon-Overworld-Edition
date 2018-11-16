package net.hdt.neutronia.groups.craftingExtension.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.craftingExtension.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class NewCraftingBlocks extends Component {

    public static Block grindstone, stoneCutter, smoker, blastFurnace, smithingTable;
    public static Block[] fletchingTables = new Block[6], cartographerTables = new Block[6], looms = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        grindstone = new BlockGrindstone();
        stoneCutter = new BlockStoneCutter();
        smithingTable = new BlockSmithingTable();
        for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            fletchingTables[type.getMetadata()] = new BlockFletchingTable(type);
            cartographerTables[type.getMetadata()] = new BlockCartographerTable(type);
            looms[type.getMetadata()] = new BlockLoom(type);
        }
        smoker = new BlockSmoker();
        blastFurnace = new BlockBlastFurnace();
    }
}
