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

    public static final Block[] coralFan = new Block[5];
    public static final Block[] deadCoralFan = new Block[5];
    public static final Block[] coral = new Block[5];
    public static final Block[] coralFanWall = new Block[5];
    public static final Block[] deadCoralFanWall = new Block[5];
    private static final Block[] coralBlock = new Block[5];
    private static final Block[] deadCoralBlock = new Block[5];
    private static final Block[] deadCoral = new Block[5];
    private static final ArrayList<Block> livingCorals = new ArrayList<>(EnumCoralColor.values().length);
    private static final ArrayList<Block> deadCorals = new ArrayList<>(EnumCoralColor.values().length);
    public static Block[] pipeCoral = new Block[5];
    public static Block[] deadPipeCoral = new Block[5];
    public static Block[] seaFan = new Block[5];
    public static Block[] deadSeaFan = new Block[5];

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
            /*pipeCoral[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "pipe_coral");
            deadPipeCoral[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "dead_pipe_coral");
            seaFan[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "sea_fan");
            deadSeaFan[coralColor.getMetadata()] = new BlockNetherDoublePlantBase(coralColor, "dead_sea_fan");*/
        }
    }

}
