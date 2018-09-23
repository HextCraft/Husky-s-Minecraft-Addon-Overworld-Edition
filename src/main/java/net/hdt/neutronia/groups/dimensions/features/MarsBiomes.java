package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.world.biomes.mars.BiomeMarsMain;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

public class MarsBiomes extends Component {

    public static final Biome MARS_MAIN = new BiomeMarsMain();

    @SubscribeEvent
    public void registerBiome(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(MARS_MAIN.setRegistryName(MOD_ID, "mars"));
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
