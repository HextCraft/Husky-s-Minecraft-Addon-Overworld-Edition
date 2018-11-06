package net.hdt.neutronia.groups.craftingExtension.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class NewMiscBlocks extends Component {

    public static Block bell;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        bell = new BlockNeutroniaBase(Material.IRON, "bell");
    }

}