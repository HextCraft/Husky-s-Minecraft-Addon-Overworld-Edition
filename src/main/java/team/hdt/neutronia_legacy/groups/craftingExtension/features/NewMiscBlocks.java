package team.hdt.neutronia_legacy.groups.craftingExtension.features;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.craftingExtension.blocks.BlockBell;

public class NewMiscBlocks extends Component {

    public static Block bell;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        bell = new BlockBell();
    }

}