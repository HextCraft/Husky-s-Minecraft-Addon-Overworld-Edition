package team.hdt.neutronia_legacy.groups.building.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaHorizontalPillar;
import team.hdt.neutronia_legacy.base.groups.Component;

public class LogPiles extends Component {

    private static Block[] logPiles = new Block[6];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            logPiles[type.getMetadata()] = new BlockNeutroniaHorizontalPillar(Material.WOOD, String.format("%s_log_pile", type.getName()));
        }
    }

}