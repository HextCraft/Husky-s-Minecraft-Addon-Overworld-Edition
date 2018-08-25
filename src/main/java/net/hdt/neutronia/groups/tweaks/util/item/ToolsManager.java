package net.hdt.neutronia.groups.tweaks.util.item;

import net.hdt.neutronia.base.util.ReflectionLib;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Collections;
import java.util.Set;

/**
 * Set of methods dealing with ItemTools.
 *
 * @author Koward
 */
public final class ToolsManager {
    private ToolsManager() {
    }


    public static Item.ToolMaterial getToolMaterial(ItemStack tool) {
        Item item = tool.getItem();
        if (item instanceof ItemTool)
            return Item.ToolMaterial.valueOf(((ItemTool) item).getToolMaterialName());
        return null;
    }

    public static void setAxesAsEffectiveAgainst(Block... blocks) {
        for (Item item : Item.REGISTRY) {
            if (!(item instanceof ItemAxe)) continue;
            ItemAxe tool = (ItemAxe) item;
            setToolAsEffectiveAgainst(tool, blocks);
        }
    }

    public static void setPickaxesAsEffectiveAgainst(Block... blocks) {
        for (Item item : Item.REGISTRY) {
            if (!(item instanceof ItemPickaxe)) continue;
            ItemPickaxe tool = (ItemPickaxe) item;
            setToolAsEffectiveAgainst(tool, blocks);
        }
    }

    public static Set<Block> getEffectiveBlocks(ItemTool tool) {
        return ReflectionHelper.getPrivateValue(ItemTool.class, tool, ReflectionLib.ITEM_TOOL_EFFECTIVE_BLOCKS);
    }

    public static void setToolAsEffectiveAgainst(ItemTool tool, Block... blocks) {
        Set<Block> effectiveOn = getEffectiveBlocks(tool);
        Collections.addAll(effectiveOn, blocks);
    }

    //Potential to crash if tool material is improperly assigned?
    public static float getSpeed(ItemStack stack) {
        Item.ToolMaterial material = getToolMaterial(stack);
        if (material != null)
            return material.getEfficiency();
        return 1;
    }

    public static float getSpeed(ItemStack stack, IBlockState state) {
        if (!stack.isEmpty() && stack.getItem() instanceof ItemTool) {
            ItemTool tool = (ItemTool) stack.getItem();
            return tool.getDestroySpeed(stack, state);
        }
        return 1;
    }

}
