package net.hdt.neutronia.groups.dimensions.features;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.dimensions.blocks.BlockMoonBase;
import net.hdt.neutronia.groups.dimensions.properties.EnumMoonBlockVariants;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoonBlocks extends Component {

    public static Block[] MOON_BLOCKS = new Block[7];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumMoonBlockVariants moonBlockVariants : EnumMoonBlockVariants.values()) {
            MOON_BLOCKS[moonBlockVariants.getID()] = new BlockMoonBase(moonBlockVariants.getName(), moonBlockVariants.getMaterial(), Neutronia.NEUTRONIA_MAIN);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}