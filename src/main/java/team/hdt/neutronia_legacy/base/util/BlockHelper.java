package team.hdt.neutronia_legacy.base.util;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockHelper {

    public static boolean isOreDict(String id, Block block) {
        for (ItemStack stack : OreDictionary.getOres(id)) {
            if (stack.getItem() instanceof ItemBlock) {
                if (((ItemBlock) stack.getItem()).getBlock() == block) {
                    return true;
                }
            }
        }

        return false;
    }

}