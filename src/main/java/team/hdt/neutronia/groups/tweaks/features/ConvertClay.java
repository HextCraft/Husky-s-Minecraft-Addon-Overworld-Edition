package team.hdt.neutronia.groups.tweaks.features;

import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.groups.Component;

public class ConvertClay extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Items.CLAY_BALL, 4), ProxyRegistry.newStack(Blocks.CLAY));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}