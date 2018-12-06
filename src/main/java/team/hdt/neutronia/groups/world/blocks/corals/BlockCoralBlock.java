package team.hdt.neutronia.groups.world.blocks.corals;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia.groups.world.blocks.BlockWaterBlockBase;
import team.hdt.neutronia.properties.EnumCoralColor;

import java.util.Random;

public class BlockCoralBlock extends BlockWaterBlockBase {

    private Block deadBlock;

    public BlockCoralBlock(Block deadBlock, EnumCoralColor colorIn) {
        super(colorIn.getName() + "_coral_block");
        this.deadBlock = deadBlock;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (canLive(worldIn, pos))
            worldIn.scheduleUpdate(pos, this, 100);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        worldIn.scheduleUpdate(pos, this, 100);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (canLive(worldIn, pos))
            worldIn.setBlockState(pos, this.deadBlock.getDefaultState(), 2);
    }

    private boolean canLive(World world, BlockPos itsPosition) {
        for (EnumFacing facing : EnumFacing.values()) {
            IBlockState sidestate = world.getBlockState(itsPosition.offset(facing));
            if (sidestate.getBlock() == Blocks.WATER || sidestate.getBlock() == Blocks.FLOWING_WATER) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

}
