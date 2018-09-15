package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GlazedTerracottaPillar extends Component {

    private Block[] glazedTerracottaPillar = new Block[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor dyeColor : EnumDyeColor.values()) {
            glazedTerracottaPillar[dyeColor.getMetadata()] = new BlockNeutroniaPillar(Material.CLAY, String.format("%s_glazed_terracotta_pillar", dyeColor.getName())).setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return GlobalConfig.enableExperimentalFeatures;
    }

}