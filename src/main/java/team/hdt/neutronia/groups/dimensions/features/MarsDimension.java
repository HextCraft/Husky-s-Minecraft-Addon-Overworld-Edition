package team.hdt.neutronia.groups.dimensions.features;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.dimensions.world.providers.MarsWorldProvider;

public class MarsDimension extends Component {

    public static final DimensionType MARS = DimensionType.register("Mars", "_mars", 9988, MarsWorldProvider.class, false);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DimensionManager.registerDimension(9988, MARS);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
