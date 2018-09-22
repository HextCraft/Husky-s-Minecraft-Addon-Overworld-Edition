package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.blocks.overworld.BlockOverworldBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GlazedTerracottaStripes extends Component {

    private Block[] glazedTerracottaStriped = new Block[16];

    /*public GlazedTerracottaStripes() {
        super(GlobalConfig.enableExperimentalFeatures);
    }*/

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor dyeColor : EnumDyeColor.values()) {
            glazedTerracottaStriped[dyeColor.getMetadata()] = new BlockOverworldBase(Material.CLAY, String.format("%s_glazed_terracotta_stripes", dyeColor.getName()), false).setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    @Override
    public String getDescription() {
        return "Adds glazed terracotta striped which is pillars made with glazed terracotta";
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}