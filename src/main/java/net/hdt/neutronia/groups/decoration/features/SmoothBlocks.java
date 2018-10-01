package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.MRBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SmoothBlocks extends Component {

    public static MRBlock smoothEndBrick;
    public static MRBlock smoothPrismarineBrick;
    public static MRBlock smoothElderPrismarineBrick;
    public static MRBlock smoothBrick;
    public static MRBlock smoothPurpurBlock;
    public static MRBlock smoothNetherBrick;
    public static MRBlock smoothRedNetherBrick;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        smoothEndBrick = new MRBlock(Material.ROCK, "smooth_end_brick", CreativeTabs.BUILDING_BLOCKS, 0.8F, 10.0F, 0.0F, 0.0F, SoundType.STONE);
        smoothPrismarineBrick = new MRBlock(Material.ROCK, "smooth_prismarine_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, 0.0F, 0.0F, SoundType.STONE);
        smoothElderPrismarineBrick = new MRBlock(Material.ROCK, "smooth_elder_prismarine_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, 0.0F, 0.0F, SoundType.STONE);
        smoothBrick = new MRBlock(Material.ROCK, "smooth_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, 0.0F, 0.0F, SoundType.STONE);
        smoothPurpurBlock = new MRBlock(Material.ROCK, "smooth_purpur_block", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, 0.0F, 0.0F, SoundType.STONE);
        smoothNetherBrick = new MRBlock(Material.ROCK, "smooth_nether_brick", CreativeTabs.BUILDING_BLOCKS, 2.0F, 10.0F, 0.0F, 0.0F, SoundType.STONE);
        smoothRedNetherBrick = new MRBlock(Material.ROCK, "smooth_red_nether_brick", CreativeTabs.BUILDING_BLOCKS, 1.5F, 10.0F, 0.0F, 0.0F, SoundType.STONE);
    }

}