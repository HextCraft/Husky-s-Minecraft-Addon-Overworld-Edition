package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GlazedTerracottaStripes extends Component {

    public static Block[] glazedTerracottaStriped = new Block[16];

    public GlazedTerracottaStripes() {
        super(GlobalConfig.enableExperimentalFeatures);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            glazedTerracottaStriped[dyeColor.getMetadata()] = new BlockNeutroniaBase(Material.CLAY, String.format("%s_glazed_terracotta_stripes", dyeColor.getName()), false).setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}