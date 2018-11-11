package net.hdt.neutronia.groups.world.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockGroundCover extends BlockMod implements INeutroniaBlock {
    public static final PropertyInteger MODEL = PropertyInteger.create("model", 0, 4);
    private GroundCoverType groundcoverType;

    public BlockGroundCover(String name, Material material, GroundCoverType type) {
        super(name, material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(MODEL, 0));
        this.groundcoverType = type;
    }

    public GroundCoverType getGroundcoverType() {
        return this.groundcoverType;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean causesSuffocation(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canBlockStay(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
        }
    }

    private boolean canBlockStay(World world, BlockPos pos) {
        return world.isSideSolid(pos.down(), EnumFacing.UP);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isTopSolid(IBlockState state) {
        return false;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote) {
            int meta = new Random().nextInt(5);
            world.setBlockState(pos, this.getStateFromMeta(meta));
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            if (player.isSneaking()) {
                ItemStack stack = new ItemStack(this, 1, 0);
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.setBlockToAir(pos);
            } else {
                if (player.isCreative()) {
                    int meta = getMetaFromState(state);
                    if (meta != 4)
                        meta++;
                    else
                        meta = 0;

                    world.setBlockState(pos, getStateFromMeta(meta));
                }
            }
        }

        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return true;
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this.getGroundcoverType() == GroundCoverType.TWIGS) {
            if (rand.nextBoolean()) {
                return Items.STICK;
            }
        }
        return Items.AIR;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(MODEL, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(MODEL);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, MODEL);
    }

    public enum GroundCoverType {
        TWIGS,
        ROCKS,
        BONES,
        SEASHELLS,
        PINE_CONES
    }
}