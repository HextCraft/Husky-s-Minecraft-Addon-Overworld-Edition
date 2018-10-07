package net.hdt.neutronia.groups.building.features;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.blocks.BlockLogPole;
import net.hdt.neutronia.groups.building.blocks.BlockPlankPole;
import net.hdt.neutronia.init.NItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.hdt.neutronia.base.Neutronia.CREATIVE_TAB;

public class LogBlocks extends Component {

    public static final Block[] strippedLogs = new Block[6], logPoles = new Block[6], strippedLogPoles = new Block[6]/*, logDowels = new Block[6], strippedLogDowels = new Block[6]*/,
            plankPoles = new Block[6];

    private boolean acacia, birch, darkOak, jungle, oak, spruce;
    private boolean enableSlabsAndStairs, enableWalls;

    @Override
    public void setupConfig() {
        acacia = loadPropBool("Acacia Log blocks", "", true);
        birch = loadPropBool("Acacia Log blocks", "", true);
        darkOak = loadPropBool("Acacia Log blocks", "", true);
        jungle = loadPropBool("Acacia Log blocks", "", true);
        oak = loadPropBool("Acacia Log blocks", "", true);
        spruce = loadPropBool("Acacia Log blocks", "", true);
        enableSlabsAndStairs = loadPropBool("Enable Slabs and Stairs", "", true);
        enableWalls = loadPropBool("Enable walls", "", false);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
            logPoles[enumType.getMetadata()] = new BlockLogPole(enumType, false);
            strippedLogs[enumType.getMetadata()] = new BlockNeutroniaPillar(Material.WOOD, String.format("stripped_%s_log", enumType.getName())).setCreativeTab(CREATIVE_TAB);
            strippedLogPoles[enumType.getMetadata()] = new BlockLogPole(enumType, true);
            VanillaStairsAndSlabs.add(String.format("stripped_%s_log", enumType.getName()), strippedLogs[enumType.getMetadata()], 0, true);
            plankPoles[enumType.getMetadata()] = new BlockPlankPole(enumType);
            ItemStack log = ProxyRegistry.newStack(enumType.getMetadata() > 3 ? Blocks.LOG2 : Blocks.LOG, 1, enumType.getMetadata() % 4);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(strippedLogs[enumType.getMetadata()], 1), log, NItems.logStripper);
        }

        if (enableSlabsAndStairs) {
            VanillaStairsAndSlabs.add("acacia_log", Blocks.LOG2, 0, acacia, CREATIVE_TAB);
            VanillaStairsAndSlabs.add("birch_log", Blocks.LOG, 2, birch, CREATIVE_TAB);
            VanillaStairsAndSlabs.add("dark_oak_log", Blocks.LOG2, 1, darkOak, CREATIVE_TAB);
            VanillaStairsAndSlabs.add("jungle_log", Blocks.LOG, 3, jungle, CREATIVE_TAB);
            VanillaStairsAndSlabs.add("oak_log", Blocks.LOG, 0, oak, CREATIVE_TAB);
            VanillaStairsAndSlabs.add("spruce_log", Blocks.LOG, 1, spruce, CREATIVE_TAB);
        }
        if (enableWalls) {
            VanillaWalls.add("acacia_log", Blocks.LOG2, 0, acacia, CREATIVE_TAB);
            VanillaWalls.add("birch_log", Blocks.LOG, 2, birch, CREATIVE_TAB);
            VanillaWalls.add("dark_oak_log", Blocks.LOG2, 1, darkOak, CREATIVE_TAB);
            VanillaWalls.add("jungle_log", Blocks.LOG, 3, jungle, CREATIVE_TAB);
            VanillaWalls.add("oak_log", Blocks.LOG, 0, oak, CREATIVE_TAB);
            VanillaWalls.add("spruce_log", Blocks.LOG, 1, spruce, CREATIVE_TAB);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
