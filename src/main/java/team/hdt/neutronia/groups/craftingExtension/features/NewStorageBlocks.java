package team.hdt.neutronia.groups.craftingExtension.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.craftingExtension.blocks.BlockBarrel;
import team.hdt.neutronia.groups.craftingExtension.blocks.BlockLectern;

public class NewStorageBlocks extends Component {

    public static Block[] barrels = new Block[6], lecterns = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            barrels[type.getMetadata()] = new BlockBarrel(type);
            lecterns[type.getMetadata()] = new BlockLectern(type);
        }
    }

}
