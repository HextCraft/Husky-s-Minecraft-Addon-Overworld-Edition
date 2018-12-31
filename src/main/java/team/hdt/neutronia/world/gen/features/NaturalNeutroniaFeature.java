package team.hdt.neutronia.world.gen.features;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class NaturalNeutroniaFeature extends AbstractNeutroniaFeature {
    protected void placeState(World world, BlockPos pos, IBlockState state) {
        if (this.canReplace(world, pos)) {
            world.setBlockState(pos, state, 2 | 16);
        }
    }

    protected boolean canReplace(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getMaterial() == Material.VINE;
    }
}