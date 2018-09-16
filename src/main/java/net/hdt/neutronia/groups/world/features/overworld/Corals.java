package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.corals.*;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class Corals extends Component {

    private static int size = EnumCoralColor.values().length;

    private static final Block[] coralFan = new Block[size];
    private static final Block[] deadCoralFan = new Block[size];
    private static final Block[] coral = new Block[size];
    private static final Block[] coralFanWall = new Block[size];
    private static final Block[] deadCoralFanWall = new Block[size];
    private static final Block[] coralBlock = new Block[size];
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
