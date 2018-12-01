package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.blocks.*;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ChiseledBlocks extends Component {

    public static BlockPrismarineChiseled chiseledPrismarine;
    public static BlockPrismarineChiseled chiseledPrismarineFilled;
    public static BlockNetherBrickChiseled chiseledNetherBrick;
    public static BlockNetherBrickChiseled chiseledNetherBrickFilled;
    public static BlockPurpurChiseled chiseledPurpur;
    public static BlockPurpurChiseled chiseledPurpurFilled;
    public static BlockBrickChiseled chiseledBricks;
    public static BlockBrickChiseled chiseledBricksFilled;
    public static BlockRedNetherBrickChiseled chiseledRedNetherBrick;
    public static BlockRedNetherBrickChiseled chiseledRedNetherBrickFilled;
    public static BlockEndStoneChiseled chiseledEndStoneBrick;
    public static BlockEndStoneChiseled chiseledEndStoneBrickFilled;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        chiseledPrismarine = new BlockPrismarineChiseled("chiseled_prismarine", false);
        chiseledPrismarineFilled = new BlockPrismarineChiseled("filled_chiseled_prismarine", true);
        chiseledNetherBrick = new BlockNetherBrickChiseled("chiseled_netherbrick", false);
        chiseledNetherBrickFilled = new BlockNetherBrickChiseled("filled_chiseled_netherbrick", true);
        chiseledPurpur = new BlockPurpurChiseled("chiseled_purpur", false);
        chiseledPurpurFilled = new BlockPurpurChiseled("filled_chiseled_purpur", true);
        chiseledBricks = new BlockBrickChiseled("chiseled_bricks", false);
        chiseledBricksFilled = new BlockBrickChiseled("filled_chiseled_bricks", true);
        chiseledRedNetherBrick = new BlockRedNetherBrickChiseled("chiseled_red_nether_brick", false);
        chiseledRedNetherBrickFilled = new BlockRedNetherBrickChiseled("filled_chiseled_red_nether_brick", true);
        chiseledEndStoneBrick = new BlockEndStoneChiseled("chiseled_end_brick", false);
        chiseledEndStoneBrickFilled = new BlockEndStoneChiseled("filled_chiseled_end_brick", true);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}