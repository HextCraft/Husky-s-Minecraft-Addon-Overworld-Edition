package net.hdt.neutronia.groups.client.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Side.CLIENT)
public class NoPotionShift extends Component {

    @SubscribeEvent
    public void onPotionShiftEvent(GuiScreenEvent.PotionShiftEvent event) {
        event.setCanceled(true);
    }

    @Override
    public String getDescription() {
        return "This makes so the gui don't shift to the side if we have potion effects";
    }
}
