package net.hdt.neutronia.groups.building.blocks.wip;

import net.minecraft.block.Block;
import net.minecraft.state.StateHolder;
import net.minecraft.property.IProperty;
import net.minecraft.property.enums.EnumBambooLeaves;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.item.ItemSword;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Facing;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.IWorldReadable;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockView;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;

public class BlockBambooSapling extends Block implements IFertilizable
{
    protected static final VoxelShape a;
    
    public BlockBambooSapling(final Builder builder) {
        super(builder);
    }
    
    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }
    
    @Override
    public VoxelShape getBoundingBox(final BlockState aBlockState1, final IBlockView aIBlockView2, final BlockPos aBlockPos3) {
        final Vec3d vVec3d4 = aBlockState1.k(aIBlockView2, aBlockPos3);
        return BlockBambooSapling.a.a(vVec3d4.x, vVec3d4.y, vVec3d4.z);
    }
    
    @Override
    public void updateTick(final BlockState aBlockState1, final World aWorld2, final BlockPos aBlockPos3, final Random aRandom4) {
        if (aRandom4.nextInt(3) == 0 && aWorld2.isAir(aBlockPos3.up()) && aWorld2.a(aBlockPos3.up(), 0) >= 9) {
            this.a(aWorld2, aBlockPos3);
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
        if (aFacing2 == Facing.UP && aBlockState3.getBlock() == Blocks.kO) {
            aIWorld4.setBlockState(aBlockPos5, Blocks.kO.getDefaultState(), 2);
        }
        return super.onBlockUpdate(aBlockState1, aFacing2, aBlockState3, aIWorld4, aBlockPos5, aBlockPos6);
    }
    
    @Override
    public boolean isFullBlock(final BlockState aBlockState) {
        return false;
    }
    
    @Override
    public boolean isFertilizable(final IBlockView aIBlockView1, final BlockPos aBlockPos2, final BlockState aBlockState3, final boolean aBoolean4) {
        return aIBlockView1.h(aBlockPos2.up()).isAir();
    }
    
    @Override
    public boolean canGrow(final World aWorld1, final Random aRandom2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        return true;
    }
    
    @Override
    public void grow(final World aWorld1, final Random aRandom2, final BlockPos aBlockPos3, final BlockState aBlockState4) {
        this.a(aWorld1, aBlockPos3);
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
    
    protected void a(final World aWorld1, final BlockPos aBlockPos2) {
        aWorld1.setBlockState(aBlockPos2.up(), ((StateHolder<O, BlockState>)Blocks.kO.getDefaultState()).<EnumBambooLeaves, EnumBambooLeaves>with(BlockBamboo.LEAVES, EnumBambooLeaves.SMALL), 3);
    }
    
    static {
        a = Block.a(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);
    }
}
