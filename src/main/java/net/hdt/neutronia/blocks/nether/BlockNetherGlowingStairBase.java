package net.hdt.neutronia.blocks.nether;

import net.hdt.huskylib2.blocks.BlockModStairs;
import net.hdt.neutronia.init.NCreativeTabs;
import net.hdt.neutronia.base.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockNetherGlowingStairBase extends BlockModStairs {

    public BlockNetherGlowingStairBase(String name, IBlockState state) {
        super(name + "_stair", state);
        setCreativeTab(NCreativeTabs.NETHER_EXPANSION_TAB);
        this.setLightLevel(0.2F);
        this.setTickRandomly(true);
    }

    @Override
    public String getModNamespace() {
        return Reference.MOD_ID;
    }

    @Override
    public String getPrefix() {
        return Reference.MOD_ID;
    }

    /**
     * Called when the given entity walks on this Block
     */
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase) entityIn)) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @SideOnly(Side.CLIENT)
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return 15728880;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        BlockPos blockpos = pos.up();
        IBlockState iblockstate = worldIn.getBlockState(blockpos);

        if (iblockstate.getBlock() == Blocks.WATER || iblockstate.getBlock() == Blocks.FLOWING_WATER) {
            worldIn.setBlockToAir(blockpos);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

            if (worldIn instanceof WorldServer) {
                ((WorldServer) worldIn).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.25D, (double) blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    public boolean canEntitySpawn(IBlockState state, Entity entityIn) {
        return entityIn.isImmuneToFire();
    }

}
