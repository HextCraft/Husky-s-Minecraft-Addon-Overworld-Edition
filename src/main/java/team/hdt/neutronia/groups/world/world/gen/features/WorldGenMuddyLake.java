package team.hdt.neutronia.groups.world.world.gen.features;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import team.hdt.neutronia.groups.world.features.overworld.MudBlocks;

import java.util.Random;

public class WorldGenMuddyLake extends WorldGenerator {
    private static final IBlockState MUD = MudBlocks.MUD.getDefaultState();
    private final Block block;

    public WorldGenMuddyLake(Block blockIn) {
        this.block = blockIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (position = position.add(-8, 0, -8); position.getY() > 5 && worldIn.isAirBlock(position); position = position.down()) {
            ;
        }

        if (position.getY() <= 4) {
            return false;
        } else {
            position = position.down(4);
            boolean[] aboolean = new boolean[2048];
            int i = rand.nextInt(4) + 4;

            for (int j = 0; j < i; ++j) {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int l = 1; l < 15; ++l) {
                    for (int i1 = 1; i1 < 15; ++i1) {
                        for (int j1 = 1; j1 < 7; ++j1) {
                            double d6 = ((double) l - d3) / (d0 / 2.0D);
                            double d7 = ((double) j1 - d4) / (d1 / 2.0D);
                            double d8 = ((double) i1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D) {
                                aboolean[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            for (int k1 = 0; k1 < 16; ++k1) {
                for (int l2 = 0; l2 < 16; ++l2) {
                    for (int k = 0; k < 8; ++k) {
                        boolean flag = !aboolean[(k1 * 16 + l2) * 8 + k] && (k1 < 15 && aboolean[((k1 + 1) * 16 + l2) * 8 + k] || k1 > 0 && aboolean[((k1 - 1) * 16 + l2) * 8 + k] || l2 < 15 && aboolean[(k1 * 16 + l2 + 1) * 8 + k] || l2 > 0 && aboolean[(k1 * 16 + (l2 - 1)) * 8 + k] || k < 7 && aboolean[(k1 * 16 + l2) * 8 + k + 1] || k > 0 && aboolean[(k1 * 16 + l2) * 8 + (k - 1)]);

                        if (flag) {
                            Material material = worldIn.getBlockState(position.add(k1, k, l2)).getMaterial();

                            if (k >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (k < 4 && !material.isSolid() && worldIn.getBlockState(position.add(k1, k, l2)).getBlock() != this.block) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    for (int y = 0; y < 8; ++y) {
                        if (aboolean[(x * 16 + z) * 8 + y]) {
                            IBlockState lakeBlock = y >= 4 ? Blocks.AIR.getDefaultState() : this.block.getDefaultState();
                            worldIn.setBlockState(position.add(x, y, z), lakeBlock, 2);
                            if (lakeBlock == this.block.getDefaultState()) {
                                if (worldIn.getBlockState(position.add(x, y - 1, z)) == Blocks.DIRT.getDefaultState()) {
                                    worldIn.setBlockState(position.add(x, y - 1, z), MUD, 2);
                                }
                            }
                        }
                    }
                }
            }

            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    for (int y = 4; y < 8; ++y) {
                        if (aboolean[(x * 16 + z) * 8 + y]) {
                            BlockPos blockpos = position.add(x, y - 1, z);

                            if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && worldIn.getLightFor(EnumSkyBlock.SKY, position.add(x, y, z)) > 0) {
                                Biome biome = worldIn.getBiome(blockpos);

                                if (biome.topBlock.getBlock() == Blocks.MYCELIUM) {
                                    worldIn.setBlockState(blockpos, Blocks.MYCELIUM.getDefaultState(), 2);
                                } else {
                                    worldIn.setBlockState(blockpos, MUD, 2);
                                }
                            }
                        }
                    }
                }
            }

            for (int k2 = 0; k2 < 16; ++k2) {
                for (int l3 = 0; l3 < 16; ++l3) {
                    int l4 = 4;

                    if (worldIn.canBlockFreezeWater(position.add(k2, 4, l3))) {
                        int flag = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 2 | 16 : 2; //Forge: With bit 5 unset, it will notify neighbors and load adjacent chunks.
                        worldIn.setBlockState(position.add(k2, 4, l3), Blocks.ICE.getDefaultState(), flag); //Forge
                    }
                }
            }

            return true;
        }
    }
}