package net.hdt.neutronia.groups.craftingExtension.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.craftingExtension.blocks.BlockBell;
import net.hdt.neutronia.groups.craftingExtension.blocks.BlockLantern;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class NewMiscBlocks extends Component {

    public static Block bell, lantern;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        bell = new BlockBell();
        lantern = new BlockLantern();
    }

}