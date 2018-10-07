package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaColored;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockColoredRedstoneLamp;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ColoredRedstoneLamps extends Component {

    public static final BlockNeutroniaColored[] coloredRedstoneLamp = new BlockNeutroniaColored[16];
    public static final BlockNeutroniaColored[] coloredLitRedstoneLamp = new BlockNeutroniaColored[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor color : EnumDyeColor.values()) {
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
