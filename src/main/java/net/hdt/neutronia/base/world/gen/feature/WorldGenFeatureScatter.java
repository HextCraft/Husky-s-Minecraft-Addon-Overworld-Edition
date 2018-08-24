package net.hdt.neutronia.base.world.gen.feature;

import net.hdt.neutronia.base.util.Config;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenFeatureScatter extends WorldGenFeature {
    private IBlockState blockToSpawn;
    private IBlockState blockToTarget;
    private Placement placement;

    public WorldGenFeatureScatter(Config config) {
        super(config);
        blockToSpawn = config.getBlock("blockToSpawn", Blocks.BARRIER.getDefaultState());
        blockToTarget = config.getBlock("blockToTarget", Blocks.BARRIER.getDefaultState());
        placement = config.getEnum("placement", Placement.class, Placement.ON_GROUND);
    }

    public WorldGenFeatureScatter(int genAttempts, float genProbability, boolean randomizeGenAttempts, int minGenHeight, int maxGenHeight, IBlockState blockToSpawn, IBlockState blockToTarget, Placement placement) {
        super(genAttempts, genProbability, randomizeGenAttempts, minGenHeight, maxGenHeight);
        this.blockToSpawn = blockToSpawn;
        this.blockToTarget = blockToTarget;
        this.placement = placement;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (blockToSpawn.getBlock() == Blocks.BARRIER || blockToTarget.getBlock() == Blocks.BARRIER) {
            return false;
        }

        for (int i = 0; i < 64; ++i) {
            BlockPos newPos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(newPos) && world.getBlockState(newPos.down()) == blockToTarget) {
                if (blockToSpawn instanceof BlockBush) {
                    if (((BlockBush) blockToSpawn).canBlockStay(world, placement.offsetPos(pos), blockToSpawn)) {
                        world.setBlockState(placement.offsetPos(newPos), blockToSpawn, 2);
                    }
                } else {
                    world.setBlockState(placement.offsetPos(newPos), blockToSpawn, 2);
                }
            }
        }

        return true;
    }

    public enum Placement {
        ON_GROUND(null),
        IN_GROUND(EnumFacing.DOWN);

        EnumFacing offset;

        Placement(EnumFacing offsetIn) {
            offset = offsetIn;
        }

        public BlockPos offsetPos(BlockPos pos) {
            if (offset != null) {
                return pos.offset(offset);
            } else {
                return pos;
            }
        }
    }
}