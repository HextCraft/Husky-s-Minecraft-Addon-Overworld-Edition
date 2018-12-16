package team.hdt.neutronia_legacy.base.handler.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaColored;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaColoredSlab;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaColoredStair;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaColoredWall;
import team.hdt.neutronia_legacy.items.ItemModBlockColoredSlab;

import java.util.ArrayList;
import java.util.List;

import static team.hdt.neutronia_legacy.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MOD_ID)
public class ClientEventHandler {

    public static final List<Block[]> blocks = new ArrayList<>();
    public static final List<Block[]> slabs = new ArrayList<>();
    public static final List<Block[]> stairs = new ArrayList<>();
    public static final List<Block[]> walls = new ArrayList<>();
    private static IBlockColor blockHandler = (s, w, p, t) -> t == 0 ?
            ((BlockNeutroniaColored) s.getBlock()).color.getColorValue() : 0xFFFFFF;
    private static IBlockColor slabHandler = (s, w, p, t) -> t == 0 ?
            ((BlockNeutroniaColoredSlab) s.getBlock()).color.getColorValue() : 0xFFFFFF;
    private static IBlockColor stairHandler = (s, w, p, t) -> t == 0 ?
            ((BlockNeutroniaColoredStair) s.getBlock()).color.getColorValue() : 0xFFFFFF;
    private static IBlockColor wallHandler = (s, w, p, t) -> t == 0 ?
            ((BlockNeutroniaColoredWall) s.getBlock()).color.getColorValue() : 0xFFFFFF;
    private static IItemColor itemBlockHandler = (s, t) -> blockHandler.colorMultiplier(((ItemBlock) s.getItem())
            .getBlock().getDefaultState(), null, null, t);
    private static IItemColor itemSlabHandler = (s, t) -> slabHandler.colorMultiplier(((ItemModBlockColoredSlab) s.getItem())
            .getBlock().getDefaultState(), null, null, t);
    private static IItemColor itemStairHandler = (s, t) -> stairHandler.colorMultiplier(((ItemBlock) s.getItem())
            .getBlock().getDefaultState(), null, null, t);
    private static IItemColor itemWallHandler = (s, t) -> wallHandler.colorMultiplier(((ItemBlock) s.getItem())
            .getBlock().getDefaultState(), null, null, t);

    @SubscribeEvent
    public static void onColorBlocks(ColorHandlerEvent.Block event) {
        for (Block[] coloredBlocks : blocks) {
            event.getBlockColors().registerBlockColorHandler(blockHandler, coloredBlocks);
        }
        for (Block[] coloredBlocks : slabs) {
            event.getBlockColors().registerBlockColorHandler(slabHandler, coloredBlocks);
        }
        /*for (Block[] coloredBlocks : stairs) {
            event.getBlockColors().registerBlockColorHandler(stairHandler, coloredBlocks);
        }*/
        for (Block[] coloredBlocks : walls) {
            event.getBlockColors().registerBlockColorHandler(wallHandler, coloredBlocks);
        }
    }

    @SubscribeEvent
    public static void onItemColored(ColorHandlerEvent.Item event) {
        for (Block[] coloredBlocks : blocks) {
            event.getItemColors().registerItemColorHandler(itemBlockHandler, coloredBlocks);
        }
        for (Block[] coloredBlocks : slabs) {
            event.getItemColors().registerItemColorHandler(itemSlabHandler, coloredBlocks);
        }
        /*for (Block[] coloredBlocks : stairs) {
            event.getItemColors().registerItemColorHandler(itemStairHandler, coloredBlocks);
        }*/
        for (Block[] coloredBlocks : walls) {
            event.getItemColors().registerItemColorHandler(itemWallHandler, coloredBlocks);
        }
    }

}