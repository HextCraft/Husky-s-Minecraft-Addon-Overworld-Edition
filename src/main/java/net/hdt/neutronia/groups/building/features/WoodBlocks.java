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

    private boolean enableSlabsAndStairs;

    @Override
    public void setupConfig() {
        enableSlabsAndStairs = loadPropBool("Enable Slabs and Stairs", "", true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
            barkBlocks[enumType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_wood", enumType.getName()), true);
            VanillaStairsAndSlabs.add(String.format("%s_wood", enumType.getName()), barkBlocks[enumType.getMetadata()], 0, enableSlabsAndStairs);
            chiseledBarkBlocks[enumType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("%s_wood_chiseled", enumType.getName()), true);
            strippedBarkBlocks[enumType.getMetadata()] = new BlockNeutroniaBase(Material.WOOD, String.format("stripped_%s_wood", enumType.getName()), true);
            VanillaStairsAndSlabs.add(String.format("stripped_%s_wood", enumType.getName()), strippedBarkBlocks[enumType.getMetadata()], 0, enableSlabsAndStairs);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(strippedBarkBlocks[enumType.getMetadata()], 1), ProxyRegistry.newStack(barkBlocks[enumType.getMetadata()], 1), NItems.logStripper);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(chiseledBarkBlocks[enumType.getMetadata()], 1), ProxyRegistry.newStack(barkBlocks[enumType.getMetadata()], 1), NItems.chisel);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
