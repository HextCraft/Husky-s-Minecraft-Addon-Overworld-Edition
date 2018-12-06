package team.hdt.neutronia.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.groups.Component;

public class CutGlazedTerracotta extends Component {

    public static Block[] cutGlazedTerracotta = new Block[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            cutGlazedTerracotta[dyeColor.getMetadata()] = new BlockNeutroniaPillar(Material.CLAY, String.format("%s_cut_glazed_terracotta", dyeColor.getName())).setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}