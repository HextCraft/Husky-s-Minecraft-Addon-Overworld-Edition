package net.hdt.neutronia.groups.earlyGame.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.earlyGame.blocks.BlockClayCauldron;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClayCauldron extends Component {

    private static BlockClayCauldron clayCauldron;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        clayCauldron = new BlockClayCauldron();
    }

    @Override
    public String getDescription() {
        return "Adds a cauldron made of clay";
    }
}