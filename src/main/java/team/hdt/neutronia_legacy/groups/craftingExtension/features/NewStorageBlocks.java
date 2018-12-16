package team.hdt.neutronia_legacy.groups.craftingExtension.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.craftingExtension.blocks.BlockBarrel;
import team.hdt.neutronia_legacy.groups.craftingExtension.blocks.BlockLectern;
import team.hdt.neutronia_legacy.groups.craftingExtension.tileEntities.TileEntityBarrel;

public class NewStorageBlocks extends Component {

    public static Block[] barrels = new Block[6], lecterns = new Block[6];

    public static final int barrelGuiID = 0;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            barrels[type.getMetadata()] = new BlockBarrel(type);
            lecterns[type.getMetadata()] = new BlockLectern(type);
        }
        GameRegistry.registerTileEntity(TileEntityBarrel.class, "neutronia_legacy:barrel");
    }

}
