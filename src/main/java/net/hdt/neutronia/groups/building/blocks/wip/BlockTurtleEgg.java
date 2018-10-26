package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import net.minecraft.property.Properties;
import net.minecraft.entity.EntityLiving;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.Facing;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.item.ItemStack;
import javax.annotation.Nullable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.passive.EntityTurtle;
import net.minecraft.world.IBlockView;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.reference.Sounds;
import net.minecraft.entity.mob.EntityZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.property.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.property.PropertyInteger;
import net.minecraft.util.math.shapes.VoxelShape;

public class BlockTurtleEgg extends Block
{
    private static final VoxelShape c;
    private static final VoxelShape d;
    public static final PropertyInteger a;
    public static final PropertyInteger b;
    
    public BlockTurtleEgg(final Builder builder) {
        super(builder);
        this.setDefaultState(((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockTurtleEgg.a, 0)).<Comparable, Integer>with((IProperty<Comparable>)BlockTurtleEgg.b, 1));
    }
    
    @Override
    public void onSteppedOn(final World aWorld1, final BlockPos aBlockPos2, final Entity aEntity3) {
        this.a(aWorld1, aBlockPos2, aEntity3, 100);
        super.onSteppedOn(aWorld1, aBlockPos2, aEntity3);
    }
    
    @Override
    public void onLandedUpon(final World aWorld1, final BlockPos aBlockPos2, final Entity aEntity3, final float aFloat4) {
        if (!(aEntity3 instanceof EntityZombie)) {
            this.a(aWorld1, aBlockPos2, aEntity3, 3);
        }
        super.onLandedUpon(aWorld1, aBlockPos2, aEntity3, aFloat4);
    }
    
    private void a(final World aWorld1, final BlockPos aBlockPos2, final Entity aEntity3, final int aInteger4) {
        if (!this.a(aWorld1, aEntity3)) {
            super.onSteppedOn(aWorld1, aBlockPos2, aEntity3);
            return;
        }
        if (!aWorld1.isClient && aWorld1.rand.nextInt(aInteger4) == 0) {
            this.a(aWorld1, aBlockPos2, aWorld1.h(aBlockPos2));
        }
    }
    
    private void a(final World aWorld1, final BlockPos aBlockPos2, final BlockState aBlockState3) {
        aWorld1.a(null, aBlockPos2, Sounds.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7f, 0.9f + aWorld1.rand.nextFloat() * 0.2f);
        final int vInteger4 = aBlockState3.<Integer>get((IProperty<Integer>)BlockTurtleEgg.b);
        if (vInteger4 <= 1) {
            aWorld1.breakBlock(aBlockPos2, false);
        }
        else {
            aWorld1.setBlockState(aBlockPos2, ((StateHolder<O, BlockState>)aBlockState3).<Comparable, Integer>with((IProperty<Comparable>)BlockTurtleEgg.b, vInteger4 - 1), 2);
            aWorld1.fireWorldEvent(2001, aBlockPos2, Block.getStateId(aBlockState3));
        }
    }
    
