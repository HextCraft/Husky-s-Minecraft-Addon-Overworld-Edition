package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.corals.BlockCoralBlock;
import net.hdt.neutronia.groups.world.blocks.corals.BlockCoralFan;
import net.hdt.neutronia.groups.world.blocks.corals.BlockCoralPlant;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class Corals extends Component {

    private static int size = EnumCoralColor.values().length;

    public static final Block[] coralFan = new Block[size];
    public static final Block[] deadCoralFan = new Block[size];
    public static final Block[] coral = new Block[size];
    public static final Block[] coralFanWall = new Block[size];
    public static final Block[] deadCoralFanWall = new Block[size];
    private static final Block[] coralBlock = new Block[size];
    private static final Block[] deadCoralBlock = new Block[size];
    private static final Block[] deadCoral = new Block[size];
    private static final ArrayList<Block> livingCorals = new ArrayList<>(size);
    private static final ArrayList<Block> deadCorals = new ArrayList<>(size);

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumCoralColor coralColor : EnumCoralColor.values()) {
            coralBlock[coralColor.getMetadata()] = new BlockCoralBlock(coralColor, false, livingCorals, deadCorals);
            deadCoralBlock[coralColor.getMetadata()] = new BlockCoralBlock(coralColor, true, livingCorals, deadCorals);
            coralFan[coralColor.getMetadata()] = new BlockCoralFan(coralColor, false, true, livingCorals, deadCorals);
            deadCoralFan[coralColor.getMetadata()] = new BlockCoralFan(coralColor, true, true, livingCorals, deadCorals);
            coralFanWall[coralColor.getMetadata()] = new BlockCoralFan(coralColor, "_coral_wall_fan", false, false, livingCorals, deadCorals);
            deadCoralFanWall[coralColor.getMetadata()] = new BlockCoralFan(coralColor, "_coral_wall_fan", true, false, livingCorals, deadCorals);
            coral[coralColor.getMetadata()] = new BlockCoralPlant(coralColor, false, livingCorals, deadCorals);
            deadCoral[coralColor.getMetadata()] = new BlockCoralPlant(coralColor, true, livingCorals, deadCorals);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
