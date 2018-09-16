package net.hdt.neutronia.groups.world.blocks.corals;

import net.hdt.neutronia.properties.EnumCoralColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
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

public class BlockCoralWallFan extends BlockDeadCoralWallFan {

    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;
    private static final AxisAlignedBB ALGAE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
    private Block deadBlock;

    public BlockCoralWallFan(Block deadBlock, EnumCoralColor colorIn) {
        super(colorIn.getName() + "_coral_fan");
        this.deadBlock = deadBlock;
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEVEL, 15));
    }

    public BlockCoralWallFan(Block deadBlock, EnumCoralColor colorIn, String name) {
        super(colorIn.getName() + name);
        this.deadBlock = deadBlock;
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEVEL, 15));
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[]{LEVEL};
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
    protected boolean canSustainBush(IBlockState state) {
        return true;
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
        if (facing.getOpposite() == state.getValue(FACING) && !isValidPosition(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            this.checkForDeath(world, pos);
            return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        }
    }

    protected void checkForDeath(World p_checkForDeath_2_, BlockPos p_checkForDeath_3_) {
        if (!canLive(p_checkForDeath_2_, p_checkForDeath_3_)) {
            p_checkForDeath_2_.scheduleUpdate(p_checkForDeath_3_, this, 60 + new Random().nextInt(40));
        }
    }

    public int getMetaFromState(IBlockState state) {
        return (state.getValue(FACING)).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 0;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BlockLiquid.LEVEL);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!canLive(worldIn, pos)) {
            worldIn.setBlockState(pos, this.deadBlock.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
        }
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

    private boolean isValidPosition(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isTopSolid();
    }

    public boolean isFullCube(IBlockState p_isFullCube_1_) {
        return false;
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

}
