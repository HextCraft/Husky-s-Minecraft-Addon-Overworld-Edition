package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaFence;
import net.hdt.neutronia.base.blocks.BlockNeutroniaWall;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockLogFence;
import net.hdt.neutronia.groups.decoration.blocks.BlockLogWall;
import net.hdt.neutronia.init.NItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class LogFenceAndWall extends Component {

    public static BlockNeutroniaWall strippedOakLogWall, strippedSpruceLogWall, strippedBirchLogWall, strippedJungleLogWall, strippedAcaciaLogWall, strippedDarkOakLogWall;
    public static BlockLogWall oakLogWall, spruceLogWall, birchLogWall, jungleLogWall, acaciaLogWall, darkOakLogWall;
    public static BlockNeutroniaFence strippedOakLogFence, strippedSpruceLogFence, strippedBirchLogFence, strippedJungleLogFence, strippedAcaciaLogFence, strippedDarkOakLogFence;
    public static BlockLogFence oakLogFence, spruceLogFence, birchLogFence, jungleLogFence, acaciaLogFence, darkOakLogFence;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        strippedOakLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_oak_log_wall");
        oakLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(), "oak_log_wall", strippedOakLogWall.getDefaultState(), NItems.barkItem[0]);
        strippedSpruceLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_spruce_log_wall");
        spruceLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(), "spruce_log_wall", strippedSpruceLogWall.getDefaultState(), NItems.barkItem[1]);
        strippedBirchLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_birch_log_wall");
        birchLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(), "birch_log_wall", strippedBirchLogWall.getDefaultState(), NItems.barkItem[2]);
        strippedJungleLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_jungle_log_wall");
        jungleLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(), "jungle_log_wall", strippedJungleLogWall.getDefaultState(), NItems.barkItem[3]);
        strippedAcaciaLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_acacia_log_wall");
        acaciaLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(), "acacia_log_wall", strippedAcaciaLogWall.getDefaultState(), NItems.barkItem[4]);
        strippedDarkOakLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_dark_oak_log_wall");
        darkOakLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(), "dark_oak_log_wall", strippedDarkOakLogWall.getDefaultState(), NItems.barkItem[5]);
        strippedOakLogFence = new BlockNeutroniaFence("stripped_oak_log_fence", Blocks.LOG.getDefaultState());
        oakLogFence = new BlockLogFence("oak_log_fence", Blocks.LOG.getDefaultState(), strippedOakLogFence.getDefaultState());
        strippedSpruceLogFence = new BlockNeutroniaFence("stripped_spruce_log_fence", Blocks.LOG.getDefaultState());
        spruceLogFence = new BlockLogFence("spruce_log_fence", Blocks.LOG.getDefaultState(), strippedSpruceLogFence.getDefaultState());
        strippedBirchLogFence = new BlockNeutroniaFence("stripped_birch_log_fence", Blocks.LOG.getDefaultState());
        birchLogFence = new BlockLogFence("birch_log_fence", Blocks.LOG.getDefaultState(), strippedBirchLogFence.getDefaultState());
        strippedJungleLogFence = new BlockNeutroniaFence("stripped_jungle_log_fence", Blocks.LOG.getDefaultState());
        jungleLogFence = new BlockLogFence("jungle_log_fence", Blocks.LOG.getDefaultState(), strippedJungleLogFence.getDefaultState());
        strippedAcaciaLogFence = new BlockNeutroniaFence("stripped_acacia_log_fence", Blocks.LOG.getDefaultState());
        acaciaLogFence = new BlockLogFence("acacia_log_fence", Blocks.LOG.getDefaultState(), strippedAcaciaLogFence.getDefaultState());
        strippedDarkOakLogFence = new BlockNeutroniaFence("stripped_dark_oak_log_fence", Blocks.LOG.getDefaultState());
        darkOakLogFence = new BlockLogFence("dark_oak_log_fence", Blocks.LOG.getDefaultState(), strippedDarkOakLogFence.getDefaultState());

        VanillaFencesAndFenceGates.add("acacia_log", Blocks.LOG2, new ItemStack(Items.STICK), 0, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("birch_log", Blocks.LOG, new ItemStack(Items.STICK), 2, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("dark_oak_log", Blocks.LOG2, new ItemStack(Items.STICK), 1, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("jungle_log", Blocks.LOG, new ItemStack(Items.STICK), 3, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("oak_log", Blocks.LOG, new ItemStack(Items.STICK), 0, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("spruce_log", Blocks.LOG, new ItemStack(Items.STICK), 1, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("stripped_acacia_log", Blocks.LOG2, new ItemStack(Items.STICK), 0, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("stripped_birch_log", Blocks.LOG, new ItemStack(Items.STICK), 2, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("stripped_dark_oak_log", Blocks.LOG2, new ItemStack(Items.STICK), 1, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("stripped_jungle_log", Blocks.LOG, new ItemStack(Items.STICK), 3, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("stripped_oak_log", Blocks.LOG, new ItemStack(Items.STICK), 0, false, true, true, CreativeTabs.BUILDING_BLOCKS);
        VanillaFencesAndFenceGates.add("stripped_spruce_log", Blocks.LOG, new ItemStack(Items.STICK), 1, false, true, true, CreativeTabs.BUILDING_BLOCKS);
    }

}