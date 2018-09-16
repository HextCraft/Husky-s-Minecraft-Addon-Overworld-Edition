package net.hdt.neutronia.groups.world.blocks.corals;

import net.hdt.neutronia.groups.world.blocks.BlockWaterPlantBase;
import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

import static net.minecraft.block.BlockLiquid.LEVEL;

public class BlockDeadCoralWallFan extends BlockWaterPlantBase {

    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;
    private static final AxisAlignedBB ALGAE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

    public BlockDeadCoralWallFan(EnumCoralColor colorIn) {
        super("dead_" + colorIn.getName() + "_coral_fan");
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEVEL, 15));
    }

    public BlockDeadCoralWallFan(EnumCoralColor colorIn, String name) {
        super("dead_" + colorIn.getName() + name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEVEL, 15));
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[]{LEVEL};
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return ALGAE_AABB;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
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
        return new BlockStateContainer(this, FACING, BlockLiquid.LEVEL);
    }

}
