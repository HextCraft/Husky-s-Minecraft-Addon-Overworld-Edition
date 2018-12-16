package team.hdt.neutronia_legacy.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.properties.EnumNaturalAquamarineVariants;

public class NaturalAquamarine extends Component {

    private static final Block[] naturalAquamarine = new Block[13];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumNaturalAquamarineVariants naturalAquamarineVariants : EnumNaturalAquamarineVariants.values()) {
            naturalAquamarine[naturalAquamarineVariants.getID()] = new BlockNeutroniaBase(Material.ROCK, naturalAquamarineVariants.getName(), false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
