package net.hdt.neutronia.modules.building.features;

import net.hdt.huskylib2.blocks.BlockMod;
import net.hdt.neutronia.base.module.Feature;
import net.hdt.neutronia.base.module.GlobalConfig;
import net.hdt.neutronia.modules.building.blocks.BlockSoulSandstone;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SoulSandstone extends Feature {

	public static BlockMod soul_sandstone;

	boolean enableStairs;
	boolean enableWalls;

	@Override
	public void setupConfig() {
		enableStairs = loadPropBool("Enable stairs", "", true);
		enableWalls = loadPropBool("Enable walls", "", true) && GlobalConfig.enableVariants;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		soul_sandstone = new BlockSoulSandstone();

		/*RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soul_sandstone),
				"SS", "SS",
				'S', ProxyRegistry.newStack(Blocks.SOUL_SAND));
		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soul_sandstone, 4, 2),
				"SS", "SS",
				'S', ProxyRegistry.newStack(soul_sandstone, 1, 0));*/

		/*IBlockState defaultState = soul_sandstone.getDefaultState();

		String slabName = "_slab";
		BlockNeutroniaSlab halfSlab = new BlockSoulSandstoneSlab(false);
		BlockModSlab.initSlab(soul_sandstone, OreDictionary.WILDCARD_VALUE, halfSlab, new BlockSoulSandstoneSlab(true));
		
		RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(soul_sandstone, 1, 1),
				"S", "S",
				'S', ProxyRegistry.newStack(halfSlab, 1, 0));
		
		if(enableStairs)
			BlockModStairs.initStairs(soul_sandstone, 0, new BlockSoulSandstoneStairs());*/
		
		VanillaWalls.add("soul_sandstone", soul_sandstone, 0, enableWalls);

	}

	@Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}


}
