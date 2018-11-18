package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaColored;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockColoredCandles;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ColoredCandles extends Component {

    public static BlockNeutroniaColored[] coloredCandles = new BlockNeutroniaColored[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor color : EnumDyeColor.values()) {
            coloredCandles[color.getMetadata()] = new BlockColoredCandles(color);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
