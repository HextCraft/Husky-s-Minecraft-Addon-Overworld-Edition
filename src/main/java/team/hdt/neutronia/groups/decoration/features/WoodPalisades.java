package team.hdt.neutronia.groups.decoration.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.base.groups.GroupLoader;
import team.hdt.neutronia.base.lib.LibMisc;
import team.hdt.neutronia.groups.building.features.LogBlocks;
import team.hdt.neutronia.groups.decoration.blocks.BlockPalisade;

public class WoodPalisades extends Component {

    public static Block oakPlankPalisade, sprucePlankPalisade, birchPlankPalisade, junglePlankPalisade, darkOakPlankPalisade, acaciaPlankPalisade;
    public static Block oakLogPalisade, spruceLogPalisade, birchLogPalisade, jungleLogPalisade, darkOakLogPalisade, acaciaLogPalisade;
    public static Block strippedOakLogPalisade, strippedSpruceLogPalisade, strippedBirchLogPalisade, strippedJungleLogPalisade, strippedAcaciaLogPalisade, strippedDarkOakLogPalisade;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        oakPlankPalisade = new BlockPalisade("oak_planks_palisade", Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK));
        sprucePlankPalisade = new BlockPalisade("spruce_planks_palisade", Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE));
        birchPlankPalisade = new BlockPalisade("birch_planks_palisade", Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH));
        junglePlankPalisade = new BlockPalisade("jungle_planks_palisade", Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE));
        darkOakPlankPalisade = new BlockPalisade("dark_oak_planks_palisade", Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK));
        acaciaPlankPalisade = new BlockPalisade("acacia_planks_palisade", Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA));

        oakLogPalisade = new BlockPalisade("oak_log_palisade", Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));
        spruceLogPalisade = new BlockPalisade("spruce_log_palisade", Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE));
        birchLogPalisade = new BlockPalisade("birch_log_palisade", Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH));
        jungleLogPalisade = new BlockPalisade("jungle_log_palisade", Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
        darkOakLogPalisade = new BlockPalisade("dark_oak_log_palisade", Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK));
        acaciaLogPalisade = new BlockPalisade("acacia_log_palisade", Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA));

        if(GroupLoader.isFeatureEnabled(LogBlocks.class)) {
            strippedOakLogPalisade = new BlockPalisade("stripped_oak_log_palisade", LogBlocks.strippedLogs[0].getDefaultState());
            strippedSpruceLogPalisade = new BlockPalisade("stripped_spruce_log_palisade", LogBlocks.strippedLogs[1].getDefaultState());
            strippedBirchLogPalisade = new BlockPalisade("stripped_birch_log_palisade", LogBlocks.strippedLogs[2].getDefaultState());
            strippedJungleLogPalisade = new BlockPalisade("stripped_jungle_log_palisade", LogBlocks.strippedLogs[3].getDefaultState());
            strippedAcaciaLogPalisade = new BlockPalisade("stripped_acacia_log_palisade", LogBlocks.strippedLogs[4].getDefaultState());
            strippedDarkOakLogPalisade = new BlockPalisade("stripped_dark_oak_log_palisade", LogBlocks.strippedLogs[5].getDefaultState());
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public void onEnabled() {
        LibMisc.LOGGER.info("This is a test");
    }
}