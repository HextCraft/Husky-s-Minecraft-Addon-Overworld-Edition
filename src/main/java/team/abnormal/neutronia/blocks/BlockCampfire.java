package team.abnormal.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.blocks.BlockModHorizontal;
import team.abnormal.neutronia.init.NBlocks;

import java.util.Random;

public class BlockCampfire extends BlockModHorizontal implements INeutroniaBlock {

    private static final PropertyBool LIT = PropertyBool.create("lit");
    private static final PropertyBool SIGNAL_FIRE = PropertyBool.create("signal_fire");

    public BlockCampfire(boolean lit) {
        super(lit ? "lit_campfire" : "campfire", Material.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(LIT, lit).withProperty(SIGNAL_FIRE, false).withProperty(FACING, EnumFacing.NORTH));
        setHardness(2.0F);
        setResistance(2.0F);
        setLightLevel(lit ? 15 : 0);
        setTickRandomly(lit);
        setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!state.getValue(LIT)) {
            if(playerIn.getActiveItemStack().getItem() instanceof ItemFlintAndSteel) {
                worldIn.getBlockState(pos).withProperty(LIT, true);
            }
        }

        return false;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if(!entityIn.isImmuneToFire() && entityIn instanceof EntityLiving && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase) entityIn) && worldIn.getBlockState(pos).getValue(LIT)) {
            entityIn.attackEntityFrom(DamageSource.IN_FIRE, 1.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(SIGNAL_FIRE, method_17456(world.getBlockState(pos.down()))).withProperty(LIT, false).withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public int getLightValue(IBlockState state) {
        return state.getValue(LIT) ? super.getLightValue(state) : 0;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.getValue(LIT)) {
            if(rand.nextInt(10) == 0) {
                worldIn.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
            }

            if (rand.nextInt(5) == 0) {
                for(int int_1 = 0; int_1 < rand.nextInt(1) + 1; ++int_1) {
                    worldIn.spawnParticle(EnumParticleTypes.LAVA, (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), (double)(rand.nextFloat() / 2.0F), 5.0E-5D, (double)(rand.nextFloat() / 2.0F));
                }
            }

        }
    }

    public static void spawnSmokeParticle(World world_1, BlockPos blockPos_1, boolean boolean_1, boolean boolean_2) {
        Random random_1 = world_1.rand;
//        NParticles defaultParticleType_1 = boolean_1 ? NParticles.CAMPFIRE_SIGNAL_SMOKE : NParticles.CAMPFIRE_COSY_SMOKE;
        EnumParticleTypes defaultParticleType_1 = boolean_1 ? EnumParticleTypes.SMOKE_LARGE : EnumParticleTypes.SMOKE_NORMAL;
        world_1.spawnAlwaysVisibleParticle(defaultParticleType_1.getParticleID(), (double)blockPos_1.getX() + 0.5D + random_1.nextDouble() / 3.0D * (double)(random_1.nextBoolean() ? 1 : -1), (double)blockPos_1.getY() + random_1.nextDouble() + random_1.nextDouble(), (double)blockPos_1.getZ() + 0.5D + random_1.nextDouble() / 3.0D * (double)(random_1.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
        if (boolean_2) {
            world_1.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)blockPos_1.getX() + 0.25D + random_1.nextDouble() / 2.0D * (double)(random_1.nextBoolean() ? 1 : -1), (double)blockPos_1.getY() + 0.4D, (double)blockPos_1.getZ() + 0.25D + random_1.nextDouble() / 2.0D * (double)(random_1.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
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
            if (state.getValue(LIT) && !worldIn.isBlockPowered(pos)) {
                worldIn.scheduleUpdate(pos, NBlocks.CAMPFIRE, 4);
            } else if (!state.getValue(LIT) && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, NBlocks.LIT_CAMPFIRE.getDefaultState(), 2);
            }
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (state.getValue(LIT) && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, NBlocks.CAMPFIRE.getDefaultState(), 2);
                spawnSmokeParticle(worldIn, pos, state.getValue(SIGNAL_FIRE), true);
            }
        }
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    private boolean method_17456(IBlockState blockState_1) {
        return blockState_1.getBlock() == Blocks.HAY_BLOCK;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LIT, SIGNAL_FIRE, FACING);
    }

}