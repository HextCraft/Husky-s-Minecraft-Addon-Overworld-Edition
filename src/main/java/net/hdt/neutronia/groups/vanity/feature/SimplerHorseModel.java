package net.hdt.neutronia.groups.vanity.feature;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.vanity.client.renderer.RenderAbstractHorseOverride;
import net.hdt.neutronia.groups.vanity.client.renderer.RenderHorseOverride;
import net.minecraft.entity.passive.*;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SimplerHorseModel extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
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