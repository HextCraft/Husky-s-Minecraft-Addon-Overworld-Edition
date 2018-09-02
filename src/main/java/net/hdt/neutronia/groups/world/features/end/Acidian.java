package net.hdt.neutronia.groups.world.features.end;

import net.hdt.neutronia.base.blocks.BlockNeutroniaPane;
import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.end.BlockEndBase;
import net.hdt.neutronia.groups.building.features.VanillaStairsAndSlabs;
import net.hdt.neutronia.groups.building.features.VanillaWalls;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Objects;

public class Acidian extends Component {

    public Block naturalAcidian, acidianBricks, acidianPillar, chiseledAcidian, acidianBars;
    private CreativeTabs TAB = NCreativeTabs.END_EXPANSION_TAB;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        naturalAcidian = new BlockEndBase(Material.ROCK, "natural_acidian").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        acidianBricks = new BlockEndBase(Material.ROCK, "acidian_bricks").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        acidianPillar = new BlockNeutroniaPillar(Material.ROCK, "acidian_pillar").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        chiseledAcidian = new BlockEndBase(Material.ROCK, "chiseled_acidian").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        acidianBars = new BlockNeutroniaPane("acidian_bars", Material.ROCK).setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        VanillaStairsAndSlabs.add(Objects.requireNonNull(acidianBricks.getRegistryName()).getPath(), acidianBricks, 0, true);
        VanillaStairsAndSlabs.add(Objects.requireNonNull(chiseledAcidian.getRegistryName()).getPath(), chiseledAcidian, 0, true);
        VanillaStairsAndSlabs.add(Objects.requireNonNull(naturalAcidian.getRegistryName()).getPath(), naturalAcidian, 0, true);
        VanillaWalls.add(acidianBricks.getRegistryName().getPath(), acidianBricks, 0, true);
        VanillaWalls.add(chiseledAcidian.getRegistryName().getPath(), chiseledAcidian, 0, true);
        VanillaWalls.add(naturalAcidian.getRegistryName().getPath(), naturalAcidian, 0, true);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}