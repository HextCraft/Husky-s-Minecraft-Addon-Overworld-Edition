package team.hdt.neutronia_legacy.groups.craftingExtension.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.huskylib.block.BlockFacing;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockBell extends BlockFacing implements INeutroniaBlock {

    private static final PropertyEnum<EnumBellAttachment> ATTACHMENT = PropertyEnum.create("attachment", EnumBellAttachment.class);
    private static final AxisAlignedBB field_16325 = new AxisAlignedBB(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private static final AxisAlignedBB field_16322 = new AxisAlignedBB(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
    private static final AxisAlignedBB field_17090 = new AxisAlignedBB(7.0D, 13.0D, 0.0D, 9.0D, 15.0D, 16.0D);
    private static final AxisAlignedBB field_16321 = new AxisAlignedBB(0.0D, 13.0D, 7.0D, 16.0D, 15.0D, 9.0);
    private static final AxisAlignedBB field_17091 = new AxisAlignedBB(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private static final AxisAlignedBB field_17092 = new AxisAlignedBB(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private static final AxisAlignedBB field_16323 = new AxisAlignedBB(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private static final AxisAlignedBB field_17093 = new AxisAlignedBB(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private static final AxisAlignedBB field_17094 = new AxisAlignedBB(7.0D, 13.0D, 7.0D, 9.0D, 16.0D, 9.0D);

    public BlockBell() {
        super("bell", Material.IRON);
        setSoundType(SoundType.ANVIL);
        setDefaultState(this.getDefaultState().withProperty(ATTACHMENT, EnumBellAttachment.FLOOR).withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    /*@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        BlockPos thisPos = pos.offset();
        IBlockState state = worldIn.getBlockState(thisPos);
        Block block = state.getBlock();
        if(isExceptionBlockForAttaching(block)) {
            return false;
        } else {
            boolean idk = Block.
        }
    }*/

    /*public AxisAlignedBB createBoundingBoxes(IBlockState state) {
        EnumFacing facing = state.getValue(FACING);
        EnumBellAttachment attachment = state.getValue(ATTACHMENT);
        if(attachment == EnumBellAttachment.FLOOR) {
            return facing != EnumFacing.NORTH && facing != EnumFacing.SOUTH ? field_16322 : field_16325;
        } else if(attachment == EnumBellAttachment.CEILING) {
            return field_17094;
        } else if(attachment == EnumBellAttachment.DOUBLE_WALL) {
            return facing != EnumFacing.NORTH && facing!= EnumFacing.SOUTH ? field_16321 : field_17090;
        } else if(facing == EnumFacing.NORTH) {
            return field_16323;
        } else if(facing == EnumFacing.SOUTH) {
            return field_17093;
        } else {
            return facing == EnumFacing.EAST ? field_17092 : field_17091;
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return createBoundingBoxes(state);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return createBoundingBoxes(blockState);
    }*/

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        EnumFacing.Axis axis = facing.getAxis();
        IBlockState state;
        if(axis == EnumFacing.Axis.Y) {
            state = this.getDefaultState().withProperty(ATTACHMENT, facing == EnumFacing.DOWN ? EnumBellAttachment.CEILING : EnumBellAttachment.FLOOR).withProperty(FACING, placer.getAdjustedHorizontalFacing());
            if(canPlaceBlockAt(world, pos)) {
                return state;
            }
        } else {
            boolean idk = axis == EnumFacing.Axis.X && this.method_17027(world.getBlockState(pos.west()), world, pos.west(), EnumFacing.EAST) && this.method_17027(world.getBlockState(pos.east()), world, pos.east(), EnumFacing.WEST) || axis == EnumFacing.Axis.Z && this.method_17027(world.getBlockState(pos.north()), world, pos.north(), EnumFacing.SOUTH) && this.method_17027(world.getBlockState(pos.south()), world, pos.south(), EnumFacing.NORTH);
            state = this.getDefaultState().withProperty(FACING, facing.getOpposite()).withProperty(ATTACHMENT, idk ? EnumBellAttachment.DOUBLE_WALL : EnumBellAttachment.SINGLE_WALL);
            if(canPlaceBlockAt(world, pos)) {
                return state;
            }
        }

        return null;
    }

    private boolean method_17027(IBlockState var1, World var2, BlockPos var3, EnumFacing facing) {
        return isFullBlock(var1.getActualState(var2, var3)) && !isExceptionBlockForAttaching(var1.getBlock());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ATTACHMENT, FACING);
    }

    public enum EnumBellAttachment implements IStringSerializable {
        FLOOR("floor"),
        CEILING("ceiling"),
        SINGLE_WALL("single_wall"),
        DOUBLE_WALL("double_wall");

        private final String name;

        EnumBellAttachment(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

}