package net.hdt.neutronia.groups.musicRevamp.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.musicRevamp.blocks.BlockComposer;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Composer extends Component {

    public static Block composer;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        composer = new BlockComposer();
    }

}
