package team.hdt.neutronia.groups.decoration.features;

import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaColored;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.decoration.blocks.BlockColoredRedstoneLamp;

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

}
