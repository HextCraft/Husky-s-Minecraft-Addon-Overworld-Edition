package net.hdt.neutronia.base.handler.server;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class WoodVariantReplacer {

    private static Function<ItemStack, Integer> compositeFunction = stack -> 0;

    private static void addReplacementFunction(Function<ItemStack, Integer> f) {
        Function<ItemStack, Integer> curr = compositeFunction;
        compositeFunction = stack -> {
            int res = f.apply(stack);
            return res == 0 ? curr.apply(stack) : res;
        };
    }

    public static void addReplacements(int size, Block... blocks) {
        for (Block b : blocks)
            addReplacementFunction(stack -> stack.getItem() == Item.getItemFromBlock(b) ? size : 0);
    }

}

