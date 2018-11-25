package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockChain;
import net.hdt.neutronia.groups.decoration.blocks.BlockLantern;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Lanterns extends Component {

    private static Block lantern, chain;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        lantern = new BlockLantern("lantern");
        chain = new BlockChain("chain");
    }

}