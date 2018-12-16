package team.hdt.neutronia_legacy.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.building.features.VanillaStairsAndSlabs;
import team.hdt.neutronia_legacy.properties.EnumAquamarineVariants;

public class DecorativeAquamarine extends Component {

    public static final Block[] decorativeAquamarine = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumAquamarineVariants aquamarineVariants : EnumAquamarineVariants.values()) {
            decorativeAquamarine[aquamarineVariants.getID()] = new BlockNeutroniaBase(Material.ROCK, aquamarineVariants.getName(), false).setCreativeTab(CreativeTabs.DECORATIONS);
            VanillaStairsAndSlabs.add(aquamarineVariants.getName(), decorativeAquamarine[aquamarineVariants.getID()], 0, true);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
