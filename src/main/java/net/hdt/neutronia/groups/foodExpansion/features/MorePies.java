package net.hdt.neutronia.groups.foodExpansion.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.foodExpansion.blocks.BlockPumpkinPie;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MorePies extends Component {

    private Block pumpkinPie;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        pumpkinPie = new BlockPumpkinPie();
    }

}