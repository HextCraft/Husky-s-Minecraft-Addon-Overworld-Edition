package team.hdt.neutronia_legacy.base.world.gen.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.util.Config;

import java.util.Random;

public class WorldGenFeatureFluid extends WorldGenFeature {
    private IBlockState blockToSpawn;
    private IBlockState blockToTarget;
    private boolean hidden;

    public WorldGenFeatureFluid(Config config) {
        super(config);
        blockToSpawn = config.getBlock("blockToSpawn", Blocks.BARRIER.getDefaultState());
        blockToTarget = config.getBlock("blockToTarget", Blocks.BARRIER.getDefaultState());
        hidden = config.getBoolean("hidden", true);
    }

    public WorldGenFeatureFluid(int genAttempts, float genProbability, boolean randomizeGenAttempts, int minGenHeight, int maxGenHeight, IBlockState blockToSpawn, IBlockState blockToTarget, boolean hidden) {
        super(genAttempts, genProbability, randomizeGenAttempts, minGenHeight, maxGenHeight);
        this.blockToSpawn = blockToSpawn;
        this.blockToTarget = blockToTarget;
        this.hidden = hidden;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (blockToSpawn.getBlock() == Blocks.BARRIER || blockToTarget.getBlock() == Blocks.BARRIER) {
            return false;
        }

        if (world.getBlockState(pos.up()) != blockToTarget) {
            return false;
        } else if (!world.isAirBlock(pos) && world.getBlockState(pos) != blockToTarget) {
            return false;
        } else {
            int i = 0;

            if (world.getBlockState(pos.west()) == blockToTarget) {
                i++;
            }

            if (world.getBlockState(pos.east()) == blockToTarget) {
                i++;
            }

            if (world.getBlockState(pos.north()) == blockToTarget) {
                i++;
            }

            if (world.getBlockState(pos.south()) == blockToTarget) {
                i++;
            }

            if (world.getBlockState(pos.down()) == blockToTarget) {
                i++;
            }

            int j = 0;

            if (world.isAirBlock(pos.west())) {
                j++;
            }

            if (world.isAirBlock(pos.east())) {
                j++;
            }

            if (world.isAirBlock(pos.north())) {
                j++;
            }

            if (world.isAirBlock(pos.south())) {
                j++;
            }

            if (world.isAirBlock(pos.down())) {
                j++;
            }

            if (!hidden && i == 4 && j == 1 || i == 5) {
                world.setBlockState(pos, blockToSpawn, 2);
                world.immediateBlockTick(pos, blockToSpawn, rand);
            }

            return true;
        }
    }
}