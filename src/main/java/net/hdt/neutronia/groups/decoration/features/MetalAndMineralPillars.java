package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockSpecialPillar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MetalAndMineralPillars extends Component {

    public static Block diamondPillar;
    public static Block emeraldPillar;
    public static Block ironPillar;
    public static Block goldPillar;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        diamondPillar = new BlockSpecialPillar(Material.IRON, "diamond_pillar", CreativeTabs.DECORATIONS, 5.0F, 10.0F);
        emeraldPillar = new BlockSpecialPillar(Material.IRON, "emerald_pillar", CreativeTabs.DECORATIONS, 5.0F, 10.0F);
        ironPillar = new BlockSpecialPillar(Material.IRON, "iron_pillar", CreativeTabs.DECORATIONS, 5.0F, 10.0F);
        goldPillar = new BlockSpecialPillar(Material.IRON, "gold_pillar", CreativeTabs.DECORATIONS, 5.0F, 10.0F);
    }

}