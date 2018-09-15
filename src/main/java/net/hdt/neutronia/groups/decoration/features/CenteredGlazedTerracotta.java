package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CenteredGlazedTerracotta extends Component {

    private Block[] centeredGlazedTerracotta = new Block[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor dyeColor : EnumDyeColor.values()) {
            centeredGlazedTerracotta[dyeColor.getMetadata()] = new BlockOverworldBase(Material.CLAY, String.format("centered_glazed_terracotta_%s", dyeColor.getName()), false).setCreativeTab(CreativeTabs.DECORATIONS);
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