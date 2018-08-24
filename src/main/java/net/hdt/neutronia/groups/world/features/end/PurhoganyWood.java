package net.hdt.neutronia.groups.world.features.end;

import net.hdt.neutronia.base.blocks.BlockNeutroniaTrapdoor;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockModPillar;
import net.hdt.neutronia.blocks.end.BlockEndBase;
import net.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import net.hdt.neutronia.groups.building.features.VanillaWalls;
import net.hdt.neutronia.groups.world.blocks.BlockEndDoor;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Objects;

public class PurhoganyWood extends Component {

    public Block purhoganyLog, purhoganyPlanks, purhoganyDoor, purhoganyTrapdoor;
    private CreativeTabs TAB = NCreativeTabs.END_EXPANSION_TAB;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        purhoganyLog = new BlockModPillar("purhogany_log", Material.WOOD).setCreativeTab(TAB);
        purhoganyPlanks = new BlockEndBase(Material.WOOD, "purhogany_planks").setCreativeTab(TAB);
        purhoganyDoor = new BlockEndDoor(Material.WOOD, "purhogany_door").setCreativeTab(TAB);
        purhoganyTrapdoor = new BlockNeutroniaTrapdoor("purhogany_trapdoor").setCreativeTab(TAB);
        VanillaStairsAndSlabs.add(Objects.requireNonNull(purhoganyPlanks.getRegistryName()).getPath(), purhoganyPlanks, 0, true);
        VanillaStairsAndSlabs.add(Objects.requireNonNull(purhoganyLog.getRegistryName()).getPath(), purhoganyLog, 0, true);
        VanillaWalls.add(purhoganyPlanks.getRegistryName().getPath(), purhoganyPlanks, 0, true);
        VanillaWalls.add(purhoganyLog.getRegistryName().getPath(), purhoganyLog, 0, true);
    }

}