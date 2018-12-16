package team.hdt.neutronia_legacy.groups.tweaks.features;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.tweaks.client.item.ClockTimeGetter;
import team.hdt.neutronia_legacy.groups.tweaks.client.item.CompassAngleGetter;

public class CompassesWorkEverywhere extends Component {

    public static boolean enableCompassNerf, enableClockNerf, enableNether, enableEnd;

    @Override
    public void setupConfig() {
        enableCompassNerf = loadPropBool("Enable Compass Fix", "Make compasses always point north until crafted", true);
        enableClockNerf = loadPropBool("Enable Clock Fix", "Make clocks always show day until crafted", true);
        enableNether = loadPropBool("Enable Nether Compass", "Make compasses point to where the portal you came in from when in the nether", true);
        enableEnd = loadPropBool("Enable End Compass", "Make compasses point to center of the main island when in the end", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (enableCompassNerf || enableNether || enableEnd)
            Items.COMPASS.addPropertyOverride(new ResourceLocation("angle"), new CompassAngleGetter());

        if (enableClockNerf)
            Items.CLOCK.addPropertyOverride(new ResourceLocation("time"), new ClockTimeGetter());
    }

    @SubscribeEvent
    public void onUpdate(PlayerTickEvent event) {
        if (event.phase == Phase.START) {
            for (int i = 0; i < event.player.inventory.getSizeInventory(); i++) {
                ItemStack stack = event.player.inventory.getStackInSlot(i);
                if (stack.getItem() == Items.COMPASS)
                    CompassAngleGetter.tickCompass(event.player, stack);
                else if (stack.getItem() == Items.CLOCK)
                    ClockTimeGetter.tickClock(event.player, stack);
            }
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
