package team.hdt.neutronia_legacy.groups.musicRevamp.features;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.musicRevamp.blocks.BlockComposer;

public class Composer extends Component {

    public static Block composer;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        composer = new BlockComposer();
    }

}
