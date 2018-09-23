package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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