package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockWoodenLantern;
import net.minecraft.block.BlockPlanks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class VariedWoodenLanterns extends Component {

    private static BlockMod[] lanterns = new BlockMod[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(BlockPlanks.EnumType woodType : BlockPlanks.EnumType.values()) {
            lanterns[woodType.getMetadata()] = new BlockWoodenLantern(woodType);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}