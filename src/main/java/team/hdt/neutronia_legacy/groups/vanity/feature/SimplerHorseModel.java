package team.hdt.neutronia_legacy.groups.vanity.feature;

import net.minecraft.entity.passive.*;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.vanity.client.renderer.RenderAbstractHorseOverride;
import team.hdt.neutronia_legacy.groups.vanity.client.renderer.RenderHorseOverride;

public class SimplerHorseModel extends Component {

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityHorse.class, RenderHorseOverride::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDonkey.class, RenderAbstractHorseOverride::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMule.class, RenderAbstractHorseOverride::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonHorse.class, RenderAbstractHorseOverride::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieHorse.class, RenderAbstractHorseOverride::new);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}