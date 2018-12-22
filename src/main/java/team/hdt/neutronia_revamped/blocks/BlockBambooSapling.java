package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import team.hdt.neutronia_revamped.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_revamped.init.NBlocks;
import team.hdt.neutronia_revamped.properties.BambooLeaves;

import java.util.Random;

public class BlockBambooSapling extends BlockNeutroniaBase implements IGrowable {

    protected static final AxisAlignedBB field_9897 = new AxisAlignedBB(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);

    public BlockBambooSapling() {
        super("bamboo_sapling", Material.PLANTS);
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        Vec3d var4 = getOffset(state, source, pos);
        return field_9897.grow(var4.x, var4.y, var4.z);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (rand.nextInt(3) == 0 && worldIn.isAirBlock(pos.up()) && worldIn.getCombinedLight(pos.up(), 0) >= 9) {
            this.method_9351(worldIn, pos);
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).getBlock().isAir(state, worldIn, pos);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.method_9351(worldIn, pos);
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
        return player.getHeldItemMainhand().getItem() instanceof ItemSword ? 1.0F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    protected void method_9351(World var1, BlockPos var2) {
        var1.setBlockState(var2.up(), NBlocks.BAMBOO.getDefaultState().withProperty(BlockBamboo.field_9917, BambooLeaves.SMALL), 3);
    }

}