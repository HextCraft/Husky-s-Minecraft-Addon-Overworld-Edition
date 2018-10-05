package net.hdt.neutronia.groups.world.world.gen;

import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.groups.decoration.features.MoreMetals;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class OreGeneration implements IWorldGenerator {
    public static final int ORE_MIN = 8;
    public static final int ORE_MAX = 45;
    public static final int VEIN_SIZE = 4;

    public static final int ORE_LIME_MIN = 8;
    public static final int ORE_LIME_MAX = 71;
    public static final int VEIN_SIZE_LIME = 32;

    public OreGeneration() {
        GameRegistry.registerWorldGenerator(this, 5);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!(chunkGenerator instanceof ChunkGeneratorHell) && !(chunkGenerator instanceof ChunkGeneratorEnd)) {
            BlockPos pos;
            for (int n = 0; n < MoreMetals.brassVeinFrequency; n++) {
                pos = new BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(ORE_MAX - ORE_MIN) + ORE_MIN, chunkZ * 16 + random.nextInt(16));
                new WorldGenMinable(MoreMetals.brassOre.getDefaultState(), VEIN_SIZE, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
            }
            for (int n = 0; n < MoreMetals.copperVeinFrequency; n++) {
                pos = new BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(ORE_MAX - ORE_MIN) + ORE_MIN, chunkZ * 16 + random.nextInt(16));
                new WorldGenMinable(MoreMetals.copperOre.getDefaultState(), VEIN_SIZE, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
            }
            for (int n = 0; n < MoreMetals.steelVeinFrequency; n++) {
                pos = new BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(ORE_MAX - ORE_MIN) + ORE_MIN, chunkZ * 16 + random.nextInt(16));
                new WorldGenMinable(MoreMetals.steelOre.getDefaultState(), VEIN_SIZE, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
            }
            for (int n = 0; n < MoreMetals.zincVeinFrequency; n++) {
                pos = new BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(ORE_MAX - ORE_MIN) + ORE_MIN, chunkZ * 16 + random.nextInt(16));
                new WorldGenMinable(MoreMetals.zincOre.getDefaultState(), VEIN_SIZE, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
            }
            for (int n = 0; n < MoreStoneBlocks.marbleVeinFrequency; n++) {
                pos = new BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(ORE_LIME_MAX - ORE_LIME_MIN) + ORE_LIME_MIN, chunkZ * 16 + random.nextInt(16));
                new WorldGenMinable(MoreStoneBlocks.newStoneVariants[7].getDefaultState(), VEIN_SIZE_LIME, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
            }
            for (int n = 0; n < MoreStoneBlocks.limestoneVeinFrequency; n++) {
                pos = new BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(ORE_LIME_MAX - ORE_LIME_MIN) + ORE_LIME_MIN, chunkZ * 16 + random.nextInt(16));
                new WorldGenMinable(MoreStoneBlocks.newStoneVariants[5].getDefaultState(), VEIN_SIZE_LIME, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
            }

        }

    }

}