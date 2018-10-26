package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import net.minecraft.property.Properties;
import net.minecraft.fluid.Fluid;
import net.minecraft.state.StateContainer;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.util.math.Facing;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.reference.Sounds;
import net.minecraft.client.particle.config.ParticleConfig;
import net.minecraft.world.IWorldReadable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.state.IFluidState;
import java.util.Random;
import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockView;
import net.minecraft.client.particle.config.ParticleConfigDefault;
import net.minecraft.client.particle.Particles;
import net.minecraft.world.WorldServer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.property.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.property.PropertyBoolean;

public class BlockBubbleColumn extends Block implements IFluidStateSupport
{
    public static final PropertyBoolean a;
    
    public BlockBubbleColumn(final Builder builder) {
        super(builder);
        this.setDefaultState(((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockBubbleColumn.a, true));
    }
    
    @Override
    public boolean isFullBlock(final BlockState aBlockState) {
        return false;
    }
    
    @Override
    public void onEntityCollision(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Entity aEntity4) {
        final BlockState vBlockState5 = aWorld2.h(aBlockPos3.up());
        if (vBlockState5.isAir()) {
            aEntity4.j(aBlockState1.<Boolean>get((IProperty<Boolean>)BlockBubbleColumn.a));
            if (!aWorld2.isClient) {
                final WorldServer vWorldServer6 = (WorldServer)aWorld2;
                for (int vInteger7 = 0; vInteger7 < 2; ++vInteger7) {
                    vWorldServer6.<ParticleConfigDefault>a(Particles.SPLASH, aBlockPos3.getX() + aWorld2.rand.nextFloat(), aBlockPos3.getY() + 1, aBlockPos3.getZ() + aWorld2.rand.nextFloat(), 1, 0.0, 0.0, 0.0, 1.0);
                    vWorldServer6.<ParticleConfigDefault>a(Particles.BUBBLE, aBlockPos3.getX() + aWorld2.rand.nextFloat(), aBlockPos3.getY() + 1, aBlockPos3.getZ() + aWorld2.rand.nextFloat(), 1, 0.0, 0.01, 0.0, 0.2);
                }
            }
        }
        else {
            aEntity4.k(aBlockState1.<Boolean>get((IProperty<Boolean>)BlockBubbleColumn.a));
        }
    }
    
    @Override
    public void onBlockAdded(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        a(aWorld2, aBlockPos3.up(), a((IBlockView)aWorld2, aBlockPos3.down()));
    }
    
    @Override
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        a(aWorld2, aBlockPos3.up(), a((IBlockView)aWorld2, aBlockPos3));
    }
    
    @Override
    public IFluidState getFluidState(final BlockState aBlockState) {
        return Fluids.WATER.a(false);
    }
    
    public static void a(final IWorld aIWorld1, final BlockPos aBlockPos2, final boolean aBoolean3) {
        if (a(aIWorld1, aBlockPos2)) {
            aIWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)Blocks.kS.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockBubbleColumn.a, aBoolean3), 2);
        }
    }
    
    public static boolean a(final IWorld aIWorld1, final BlockPos aBlockPos2) {
        final IFluidState vIFluidState3 = aIWorld1.getFluidState(aBlockPos2);
        return aIWorld1.h(aBlockPos2).getBlock() == Blocks.A && vIFluidState3.getLevel() >= 8 && vIFluidState3.isStill();
    }
    
    private static boolean a(final IBlockView aIBlockView1, final BlockPos aBlockPos2) {
        final BlockState vBlockState3 = aIBlockView1.h(aBlockPos2);
        final Block vBlock4 = vBlockState3.getBlock();
        if (vBlock4 == Blocks.kS) {
            return vBlockState3.<Boolean>get((IProperty<Boolean>)BlockBubbleColumn.a);
        }
        return vBlock4 != Blocks.cK;
    }
    
    @Override
    public int getTickRate(final IWorldReadable aIWorldReadable) {
        return 5;
    }
    
    @Sided(Side.CLIENT)
    @Override
    public void randomDisplayTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        final double vDouble5 = aBlockPos3.getX();
        final double vDouble6 = aBlockPos3.getY();
        final double vDouble7 = aBlockPos3.getZ();
        if (aBlockState1.<Boolean>get((IProperty<Boolean>)BlockBubbleColumn.a)) {
            aWorld2.b(Particles.BUBBLE_COLUMN_DOWN, vDouble5 + 0.5, vDouble6 + 0.8, vDouble7, 0.0, 0.0, 0.0);
            if (aRandom4.nextInt(200) == 0) {
                aWorld2.playSound(vDouble5, vDouble6, vDouble7, Sounds.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS, 0.2f + aRandom4.nextFloat() * 0.2f, 0.9f + aRandom4.nextFloat() * 0.15f, false);
            }
        }
        else {
            aWorld2.b(Particles.W, vDouble5 + 0.5, vDouble6, vDouble7 + 0.5, 0.0, 0.04, 0.0);
            aWorld2.b(Particles.W, vDouble5 + aRandom4.nextFloat(), vDouble6 + aRandom4.nextFloat(), vDouble7 + aRandom4.nextFloat(), 0.0, 0.04, 0.0);
            if (aRandom4.nextInt(200) == 0) {
                aWorld2.playSound(vDouble5, vDouble6, vDouble7, Sounds.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundCategory.BLOCKS, 0.2f + aRandom4.nextFloat() * 0.2f, 0.9f + aRandom4.nextFloat() * 0.15f, false);
            }
        }
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (!aBlockState1.a(aIWorld4, aBlockPos5)) {
            return Blocks.A.getDefaultState();
        }
        if (aFacing2 == Facing.DOWN) {
            aIWorld4.setBlockState(aBlockPos5, ((StateHolder<O, BlockState>)Blocks.kS.getDefaultState()).<Comparable, Boolean>with((IProperty<Comparable>)BlockBubbleColumn.a, a((IBlockView)aIWorld4, aBlockPos6)), 2);
        }
        else if (aFacing2 == Facing.UP && aBlockState3.getBlock() != Blocks.kS && a(aIWorld4, aBlockPos6)) {
            aIWorld4.getBlockTickScheduler().scheduleTick(aBlockPos5, this, this.getTickRate(aIWorld4));
        }
        aIWorld4.getFluidTickScheduler().scheduleTick(aBlockPos5, Fluids.WATER, Fluids.WATER.a(aIWorld4));
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public boolean canBePlaced(final BlockState aBlockState1, final IWorldReadable aIWorldReadable2, final BlockPos aBlockPos3) {
        final Block vBlock4 = aIWorldReadable2.h(aBlockPos3.down()).getBlock();
        return vBlock4 == Blocks.kS || vBlock4 == Blocks.iz || vBlock4 == Blocks.cK;
    }
    
    @Override
    public boolean canCollide() {
        return false;
    }
    
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(final IBlockView aIBlockView1, final BlockState aBlockState2, final BlockPos aBlockPos3, final Facing aFacing4) {
        return BlockFaceShape.UNDEFINED;
    }
    
    @Override
    public BlockRenderType getRenderType(final BlockState aBlockState) {
        return BlockRenderType.INVISIBLE;
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockBubbleColumn.a);
    }
    
    @Override
    public Fluid getContainedFluid(final IWorld aIWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3) {
        aIWorld1.setBlockState(aBlockPos2, Blocks.a.getDefaultState(), 11);
        return Fluids.WATER;
    }
    
    static {
        a = Properties.DRAG;
    }
}
