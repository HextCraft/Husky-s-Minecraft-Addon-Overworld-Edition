package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.*;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockCoconut;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenPalmTree;
import net.hdt.neutronia.items.ItemCoconut;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}