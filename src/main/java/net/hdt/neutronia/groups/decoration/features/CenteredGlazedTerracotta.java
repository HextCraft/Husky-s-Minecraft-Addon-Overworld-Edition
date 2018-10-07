package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CenteredGlazedTerracotta extends Component {

    public static Block[] centeredGlazedTerracotta = new Block[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            centeredGlazedTerracotta[dyeColor.getMetadata()] = new BlockNeutroniaBase(Material.CLAY, String.format("centered_glazed_terracotta_%s", dyeColor.getName()), false).setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}