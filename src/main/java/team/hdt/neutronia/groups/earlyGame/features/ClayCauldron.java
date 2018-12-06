package team.hdt.neutronia.groups.earlyGame.features;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.earlyGame.blocks.BlockClayCauldron;

public class ClayCauldron extends Component {

    private static BlockClayCauldron clayCauldron;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        clayCauldron = new BlockClayCauldron();
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}