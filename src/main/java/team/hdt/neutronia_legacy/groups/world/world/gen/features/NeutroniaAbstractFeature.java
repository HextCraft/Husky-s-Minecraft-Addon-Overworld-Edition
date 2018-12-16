package team.hdt.neutronia_legacy.groups.world.world.gen.features;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class NeutroniaAbstractFeature extends WorldGenerator implements INeutroniaFeature {
    public NeutroniaAbstractFeature() {
        super(false);
    }

    @Override
    public final boolean generate(World world, Random random, BlockPos origin) {
        return this.placeFeature(world, random, origin);
    }

    @Override
    protected void setBlockAndNotifyAdequately(World world, BlockPos pos, IBlockState state) {
        world.setBlockState(pos, state, 2 | 16);
    }
}