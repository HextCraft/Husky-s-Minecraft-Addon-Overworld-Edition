package team.hdt.neutronia_legacy.groups.world.features.overworld;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.*;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.building.features.VanillaStairsAndSlabs;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockPVJLeaves;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockPVJLog;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockPVJSapling;
import team.hdt.neutronia_legacy.groups.world.world.gen.features.tree.WorldGenCherryTree;

public class CherryTrees extends Component {

    public static BlockPVJLog cherryLog;
    public static BlockNeutroniaBase cherryPlanks;
    public static BlockPVJLeaves cherryLeaves;
    public static BlockPVJSapling cherrySapling;
    public static BlockNeutroniaTrapdoor cherryTrapdoor;
    public static BlockNeutroniaDoor cherryDoor;
    public static BlockNeutroniaFence cherryFence;
    public static BlockNeutroniaFenceGate cherryFenceGate;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        cherryLog = new BlockPVJLog("cherry_log");
        cherryPlanks = new BlockNeutroniaBase(Material.WOOD, "cherry_planks");
        cherryLeaves = new BlockPVJLeaves("cherry_leaves");
        cherrySapling = new BlockPVJSapling("cherry_sapling", new WorldGenCherryTree());
        cherryLeaves.setSapling(cherrySapling);
        cherryTrapdoor = new BlockNeutroniaTrapdoor("cherry_trapdoor");
        cherryDoor = new BlockNeutroniaDoor("cherry_door");
        cherryFence = new BlockNeutroniaFence("cherry_fence", cherryPlanks.getDefaultState());
        cherryFenceGate = new BlockNeutroniaFenceGate("cherry_fence_gate");
        VanillaStairsAndSlabs.add("cherry", cherryPlanks, 0, true);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}