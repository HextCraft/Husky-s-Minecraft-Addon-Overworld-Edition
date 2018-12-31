package team.hdt.neutronia.world.template;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public interface TemplatePostProcessor {
    void process(World world, Random random, BlockPos pos, IBlockState state);
}