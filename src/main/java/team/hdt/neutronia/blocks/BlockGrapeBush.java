package team.hdt.neutronia.blocks;

import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPlant;
import team.hdt.neutronia.base.blocks.PlantBehaviorType;
import team.hdt.neutronia.init.NDamageSource;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockGrapeBush extends BlockNeutroniaPlant implements IGrowable {

    public static final PropertyInteger AGE;
    private static final AxisAlignedBB SMALL_SHAPE;
    private static final AxisAlignedBB LARGE_SHAPE;
    private Item grapeItem;

    public BlockGrapeBush(String grapeColor, Item grapeItem) {
        super(String.format("%s_grape_bush", grapeColor), PlantBehaviorType.BUSH);
        this.grapeItem = grapeItem;
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(AGE, 0));
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(grapeItem);
    }

    /**
     * @deprecated call via {@link IBlockState#getBoundingBox(IBlockAccess,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BUSH_AABB;
    }

    /**
     * @deprecated call via {@link IBlockState#getCollisionBoundingBox(IBlockAccess,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    /*@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.getValue(AGE) == 0) {
            return SMALL_SHAPE;
        } else {
            return state.getValue(AGE) < 3 ? LARGE_SHAPE : super.getBoundingBox(state, source, pos);
        }
    }*/

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        int var5 = state.getValue(AGE);
        if (var5 < 3 && rand.nextInt(5) == 0 && worldIn.getCombinedLight(pos.up(), 0) >= 9) {
            worldIn.setBlockState(pos, state.withProperty(AGE, var5 + 1), 2);
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        int var5 = Math.min(3, state.getValue(AGE) + 1);
        worldIn.setBlockState(pos, state.withProperty(AGE, var5), 2);
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.setInWeb();
        if (!worldIn.isRemote && state.getValue(AGE) > 0 && (entityIn.prevPosX != entityIn.posX || entityIn.prevPosZ != entityIn.posZ)) {
            double var5 = Math.abs(entityIn.posX - entityIn.prevPosX);
            double var7 = Math.abs(entityIn.posZ - entityIn.prevPosZ);
            if (var5 >= 0.003000000026077032D || var7 >= 0.003000000026077032D) {
                entityIn.attackEntityFrom(NDamageSource.BERRY_BUSH, 1.0F);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int var10 = state.getValue(AGE);
        boolean var11 = var10 == 3;
        if (!var11 && playerIn.getHeldItem(hand).getItem() == new ItemStack(Items.DYE, Items.DYE.getMetadata(15)).getItem()) {
            return false;
        } else if (var10 > 1) {
            int var12 = 1 + worldIn.rand.nextInt(2);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(grapeItem, var12 + (var11 ? 1 : 0)));
            worldIn.setBlockState(pos, state.withProperty(AGE, 1), 2);
            return true;
        } else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }

    static {
        AGE = PropertyInteger.create("age", 0, 3);
        SMALL_SHAPE = new AxisAlignedBB(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
        LARGE_SHAPE = new AxisAlignedBB(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    }

}