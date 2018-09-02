package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.world.providers.SunWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SunDimension extends Component {

    public static final DimensionType SUN = DimensionType.register("Sun", "_sun", 4, SunWorldProvider.class, false);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DimensionManager.registerDimension(4, SUN);
    }

    @Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}

}
