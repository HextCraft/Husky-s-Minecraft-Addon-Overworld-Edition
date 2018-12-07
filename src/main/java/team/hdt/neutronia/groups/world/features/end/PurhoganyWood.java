package team.hdt.neutronia.groups.world.features.end;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.blocks.BlockNeutroniaTrapdoor;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.world.blocks.BlockEndDoor;

public class PurhoganyWood extends Component {

    public static Block purhoganyLog, purhoganyPlanks, purhoganyDoor, purhoganyTrapdoor;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        purhoganyLog = new BlockNeutroniaPillar(Material.WOOD, "purhogany_log");
        purhoganyPlanks = new BlockNeutroniaBase(Material.WOOD, "purhogany_planks");
        purhoganyDoor = new BlockEndDoor(Material.WOOD, "purhogany_door");
        purhoganyTrapdoor = new BlockNeutroniaTrapdoor("purhogany_trapdoor");
//        VanillaStairsAndSlabs.add(Objects.requireNonNull(purhoganyPlanks.getRegistryName()).getPath(), purhoganyPlanks, 0, true, false, true);
//        VanillaStairsAndSlabs.add(Objects.requireNonNull(purhoganyLog.getRegistryName()).getPath(), purhoganyLog, 0, true, false, true);
//        VanillaWalls.add(purhoganyPlanks.getRegistryName().getPath(), purhoganyPlanks, 0, true);
//        VanillaWalls.add(purhoganyLog.getRegistryName().getPath(), purhoganyLog, 0, true);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}