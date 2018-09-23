package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.world.providers.MoonWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
