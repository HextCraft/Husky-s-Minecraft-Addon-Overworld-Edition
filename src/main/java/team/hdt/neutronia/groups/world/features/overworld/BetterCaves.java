package team.hdt.neutronia.groups.world.features.overworld;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.world.world.gen.features.WorldGenNewCave;

public class BetterCaves extends Component {

    @SubscribeEvent
    public static void onMapGen(InitMapGenEvent event) {
        if (event.getType().equals(InitMapGenEvent.EventType.CAVE)) {
            event.setNewGen(new WorldGenNewCave());
        }
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }

}
