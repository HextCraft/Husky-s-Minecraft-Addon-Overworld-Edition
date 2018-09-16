package net.hdt.neutronia.groups.world.blocks.corals;

import net.hdt.neutronia.groups.world.blocks.BlockWaterPlantBase;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 7/8/18 by HuskyTheArtist.
 * This decorativeCoralBlock plant turns dead if no water blocks are adjacent to it
 */
public class BlockCoralPlant extends BlockWaterPlantBase {

    private boolean dead;
    private ArrayList<Block> livingVersion, deadVersion;

    public BlockCoralPlant(EnumCoralColor colorIn, boolean isDead, ArrayList<Block> livingVersion, ArrayList<Block> deadVersion) {
        super(isDead ? "dead_" + colorIn.getName() + "_coral" : colorIn.getName() + "_coral");
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
        if (!this.dead && canLive(worldIn, pos))
            worldIn.scheduleUpdate(pos, this, 100);
        if (this.dead && !canLive(worldIn, pos))
            worldIn.scheduleUpdate(pos, this, 100);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.dead && canLive(worldIn, pos))
            worldIn.scheduleUpdate(pos, this, 100);
        if (this.dead && !canLive(worldIn, pos))
            worldIn.scheduleUpdate(pos, this, 100);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!this.dead && canLive(worldIn, pos))
            worldIn.setBlockState(pos, deadVersion.get(livingVersion.indexOf(this)).getDefaultState());
        if (this.dead && !canLive(worldIn, pos))
            worldIn.setBlockState(pos, livingVersion.get(deadVersion.indexOf(this)).getDefaultState());
    }

    private boolean canLive(World world, BlockPos itsPosition) {
        for (EnumFacing facing : EnumFacing.values()) {
            IBlockState sidestate = world.getBlockState(itsPosition.offset(facing));
            if (sidestate.getBlock() == Blocks.WATER || sidestate.getBlock() == Blocks.FLOWING_WATER) return false;
        }
        return true;
    }

}
