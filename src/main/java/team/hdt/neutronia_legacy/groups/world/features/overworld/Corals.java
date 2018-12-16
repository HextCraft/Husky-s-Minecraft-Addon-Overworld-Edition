package team.hdt.neutronia_legacy.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.world.blocks.corals.*;
import team.hdt.neutronia_legacy.properties.EnumCoralColor;

import java.util.ArrayList;

public class Corals extends Component {

    private static int size = EnumCoralColor.values().length;

    public static final Block[] coralFan = new Block[size];
    private static final Block[] deadCoralFan = new Block[size];
    public static final Block[] coral = new Block[size];
    public static final Block[] coralFanWall = new Block[size];
    private static final Block[] deadCoralFanWall = new Block[size];
    public static final Block[] coralBlock = new Block[size];
    private static final Block[] deadCoralBlock = new Block[size];
    private static final Block[] deadCoral = new Block[size];
    private static final ArrayList<Block> livingCorals = new ArrayList<>(size);
    private static final ArrayList<Block> deadCorals = new ArrayList<>(size);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumCoralColor coralColor : EnumCoralColor.values()) {
            deadCoralBlock[coralColor.getMetadata()] = new BlockDeadCoralBlock(coralColor);
            coralBlock[coralColor.getMetadata()] = new BlockCoralBlock(deadCoralBlock[coralColor.getMetadata()], coralColor);
            deadCoralFan[coralColor.getMetadata()] = new BlockDeadCoralFan(coralColor);
            coralFan[coralColor.getMetadata()] = new BlockCoralFin(deadCoralFan[coralColor.getMetadata()], coralColor);
            deadCoralFanWall[coralColor.getMetadata()] = new BlockDeadCoralWallFan(String.format("%s_coral_wall_fan", coralColor.getName()));
            coralFanWall[coralColor.getMetadata()] = new BlockCoralWallFan(deadCoralFanWall[coralColor.getMetadata()], coralColor, "_coral_wall_fan");
            coral[coralColor.getMetadata()] = new BlockCoralPlant(coralColor, false, livingCorals, deadCorals);
            deadCoral[coralColor.getMetadata()] = new BlockCoralPlant(coralColor, true, livingCorals, deadCorals);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
