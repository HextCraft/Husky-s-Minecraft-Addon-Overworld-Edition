package team.hdt.neutronia_legacy.groups.decoration.features;

import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaColored;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.decoration.blocks.BlockColoredCandles;

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
