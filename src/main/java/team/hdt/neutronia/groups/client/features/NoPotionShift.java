package team.hdt.neutronia.groups.client.features;

import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.groups.Component;

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
