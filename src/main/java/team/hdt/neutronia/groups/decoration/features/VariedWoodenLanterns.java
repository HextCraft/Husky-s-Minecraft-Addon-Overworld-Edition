package team.hdt.neutronia.groups.decoration.features;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.BlockPlanks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.decoration.blocks.BlockWoodenLantern;

public class VariedWoodenLanterns extends Component {

    private static BlockMod[] lanterns = new BlockMod[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
            lanterns[woodType.getMetadata()] = new BlockWoodenLantern(woodType);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}