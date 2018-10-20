package net.hdt.neutronia.groups.world.features.end;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.blocks.BlockNeutroniaTrapdoor;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import net.hdt.neutronia.groups.world.blocks.BlockEndDoor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Objects;

public class PurhoganyWood extends Component {

    public static Block purhoganyLog, purhoganyPlanks, purhoganyDoor, purhoganyTrapdoor;
    private CreativeTabs TAB = Neutronia.CREATIVE_TAB;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        purhoganyLog = new BlockNeutroniaPillar(Material.WOOD, "purhogany_log");
        purhoganyPlanks = new BlockNeutroniaBase(Material.WOOD, "purhogany_planks");
        purhoganyDoor = new BlockEndDoor(Material.WOOD, "purhogany_door");
        purhoganyTrapdoor = new BlockNeutroniaTrapdoor("purhogany_trapdoor");
        VanillaStairsAndSlabs.add(Objects.requireNonNull(purhoganyPlanks.getRegistryName()).getPath(), purhoganyPlanks, 0, true);
        VanillaStairsAndSlabs.add(Objects.requireNonNull(purhoganyLog.getRegistryName()).getPath(), purhoganyLog, 0, true);
//        VanillaWalls.add(purhoganyPlanks.getRegistryName().getPath(), purhoganyPlanks, 0, true);
//        VanillaWalls.add(purhoganyLog.getRegistryName().getPath(), purhoganyLog, 0, true);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}