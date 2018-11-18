package net.hdt.neutronia.groups.building.features;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.blocks.BlockFrostedGlass;
import net.hdt.neutronia.groups.building.blocks.BlockFrostedGlassPane;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FrostedGlass extends Component {

	public static Block frosted_glass;
	public static Block frosted_glass_pane;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		frosted_glass = new BlockFrostedGlass();
		frosted_glass_pane = new BlockFrostedGlassPane();
		
		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(frosted_glass, 4),
				"GGG", "GIG", "GGG",
				'G', ProxyRegistry.newStack(Blocks.GLASS),
				'I', Blocks.PACKED_ICE);

		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(frosted_glass, 4),
				"III", "IGI", "III",
				'G', ProxyRegistry.newStack(Blocks.GLASS),
				'I', Blocks.PACKED_ICE);
		
		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(frosted_glass_pane, 16),
				"GGG", "GGG",
				'G', ProxyRegistry.newStack(frosted_glass));
	}

	@Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}
	
}