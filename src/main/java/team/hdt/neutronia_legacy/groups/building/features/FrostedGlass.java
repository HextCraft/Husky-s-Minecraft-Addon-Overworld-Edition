package team.hdt.neutronia_legacy.groups.building.features;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.building.blocks.BlockFrostedGlass;
import team.hdt.neutronia_legacy.groups.building.blocks.BlockFrostedGlassPane;

public class FrostedGlass extends Component {

    public static Block frosted_glass;
    public static Block frosted_glass_pane;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        frosted_glass = new BlockFrostedGlass();
        frosted_glass_pane = new BlockFrostedGlassPane();

        /*RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(frosted_glass, 4),
                "GGG", "GIG", "GGG",
                'G', ProxyRegistry.newStack(Blocks.GLASS),
                'I', Blocks.PACKED_ICE);

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(frosted_glass, 4),
                "III", "IGI", "III",
                'G', ProxyRegistry.newStack(Blocks.GLASS),
                'I', Blocks.PACKED_ICE);

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(frosted_glass_pane, 16),
                "GGG", "GGG",
                'G', ProxyRegistry.newStack(frosted_glass));*/
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}