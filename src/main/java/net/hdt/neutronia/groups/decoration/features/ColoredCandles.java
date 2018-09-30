package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockColoredAlt;
import net.hdt.neutronia.groups.decoration.blocks.BlockColoredCandles;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ColoredCandles extends Component {

    public static BlockColoredAlt[] coloredCandles = new BlockColoredAlt[16];
    public static BlockColoredAlt[] coloredLitCandles = new BlockColoredAlt[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor color : EnumDyeColor.values()) {
            coloredCandles[color.getMetadata()] = new BlockColoredCandles(color, false);
            coloredLitCandles[color.getMetadata()] = new BlockColoredCandles(color, true);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

}
