package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.groups.decoration.blocks.BlockTerracottaFlowerPot;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerracottaFlowerPots extends Component {

    private static BlockTerracottaFlowerPot[] pots = new BlockTerracottaFlowerPot[16];

    @SubscribeEvent
    public static void coloringHandling(ColorHandlerEvent.Block event) {
        BlockColors blocks = event.getBlockColors();
        IBlockColor handlerBlocks = (s, w, p, t) -> t == 0 ? ((BlockTerracottaFlowerPot) s.getBlock()).color.getColorValue() : 0xFFFFFF;
        Block[][] toColor = new Block[][]{pots};
        Block[] coloredStuff = new Block[16 * toColor.length];
        for (int i = 0; i < toColor.length; i++) {
            Block[] colored = toColor[i];
            System.arraycopy(colored, 0, coloredStuff, i * 16, 16);
        }
        blocks.registerBlockColorHandler(handlerBlocks, coloredStuff);
    }

    @SubscribeEvent
    public static void coloringHandling(ColorHandlerEvent.Item event) {
        ItemColors items = event.getItemColors();
        IItemColor handlerItems = (s, t) -> event.getBlockColors().colorMultiplier(((ItemBlock) s.getItem()).getBlock().getDefaultState(), null, null, t);
        Block[][] toColor = new Block[][]{pots};
        Block[] coloredStuff = new Block[16 * toColor.length];
        for (int i = 0; i < toColor.length; i++) {
            Block[] colored = toColor[i];
            System.arraycopy(colored, 0, coloredStuff, i * 16, 16);
        }
        items.registerItemColorHandler(handlerItems, coloredStuff);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor color : EnumDyeColor.values()) {
            pots[color.getMetadata()] = new BlockTerracottaFlowerPot(color);
            RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(pots[color.getMetadata()]), ProxyRegistry.newStack(Items.FLOWER_POT), LibMisc.OREDICT_DYES.get(color.getDyeDamage()));
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}