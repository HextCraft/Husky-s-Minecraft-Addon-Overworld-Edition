package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.*;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenCherryTree;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}