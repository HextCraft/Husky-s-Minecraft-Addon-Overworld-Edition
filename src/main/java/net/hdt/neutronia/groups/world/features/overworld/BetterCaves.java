package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.world.gen.WorldGenNewCave;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BetterCaves extends Component {

    @SubscribeEvent
    public static void onMapGen(InitMapGenEvent event) {
        if (event.getType().equals(InitMapGenEvent.EventType.CAVE)) {
            event.setNewGen(new WorldGenNewCave());
        }
    }

    @Override
    public String getDescription() {
        return "Makes caves better";
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }

}
