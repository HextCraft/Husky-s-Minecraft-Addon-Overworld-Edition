package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockChain;
import net.hdt.neutronia.groups.decoration.blocks.BlockLantern;
import net.hdt.neutronia.groups.decoration.blocks.BlockLanternRedstone;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Lanterns extends Component {

    public static Block ironLantern, goldLantern, ironChain, goldChain, ropes;
    public static BlockLanternRedstone redstoneIronLantern, redstoneIronLanternOff, redstoneGoldLantern, redstoneGoldLanternOff;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ironLantern = new BlockLantern("iron_lantern");
        goldLantern = new BlockLantern("gold_lantern");
        redstoneIronLantern = new BlockLanternRedstone("lit_iron_redstone_lantern", true);
        redstoneIronLanternOff = new BlockLanternRedstone("iron_redstone_lantern", false);
        redstoneIronLantern.setOnBlock(redstoneIronLantern.getDefaultState());
        redstoneIronLantern.setOffBlock(redstoneIronLanternOff.getDefaultState());
        redstoneIronLanternOff.setOnBlock(redstoneIronLantern.getDefaultState());
        redstoneIronLanternOff.setOffBlock(redstoneIronLanternOff.getDefaultState());
        redstoneGoldLantern = new BlockLanternRedstone("lit_gold_redstone_lantern", true);
        redstoneGoldLanternOff = new BlockLanternRedstone("gold_redstone_lantern", false);
        redstoneGoldLantern.setOnBlock(redstoneGoldLantern.getDefaultState());
        redstoneGoldLantern.setOffBlock(redstoneGoldLanternOff.getDefaultState());
        redstoneGoldLanternOff.setOnBlock(redstoneGoldLantern.getDefaultState());
        redstoneGoldLanternOff.setOffBlock(redstoneGoldLanternOff.getDefaultState());
        ironChain = new BlockChain("iron_chain");
        goldChain = new BlockChain("gold_chain");
        ropes = new BlockChain("rope");
    }

}