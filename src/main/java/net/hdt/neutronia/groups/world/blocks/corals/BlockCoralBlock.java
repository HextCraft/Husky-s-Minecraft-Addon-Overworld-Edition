package net.hdt.neutronia.groups.world.blocks.corals;

import net.hdt.neutronia.groups.world.blocks.BlockWaterBlockBase;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 7/5/18 by alexiy.
 * This decorativeCoralBlock turns dead if no water blocks are adjacent to it
 */
public class BlockCoralBlock extends BlockWaterBlockBase {

    private boolean dead;
    private ArrayList<Block> livingVersion, deadVersion;

    public BlockCoralBlock(EnumCoralColor colorIn, boolean isDead, ArrayList<Block> livingVersion, ArrayList<Block> deadVersion) {
        super(isDead ? "dead_" + colorIn.getName() + "_coral_block" : colorIn.getName() + "_coral_block");
        this.dead = isDead;
        this.livingVersion = livingVersion;
        this.deadVersion = deadVersion;
        if (isDead) {
            deadVersion.add(this);
        } else {
            livingVersion.add(this);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!dead && !canLive(worldIn, pos))
            worldIn.scheduleUpdate(pos, this, 100);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        worldIn.scheduleUpdate(pos, this, 100);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

        if (!this.dead && !canLive(worldIn, pos))
            worldIn.setBlockState(pos, deadVersion.get(livingVersion.indexOf(this)).getDefaultState());
        if (this.dead && canLive(worldIn, pos))
            worldIn.setBlockState(pos, livingVersion.get(deadVersion.indexOf(this)).getDefaultState());
    }

    private boolean canLive(World world, BlockPos itsPosition) {
        for (EnumFacing facing : EnumFacing.values()) {
            IBlockState sidestate = world.getBlockState(itsPosition.offset(facing));
            if (sidestate.getBlock() == Blocks.WATER || sidestate.getBlock() == Blocks.FLOWING_WATER) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        switch (face) {
            case DOWN:
                return false;
            case UP:
                return isWater(world, pos.add(0, 1, 0));
            case NORTH:
                return isWater(world, pos.add(0, 0, -1));
            case SOUTH:
                return isWater(world, pos.add(0, 0, 1));
            case EAST:
                return isWater(world, pos.add(1, 0, 0));
            case WEST:
                return isWater(world, pos.add(-1, 0, 0));
        }
        return false;
    }

    private boolean isWater(IBlockAccess world, BlockPos pos) {
        return world.getBlockState(pos).getMaterial().isLiquid();
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
