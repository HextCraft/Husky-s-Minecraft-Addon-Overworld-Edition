package team.hdt.neutronia.groups.dimensions.features;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.dimensions.world.providers.AOWorldProvider;

public class AlienOverworld extends Component {

    public static final Block ALIEN_DIRT = Blocks.AIR, ALIEN_GRASS = Blocks.AIR, ALIEN_STONE = Blocks.AIR;

    public static final DimensionType AO = DimensionType.register("Alien Overworld", "_ao", 9989, AOWorldProvider.class, false);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DimensionManager.registerDimension(9989, AO);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
