package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FrostedBlocks extends Component {

    public static Block[] frostedClay = new Block[16];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            frostedClay[dyeColor.getMetadata()] = new BlockNeutroniaBase(Material.ROCK, String.format("frozen_%s_terracotta", dyeColor.getName()), false);
            VanillaStairsAndSlabs.add(String.format("frozen_%s_terracotta", dyeColor.getName()), frostedClay[dyeColor.getMetadata()], 0, true, false, false);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

}