package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.blocks.BlockNeutroniaFence;
import net.hdt.neutronia.base.blocks.BlockNeutroniaFenceGate;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenDeadTree;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class DeadTree extends Component {

    public static BlockPVJLog deadLog;
    public static BlockNeutroniaBase deadPlanks;
    public static BlockPVJSapling deadSapling;
    public static BlockNeutroniaFence deadFence;
    public static BlockNeutroniaFenceGate deadFenceGate;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        deadLog = new BlockPVJLog("dead_log");
        deadPlanks = new BlockNeutroniaBase(Material.WOOD, "dead_planks");
        deadSapling = new BlockPVJSapling("dead_sapling", new WorldGenDeadTree());
        deadFence = new BlockNeutroniaFence("dead_fence", deadPlanks.getDefaultState());
        deadFenceGate = new BlockNeutroniaFenceGate("dead_fence_gate");
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}