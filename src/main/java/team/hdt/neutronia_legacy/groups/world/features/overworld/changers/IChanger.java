package team.hdt.neutronia_legacy.groups.world.features.overworld.changers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;

public interface IChanger {

    boolean canChange(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original);

    IBlockState change(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState original);

}
