package team.hdt.neutronia_legacy.groups.experimental.world;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;
import team.hdt.neutronia_legacy.groups.experimental.features.BiggerCaves;

import java.util.Random;

public class BigCaveGenerator extends MapGenCaves {

    // We need to use a second RNG since changing the amount of calls in the built-in one will change the way the caves generate
    Random rand2 = new Random();

    @Override
    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int p_180701_4_, int p_180701_5_, ChunkPrimer chunkPrimerIn) {
        int i = rand.nextInt(rand.nextInt(rand.nextInt(15) + 1) + 1);
        rand2.setSeed(worldIn.getSeed()); // new

        if (rand.nextInt(7) != 0)
            i = 0;

        for (int j = 0; j < i; ++j) {
            double d0 = chunkX * 16 + rand.nextInt(16);
            double d1 = rand.nextInt(rand.nextInt(120) + 8);
            double d2 = chunkZ * 16 + rand.nextInt(16);
            int k = 1;

            if (rand.nextInt(4) == 0) {
                addRoom(rand.nextLong(), p_180701_4_, p_180701_5_, chunkPrimerIn, d0, d1, d2);
                k += rand.nextInt(4);
            }

            for (int l = 0; l < k; ++l) {
                float f = rand.nextFloat() * ((float) Math.PI * 2F);
                float f1 = (rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = rand.nextFloat() * BiggerCaves.overallCaveSizeVariance + BiggerCaves.overallCaveSizeBase;

                if (rand.nextInt(10) == 0) // this value can't be changed because that would change the amount of RNG calls
                    f2 *= rand.nextFloat() * rand.nextFloat() * BiggerCaves.bigCaveSizeVariance + BiggerCaves.bigCaveSizeBase;

                if (BiggerCaves.generateHugeCaves && rand2.nextInt(BiggerCaves.hugeCaveChance) == 0 && d1 < BiggerCaves.hugeCaveMaxY)
                    f2 = BiggerCaves.hugeCaveSizeBase + rand2.nextFloat() * BiggerCaves.hugeCaveSizeVariance;

                addTunnel(rand.nextLong(), p_180701_4_, p_180701_5_, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }

}
