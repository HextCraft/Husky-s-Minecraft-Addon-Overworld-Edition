package team.abnormal.neutronia.world.biomes;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import java.util.Arrays;
import java.util.Random;

public class ArcticBiome extends NBiome {

    private IBlockState[] bands;
    private long worldSeed;
    private NoiseGeneratorPerlin pillarNoise;
    private NoiseGeneratorPerlin pillarRoofNoise;
    private NoiseGeneratorPerlin bandsOffsetNoise;

    public ArcticBiome(Biome.BiomeProperties properties) {
        super(properties);
        topBlock = SNOW;
        fillerBlock = SNOW;
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (bands == null || worldSeed != worldIn.getSeed()) {
            generateBands(worldIn.getSeed());
        }

        if (pillarNoise == null || pillarRoofNoise == null || worldSeed != worldIn.getSeed()) {
            Random random = new Random(worldSeed);
            pillarNoise = new NoiseGeneratorPerlin(random, 4);
            pillarRoofNoise = new NoiseGeneratorPerlin(random, 1);
        }

        worldSeed = worldIn.getSeed();

        int i = (x & -16) + (z & 15);
        int j = (z & -16) + (x & 15);
        double d0 = Math.min(Math.abs(noiseVal), pillarNoise.getValue((double) i * 0.25D, (double) j * 0.25D));

        if (d0 > 0.0D) {
            double d2 = Math.abs(pillarRoofNoise.getValue((double) i * 0.001953125D, (double) j * 0.001953125D));
            double d4 = d0 * d0 * 2.5D;
            double d3 = Math.ceil(d2 * 50.0D) + 14.0D;

            if (d4 > d3) {
                d4 = d3;
            }

            d4 = d4 + 64.0D;
        }
        generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    public void generateBands(long p_150619_1_)
    {
        bands = new IBlockState[64];
        Arrays.fill(bands, PACKED_ICE);
        Random random = new Random(p_150619_1_);
        bandsOffsetNoise = new NoiseGeneratorPerlin(random, 1);

        for (int l1 = 0; l1 < 64; ++l1)
        {
            l1 += random.nextInt(5) + 1;

            if (l1 < 64)
            {
                bands[l1] = ICE;
            }
        }

        int i2 = random.nextInt(4) + 2;

        for (int i = 0; i < i2; ++i)
        {
            int j = random.nextInt(3) + 1;
            int k = random.nextInt(64);

            for (int l = 0; k + l < 64 && l < j; ++l)
            {
                bands[k + l] = PACKED_ICE;
            }
        }

        int j2 = random.nextInt(4) + 2;

        for (int k2 = 0; k2 < j2; ++k2)
        {
            int i3 = random.nextInt(3) + 2;
            int l3 = random.nextInt(64);

            for (int i1 = 0; l3 + i1 < 64 && i1 < i3; ++i1)
            {
                bands[l3 + i1] = ICE;
            }
        }

        int l2 = random.nextInt(4) + 2;

        for (int j3 = 0; j3 < l2; ++j3)
        {
            int i4 = random.nextInt(3) + 1;
            int k4 = random.nextInt(64);

            for (int j1 = 0; k4 + j1 < 64 && j1 < i4; ++j1)
            {
                bands[k4 + j1] = PACKED_ICE;
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
                bands[j4 + k1] = ICE;

                if (j4 + k1 > 1 && random.nextBoolean())
                {
                    bands[j4 + k1 - 1] = PACKED_ICE;
                }

                if (j4 + k1 < 63 && random.nextBoolean())
                {
                    bands[j4 + k1 + 1] = SNOW;
                }
            }
        }
    }
}