package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import net.hdt.neutronia.groups.building.features.VanillaWalls;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CarvedPlanks extends Component {

    public boolean stairs, walls, slabs;
    public static final Block[] carvedPlanks = new Block[6];

    @Override
    public void setupConfig() {
        stairs = loadPropBool("Enable Stairs", "", true);
        walls = loadPropBool("Enable Walls", "", false);
        slabs = loadPropBool("Enable Slabs", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType plankTypes : BlockPlanks.EnumType.values()) {
            carvedPlanks[plankTypes.getMetadata()] = new BlockOverworldBase(Material.WOOD, String.format("carved_%s_planks", plankTypes.getName()), true);
            if (GlobalConfig.enableVariants) {
                VanillaStairsAndSlabs.add(String.format("carved_%s_planks", plankTypes.getName()), carvedPlanks[plankTypes.getMetadata()], 0, stairs, slabs, true, NCreativeTabs.NEUTRONIA_MAIN);
                VanillaWalls.add(String.format("carved_%s_planks", plankTypes.getName()), carvedPlanks[plankTypes.getMetadata()], 0, walls, NCreativeTabs.NEUTRONIA_MAIN);
            }
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }
}
