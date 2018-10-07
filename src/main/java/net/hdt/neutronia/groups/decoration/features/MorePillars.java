package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.hdt.neutronia.base.Neutronia.NEUTRONIA_MAIN;

public class MorePillars extends Component {

    public static Block sandstonePillar, redSandstonePillar, stonePillar, polishedAndesitePillar, polishedGranitePillar, polishedDioritePillar, stoneBrickPillar, prismarineColumn;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        prismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "prismarine_column").setCreativeTab(NEUTRONIA_MAIN);
        sandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "sandstone_pillar").setCreativeTab(NEUTRONIA_MAIN);
        redSandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "red_sandstone_pillar").setCreativeTab(NEUTRONIA_MAIN);
        stonePillar = new BlockNeutroniaPillar(Material.ROCK, "stone_pillar").setCreativeTab(NEUTRONIA_MAIN);
        polishedAndesitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_andesite_pillar").setCreativeTab(NEUTRONIA_MAIN);
        polishedDioritePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_diorite_pillar").setCreativeTab(NEUTRONIA_MAIN);
        polishedGranitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_granite_pillar").setCreativeTab(NEUTRONIA_MAIN);
        stoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "stone_brick_pillar").setCreativeTab(NEUTRONIA_MAIN);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}