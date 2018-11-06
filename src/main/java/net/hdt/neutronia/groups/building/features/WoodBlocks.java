package net.hdt.neutronia.groups.building.features;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.init.NItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WoodBlocks extends Component {
    
    private static int woodAmount = 6;

    public static final Block[] barkBlocks = new Block[woodAmount], chiseledBarkBlocks = new Block[woodAmount], strippedBarkBlocks = new Block[woodAmount], unnamedChiseledBarkBlock = new Block[woodAmount];

    private boolean enableSlabsAndStairs, enableWalls;

    @Override
    public void setupConfig() {
        enableSlabsAndStairs = loadPropBool("Enable Slabs and Stairs", "", true);
        enableWalls = loadPropBool("Enable walls", "", false);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
//            potterySpinner[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine", enumType.getName()), false).setCreativeTab(CREATIVE_TAB);
//            potterySpinnerActive[enumType.getMetadata()] = new BlockPotteryClayMachine(enumType.getMetadata(), String.format("%s_pottery_clay_machine_active", enumType.getName()), true).setCreativeTab(null);
            barkBlocks[enumType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_wood", enumType.getName()), true);
            VanillaStairsAndSlabs.add(String.format("%s_wood", enumType.getName()), barkBlocks[enumType.getMetadata()], 0, enableSlabsAndStairs);
            VanillaWalls.add(String.format("%s_wood", enumType.getName()), barkBlocks[enumType.getMetadata()], 0, enableWalls);
            chiseledBarkBlocks[enumType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_wood_chiseled", enumType.getName()), true);
            strippedBarkBlocks[enumType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("stripped_%s_wood", enumType.getName()), true);
            VanillaStairsAndSlabs.add(String.format("stripped_%s_wood", enumType.getName()), strippedBarkBlocks[enumType.getMetadata()], 0, enableSlabsAndStairs);
            VanillaWalls.add(String.format("stripped_%s_wood", enumType.getName()), strippedBarkBlocks[enumType.getMetadata()], 0, enableWalls);
//            logDowels[enumType.getMetadata()] = new BlockRodBase(String.format("%s_log_dowel", enumType.getName()), CREATIVE_TAB, false);
//            strippedLogDowels[enumType.getMetadata()] = new BlockRodBase(String.format("stripped_%s_log_dowel", enumType.getName()), CREATIVE_TAB, false);
//            plankDowels[enumType.getMetadata()] = new BlockRodBase(String.format("%s_plank_dowel", enumType.getName()), CREATIVE_TAB, false);
//            coffins[enumType.getMetadata()] = new BlockOverworldBase(Material.WOOD, enumType.getName() + "_coffin", true).setCreativeTab(CREATIVE_TAB);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(strippedBarkBlocks[enumType.getMetadata()], 1), ProxyRegistry.newStack(barkBlocks[enumType.getMetadata()], 1), NItems.logStripper);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(chiseledBarkBlocks[enumType.getMetadata()], 1), ProxyRegistry.newStack(barkBlocks[enumType.getMetadata()], 1), NItems.chisel);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
