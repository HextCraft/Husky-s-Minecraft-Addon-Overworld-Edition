package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.*;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenWillowTree;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WillowTree extends Component {

    public static BlockPVJLog willowLog;
    public static BlockNeutroniaBase willowPlanks;
    public static BlockPVJLeaves willowLeaves;
    public static BlockPVJSapling willowSapling;
    public static BlockNeutroniaTrapdoor willowTrapdoor;
    public static BlockNeutroniaDoor willowDoor;
    public static BlockNeutroniaFence willowFence;
    public static BlockNeutroniaFenceGate willowFenceGate;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        willowLog = new BlockPVJLog("willow_log");
        willowPlanks = new BlockNeutroniaBase(Material.WOOD, "willow_planks");
        willowLeaves = new BlockPVJLeaves("willow_leaves");
        willowSapling = new BlockPVJSapling("willow_sapling", new WorldGenWillowTree());
        willowLeaves.setSapling(willowSapling);
        willowTrapdoor = new BlockNeutroniaTrapdoor("willow_trapdoor");
        willowDoor = new BlockNeutroniaDoor("willow_door");
        willowFence = new BlockNeutroniaFence("willow_fence", willowPlanks.getDefaultState());
        willowFenceGate = new BlockNeutroniaFenceGate("willow_fence_gate");
        VanillaStairsAndSlabs.add("willow", willowPlanks, 0, true);
    }

    /*@SubscribeEvent
    public void onChunkDecorate(DecorateBiomeEvent.Decorate event) {
        if(event.getWorld().getBiome(Objects.requireNonNull(event.getPlacementPos())) == Biomes.SWAMPLAND) {
            if(event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE) {
                event.setResult(Event.Result.DENY);
                new WorldGenWillowTree().generate(event.getWorld(), event.getRand(), event.getPlacementPos());
            }
        }
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }*/

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}