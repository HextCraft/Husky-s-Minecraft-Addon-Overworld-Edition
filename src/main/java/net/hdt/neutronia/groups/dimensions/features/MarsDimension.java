package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.world.providers.MarsWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MarsDimension extends Component {

    public static final DimensionType MARS = DimensionType.register("Mars", "_mars", 9989, MarsWorldProvider.class, false);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DimensionManager.registerDimension(9989, MARS);
    }

    @Override
    public String getDescription() {
        return "Adds Mars";
    }

}