package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CutGlazedTerracotta extends Component {

    private Block[] cutGlazedTerracotta = new Block[16];

    /*public CutGlazedTerracotta() {
        super(GlobalConfig.enableExperimentalFeatures);
    }*/

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor dyeColor : EnumDyeColor.values()) {
            cutGlazedTerracotta[dyeColor.getMetadata()] = new BlockNeutroniaPillar(Material.CLAY, String.format("%s_cut_glazed_terracotta", dyeColor.getName())).setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    @Override
    public String getDescription() {
        return "Adds more glazed terracotta variants";
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}