package net.hdt.neutronia.groups.world.features.overworld.changers;

import net.hdt.neutronia.base.registry.block.recipe.BlockIngredient;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class IngredientChanger implements IChanger {
    protected IBlockState state;
    private BlockIngredient ingredient;

    public IngredientChanger(BlockIngredient ingredient, IBlockState state) {
        this.ingredient = ingredient;
        this.state = state;
    }

    @Override
    public boolean canChange(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
        return ingredient.apply(world, pos, original);
    }

    @Override
    public IBlockState change(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
        BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(world.getBiome(pos), original);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getReplacement() != null) {
            return event.getReplacement();
        }
        return state;
    }
}
