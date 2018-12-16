package team.hdt.neutronia_legacy.groups.world.features.overworld.changers;

import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Set;

public class RelativePosChanger implements IChanger {
    private Set<BlockPos> check;
    private IBlockState state;

    public RelativePosChanger(IBlockState state, AxisAlignedBB box) {
        this.check = Sets.newHashSet(getPosInBox(box));
        this.state = state;
    }

    public RelativePosChanger(IBlockState state, BlockPos... positions) {
        this.check = Sets.newHashSet(positions);
        this.state = state;
    }

    public static Iterable<BlockPos> getPosInBox(AxisAlignedBB box) {
        return BlockPos.getAllInBox(new BlockPos((int) box.minX, (int) box.minY, (int) box.minZ), new BlockPos((int) (box.maxX), (int) (box.maxY), (int) (box.maxZ)));
    }

    @Override
    public boolean canChange(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
        return check.contains(relativePos);
    }

    @Override
    public IBlockState change(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original) {
        return state;
    }
}
