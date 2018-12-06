package team.hdt.neutronia.groups.musicRevamp.features;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.musicRevamp.blocks.BlockComposer;

public class Composer extends Component {

    public static Block composer;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        composer = new BlockComposer();
    }

}
