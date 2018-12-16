package team.hdt.neutronia_legacy.base.world.gen.feature;

import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.util.Config;

import java.util.Random;

public class WorldGenFeatureBigMushroom extends WorldGenFeature {
    private IBlockState mushroomCap;
    private IBlockState mushroomStem;
    private IBlockState blockToPlaceOn;
    private Shape shape;

    public WorldGenFeatureBigMushroom(Config config) {
        super(config);
        mushroomCap = config.getBlock("mushroomCap", Blocks.BARRIER.getDefaultState());
        mushroomStem = config.getBlock("mushroomStem", Blocks.BARRIER.getDefaultState());
        blockToPlaceOn = config.getBlock("blockToPlaceOn", Blocks.BARRIER.getDefaultState());
        shape = config.getEnum("shape", Shape.class, Shape.FLAT);
    }

    public WorldGenFeatureBigMushroom(int genAttempts, float genProbability, boolean randomizeGenAttempts, int minGenHeight, int maxGenHeight, IBlockState mushroomCap, IBlockState mushroomStem, IBlockState blockToPlaceOn, Shape shape) {
        super(genAttempts, genProbability, randomizeGenAttempts, minGenHeight, maxGenHeight);
        this.mushroomCap = mushroomCap;
        this.mushroomStem = mushroomStem;
        this.blockToPlaceOn = blockToPlaceOn;
        this.shape = shape;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (mushroomCap.getBlock() == Blocks.BARRIER || mushroomStem.getBlock() == Blocks.BARRIER || blockToPlaceOn.getBlock() == Blocks.BARRIER) {
            return false;
        }

        int stemHeight = rand.nextInt(3) + 4;

        if (rand.nextInt(12) == 0) {
            stemHeight *= 2;
        }

        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + stemHeight + 1 < 256) {
            for (int y = pos.getY(); y <= pos.getY() + 1 + stemHeight; ++y) {
                int k = 3;

                if (y <= pos.getY() + 3) {
                    k = 0;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int x = pos.getX() - k; x <= pos.getX() + k && flag; ++x) {
                    for (int z = pos.getZ() - k; z <= pos.getZ() + k && flag; ++z) {
                        if (y >= 0 && y < 256) {
                            IBlockState state = world.getBlockState(mutablePos.setPos(x, y, z));

                            if (!state.getBlock().isAir(state, world, mutablePos) && !state.getBlock().isLeaves(state, world, mutablePos)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                if (world.getBlockState(pos.down()) != blockToPlaceOn) {
                    return false;
                } else {
                    int k2 = pos.getY() + stemHeight;

                    if (shape == Shape.BULB) {
                        k2 = pos.getY() + stemHeight - 3;
                    }

                    for (int l2 = k2; l2 <= pos.getY() + stemHeight; ++l2) {
                        int j3 = 1;

                        if (l2 < pos.getY() + stemHeight) {
                            ++j3;
                        }

                        if (shape == Shape.FLAT) {
                            j3 = 3;
                        }

                        int k3 = pos.getX() - j3;
                        int l3 = pos.getX() + j3;
                        int j1 = pos.getZ() - j3;
                        int k1 = pos.getZ() + j3;

                        for (int l1 = k3; l1 <= l3; ++l1) {
                            for (int i2 = j1; i2 <= k1; ++i2) {
                                int j2 = 5;

                                if (l1 == k3) {
                                    --j2;
                                } else if (l1 == l3) {
                                    ++j2;
                                }

                                if (i2 == j1) {
                                    j2 -= 3;
                                } else if (i2 == k1) {
                                    j2 += 3;
                                }

                                BlockHugeMushroom.EnumType mushroomType = BlockHugeMushroom.EnumType.byMetadata(j2);

                                if (shape == Shape.FLAT || l2 < pos.getY() + stemHeight) {
                                    if ((l1 == k3 || l1 == l3) && (i2 == j1 || i2 == k1)) {
                                        continue;
                                    }

                                    if (l1 == pos.getX() - (j3 - 1) && i2 == j1) {
                                        mushroomType = BlockHugeMushroom.EnumType.NORTH_WEST;
                                    }

                                    if (l1 == k3 && i2 == pos.getZ() - (j3 - 1)) {
                                        mushroomType = BlockHugeMushroom.EnumType.NORTH_WEST;
                                    }

                                    if (l1 == pos.getX() + (j3 - 1) && i2 == j1) {
                                        mushroomType = BlockHugeMushroom.EnumType.NORTH_EAST;
                                    }

                                    if (l1 == l3 && i2 == pos.getZ() - (j3 - 1)) {
                                        mushroomType = BlockHugeMushroom.EnumType.NORTH_EAST;
                                    }

                                    if (l1 == pos.getX() - (j3 - 1) && i2 == k1) {
                                        mushroomType = BlockHugeMushroom.EnumType.SOUTH_WEST;
                                    }

                                    if (l1 == k3 && i2 == pos.getZ() + (j3 - 1)) {
                                        mushroomType = BlockHugeMushroom.EnumType.SOUTH_WEST;
                                    }

                                    if (l1 == pos.getX() + (j3 - 1) && i2 == k1) {
                                        mushroomType = BlockHugeMushroom.EnumType.SOUTH_EAST;
                                    }

                                    if (l1 == l3 && i2 == pos.getZ() + (j3 - 1)) {
                                        mushroomType = BlockHugeMushroom.EnumType.SOUTH_EAST;
                                    }
                                }

                                if (mushroomType == BlockHugeMushroom.EnumType.CENTER && l2 < pos.getY() + stemHeight) {
                                    mushroomType = BlockHugeMushroom.EnumType.ALL_INSIDE;
                                }

                                if (pos.getY() >= pos.getY() + stemHeight - 1 || mushroomType != BlockHugeMushroom.EnumType.ALL_INSIDE) {
                                    BlockPos blockpos = new BlockPos(l1, l2, i2);
                                    IBlockState state = world.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos)) {
                                        setBlockAndNotifyAdequately(world, blockpos, mushroomCap);
                                    }
                                }
                            }
                        }
                    }

                    for (int i3 = 0; i3 < stemHeight; ++i3) {
                        IBlockState iblockstate = world.getBlockState(pos.up(i3));

                        if (iblockstate.getBlock().canBeReplacedByLeaves(iblockstate, world, pos.up(i3))) {
                            setBlockAndNotifyAdequately(world, pos.up(i3), mushroomStem);
                        }
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public enum Shape {
        FLAT,
        BULB
    }
}