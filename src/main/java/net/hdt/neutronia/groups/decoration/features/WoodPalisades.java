package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockPalisade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WoodPalisades extends Component {

    public static Block[] palisades = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            palisades[type.getMetadata()] = new BlockPalisade(String.format("%s_palisade", type.getName()), Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, type));
        }
    }

}
