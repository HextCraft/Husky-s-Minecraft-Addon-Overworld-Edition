package team.hdt.neutronia_legacy.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.base.groups.GlobalConfig;

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