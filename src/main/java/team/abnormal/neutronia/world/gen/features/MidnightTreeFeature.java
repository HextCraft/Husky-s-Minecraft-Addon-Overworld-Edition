package team.abnormals.neutronia.world.gen.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

public abstract class MidnightTreeFeature extends NaturalNeutroniaFeature {
    protected final IBlockState log;
    protected final IBlockState leaves;

    protected MidnightTreeFeature(IBlockState log, IBlockState leaves) {
        this.log = log;
        if (leaves.getBlock() instanceof BlockLeaves) {
            leaves = leaves.withProperty(BlockLeaves.CHECK_DECAY, false);
        }
        this.leaves = leaves;
    }

    protected boolean canFit(World world, BlockPos pos, int height, IntFunction<Integer> widthSupplier) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(pos);

        for (int localY = 0; localY < height; localY++) {
            int width = widthSupplier.apply(localY);
            for (int localZ = -width; localZ <= width; localZ++) {
                for (int localX = -width; localX <= width; localX++) {
                    mutablePos.setPos(pos.getX() + localX, pos.getY() + localY, pos.getZ() + localZ);
                    if (!this.canReplace(world, mutablePos)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    protected boolean canGrow(World world, BlockPos pos) {
        BlockPos groundPos = pos.down();
        IBlockState groundState = world.getBlockState(groundPos);
        Block groundBlock = groundState.getBlock();
        return groundBlock.canSustainPlant(groundState, world, groundPos, EnumFacing.UP, (IPlantable) Blocks.SAPLING);
    }

    protected void notifyGrowth(World world, BlockPos pos) {
        BlockPos groundPos = pos.down();
        IBlockState groundState = world.getBlockState(groundPos);
        Block groundBlock = groundState.getBlock();
        groundBlock.onPlantGrow(groundState, world, groundPos, pos);
    }

    protected void placeLog(World world, BlockPos pos) {
        this.placeState(world, pos, this.log);
    }

    protected void placeLog(World world, BlockPos pos, BlockLog.EnumAxis axis) {
        this.placeState(world, pos, this.log.withProperty(BlockLog.LOG_AXIS, axis));
    }

    protected void placeLeaves(World world, BlockPos pos) {
        this.placeState(world, pos, this.leaves);
    }

    protected Set<BlockPos> produceBlob(BlockPos origin, double radius) {
        return this.produceBlob(origin, radius, radius);
    }

    protected Set<BlockPos> produceBlob(BlockPos origin, double horizontalRadius, double verticalRadius) {
        Set<BlockPos> positions = new HashSet<>();

        int verticalRadiusCeil = MathHelper.ceil(verticalRadius);
        int horizontalRadiusCeil = MathHelper.ceil(horizontalRadius);

        BlockPos minPos = origin.add(-horizontalRadiusCeil, -verticalRadiusCeil, -horizontalRadiusCeil);
        BlockPos maxPos = origin.add(horizontalRadiusCeil, verticalRadiusCeil, horizontalRadiusCeil);
        for (BlockPos pos : BlockPos.getAllInBoxMutable(minPos, maxPos)) {
            double deltaX = (pos.getX() - origin.getX()) / horizontalRadius;
            double deltaY = (pos.getY() - origin.getY()) / verticalRadius;
            double deltaZ = (pos.getZ() - origin.getZ()) / horizontalRadius;
            double distanceSquared = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
            if (distanceSquared <= 1.0) {
                positions.add(pos.toImmutable());
            }
        }

        return positions;
    }

    @Override
    public DecorateBiomeEvent.Decorate.EventType getEventType() {
        return DecorateBiomeEvent.Decorate.EventType.TREE;
    }
}