package team.hdt.neutronia_legacy.groups.client.features;

import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia_legacy.base.groups.Component;

public class NoPotionShift extends Component {

    @Override
    public boolean hasSubscriptions() {
        return isClient();
    }

    @SubscribeEvent
    public void onPotionShiftEvent(GuiScreenEvent.PotionShiftEvent event) {
        event.setCanceled(true);
    }

}
