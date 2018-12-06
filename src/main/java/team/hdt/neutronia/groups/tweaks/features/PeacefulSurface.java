package team.hdt.neutronia.groups.tweaks.features;

import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.groups.Component;

public class PeacefulSurface extends Component {

    private boolean enabled;

    @Override
    public void setupConfig() {
        enabled = loadPropBool("Enabled", "This defines if this component is enabled or not", true);
    }

    @SubscribeEvent
    public void registerTweak(LivingSpawnEvent event) {
        if (!enabled) {
            return;
        }
        if (event.getEntity() == null || !(event.getEntity() instanceof EntityMob)) {
            return;
        }
        if (event.getWorld().provider.getMoonPhase(event.getWorld().getWorldTime()) != 4 && event.getEntity().getPosition().getY() >= event.getWorld().provider.getAverageGroundLevel()) {
            event.setResult(Event.Result.DENY);
        }
    }
}