package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.world.biomes.sun.BiomeSunMain;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

public class SunBiomes extends Component {

    public static final Biome SUN_MAIN = new BiomeSunMain();

    @SubscribeEvent
    public void registerBiome(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(SUN_MAIN.setRegistryName(MOD_ID, "sun"));
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
