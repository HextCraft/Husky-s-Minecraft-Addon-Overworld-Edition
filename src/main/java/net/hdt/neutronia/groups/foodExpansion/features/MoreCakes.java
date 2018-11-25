package net.hdt.neutronia.groups.foodExpansion.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.foodExpansion.blocks.BlockChocolateCake;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoreCakes extends Component {

    public static Block chocolateCake;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        chocolateCake = new BlockChocolateCake();
    }

}