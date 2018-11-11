package net.hdt.neutronia.init;

import net.hdt.huskylib2.block.BlockModStairs;
import net.hdt.huskylib2.block.MRSlab;
import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.blocks.overworld.BlockFireflyBulb;
import net.hdt.neutronia.groups.building.blocks.stair.BlockVanillaStairs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NBlocks {

    //Wood Blocks
    public static final Block[] potterySpinner = new Block[6], potterySpinnerActive = new Block[6];
    public static final Block fireflyBulbOff, fireflyBulbOn;
    // Sea Blocks
    private static final Block driedKelpBlock;
    private static final Block wrautnaut, wrautnautOld, wrautnautPorthole;

    static {
        driedKelpBlock = new BlockNeutroniaBase(Material.PLANTS, "dried_kelp_block", false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        wrautnaut = new BlockNeutroniaBase(Material.IRON, "wrautnaut", false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        wrautnautOld = new BlockNeutroniaBase(Material.IRON, "old_wrautnaut", false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        wrautnautPorthole = new BlockNeutroniaBase(Material.IRON, "wrautnaut_porthole", false).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        // Frosted versions of vanilla stones & dirt
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            add(String.format("%s_terracotta", dyeColor.getName()), Blocks.STAINED_HARDENED_CLAY, dyeColor.getMetadata(), true, true);
        }

        //Misc
        fireflyBulbOff = new BlockFireflyBulb(false);
        fireflyBulbOn = new BlockFireflyBulb(true);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    private static void add(String name, Block block, int meta, boolean slabs, boolean stairs) {
        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";

        if (stairs) {
            BlockModStairs.initStairs(block, meta, (BlockStairs) new BlockVanillaStairs(stairsName, state).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        }
        if (slabs) {
            MRSlab singleSlab = new MRSlab.Half(name + "_slab", block.getMaterial(block.getDefaultState()), CreativeTabs.BUILDING_BLOCKS, 0.0F);
            MRSlab doubleSlab = new MRSlab.Double(name + "_slab_double", block.getMaterial(block.getDefaultState()), CreativeTabs.BUILDING_BLOCKS, 0.0F);
            MRSlab.registerSlab(block, meta, singleSlab, doubleSlab);
        }
    }

}