package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.corals.BlockSeaPickle;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SeaPickles extends Component {

    public static BlockSeaPickle SEA_PICKLE;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        SEA_PICKLE = new BlockSeaPickle();
    }

}
