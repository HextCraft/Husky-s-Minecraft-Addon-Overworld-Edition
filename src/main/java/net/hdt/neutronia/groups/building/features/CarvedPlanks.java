package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CarvedPlanks extends Component {

    public static final Block[] carvedPlanks = new Block[6];
    public boolean stairs, walls, slabs;

    @Override
    public void setupConfig() {
        stairs = loadPropBool("Enable Stairs", "", true);
        walls = loadPropBool("Enable Walls", "", false);
        slabs = loadPropBool("Enable Slabs", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType plankTypes : BlockPlanks.EnumType.values()) {
            carvedPlanks[plankTypes.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("carved_%s_planks", plankTypes.getName()), true);
            if (GlobalConfig.enableVariants) {
                VanillaStairsAndSlabs.add(String.format("carved_%s_planks", plankTypes.getName()), carvedPlanks[plankTypes.getMetadata()], 0, stairs, slabs, true);
                VanillaWalls.add(String.format("carved_%s_planks", plankTypes.getName()), carvedPlanks[plankTypes.getMetadata()], 0, walls);
            }
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
