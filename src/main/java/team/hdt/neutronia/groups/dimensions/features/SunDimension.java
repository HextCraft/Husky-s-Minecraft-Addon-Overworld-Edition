package team.hdt.neutronia.groups.dimensions.features;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.dimensions.world.providers.SunWorldProvider;

public class SunDimension extends Component {

    public static final DimensionType SUN = DimensionType.register("Sun", "_sun", 9986, SunWorldProvider.class, false);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DimensionManager.registerDimension(9986, SUN);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
