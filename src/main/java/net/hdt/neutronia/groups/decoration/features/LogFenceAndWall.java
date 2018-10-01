package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaFence;
import net.hdt.neutronia.base.blocks.BlockNeutroniaWall;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.blocks.BlockLogFence;
import net.hdt.neutronia.groups.decoration.blocks.BlockLogWall;
import net.hdt.neutronia.init.NItems;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class LogFenceAndWall extends Component {

    public static BlockNeutroniaWall strippedOakLogWall;
    public static BlockLogWall oakLogWall;
    public static BlockNeutroniaWall strippedSpruceLogWall;
    public static BlockLogWall spruceLogWall;
    public static BlockNeutroniaWall strippedBirchLogWall;
    public static BlockLogWall birchLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(),"birch_log_wall" , strippedBirchLogWall.getDefaultState(), NItems.barkItem[2]);
    public static BlockNeutroniaWall strippedJungleLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_jungle_log_wall");
    public static BlockLogWall jungleLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(),"jungle_log_wall" , strippedJungleLogWall.getDefaultState(), NItems.barkItem[3]);
    public static BlockNeutroniaWall strippedAcaciaLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_acacia_log_wall");
    public static BlockLogWall acaciaLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(),"acacia_log_wall" , strippedAcaciaLogWall.getDefaultState(), NItems.barkItem[4]);
    public static BlockNeutroniaWall strippedDarkOakLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_dark_oak_log_wall");
    public static BlockLogWall darkOakLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(),"dark_oak_log_wall" , strippedDarkOakLogWall.getDefaultState(), NItems.barkItem[5]);
    public static BlockNeutroniaFence strippedOakLogFence = new BlockNeutroniaFence("stripped_oak_log_fence", Blocks.LOG.getDefaultState(), 2.0F, 5.0F);
    public static BlockLogFence oakLogFence = new BlockLogFence("oak_log_fence", Blocks.LOG.getDefaultState(), strippedOakLogFence.getDefaultState());
    public static BlockNeutroniaFence strippedSpruceLogFence = new BlockNeutroniaFence("stripped_spruce_log_fence", Blocks.LOG.getDefaultState(), 2.0F, 5.0F);
    public static BlockLogFence spruceLogFence = new BlockLogFence("spruce_log_fence", Blocks.LOG.getDefaultState(), strippedSpruceLogFence.getDefaultState());
    public static BlockNeutroniaFence strippedBirchLogFence = new BlockNeutroniaFence("stripped_birch_log_fence", Blocks.LOG.getDefaultState(), 2.0F, 5.0F);
    public static BlockLogFence birchLogFence = new BlockLogFence("birch_log_fence", Blocks.LOG.getDefaultState(), strippedBirchLogFence.getDefaultState());
    public static BlockNeutroniaFence strippedJungleLogFence = new BlockNeutroniaFence("stripped_jungle_log_fence", Blocks.LOG.getDefaultState(), 2.0F, 5.0F);
    public static BlockLogFence jungleLogFence = new BlockLogFence("jungle_log_fence", Blocks.LOG.getDefaultState(), strippedJungleLogFence.getDefaultState());
    public static BlockNeutroniaFence strippedAcaciaLogFence = new BlockNeutroniaFence("stripped_acacia_log_fence", Blocks.LOG.getDefaultState(), 2.0F, 5.0F);
    public static BlockLogFence acaciaLogFence = new BlockLogFence("acacia_log_fence", Blocks.LOG.getDefaultState(), strippedAcaciaLogFence.getDefaultState());
    public static BlockNeutroniaFence strippedDarkOakLogFence = new BlockNeutroniaFence("stripped_dark_oak_log_fence", Blocks.LOG.getDefaultState(), 2.0F, 5.0F);
    public static BlockLogFence darkOakLogFence = new BlockLogFence("dark_oak_log_fence", Blocks.LOG.getDefaultState(), strippedDarkOakLogFence.getDefaultState());

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        strippedOakLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_oak_log_wall");
        oakLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(),"oak_log_wall" , strippedOakLogWall.getDefaultState(), NItems.barkItem[0]);
        strippedSpruceLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_spruce_log_wall");
        spruceLogWall = new BlockLogWall(Blocks.LOG.getDefaultState(),"spruce_log_wall" , strippedSpruceLogWall.getDefaultState(), NItems.barkItem[1]);
        strippedBirchLogWall = new BlockNeutroniaWall(Blocks.LOG.getDefaultState(), "stripped_birch_log_wall");
    }

}