package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockColoredAlt;
import net.hdt.neutronia.blocks.overworld.BlockColoredSlime;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ColoredSlimeBlocks extends Component {

    public static final BlockColoredAlt[] coloredSlimeBlocks = new BlockColoredAlt[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor color : EnumDyeColor.values()) {
            coloredSlimeBlocks[color.getMetadata()] = new BlockColoredSlime(color);
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
