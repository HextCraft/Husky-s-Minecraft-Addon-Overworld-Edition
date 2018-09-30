package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockColoredAlt;
import net.hdt.neutronia.blocks.overworld.BlockColoredRedstoneLamp;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ColoredRedstoneLamps extends Component {

    public static final BlockColoredAlt[] coloredRedstoneLamp = new BlockColoredAlt[16];
    public static final BlockColoredAlt[] coloredLitRedstoneLamp = new BlockColoredAlt[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor color : EnumDyeColor.values()) {
            coloredRedstoneLamp[color.getMetadata()] = new BlockColoredRedstoneLamp(color, false);
            coloredLitRedstoneLamp[color.getMetadata()] = new BlockColoredRedstoneLamp(color, true);
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
