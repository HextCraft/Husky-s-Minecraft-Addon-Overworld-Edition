package team.abnormal.neutronia.blocks;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import team.abnormal.abnormalib.block.BlockMod;

public class BlockLantern extends BlockMod implements INeutroniaBlock {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool HANGING = PropertyBool.create("hanging");      //False = Not Extended, True = Model is Extended

    public static final double PIXEL_LENGTH = 1D / 16D;

    public static final AxisAlignedBB LANTERN_NORTH_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 12.5D);
    public static final AxisAlignedBB LANTERN_SOUTH_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 9.5D);
    public static final AxisAlignedBB LANTERN_WEST_AABB = new AxisAlignedBB(PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 12.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final AxisAlignedBB LANTERN_EAST_AABB = new AxisAlignedBB(PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 9.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final AxisAlignedBB LANTERN_UP_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 0D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 9D, PIXEL_LENGTH * 11D);
    public static final AxisAlignedBB LANTERN_DOWN_AABB = new AxisAlignedBB(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);

    public BlockLantern() {
        super("lantern", Material.IRON);
        setCreativeTab(CreativeTabs.DECORATIONS);
        setLightLevel(1.0F);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(HANGING, false));
    }

    public BlockLantern(String name) {
        super(name + "_lantern", Material.IRON);
        setCreativeTab(CreativeTabs.DECORATIONS);
        setLightLevel(1.0F);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(HANGING, false));
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
                return LANTERN_UP_AABB.offset(getOffset(state, source, pos));
            case DOWN:
                return LANTERN_DOWN_AABB.offset(getOffset(state, source, pos));
            default:
                return LANTERN_UP_AABB;
        }
    }

    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        if(state.getValue(FACING) == EnumFacing.UP) {
            long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
            return new Vec3d(((double)((float)(i >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D, 0.0D, ((double)((float)(i >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D);
        } else if(state.getValue(FACING) == EnumFacing.DOWN) {
            long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
            return new Vec3d(((double)((float)(i >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D, ((double)((float)(i >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D, ((double)((float)(i >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D);
        } else {
            return Vec3d.ZERO;
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, facing).withProperty(HANGING, facing == EnumFacing.DOWN || world.getBlockState(pos.up()).getBlock() instanceof BlockChain);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, HANGING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7)).withProperty(HANGING, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getIndex();

        if (state.getValue(HANGING)) {
            i |= 8;
        }

        return i;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}