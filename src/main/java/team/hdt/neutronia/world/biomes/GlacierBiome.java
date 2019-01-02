package team.hdt.neutronia.world.biomes;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.*;

import java.util.Arrays;
import java.util.Random;

public class GlacierBiome extends NBiome {

    private IBlockState[] bands;
    private long worldSeed;
    private NoiseGeneratorPerlin pillarNoise;
    private NoiseGeneratorPerlin pillarRoofNoise;
    private NoiseGeneratorPerlin bandsOffsetNoise;
    private final WorldGenIceSpike iceSpike = new WorldGenIceSpike();
    private final WorldGenIcePath icePatch = new WorldGenIcePath(4);

    public GlacierBiome(BiomeProperties properties) {
        super(properties);
        getEnableSnow();
        properties.setSnowEnabled();
        spawnableCreatureList.add(new SpawnListEntry(EntityStray.class, 10, 3, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityPolarBear.class, 3, 1, 1));
        if (topBlock == Blocks.GRASS.getDefaultState()) {
            decorator.treesPerChunk = 2;
        }
    }

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        if (rand.nextInt(100) < 1) {
            for (int i = 0; i < 3; ++i) {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                iceSpike.generate(worldIn, rand, worldIn.getHeight(pos.add(j, -3, k)));
            }
        }
        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ICE)) {
            for (int l = 0; l < 2; ++l)
            {
                int i1 = rand.nextInt(16) + 8;
                int j1 = rand.nextInt(16) + 8;
                icePatch.generate(worldIn, rand, worldIn.getHeight(pos.add(i1, 0, j1)));
            }
        }
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return rand.nextInt(3) == 0 ? PINE_TREE : SPRUCE_TREE;
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (bands == null || worldSeed != worldIn.getSeed())
        {
            generateBands(worldIn.getSeed());
        }

        if (pillarNoise == null || pillarRoofNoise == null || worldSeed != worldIn.getSeed())
        {
            Random random = new Random(worldSeed);
            pillarNoise = new NoiseGeneratorPerlin(random, 4);
            pillarRoofNoise = new NoiseGeneratorPerlin(random, 1);
        }

        worldSeed = worldIn.getSeed();
        double d4 = 0.0D;

        int k1 = x & 15;
        int l1 = z & 15;
        int i2 = worldIn.getSeaLevel();
        IBlockState iblockstate = SNOW;
        IBlockState iblockstate3 = fillerBlock;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        boolean flag = Math.cos(noiseVal / 3.0D * Math.PI) > 0.0D;
        int l = -1;
        boolean flag1 = false;
        int i1 = 0;

        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (chunkPrimerIn.getBlockState(l1, j1, k1).getMaterial() == Material.AIR && j1 < (int)d4)
            {
                chunkPrimerIn.setBlockState(l1, j1, k1, STONE);
            }

            if (j1 <= rand.nextInt(5))
            {
                chunkPrimerIn.setBlockState(l1, j1, k1, BEDROCK);
            }
            else if (i1 < 15)
            {
                IBlockState iblockstate1 = chunkPrimerIn.getBlockState(l1, j1, k1);

                if (iblockstate1.getMaterial() == Material.AIR)
                {
                    l = -1;
                }
                else if (iblockstate1.getBlock() == Blocks.STONE)
                {
                    if (l == -1)
                    {
                        flag1 = false;

                        if (k <= 0)
                        {
                            iblockstate = AIR;
                            iblockstate3 = STONE;
                        }
                        else if (j1 >= i2 - 4 && j1 <= i2 + 1)
                        {
                            iblockstate = SNOW;
                            iblockstate3 = fillerBlock;
                        }

                        if (j1 < i2 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                        {
                            iblockstate = WATER;
                        }

                        l = k + Math.max(0, j1 - i2);

                        if (j1 >= i2 - 1)
                        {
                            if (j1 > i2 + 3 + k)
                            {
                                IBlockState iblockstate2;

                                if (j1 >= 64 && j1 <= 127)
                                {
                                    if (flag)
                                    {
                                        iblockstate2 = SNOW;
                                    }
                                    else
                                    {
                                        iblockstate2 = getBand(x, j1, z);
                                    }
                                }
                                else
                                {
                                    iblockstate2 = PACKED_ICE;
                                }

                                chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate2);
                            }
                            else
                            {
                                chunkPrimerIn.setBlockState(l1, j1, k1, topBlock);
                                flag1 = true;
                            }
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate3);

                            if (iblockstate3.getBlock() == Blocks.SNOW)
                            {
                                chunkPrimerIn.setBlockState(l1, j1, k1, PACKED_ICE);
                            }
                        }
                    }
                    else if (l > 0)
                    {
                        --l;

                        if (flag1)
                        {
                            chunkPrimerIn.setBlockState(l1, j1, k1, PACKED_ICE);
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(l1, j1, k1, getBand(x, j1, z));
                        }
                    }

                    ++i1;
                }
            }
        }
    }

    public void generateBands(long p_150619_1_)
    {
        bands = new IBlockState[64];
        Arrays.fill(bands, SNOW);
        Random random = new Random(p_150619_1_);
        bandsOffsetNoise = new NoiseGeneratorPerlin(random, 1);

        for (int l1 = 0; l1 < 64; ++l1)
        {
            l1 += random.nextInt(5) + 1;

            if (l1 < 64)
            {
                bands[l1] = PACKED_ICE;
            }
        }

        int i2 = random.nextInt(4) + 2;

        for (int i = 0; i < i2; ++i)
        {
            int j = random.nextInt(3) + 1;
            int k = random.nextInt(64);

            for (int l = 0; k + l < 64 && l < j; ++l)
            {
                bands[k + l] = SNOW;
            }
        }

        int j2 = random.nextInt(4) + 2;

        for (int k2 = 0; k2 < j2; ++k2)
        {
            int i3 = random.nextInt(3) + 2;
            int l3 = random.nextInt(64);

            for (int i1 = 0; l3 + i1 < 64 && i1 < i3; ++i1)
            {
                bands[l3 + i1] = PACKED_ICE;
            }
        }

        int l2 = random.nextInt(4) + 2;

        for (int j3 = 0; j3 < l2; ++j3)
        {
            int i4 = random.nextInt(3) + 1;
            int k4 = random.nextInt(64);

            for (int j1 = 0; k4 + j1 < 64 && j1 < i4; ++j1)
            {
                bands[k4 + j1] = SNOW;
            }
        }

        int k3 = random.nextInt(3) + 3;
        int j4 = 0;

        for (int l4 = 0; l4 < k3; ++l4)
        {
            int i5 = 1;
            j4 += random.nextInt(16) + 4;

            for (int k1 = 0; j4 + k1 < 64 && k1 < 1; ++k1)
            {
                bands[j4 + k1] = SNOW;

                if (j4 + k1 > 1 && random.nextBoolean())
                {
                    bands[j4 + k1 - 1] = SNOW;
                }

                if (j4 + k1 < 63 && random.nextBoolean())
                {
                    bands[j4 + k1 + 1] = PACKED_ICE;
                }
            }
        }
    }

    public IBlockState getBand(int p_180629_1_, int p_180629_2_, int p_180629_3_)
    {
        int i = (int)Math.round(this.bandsOffsetNoise.getValue((double)p_180629_1_ / 512.0D, (double)p_180629_1_ / 512.0D) * 2.0D);
        return bands[(p_180629_2_ + i + 64) % 64];
    }
}