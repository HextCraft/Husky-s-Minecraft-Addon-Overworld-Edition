package team.hdt.neutronia_legacy.base.world.gen.feature;

import com.google.gson.JsonPrimitive;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import team.hdt.neutronia_legacy.base.util.Config;
import team.hdt.neutronia_legacy.base.util.NumberHelper;

import java.util.Random;

public abstract class WorldGenFeature extends WorldGenerator {
    protected int genAttempts;
    protected float genProbability;
    protected boolean randomizeGenAttempts;
    protected int minGenHeight;
    protected int maxGenHeight;

    public WorldGenFeature(Config config) {
        genAttempts = config.getInt("genAttempts", 4);
        genProbability = config.getFloat("genProbability", 1.0F);
        randomizeGenAttempts = config.getBoolean("randomizeGenAttempts", false);

        if (config.hasData("minHeight")) {
            minGenHeight = config.getInt("minHeight");
            config.removeData("minHeight");
            config.addData("minGenHeight", new JsonPrimitive(minGenHeight));
        } else {
            minGenHeight = config.getInt("minGenHeight", 0);
        }

        if (config.hasData("maxHeight")) {
            maxGenHeight = config.getInt("maxHeight");
            config.removeData("maxHeight");
            config.addData("maxGenHeight", new JsonPrimitive(maxGenHeight));
        } else {
            maxGenHeight = config.getInt("maxGenHeight", 255);
        }
    }

    public WorldGenFeature(int genAttempts, float genProbability, boolean randomizeGenAttempts, int minGenHeight, int maxGenHeight) {
        this.genAttempts = genAttempts;
        this.genProbability = genProbability;
        this.randomizeGenAttempts = randomizeGenAttempts;
        this.minGenHeight = minGenHeight;
        this.maxGenHeight = maxGenHeight;
    }

    @Override
    public abstract boolean generate(World world, Random rand, BlockPos pos);

    public int getGenAttempts() {
        return genAttempts;
    }

    public int getGenAttempts(Random rand) {
        int attempts = genAttempts;

        if (genProbability > 0.0F && genProbability < 1.0F && rand.nextFloat() > genProbability) {
            attempts = 0;
        }
        if (randomizeGenAttempts) {
            attempts = NumberHelper.getNumberInRange(1, attempts, rand);
        }

        return attempts;
    }

    public float getGenProbability() {
        return genProbability;
    }

    public boolean randomizeGenAttempts() {
        return randomizeGenAttempts;
    }

    public int getMinHeight() {
        return minGenHeight;
    }

    public int getMaxHeight() {
        return maxGenHeight;
    }
}