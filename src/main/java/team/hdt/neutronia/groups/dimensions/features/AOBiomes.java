package team.hdt.neutronia.groups.dimensions.features;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.dimensions.world.biomes.mars.BiomeMarsMain;

import static team.hdt.neutronia.base.util.Reference.MOD_ID;

public class AOBiomes extends Component {

    public static final Biome MARS_MAIN = new BiomeMarsMain();

    @SubscribeEvent
    public void registerBiome(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(MARS_MAIN.setRegistryName(MOD_ID, "alien_plains"));
        event.getRegistry().register(MARS_MAIN.setRegistryName(MOD_ID, "alien_forest"));
        event.getRegistry().register(MARS_MAIN.setRegistryName(MOD_ID, "alien_mountains"));
        event.getRegistry().register(MARS_MAIN.setRegistryName(MOD_ID, "alien_craters"));
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
