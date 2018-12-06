package team.hdt.neutronia.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.*;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import team.hdt.neutronia.groups.world.blocks.BlockCoconut;
import team.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import team.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import team.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import team.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenPalmTree;
import team.hdt.neutronia.items.ItemCoconut;

public class PalmTrees extends Component {

    public static Block coconut;
    public static Item crackedCoconut;
    public static BlockPVJLog palmLog;
    public static BlockNeutroniaBase palmPlanks;
    public static BlockPVJLeaves palmLeaves;
    public static BlockPVJSapling palmSapling;
    public static BlockNeutroniaTrapdoor palmTrapdoor;
    public static BlockNeutroniaDoor palmDoor;
    public static BlockNeutroniaFence palmFence;
    public static BlockNeutroniaFenceGate palmFenceGate;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        coconut = new BlockCoconut();
        crackedCoconut = new ItemCoconut(coconut);
        palmLog = new BlockPVJLog("palm_log");
        palmPlanks = new BlockNeutroniaBase(Material.WOOD, "palm_planks");
        palmLeaves = new BlockPVJLeaves("palm_leaves");
        palmSapling = new BlockPVJSapling("palm_sapling", new WorldGenPalmTree());
        palmLeaves.setSapling(palmSapling);
        palmTrapdoor = new BlockNeutroniaTrapdoor("palm_trapdoor");
        palmDoor = new BlockNeutroniaDoor("palm_door");
        palmFence = new BlockNeutroniaFence("palm_fence", palmPlanks.getDefaultState());
        palmFenceGate = new BlockNeutroniaFenceGate("palm_fence_gate");
        VanillaStairsAndSlabs.add("palm", palmPlanks, 0, true);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}