package net.hdt.neutronia.groups.building.blocks.wip;

import net.hdt.huskylib2.block.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.state.IFluidState;
import net.minecraft.item.ItemSword;
import net.minecraft.property.IProperty;
import net.minecraft.property.Properties;
import net.minecraft.property.PropertyEnum;
import net.minecraft.property.PropertyInteger;
import net.minecraft.property.enums.EnumBambooLeaves;
import net.minecraft.state.StateContainer;
import net.minecraft.state.StateHolder;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.BlockPlaceItemInteraction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Facing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReadable;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockBamboo extends BlockMod implements IFertilizable
{
    protected static final VoxelShape a;
    protected static final VoxelShape b;
    protected static final VoxelShape c;
    public static final PropertyInteger AGE;
    public static final PropertyEnum<EnumBambooLeaves> LEAVES;
    public static final PropertyInteger STAGE;
    
    public BlockBamboo(final Builder builder) {
        super(builder, "bamboo");
        this.setDefaultState(((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)this.stateContainer.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockBamboo.AGE, 0)).<EnumBambooLeaves, EnumBambooLeaves>with(BlockBamboo.LEAVES, EnumBambooLeaves.NONE)).<Comparable, Integer>with((IProperty<Comparable>)BlockBamboo.STAGE, 0));
    }
    
    @Override
    protected void addStateProperties(final StateContainer.Builder<Block, BlockState> aStateContainerBuilder) {
        aStateContainerBuilder.addProperties(BlockBamboo.AGE, BlockBamboo.LEAVES, BlockBamboo.STAGE);
    }
    
    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }
    
    @Override
    public boolean a_(final IBlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        return false;
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        final VoxelShape vVoxelShape4 = (aBlockState1.<EnumBambooLeaves>get(BlockBamboo.LEAVES) == EnumBambooLeaves.LARGE) ? BlockBamboo.b : BlockBamboo.a;
        final Vec3d vVec3d5 = aBlockState1.k(aIBlockView2, aBlockPos3);
        return vVoxelShape4.a(vVec3d5.x, vVec3d5.y, vVec3d5.z);
    }
    
    @Override
    public VoxelShape getEmptyShape(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        final Vec3d vVec3d4 = aBlockState1.k(aIBlockView2, aBlockPos3);
        return BlockBamboo.c.a(vVec3d4.x, vVec3d4.y, vVec3d4.z);
    }
    
    @Nullable
    @Override
    public BlockState getDefaultState(final BlockPlaceItemInteraction aBlockPlaceItemInteraction) {
        final IFluidState vIFluidState2 = aBlockPlaceItemInteraction.getWorld().getFluidState(aBlockPlaceItemInteraction.getPosition());
        if (!vIFluidState2.isEmpty()) {
            return null;
        }
        final BlockState vBlockState3 = aBlockPlaceItemInteraction.getWorld().h(aBlockPlaceItemInteraction.getPosition().down());
        if (!vBlockState3.a(BlockTags.O)) {
            return null;
        }
        final Block vBlock4 = vBlockState3.getBlock();
        if (vBlock4 == Blocks.kN) {
            return ((StateHolder<O, BlockState>)this.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockBamboo.AGE, 0);
        }
        if (vBlock4 == Blocks.kO) {
            final int vInteger5 = (vBlockState3.<Integer>get((IProperty<Integer>)BlockBamboo.AGE) > 0) ? 1 : 0;
            return ((StateHolder<O, BlockState>)this.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockBamboo.AGE, vInteger5);
        }
        return Blocks.kN.getDefaultState();
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(final IBlockView aIBlockView1, final BlockState aBlockState2, final BlockPos aBlockPos3, final Facing aFacing4) {
        return BlockFaceShape.UNDEFINED;
    }
    
    @Override
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        if (aBlockState1.<Integer>get((IProperty<Integer>)BlockBamboo.STAGE) != 0) {
            return;
        }
        if (aRandom4.nextInt(3) == 0 && aWorld2.isAir(aBlockPos3.up()) && aWorld2.a(aBlockPos3.up(), 0) >= 9) {
            final int vInteger5 = this.b(aWorld2, aBlockPos3) + 1;
            if (vInteger5 < 16) {
                this.a(aBlockState1, aWorld2, aBlockPos3, aRandom4, vInteger5);
            }
        }
    }
    
    @Override
    public boolean canBePlaced(final BlockState aBlockState1, final IWorldReadable aIWorldReadable2, final BlockPos aBlockPos3) {
        return aIWorldReadable2.h(aBlockPos3.down()).a(BlockTags.O);
    }
    
    @Override
    public BlockState onBlockUpdate(final BlockState aBlockState1, final Facing aFacing2, final BlockState aBlockState3, final IWorld aIWorld4, final BlockPos aBlockPos5, final BlockPos aBlockPos6) {
        if (!aBlockState1.a(aIWorld4, aBlockPos5)) {
            return Blocks.a.getDefaultState();
        }
        if (aFacing2 == Facing.UP && aBlockState3.getBlock() == Blocks.kO && aBlockState3.<Integer>get((IProperty<Integer>)BlockBamboo.AGE) > aBlockState1.<Integer>get((IProperty<Integer>)BlockBamboo.AGE)) {
            aIWorld4.setBlockState(aBlockPos5, ((StateHolder<O, BlockState>)aBlockState1).<Comparable>a((IProperty<Comparable>)BlockBamboo.AGE), 2);
        }
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public boolean isFullBlock(final BlockState aBlockState) {
        return false;
    }
    
    @Override
    public boolean isFertilizable(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final boolean aBoolean4) {
        final int vInteger5 = this.a(aIBlockView1, aBlockPos2);
        final int vInteger6 = this.b(aIBlockView1, aBlockPos2);
        return vInteger5 + vInteger6 + 1 < 16 && aIBlockView1.h(aBlockPos2.up(vInteger5)).<Integer>get((IProperty<Integer>)BlockBamboo.STAGE) != 1;
    }
    
    @Override
    public boolean canGrow(final World aWorld1, final Random aRandom2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        return true;
    }
    
    @Override
    public void grow(final World aWorld1, final Random aRandom2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        int vInteger5 = this.a(aWorld1, aBlockPos3);
        final int vInteger6 = this.b(aWorld1, aBlockPos3);
        int vInteger7 = vInteger5 + vInteger6 + 1;
        for (int vInteger8 = 1 + aRandom2.nextInt(2), vInteger9 = 0; vInteger9 < vInteger8; ++vInteger9) {
            final BlockPos vBlockPos10 = aBlockPos3.up(vInteger5);
            final BlockState vBlockState11 = aWorld1.h(vBlockPos10);
            if (vInteger7 >= 16 || vBlockState11.<Integer>get((IProperty<Integer>)BlockBamboo.STAGE) == 1 || !aWorld1.isAir(vBlockPos10.up())) {
                return;
            }
            this.a(vBlockState11, aWorld1, vBlockPos10, aRandom2, vInteger7);
            ++vInteger5;
            ++vInteger7;
        }
    }
    
    @Override
    public float getHardnessForPlayer(final BlockState aBlockState1, final EntityPlayer aEntityPlayer2, final IBlockView aIBlockView3, final BlockPos aBlockPos4) {
        if (aEntityPlayer2.getMainHandStack().getItem() instanceof ItemSword) {
            return 1.0f;
        }
        return super.getHardnessForPlayer(aBlockState1, aEntityPlayer2, aIBlockView3, aBlockPos4);
    }
    
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
    protected void a(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4, final int aInteger5) {
        final BlockState vBlockState6 = aWorld2.h(aBlockPos3.down());
        final BlockPos vBlockPos7 = aBlockPos3.down(2);
        final BlockState vBlockState7 = aWorld2.h(vBlockPos7);
        EnumBambooLeaves vEnumBambooLeaves9 = EnumBambooLeaves.NONE;
        if (aInteger5 >= 1) {
            if (vBlockState6.getBlock() != Blocks.kO || vBlockState6.<EnumBambooLeaves>get(BlockBamboo.LEAVES) == EnumBambooLeaves.NONE) {
                vEnumBambooLeaves9 = EnumBambooLeaves.SMALL;
            }
            else if (vBlockState6.getBlock() == Blocks.kO && vBlockState6.<EnumBambooLeaves>get(BlockBamboo.LEAVES) != EnumBambooLeaves.NONE) {
                vEnumBambooLeaves9 = EnumBambooLeaves.LARGE;
                if (vBlockState7.getBlock() == Blocks.kO) {
                    aWorld2.setBlockState(aBlockPos3.down(), ((StateHolder<O, BlockState>)vBlockState6).<EnumBambooLeaves, EnumBambooLeaves>with(BlockBamboo.LEAVES, EnumBambooLeaves.SMALL), 3);
                    aWorld2.setBlockState(vBlockPos7, ((StateHolder<O, BlockState>)vBlockState7).<EnumBambooLeaves, EnumBambooLeaves>with(BlockBamboo.LEAVES, EnumBambooLeaves.NONE), 3);
                }
            }
        }
        final int vInteger10 = (aBlockState1.<Integer>get((IProperty<Integer>)BlockBamboo.AGE) == 1 || vBlockState7.getBlock() == Blocks.kO) ? 1 : 0;
        final int vInteger11 = ((aInteger5 >= 11 && aRandom4.nextFloat() < 0.25f) || aInteger5 == 15) ? 1 : 0;
        aWorld2.setBlockState(aBlockPos3.up(), ((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)((StateHolder<O, BlockState>)this.getDefaultState()).<Comparable, Integer>with((IProperty<Comparable>)BlockBamboo.AGE, vInteger10)).<EnumBambooLeaves, EnumBambooLeaves>with(BlockBamboo.LEAVES, vEnumBambooLeaves9)).<Comparable, Integer>with((IProperty<Comparable>)BlockBamboo.STAGE, vInteger11), 3);
    }
    
    protected int a(final IBlockView aIBlockView1, final BlockPos aBlockPos2) {
        int vInteger3;
        for (vInteger3 = 0; vInteger3 < 16 && aIBlockView1.h(aBlockPos2.up(vInteger3 + 1)).getBlock() == Blocks.kO; ++vInteger3) {}
        return vInteger3;
    }
    
    protected int b(final IBlockView aIBlockView1, final BlockPos aBlockPos2) {
        int vInteger3;
        for (vInteger3 = 0; vInteger3 < 16 && aIBlockView1.h(aBlockPos2.down(vInteger3 + 1)).getBlock() == Blocks.kO; ++vInteger3) {}
        return vInteger3;
    }
    
    static {
        a = Block.a(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
        b = Block.a(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
        c = Block.a(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);
        AGE = Properties.AGE_0;
        LEAVES = Properties.LEAVES;
        STAGE = Properties.STAGE;
    }
}
