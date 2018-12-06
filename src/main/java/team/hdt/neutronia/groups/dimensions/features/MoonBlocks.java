package team.hdt.neutronia.groups.dimensions.features;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.dimensions.blocks.BlockMoonBase;
import team.hdt.neutronia.groups.dimensions.properties.EnumMoonBlockVariants;

public class MoonBlocks extends Component {

    public static Block[] MOON_BLOCKS = new Block[7];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumMoonBlockVariants moonBlockVariants : EnumMoonBlockVariants.values()) {
            MOON_BLOCKS[moonBlockVariants.getID()] = new BlockMoonBase(moonBlockVariants.getName(), moonBlockVariants.getMaterial(), CreativeTabs.BUILDING_BLOCKS);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}