package net.hdt.neutronia.groups.decoration.features;

import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.neutronia.base.blocks.BlockNeutroniaColoredWall;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.base.BlockColoredAlt;
import net.hdt.neutronia.blocks.base.BlockModColoredSlab;
import net.hdt.neutronia.blocks.overworld.BlockOverworldColoredSlab;
import net.hdt.neutronia.blocks.overworld.BlockOverworldColoredStair;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ColoredPlanks extends Component {

    private static final Block[] coloredPlanks = new Block[16];
    private static final BlockOverworldColoredSlab[] coloredSlabsSingle = new BlockOverworldColoredSlab[16];
    private static final BlockOverworldColoredSlab[] coloredSlabsDouble = new BlockOverworldColoredSlab[16];
    private static final BlockStairs[] coloredStairs = new BlockStairs[16];

    private static void addColored(String name, Block block, int meta, boolean slab, boolean stairs, EnumDyeColor color) {
        IBlockState state = block.getStateFromMeta(meta);

        if (stairs) {
            coloredStairs[color.getMetadata()] = new BlockOverworldColoredStair(name, state, color, CreativeTabs.BUILDING_BLOCKS);
            BlockModStairs.initStairs(block, meta, coloredStairs[color.getMetadata()]);
        }
        if (slab) {
            coloredSlabsSingle[color.getMetadata()] = new BlockOverworldColoredSlab(name, color, state.getMaterial(), false);
            coloredSlabsDouble[color.getMetadata()] = new BlockOverworldColoredSlab(name, color, state.getMaterial(), true);
            BlockModColoredSlab.initSlab(block, meta, coloredSlabsSingle[color.getMetadata()], coloredSlabsDouble[color.getMetadata()]);
        }
    }

    private static void addColoredWalls(String name, Block block, int meta, EnumDyeColor color) {
        IBlockState state = block.getStateFromMeta(meta);
        Block[] coloredWallBlocks = new Block[16];
        coloredWallBlocks[color.getMetadata()] = new BlockNeutroniaColoredWall(name, state, color);
        BlockNeutroniaColoredWall.initWall(block, meta, coloredWallBlocks[color.getMetadata()]);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor color : EnumDyeColor.values()) {
            coloredPlanks[color.getMetadata()] = new BlockColoredAlt("colored_plank", color).setCreativeTab(NCreativeTabs.WOOD_EXPANSION_TAB);
            addColored("colored_planks", coloredPlanks[color.getMetadata()], 0, true, false, color);
//            addColoredWalls("colored_planks", coloredPlanks[color.getMetadata()], 0, color);
        }
        addColoredBlocks(coloredPlanks);
        addColoredStairs(coloredStairs);
        addColoredSlabs(coloredSlabsSingle, coloredSlabsDouble);
    }

    @Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}

}