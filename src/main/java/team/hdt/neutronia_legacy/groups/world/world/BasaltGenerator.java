package team.hdt.neutronia_legacy.groups.world.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import team.hdt.neutronia_legacy.groups.building.features.MoreStoneBlocks;
import team.hdt.neutronia_legacy.groups.world.features.overworld.BetterStoneGeneration.StoneInfo;

import java.util.Random;
import java.util.function.Supplier;

public class BasaltGenerator extends StoneInfoBasedGenerator implements IWorldGenerator {

    public BasaltGenerator(Supplier<StoneInfo> infoSupplier) {
        super(infoSupplier, MoreStoneBlocks.newStoneVariants[0].getDefaultState(), "basalt");
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generate(chunkX, chunkZ, world);
    }

    @Override
    public boolean canPlaceBlock(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return block == Blocks.STONE || block == Blocks.NETHERRACK;
    }

}
