package team.hdt.neutronia.groups.foodExpansion.features;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.foodExpansion.blocks.BlockChocolateCake;

public class MoreCakes extends Component {

    public static Block chocolateCake;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        chocolateCake = new BlockChocolateCake();
    }

}