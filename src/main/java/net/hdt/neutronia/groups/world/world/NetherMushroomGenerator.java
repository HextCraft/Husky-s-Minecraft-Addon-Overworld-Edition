package net.hdt.neutronia.groups.world.world;

import net.hdt.neutronia.groups.world.world.gen.WorldGenNetherMushroom;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NetherMushroomGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
                         IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case -1:
                generateNether(random, chunkX, chunkZ, world);
                break;
        }
    }

    private void generateNether(Random random, int chunkX, int chunkZ, World world) {
        generateNetherMushroom(random, chunkX, chunkZ, world, 0.3F);
    }

    private void generateNetherMushroom(Random random, int chunkX, int chunkZ, World world, float probability) {
        if (random.nextFloat() < probability) {

            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int y = findGroundFromBelow(world, Blocks.NETHERRACK, x, 10, 50, z);
            if (y > 0) {
                new WorldGenNetherMushroom().generate(world, random, new BlockPos(x, y + 1, z));
            }

            x = chunkX * 16 + random.nextInt(16);
            z = chunkZ * 16 + random.nextInt(16);
            y = findGroundFromAbove(world, Blocks.NETHERRACK, x, 51, 100, z);
            if (y > 0) {
                new WorldGenNetherMushroom().generate(world, random, new BlockPos(x, y + 1, z));
            }
        }
    }

    private int findGroundFromBelow(World world, Block groundBlock, int x, int minY, int maxY, int z) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, 0, z);
        for (int y = minY; y <= maxY; y++) {
            if (world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x, y, z)).getBlock() == groundBlock) {
                return y;
            }
        }
        return 0;
    }

    private int findGroundFromAbove(World world, Block groundBlock, int x, int minY, int maxY, int z) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, 0, z);
        for (int y = maxY; y >= minY; y--) {
            if (world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x, y, z)).getBlock() == groundBlock) {
                return y;
            }
        }
        return 0;
    }

}