package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.corals.BlockDecorativeCoralBlockBase;
import net.hdt.neutronia.groups.decoration.blocks.corals.BlockDecorativeCoralPlantBase;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class DecorativeCorals extends Component {

    public static final Block[] decorativeCoralBlock = new Block[5];
    public static final Block[] decorativeDeadCoralBlock = new Block[5];
    public static final Block[] decorativeCoralFan = new Block[5];
    public static final Block[] decorativeDeadCoralFan = new Block[5];
    public static final Block[] decorativeCoralFanWall = new Block[5];
    public static final Block[] decorativeDeadCoralFanWall = new Block[5];
    public static final Block[] decorativeCoral = new Block[5];
    public static final Block[] decorativeDeadCoral = new Block[5];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumCoralColor coralColor : EnumCoralColor.values()) {
            decorativeCoralBlock[coralColor.getMetadata()] = new BlockDecorativeCoralBlockBase(String.format("decorative_%s_coral_block", coralColor.getNewName()));
            decorativeDeadCoralBlock[coralColor.getMetadata()] = new BlockDecorativeCoralBlockBase(String.format("decorative_dead_%s_coral_block", coralColor.getNewName()));
            decorativeCoralFan[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_%s_coral_fan", coralColor.getNewName()));
            decorativeDeadCoralFan[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_dead_%s_coral_fan", coralColor.getNewName()));
            decorativeCoralFanWall[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_%s_coral_wall_fan", coralColor.getNewName()));
            decorativeDeadCoralFanWall[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_dead_%s_coral_wall_fan", coralColor.getNewName()));
            decorativeCoral[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_%s_coral", coralColor.getNewName()));
            decorativeDeadCoral[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_dead_%s_coral", coralColor.getNewName()));
        }
    }

}
