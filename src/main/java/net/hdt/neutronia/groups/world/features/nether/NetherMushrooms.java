package net.hdt.neutronia.groups.world.features.nether;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.world.NetherMushroomGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class NetherMushrooms extends Component {

    @Override
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new NetherMushroomGenerator(), 10);
    }

    @Override
    public String getDescription() {
        return "Spawns mushrooms made out of dirt and stone in the nether";
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
