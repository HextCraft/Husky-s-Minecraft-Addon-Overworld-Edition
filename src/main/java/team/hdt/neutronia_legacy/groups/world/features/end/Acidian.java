package team.hdt.neutronia_legacy.groups.world.features.end;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.building.features.VanillaStairsAndSlabs;

public class Acidian extends Component {

    public static Block naturalAcidian, acidianBricks, acidianPillar, chiseledAcidian, acidianBars;
    private CreativeTabs TAB = CreativeTabs.BUILDING_BLOCKS;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        naturalAcidian = new BlockNeutroniaBase(Material.ROCK, "natural_acidian").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        acidianBricks = new BlockNeutroniaBase(Material.ROCK, "acidian_bricks").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        acidianPillar = new BlockNeutroniaPillar(Material.ROCK, "acidian_pillar").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        chiseledAcidian = new BlockNeutroniaBase(Material.ROCK, "chiseled_acidian").setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
//        acidianBars = new BlockNeutroniaPane("acidian_bars", Material.ROCK).setHardness(50.0F).setResistance(2000.0F).setCreativeTab(TAB);
        VanillaStairsAndSlabs.add("acidian_bricks", acidianBricks, 0, true);
        VanillaStairsAndSlabs.add("chiseled_acidian", chiseledAcidian, 0, true);
        VanillaStairsAndSlabs.add("natural_acidian", naturalAcidian, 0, true);
//        VanillaWalls.add(acidianBricks.getRegistryName().getPath(), acidianBricks, 0, true);
//        VanillaWalls.add(chiseledAcidian.getRegistryName().getPath(), chiseledAcidian, 0, true);
//        VanillaWalls.add(naturalAcidian.getRegistryName().getPath(), naturalAcidian, 0, true);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}