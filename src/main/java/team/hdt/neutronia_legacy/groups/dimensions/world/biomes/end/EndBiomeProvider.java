package team.hdt.neutronia_legacy.groups.dimensions.world.biomes.end;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import team.hdt.neutronia_legacy.groups.dimensions.world.gen.end.layer.EndGenLayer;

public class EndBiomeProvider extends BiomeProvider {
    public EndBiomeProvider(World world) {
        super();
        GenLayer[] genLayers = EndGenLayer.initializeAllBiomeGenerators(world.getSeed(), world.getWorldType());
        ObfuscationReflectionHelper.setPrivateValue(BiomeProvider.class, this, genLayers[0], "field_76944_d", "genBiomes");
        ObfuscationReflectionHelper.setPrivateValue(BiomeProvider.class, this, genLayers[1], "field_76945_e", "biomeIndexLayer");
    }
}