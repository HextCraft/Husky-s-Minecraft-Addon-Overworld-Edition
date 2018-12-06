package team.hdt.neutronia.groups.world.features.overworld;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.blocks.BlockNeutroniaFence;
import team.hdt.neutronia.base.blocks.BlockNeutroniaFenceGate;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import team.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import team.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import team.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenDeadTree;

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
        VanillaStairsAndSlabs.add("dead", deadPlanks, 0, true);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}