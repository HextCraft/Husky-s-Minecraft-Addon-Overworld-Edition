package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import net.hdt.neutronia.properties.EnumAquamarineVariants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.hdt.neutronia.init.NCreativeTabs.NEUTRONIA_MAIN;

public class DecorativeAquamarine extends Component {

    public static final Block[] decorativeAquamarine = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumAquamarineVariants aquamarineVariants : EnumAquamarineVariants.values()) {
            decorativeAquamarine[aquamarineVariants.getID()] = new BlockOverworldBase(Material.ROCK, aquamarineVariants.getName(), false).setCreativeTab(NEUTRONIA_MAIN);
            VanillaStairsAndSlabs.add(aquamarineVariants.getName(), decorativeAquamarine[aquamarineVariants.getID()], 0, true);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
