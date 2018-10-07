package net.hdt.neutronia.groups.world.world.gen.features;

import net.hdt.neutronia.groups.world.util.GeneratorUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenCoralLump extends WorldGenerator {
    private static final IBlockState WATER = Blocks.WATER.getDefaultState();
    private final int maxSize;

    public WorldGenCoralLump(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean generate(World worldIn, Random rand, BlockPos start) {
        //add a bit of variation to size of lump
        int height = 2 + rand.nextInt(maxSize - 3);
        int width = 4 + rand.nextInt(maxSize - 3);
        int length = 4 + rand.nextInt(maxSize - 3);

        //randomly pick a coral color
        IBlockState block = GeneratorUtils.CORAL_BLOCK_TYPES[rand.nextInt(GeneratorUtils.CORAL_BLOCK_TYPES.length)];

        //set the lumps chances of not generating a block between 10-50%
        int decayChance = 10 * (rand.nextInt(5) + 1);

        for (int x = 0; x < width; x++) {
            for (int z = 0; z < length; z++) {
                for (int y = 0; y < height; y++) {
                    if (rand.nextInt(100) > decayChance) {

                        //if we're on the outside edge of the cube, skip this block
                        if ((x == 0 || x == width - 1) && (z == 0 || z == length - 1) ||
                                y == height - 1 && (x == 0 || z == 0 || x == width - 1 || z == length - 1)) {
                            continue;
                        }

                        //if we're inside the cube, skip this block
                        if (x > 0 && z > 0 && x < width - 1 && z < length - 1 && y < height - 1) {
                            continue;
                        }

                        BlockPos pos = start.add(x, y, z);
                        placeBlockInWater(worldIn, pos, block);
                    }
                }
            }
        }

        return true;
    }

    //only place blocks in water and below the sea level surface
    private void placeBlockInWater(World world, BlockPos pos, IBlockState state) {
        if (pos.getY() >= world.getSeaLevel() - 1)
            return;

        IBlockState toReplace = world.getBlockState(pos);
        if (toReplace == WATER)
            this.setBlockAndNotifyAdequately(world, pos, state);
    }

}