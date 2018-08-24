package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AxeLeaves extends Component {
    @Override
    public String getFeatureDescription() {
        return "Axes are fast at breaking leaves";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Blocks.LEAVES.setHarvestLevel("axe", 0);
        Blocks.LEAVES2.setHarvestLevel("axe", 1);
    }

}
