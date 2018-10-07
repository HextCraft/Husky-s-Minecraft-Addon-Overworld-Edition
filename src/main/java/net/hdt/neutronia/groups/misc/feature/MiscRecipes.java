package net.hdt.neutronia.groups.misc.feature;

import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MiscRecipes extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Blocks.SAND, 4),
                ProxyRegistry.newStack(Blocks.SANDSTONE, 1));

        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Blocks.SAND, 4),
                ProxyRegistry.newStack(Blocks.SANDSTONE, 1));

        RecipeHandler.addShapelessOreDictRecipe(ProxyRegistry.newStack(Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND).getBlock(), 4),
                ProxyRegistry.newStack(Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.CHISELED).getBlock(), 1));
    }

}