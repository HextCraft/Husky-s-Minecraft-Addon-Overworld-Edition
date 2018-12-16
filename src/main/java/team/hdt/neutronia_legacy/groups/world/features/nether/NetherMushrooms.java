package team.hdt.neutronia_legacy.groups.world.features.nether;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.world.world.NetherMushroomGenerator;

public class NetherMushrooms extends Component {

    @Override
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new NetherMushroomGenerator(), 10);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
