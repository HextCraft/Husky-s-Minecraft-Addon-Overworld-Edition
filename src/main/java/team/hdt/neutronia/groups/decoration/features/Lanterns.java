package team.hdt.neutronia.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.decoration.blocks.BlockChain;
import team.hdt.neutronia.groups.decoration.blocks.BlockLantern;
import team.hdt.neutronia.groups.decoration.blocks.BlockLanternRedstone;

public class Lanterns extends Component {

    public static Block ironLantern, goldLantern, prismarineLantern, ironChain, goldChain, prismarineChain, ropes;
    public static BlockLanternRedstone redstoneIronLantern, redstoneIronLanternOff, redstoneGoldLantern, redstoneGoldLanternOff;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ironLantern = new BlockLantern("iron");
        goldLantern = new BlockLantern("gold");
        prismarineLantern = new BlockLantern("prismarine");
        redstoneIronLantern = new BlockLanternRedstone("iron", true);
        redstoneIronLanternOff = new BlockLanternRedstone("iron", false);
        redstoneIronLantern.setOffBlock(redstoneIronLanternOff.getDefaultState());
        redstoneIronLanternOff.setOnBlock(redstoneIronLantern.getDefaultState());
        redstoneGoldLantern = new BlockLanternRedstone("gold", true);
        redstoneGoldLanternOff = new BlockLanternRedstone("gold", false);
        redstoneGoldLantern.setOffBlock(redstoneGoldLanternOff.getDefaultState());
        redstoneGoldLanternOff.setOnBlock(redstoneGoldLantern.getDefaultState());
        ironChain = new BlockChain("iron");
        goldChain = new BlockChain("gold");
        prismarineChain = new BlockChain("prismarine");
        ropes = new BlockChain(Material.CLOTH, "rope");
    }

}