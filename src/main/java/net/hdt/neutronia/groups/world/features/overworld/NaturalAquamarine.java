package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.properties.EnumNaturalAquamarineVariants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.hdt.neutronia.base.Neutronia.CREATIVE_TAB;

public class NaturalAquamarine extends Component {

    private static final Block[] naturalAquamarine = new Block[13];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumNaturalAquamarineVariants naturalAquamarineVariants : EnumNaturalAquamarineVariants.values()) {
            naturalAquamarine[naturalAquamarineVariants.getID()] = new BlockNeutroniaBase(Material.ROCK, naturalAquamarineVariants.getName(), false).setCreativeTab(CREATIVE_TAB);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
