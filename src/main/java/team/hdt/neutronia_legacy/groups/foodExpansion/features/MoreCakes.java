package team.hdt.neutronia_legacy.groups.foodExpansion.features;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.foodExpansion.blocks.BlockChocolateCake;

public class MoreCakes extends Component {

    public static Block chocolateCake;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        chocolateCake = new BlockChocolateCake();
    }

}