package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.world.CustomStructureGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MoreStructures extends Component {

    @Override
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new CustomStructureGenerator(), 1);
    }

}
