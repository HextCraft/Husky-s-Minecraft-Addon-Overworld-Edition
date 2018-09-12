package net.hdt.neutronia.groups.world.blocks.corals;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.hdt.huskylib2.block.BlockFacing;
import net.hdt.neutronia.groups.world.blocks.BlockWaterPlantBase;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static net.minecraft.block.BlockLiquid.LEVEL;

/**
 * Created on 7/5/18 by alexiy.
 * This coral fan turns dead if no water blocks are adjacent to it
 */
public class BlockCoralFan extends BlockWaterPlantBase {

    private static final Map<EnumFacing, AxisAlignedBB> field_211885_c = Maps.newEnumMap(ImmutableMap.of(EnumFacing.NORTH, new AxisAlignedBB(0.0D, 4.0D, 5.0D, 16.0D, 12.0D, 16.0D), EnumFacing.SOUTH, new AxisAlignedBB(0.0D, 4.0D, 0.0D, 16.0D, 12.0D, 11.0D), EnumFacing.WEST, new AxisAlignedBB(5.0D, 4.0D, 0.0D, 16.0D, 12.0D, 16.0D), EnumFacing.EAST, new AxisAlignedBB(0.0D, 4.0D, 0.0D, 11.0D, 12.0D, 16.0D)));
    private static final PropertyEnum<EnumFacing> FACING = BlockFacing.FACING;
    private static final PropertyBool ON_WALL = PropertyBool.create("on_wall");
    private static final PropertyBool IS_DEAD = PropertyBool.create("dead");
    private boolean dead, onWall;
    private ArrayList<Block> livingVersion, deadVersion;
    private EnumCoralColor color;

    public BlockCoralFan(EnumCoralColor colorIn, boolean isDead, boolean isOnWall, ArrayList<Block> livingVersion, ArrayList<Block> deadVersion) {
        super(isDead ? "dead_" + colorIn.getNewName() + "_coral_fan" : colorIn.getNewName() + "_coral_fan");
        this.dead = isDead;
        this.onWall = isOnWall;
        this.color = colorIn;
        this.livingVersion = livingVersion;
        this.deadVersion = deadVersion;
        if (isDead) {
            deadVersion.add(this);
        } else {
            livingVersion.add(this);
        }
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEVEL, 15).withProperty(ON_WALL, isOnWall).withProperty(IS_DEAD, isDead));
    }

    public BlockCoralFan(EnumCoralColor colorIn, String name, boolean isDead, boolean isOnWall, ArrayList<Block> livingVersion, ArrayList<Block> deadVersion) {
        super(isDead ? "dead_" + colorIn.getNewName() + name : colorIn.getNewName() + name);
        this.dead = isDead;
        this.onWall = isOnWall;
        this.color = colorIn;
        this.livingVersion = livingVersion;
        this.deadVersion = deadVersion;
        if (isDead) {
            deadVersion.add(this);
        } else {
            livingVersion.add(this);
        }
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEVEL, 15).withProperty(ON_WALL, isOnWall).withProperty(IS_DEAD, isDead));
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[]{LEVEL};
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
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.WATER || state.getMaterial() == Material.ICE;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        if (pos.getY() >= 0 && pos.getY() < 256) {
            IBlockState iblockstate = worldIn.getBlockState(pos.down());
            Material material = iblockstate.getMaterial();
            return material == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL) == 0 || material == Material.ICE;
        } else
            return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos.down());
        return super.canPlaceBlockAt(worldIn, pos) && state.getBlock() == Blocks.WATER;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }


    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return field_211885_c.get(state.getValue(FACING));
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return field_211885_c.get(state.getValue(FACING));
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     *
     * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
     * fine.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     *
     * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
        return onWall;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        return state.withProperty(FACING, placer.getHorizontalFacing());
    }

    public int getMetaFromState(IBlockState state) {
        return (state.getValue(FACING)).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BlockLiquid.LEVEL, ON_WALL, IS_DEAD);
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