    @Override
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        if (this.a(aWorld2) && this.a(aWorld2, aBlockPos3)) {
            final int vInteger5 = aBlockState1.<Integer>get((IProperty<Integer>)BlockTurtleEgg.a);
            if (vInteger5 < 2) {
                aWorld2.a(null, aBlockPos3, Sounds.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7f, 0.9f + aRandom4.nextFloat() * 0.2f);
                aWorld2.setBlockState(aBlockPos3, ((StateHolder<O, BlockState>)aBlockState1).<Comparable, Integer>with((IProperty<Comparable>)BlockTurtleEgg.a, vInteger5 + 1), 2);
            }
            else {
                aWorld2.a(null, aBlockPos3, Sounds.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7f, 0.9f + aRandom4.nextFloat() * 0.2f);
                aWorld2.setBlockToAir(aBlockPos3);
                if (!aWorld2.isClient) {
                    for (int vInteger6 = 0; vInteger6 < aBlockState1.<Integer>get((IProperty<Integer>)BlockTurtleEgg.b); ++vInteger6) {
                        aWorld2.fireWorldEvent(2001, aBlockPos3, Block.getStateId(aBlockState1));
                        final EntityTurtle vEntityTurtle7 = new EntityTurtle(aWorld2);
                        vEntityTurtle7.setBreedingAge(-24000);
                        vEntityTurtle7.g(aBlockPos3);
                        vEntityTurtle7.setPositionAndAngles(aBlockPos3.getX() + 0.3 + vInteger6 * 0.2, aBlockPos3.getY(), aBlockPos3.getZ() + 0.3, 0.0f, 0.0f);
                        aWorld2.spawnEntity(vEntityTurtle7);
                    }
                }
            }
        }
    }
    
    private boolean a(final IBlockView aIBlockView1, final BlockPos aBlockPos2) {
        return aIBlockView1.h(aBlockPos2.down()).getBlock() == Blocks.C;
    }
    
    @Override
    public void onBlockAdded(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        if (this.a(aWorld2, aBlockPos3) && !aWorld2.isClient) {
            aWorld2.fireWorldEvent(2005, aBlockPos3, 0);
        }
    }
    
    private boolean a(final World aWorld) {
        final float vFloat2 = aWorld.k(1.0f);
        return (vFloat2 < 0.69 && vFloat2 > 0.65) || aWorld.rand.nextInt(500) == 0;
    }
    
    @Override
    public void afterBreak(final World aWorld1, final EntityPlayer aEntityPlayer2, final BlockPos aBlockPos3, final BlockState aBlockState4, @Nullable final BlockEntity aBlockEntity5, final ItemStack aItemStack6) {
        super.afterBreak(aWorld1, aEntityPlayer2, aBlockPos3, aBlockState4, aBlockEntity5, aItemStack6);
        this.a(aWorld1, aBlockPos3, aBlockState4);
    }
    
    @Override
    public boolean a(final BlockState aBlockState1, final BlockPlaceItemInteraction aBlockPlaceItemInteraction2) {
        return (aBlockPlaceItemInteraction2.getHeldItem().getItem() == this.getItem() && aBlockState1.<Integer>get((IProperty<Integer>)BlockTurtleEgg.b) < 4) || super.a(aBlockState1, aBlockPlaceItemInteraction2);
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        final BlockState vBlockState2 = aBlockPlaceItemInteraction.getWorld().h(aBlockPlaceItemInteraction.getPosition());
        if (vBlockState2.getBlock() == this) {
            return ((StateHolder<O, BlockState>)vBlockState2).<Comparable, Integer>with((IProperty<Comparable>)BlockTurtleEgg.b, Math.min(4, vBlockState2.<Integer>get((IProperty<Integer>)BlockTurtleEgg.b) + 1));
        }
        return super.getDefaultState(aBlockPlaceItemInteraction);
    }
    
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
    @Override
    public boolean isFullBlock(final BlockState aBlockState) {
        return false;
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        if (aBlockState1.<Integer>get((IProperty<Integer>)BlockTurtleEgg.b) > 1) {
            return BlockTurtleEgg.d;
        }
        return BlockTurtleEgg.c;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(final IBlockView aIBlockView1, final BlockState aBlockState2, final BlockPos aBlockPos3, final Facing aFacing4) {
        return BlockFaceShape.UNDEFINED;
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockTurtleEgg.a, BlockTurtleEgg.b);
    }
    
    private boolean a(final World aWorld1, final Entity aEntity2) {
        return !(aEntity2 instanceof EntityTurtle) && (!(aEntity2 instanceof EntityLiving) || aEntity2 instanceof EntityPlayer || aWorld1.getGameRules().getBoolean("mobGriefing"));
    }
    
    static {
        c = Block.a(3.0, 0.0, 3.0, 12.0, 7.0, 12.0);
        d = Block.a(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);
        a = Properties.HATCH;
        b = Properties.EGGS;
    }
}
