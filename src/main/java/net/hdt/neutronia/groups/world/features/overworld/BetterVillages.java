package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BetterVillages extends Component {

    @SubscribeEvent
    public static void onMapGen(InitMapGenEvent event) {
        if (event.getType().equals(InitMapGenEvent.EventType.VILLAGE)) {
//            event.setNewGen(new MapGenVillage());
        }
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }

}
