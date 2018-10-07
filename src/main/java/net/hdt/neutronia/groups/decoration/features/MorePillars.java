package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.hdt.neutronia.base.Neutronia.CREATIVE_TAB;

public class MorePillars extends Component {

    public static Block sandstonePillar, redSandstonePillar, stonePillar, polishedAndesitePillar, polishedGranitePillar, polishedDioritePillar, stoneBrickPillar, prismarineColumn;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        prismarineColumn = new BlockNeutroniaPillar(Material.ROCK, "prismarine_column").setCreativeTab(CREATIVE_TAB);
        sandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "sandstone_pillar").setCreativeTab(CREATIVE_TAB);
        redSandstonePillar = new BlockNeutroniaPillar(Material.ROCK, "red_sandstone_pillar").setCreativeTab(CREATIVE_TAB);
        stonePillar = new BlockNeutroniaPillar(Material.ROCK, "stone_pillar").setCreativeTab(CREATIVE_TAB);
        polishedAndesitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_andesite_pillar").setCreativeTab(CREATIVE_TAB);
        polishedDioritePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_diorite_pillar").setCreativeTab(CREATIVE_TAB);
        polishedGranitePillar = new BlockNeutroniaPillar(Material.ROCK, "polished_granite_pillar").setCreativeTab(CREATIVE_TAB);
        stoneBrickPillar = new BlockNeutroniaPillar(Material.ROCK, "stone_brick_pillar").setCreativeTab(CREATIVE_TAB);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}