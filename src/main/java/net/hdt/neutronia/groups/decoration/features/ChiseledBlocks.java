package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.BlockBrickChiseled;
import net.hdt.neutronia.groups.decoration.blocks.BlockNetherbrickChiseled;
import net.hdt.neutronia.groups.decoration.blocks.BlockPrismarineChiseled;
import net.hdt.neutronia.groups.decoration.blocks.BlockPurpurChiseled;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ChiseledBlocks extends Component {

    public static BlockPrismarineChiseled chiseledPrismarine;
    public static BlockPrismarineChiseled chiseledPrismarineFilled;
    public static BlockNetherbrickChiseled chiseledNetherbrick;
    public static BlockNetherbrickChiseled chiseledNetherbrickFilled;
    public static BlockPurpurChiseled chiseledPurpur;
    public static BlockPurpurChiseled chiseledPurpurFilled;
    public static BlockBrickChiseled chiseledBricks;
    public static BlockBrickChiseled chiseledBricksFilled;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        chiseledPrismarine = new BlockPrismarineChiseled("chiseled_prismarine", false);
        chiseledPrismarineFilled = new BlockPrismarineChiseled("filled_chiseled_prismarine", true);
        chiseledNetherbrick = new BlockNetherbrickChiseled("chiseled_netherbrick", false);
        chiseledNetherbrickFilled = new BlockNetherbrickChiseled("filled_chiseled_netherbrick", true);
        chiseledPurpur = new BlockPurpurChiseled("chiseled_purpur", false);
        chiseledPurpurFilled = new BlockPurpurChiseled("filled_chiseled_purpur", true);
        chiseledBricks = new BlockBrickChiseled("chiseled_bricks", false);
        chiseledBricksFilled = new BlockBrickChiseled("filled_chiseled_bricks", true);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }
}