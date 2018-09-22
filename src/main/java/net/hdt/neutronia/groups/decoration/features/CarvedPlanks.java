package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import net.hdt.neutronia.groups.building.features.VanillaWalls;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CarvedPlanks extends Component {

    private Block[] carvedPlanks = new Block[6];

    public boolean stairs, walls, slabs;

    @Override
    public void setupConfig() {
        stairs = loadProperty("Enable Stairs", true).get();
        walls = loadProperty("Enable Walls", false).get();
        slabs = loadProperty("Enable Slabs", true).get();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(BlockPlanks.EnumType plankTypes : BlockPlanks.EnumType.values()) {
            carvedPlanks[plankTypes.getMetadata()] = new BlockOverworldBase(Material.WOOD, String.format("carved_%s_planks", plankTypes.getName()), true).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
            VanillaStairsAndSlabs.add(String.format("carved_%s_planks", plankTypes.getName()), carvedPlanks[plankTypes.getMetadata()], 0, stairs, slabs, true, CreativeTabs.BUILDING_BLOCKS);
            VanillaWalls.add(String.format("carved_%s_planks", plankTypes.getName()), carvedPlanks[plankTypes.getMetadata()], 0, walls, CreativeTabs.BUILDING_BLOCKS);
        }
    }

    @Override
    public String getDescription() {
        return "This adds carved planks";
    }
    
}