package net.hdt.neutronia.world.gen;

import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.groups.decoration.features.NetherBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGen implements IWorldGenerator {

    // World Generators
    private WorldGenerator marble;
    private WorldGenerator basalt;
    private WorldGenerator limestone;
    private WorldGenerator fieryStone;
    private WorldGenerator volcanicGlowRock;
    private WorldGenerator volcanicRock;

    public OreGen() {
        marble = new WorldGenMinable(MoreStoneBlocks.newStoneVariants[7].getDefaultState(), 22);
//        basalt = new WorldGenMinable(NBlocks.newStoneVariants[3].getDefaultState(), 13);
        limestone = new WorldGenMinable(MoreStoneBlocks.newStoneVariants[5].getDefaultState(), 25);
        fieryStone = new WorldGenMinable(NetherBlocks.glowingNetherBlocks[0].getDefaultState(), 33, BlockMatcher.forBlock(Blocks.NETHERRACK));
        volcanicGlowRock = new WorldGenMinable(NetherBlocks.glowingNetherBlocks[1].getDefaultState(), 33, BlockMatcher.forBlock(Blocks.NETHERRACK));
        volcanicRock = new WorldGenMinable(NetherBlocks.glowingNetherBlocks[2].getDefaultState(), 33, BlockMatcher.forBlock(Blocks.NETHERRACK));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
                         IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == -1) {

            int i2 = world.getSeaLevel() / 2 + 1;

//            this.runGenerator(basalt, world, random, chunkX, chunkZ, 400, 1, 15);
            this.runGenerator(fieryStone, world, random, chunkX, chunkZ, 90, 0, i2 - 5 + random.nextInt(10));
            this.runGenerator(volcanicGlowRock, world, random, chunkX, chunkZ, 90, 0, i2 - 5 + random.nextInt(10));
            this.runGenerator(volcanicRock, world, random, chunkX, chunkZ, 90, 0, i2 - 5 + random.nextInt(10));
        }
        if (world.provider.getDimension() == 0) {
            this.runGenerator(marble, world, random, chunkX, chunkZ, 10, 60, 180);
            this.runGenerator(limestone, world, random, chunkX, chunkZ, 7, 10, 75);
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Wrong Height Arguments");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            BlockPos pos = new BlockPos(x, y, z);
            generator.generate(world, rand, pos);

        }
    }

}
