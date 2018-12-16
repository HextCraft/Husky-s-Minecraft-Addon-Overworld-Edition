package team.hdt.neutronia_rewrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import team.hdt.huskylib.block.BlockMod;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

import java.util.Random;

public class BlockLanternRedstone extends BlockMod implements INeutroniaBlock {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool CHAIN_EXTENDED = PropertyBool.create("chain_extended");      //False = Not Extended, True = Model is Extended

    public static final double PIXEL_LENGTH = 1D / 16D;
    public static final AxisAlignedBB LANTERN_NORTH_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 12.5D);
    public static final AxisAlignedBB LANTERN_SOUTH_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 9.5D);
    public static final AxisAlignedBB LANTERN_WEST_AABB = new AxisAlignedBB(PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 12.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final AxisAlignedBB LANTERN_EAST_AABB = new AxisAlignedBB(PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 9.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final AxisAlignedBB LANTERN_UP_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 0D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 9D, PIXEL_LENGTH * 11D);
    public static final AxisAlignedBB LANTERN_DOWN_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static IBlockState onBlock, offBlock;
    private final boolean isOn;

    public BlockLanternRedstone(String name, boolean isOn) {
        super(isOn ? "lit_" + name + "_redstone_lantern" : name + "_redstone_lantern", Material.IRON);
        this.isOn = isOn;
        if (isOn) this.setLightLevel(1.0F);
        setCreativeTab(CreativeTabs.REDSTONE);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(CHAIN_EXTENDED, Boolean.FALSE));
    }

    public void setOffBlock(IBlockState offBlock) {
        BlockLanternRedstone.offBlock = offBlock;
    }

    public void setOnBlock(IBlockState onBlock) {
        BlockLanternRedstone.onBlock = onBlock;
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, offBlock, 2);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, onBlock, 2);
            }
        }
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.scheduleUpdate(pos, this, 4);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, onBlock, 2);
            }
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, offBlock, 2);
            }
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(FACING)) {
            case NORTH:
                return LANTERN_NORTH_AABB;
            case SOUTH:
                return LANTERN_SOUTH_AABB;
            case EAST:
                return LANTERN_EAST_AABB;
            case WEST:
                return LANTERN_WEST_AABB;
            case UP:
                return LANTERN_UP_AABB;
            case DOWN:
                return LANTERN_DOWN_AABB;
            default:
                return LANTERN_UP_AABB;
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(CHAIN_EXTENDED, Boolean.FALSE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, CHAIN_EXTENDED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7)).withProperty(CHAIN_EXTENDED, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getIndex();

        if (state.getValue(CHAIN_EXTENDED)) {
            i |= 8;
        }

        return i;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}