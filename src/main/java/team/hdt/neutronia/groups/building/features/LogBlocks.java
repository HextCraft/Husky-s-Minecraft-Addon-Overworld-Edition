package team.hdt.neutronia.groups.building.features;

import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.base.groups.GlobalConfig;
import team.hdt.neutronia.init.NItems;

public class LogBlocks extends Component {

    public static final Block[] strippedLogs = new Block[6];

    private boolean acacia, birch, darkOak, jungle, oak, spruce;
    private boolean enableSlabsAndStairs, enableWalls, enableFences;

    @Override
    public void setupConfig() {
        acacia = loadPropBool("Acacia Log blocks", "", true);
        birch = loadPropBool("Acacia Log blocks", "", true);
        darkOak = loadPropBool("Acacia Log blocks", "", true);
        jungle = loadPropBool("Acacia Log blocks", "", true);
        oak = loadPropBool("Acacia Log blocks", "", true);
        spruce = loadPropBool("Acacia Log blocks", "", true);
        enableSlabsAndStairs = loadPropBool("Enable Slabs and Stairs", "", true) & GlobalConfig.enableVariants;
        enableWalls = loadPropBool("Enable walls", "", false) & GlobalConfig.enableVariants;
        enableFences = loadPropBool("Enable fences", "", true) & GlobalConfig.enableVariants;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
            strippedLogs[enumType.getMetadata()] = new BlockNeutroniaPillar(Material.WOOD, String.format("stripped_%s_log", enumType.getName()), CreativeTabs.BUILDING_BLOCKS);
            VanillaStairsAndSlabs.add(String.format("stripped_%s_log", enumType.getName()), strippedLogs[enumType.getMetadata()], 0, true);
            ItemStack log = ProxyRegistry.newStack(enumType.getMetadata() > 3 ? Blocks.LOG2 : Blocks.LOG, 1, enumType.getMetadata() % 4);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(strippedLogs[enumType.getMetadata()], 1), log, NItems.logStripper);
        }

        if (enableSlabsAndStairs) {
            VanillaStairsAndSlabs.add("acacia_log", Blocks.LOG2, 0, acacia);
            VanillaStairsAndSlabs.add("birch_log", Blocks.LOG, 2, birch);
            VanillaStairsAndSlabs.add("dark_oak_log", Blocks.LOG2, 1, darkOak);
            VanillaStairsAndSlabs.add("jungle_log", Blocks.LOG, 3, jungle);
            VanillaStairsAndSlabs.add("oak_log", Blocks.LOG, 0, oak);
            VanillaStairsAndSlabs.add("spruce_log", Blocks.LOG, 1, spruce);
        }
        if (enableWalls) {
            VanillaWalls.add("acacia_log", Blocks.LOG2, 0, acacia);
            VanillaWalls.add("birch_log", Blocks.LOG, 2, birch);
            VanillaWalls.add("dark_oak_log", Blocks.LOG2, 1, darkOak);
            VanillaWalls.add("jungle_log", Blocks.LOG, 3, jungle);
            VanillaWalls.add("oak_log", Blocks.LOG, 0, oak);
            VanillaWalls.add("spruce_log", Blocks.LOG, 1, spruce);
        }

        if (enableFences) {
            VanillaFencesAndFenceGates.add("stripped_acacia_log", strippedLogs[BlockPlanks.EnumType.ACACIA.getMetadata()], new ItemStack(Items.STICK), 0, true);
            VanillaFencesAndFenceGates.add("stripped_birch_log", strippedLogs[BlockPlanks.EnumType.BIRCH.getMetadata()], new ItemStack(Items.STICK), 2, true);
            VanillaFencesAndFenceGates.add("stripped_dark_oak_log", strippedLogs[BlockPlanks.EnumType.DARK_OAK.getMetadata()], new ItemStack(Items.STICK), 1, true);
            VanillaFencesAndFenceGates.add("stripped_jungle_log", strippedLogs[BlockPlanks.EnumType.JUNGLE.getMetadata()], new ItemStack(Items.STICK), 3, true);
            VanillaFencesAndFenceGates.add("stripped_oak_log", strippedLogs[BlockPlanks.EnumType.OAK.getMetadata()], new ItemStack(Items.STICK), 0, true);
            VanillaFencesAndFenceGates.add("stripped_spruce_log", strippedLogs[BlockPlanks.EnumType.SPRUCE.getMetadata()], new ItemStack(Items.STICK), 1, true);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
