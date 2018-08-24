package net.hdt.neutronia.groups.tweaks.features;

import com.google.common.collect.ImmutableSet;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BlastProofShulkerBoxes extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ImmutableSet.of(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX,
                Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX,
                Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX,
                Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX)
                .forEach((b) -> b.setResistance(2000));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}