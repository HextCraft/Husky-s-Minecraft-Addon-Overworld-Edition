package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockChain;
import net.hdt.neutronia.groups.decoration.blocks.BlockLantern;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Lanterns extends Component {

    private static Block ironLantern, goldLantern, ironChain, goldChain;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ironLantern = new BlockLantern("iron_lantern");
        goldLantern = new BlockLantern("gold_lantern");
        ironChain = new BlockChain("iron_chain");
        goldChain = new BlockChain("gold_chain");
    }

}