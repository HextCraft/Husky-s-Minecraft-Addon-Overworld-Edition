package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AlienOverworld extends Component {

    public static final Block ALIEN_DIRT = Blocks.AIR, ALIEN_GRASS = Blocks.AIR, ALIEN_STONE = Blocks.AIR;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

}
