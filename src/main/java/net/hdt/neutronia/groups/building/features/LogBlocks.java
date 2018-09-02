package net.hdt.neutronia.groups.building.features;

import net.hdt.huskylib2.block.BlockModSlab;
import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockModPillar;
import net.hdt.neutronia.blocks.base.BlockRodBase;
import net.hdt.neutronia.blocks.overworld.BlockOverworldSlabBase;
import net.hdt.neutronia.blocks.overworld.BlockOverworldStairBase;
import net.hdt.neutronia.groups.building.blocks.slab.BlockWoodSlabBase;
import net.hdt.neutronia.groups.building.blocks.stair.BlockWoodStairBase;
import net.hdt.neutronia.init.NItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.hdt.neutronia.init.NCreativeTabs.WOOD_EXPANSION_TAB;

public class LogBlocks extends Component {

    private static final Block[] strippedLogs = new Block[6], logPoles = new Block[6], strippedLogPoles = new Block[6], logDowels = new Block[6], strippedLogDowels = new Block[6],
            plankButtons = new Block[6], plankPressurePlates = new Block[6], plankPoles = new Block[6];

    public static void add(String name, Block block, Material material, int meta, CreativeTabs creativeTabs) {
        add(name, block, material, meta, true, true, creativeTabs);
    }

    public static void add(String name, Block block, Material material, int meta, boolean slabs, boolean stairs, CreativeTabs creativeTabs) {
        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";
        String slabName = name + "_slab";

        if (stairs) {
            BlockModStairs.initStairs(block, meta, new BlockOverworldStairBase(stairsName, state, creativeTabs));
        }
        if (slabs) {
            BlockModSlab.initSlab(block, meta, new BlockOverworldSlabBase(slabName, material, false, creativeTabs), new BlockOverworldSlabBase(slabName, material, true, creativeTabs));
        }
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
            logPoles[enumType.getMetadata()] = new BlockRodBase(String.format("%s_log_pole", enumType.getName()), WOOD_EXPANSION_TAB, false);
            strippedLogs[enumType.getMetadata()] = new BlockModPillar(String.format("stripped_%s_log", enumType.getName()), Material.WOOD).setCreativeTab(WOOD_EXPANSION_TAB);
            strippedLogPoles[enumType.getMetadata()] = new BlockRodBase(String.format("stripped_%s_log_pole", enumType.getName()), WOOD_EXPANSION_TAB, false);
            BlockModSlab.initSlab(strippedLogs[enumType.getMetadata()], 0, new BlockWoodSlabBase(String.format("stripped_%s_log", enumType.getName()), false, WOOD_EXPANSION_TAB), new BlockWoodSlabBase(String.format("stripped_%s_log", enumType.getName()), true, WOOD_EXPANSION_TAB));
            BlockModStairs.initStairs(strippedLogs[enumType.getMetadata()], 0, new BlockWoodStairBase(String.format("stripped_%s_log", enumType.getName()), strippedLogs[enumType.getMetadata()].getDefaultState(), WOOD_EXPANSION_TAB));
            plankPoles[enumType.getMetadata()] = new BlockRodBase(Material.WOOD, String.format("%s_plank_pole", enumType.getName()), WOOD_EXPANSION_TAB, false);
            ItemStack log = ProxyRegistry.newStack(enumType.getMetadata() > 3 ? Blocks.LOG2 : Blocks.LOG, 1, enumType.getMetadata() % 4);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(strippedLogs[enumType.getMetadata()], 1), log, NItems.logStripper);
        }
        VanillaStairsAndSlabs.add("oak_log", Blocks.LOG, 0, true, true, true);
        VanillaStairsAndSlabs.add("spruce_log", Blocks.LOG, 1, true, true, true);
        VanillaStairsAndSlabs.add("birch_log", Blocks.LOG, 2, true, true, true);
        VanillaStairsAndSlabs.add("jungle_log", Blocks.LOG, 3, true, true, true);
        VanillaStairsAndSlabs.add("acacia_log", Blocks.LOG2, 0, true, true, true);
        VanillaStairsAndSlabs.add("dark_oak_log", Blocks.LOG2, 1, true, true, true);
        VanillaWalls.add("oak_log", Blocks.LOG, 0, true);
        VanillaWalls.add("spruce_log", Blocks.LOG, 1, true);
        VanillaWalls.add("birch_log", Blocks.LOG, 2, true);
        VanillaWalls.add("jungle_log", Blocks.LOG, 3, true);
        VanillaWalls.add("acacia_log", Blocks.LOG2, 0, true);
        VanillaWalls.add("dark_oak_log", Blocks.LOG2, 1, true);
    }

    @Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}

}
