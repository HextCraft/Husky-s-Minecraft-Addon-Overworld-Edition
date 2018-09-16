package net.hdt.neutronia.groups.world.features.overworld.changers;

import net.hdt.neutronia.base.registry.block.recipe.BlockIngredient;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class BiomeIngredientChanger extends IngredientChanger {

    public BiomeIngredientChanger(BlockIngredient ingredient, IBlockState state) {
        super(ingredient, state);
    }

    @Override
    public IBlockState change(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
        BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(world.getBiome(pos), state);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getReplacement() != null) {
            return event.getReplacement();
        }
        return state;
    }
}
