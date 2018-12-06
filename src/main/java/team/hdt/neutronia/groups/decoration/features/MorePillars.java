package team.hdt.neutronia.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.groups.Component;

public class MorePillars extends Component {

    public static Block sandstonePillar, redSandstonePillar, stonePillar, polishedAndesitePillar, polishedGranitePillar, polishedDioritePillar,
            stoneBrickPillar, prismarineColumn, brickPillar, endStonePillar, netherBrickPillar;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        prismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "prismarine_column").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        sandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        redSandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "red_sandstone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        stonePillar = new BlockNeutroniaPillar(Material.ROCK, "stone_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedAndesitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_andesite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedDioritePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_diorite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        polishedGranitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_granite_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        stoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "stone_brick_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        brickPillar = new BlockNeutroniaPillar(Material.ROCK, "brick_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        endStonePillar = new BlockNeutroniaPillar(Material.ROCK, "end_brick_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        netherBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "netherbrick_pillar").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}