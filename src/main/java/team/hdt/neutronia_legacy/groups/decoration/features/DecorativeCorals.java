package team.hdt.neutronia_legacy.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.decoration.blocks.corals.BlockDecorativeCoralBlockBase;
import team.hdt.neutronia_legacy.groups.decoration.blocks.corals.BlockDecorativeCoralPlantBase;
import team.hdt.neutronia_legacy.properties.EnumCoralColor;

public class DecorativeCorals extends Component {

    private static int size = EnumCoralColor.values().length;

    public static final Block[] decorativeCoralBlock = new Block[size];
    public static final Block[] decorativeDeadCoralBlock = new Block[size];
    public static final Block[] decorativeCoralFan = new Block[size];
    public static final Block[] decorativeDeadCoralFan = new Block[size];
    public static final Block[] decorativeCoralFanWall = new Block[size];
    public static final Block[] decorativeDeadCoralFanWall = new Block[size];
    public static final Block[] decorativeCoral = new Block[size];
    public static final Block[] decorativeDeadCoral = new Block[size];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for (EnumCoralColor coralColor : EnumCoralColor.values()) {
            decorativeCoralBlock[coralColor.getMetadata()] = new BlockDecorativeCoralBlockBase(String.format("decorative_%s_coral_block", coralColor.getName()));
            decorativeDeadCoralBlock[coralColor.getMetadata()] = new BlockDecorativeCoralBlockBase(String.format("decorative_dead_%s_coral_block", coralColor.getName()));
            decorativeCoralFan[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_%s_coral_fan", coralColor.getName()));
            decorativeDeadCoralFan[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_dead_%s_coral_fan", coralColor.getName()));
            decorativeCoralFanWall[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_%s_coral_wall_fan", coralColor.getName()));
            decorativeDeadCoralFanWall[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_dead_%s_coral_wall_fan", coralColor.getName()));
            decorativeCoral[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_%s_coral", coralColor.getName()));
            decorativeDeadCoral[coralColor.getMetadata()] = new BlockDecorativeCoralPlantBase(String.format("decorative_dead_%s_coral", coralColor.getName()));
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
