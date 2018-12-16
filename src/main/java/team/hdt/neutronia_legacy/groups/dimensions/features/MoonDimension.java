package team.hdt.neutronia_legacy.groups.dimensions.features;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.dimensions.world.providers.MoonWorldProvider;

public class MoonDimension extends Component {

    public static final DimensionType MOON = DimensionType.register("Moon", "_moon", 9987, MoonWorldProvider.class, false);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DimensionManager.registerDimension(9987, MOON);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
