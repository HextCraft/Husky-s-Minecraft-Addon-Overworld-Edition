package net.hdt.neutronia.groups.world.world;

import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.groups.world.blocks.BlockStalactite;
import net.hdt.neutronia.groups.world.features.overworld.Stalactite;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class StalactiteGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!Stalactite.dimensionConfig.canSpawnHere(world))
            return;

        int x = chunkX * 16 + 8;
        int z = chunkZ * 16 + 8;

        int spread = 10;
        int tries = Stalactite.tries;
        int innerSpread = 6;
        int innerTries = Stalactite.clusterCount;
        int upperBound = Stalactite.maxHeight;
        int offset = 6;

        if (world.provider.isNether()) {
            upperBound = 128;
            offset = 0;
            tries = Stalactite.netherTries;
            innerTries = Stalactite.netherClusterCount;
        }

        for (int i = 0; i < tries; i++) {
            BlockPos target = new BlockPos(x + random.nextInt(spread), random.nextInt(upperBound) + offset, z + random.nextInt(spread));
            if (placeStalactiteCluster(random, world, target, innerSpread, innerTries))
                i++;
        }
    }

    private boolean placeStalactiteCluster(Random random, World world, BlockPos pos, int spread, int tries) {
        if (!findAndPlaceStalactite(random, world, pos))
            return false;

        for (int i = 0; i < tries; i++) {
            BlockPos target = pos.add(random.nextInt(spread * 2 + 1) - spread, random.nextInt(spread + 1) - spread / 1, random.nextInt(spread * 2 + 1) - spread);
            findAndPlaceStalactite(random, world, target);
        }

        return true;
    }

    private boolean findAndPlaceStalactite(Random random, World world, BlockPos pos) {
        if (!world.isAirBlock(pos))
            return false;

        int off = world.provider.isNether() ? -1000 : 0;
        boolean up = random.nextBoolean();
        EnumFacing diff = (up ? EnumFacing.UP : EnumFacing.DOWN);

        if(!up && world.canBlockSeeSky(pos))
            return false;

        IBlockState stateAt;
        do {
            pos = pos.offset(diff);
            stateAt = world.getBlockState(pos);
            off++;
        } while(pos.getY() > 4 && pos.getY() < 200 && !stateAt.getBlock().isFullBlock(stateAt) && off < 10);

        Block type = getStalactiteType(stateAt);
        placeStalactite(random, world, pos, type);

        return true;
    }

    private void placeStalactite(Random random, World world, BlockPos pos, Block type) {
        if (type == null)
            return;

        EnumFacing diff = EnumFacing.DOWN;
        int size = random.nextInt(3) == 0 ? 2 : 3;
        if (random.nextInt(20) == 0)
            size = 1;

        for (int i = 0; i < size; i++) {
            pos = pos.offset(diff);
            if (!world.isAirBlock(pos))
                return;

            BlockStalactite.EnumSize sizeType = BlockStalactite.EnumSize.values()[size - i - 1];
            IBlockState targetBlock = type.getDefaultState().withProperty(BlockStalactite.SIZE, sizeType);
            world.setBlockState(pos, targetBlock);
        }
    }

    @SuppressWarnings("incomplete-switch")
    private Block getStalactiteType(IBlockState state) {
        Block block = state.getBlock();

        if (block == Blocks.NETHERRACK)
            return Stalactite.netherrack_stalactite;
        else if (block == Blocks.STONE) {
            switch (state.getValue(BlockStone.VARIANT)) {
                case STONE:
                    return Stalactite.stone_stalactite;
                case ANDESITE:
                    return Stalactite.andesite_stalactite;
                case GRANITE:
                    return Stalactite.granite_stalactite;
                case DIORITE:
                    return Stalactite.diorite_stalactite;
            }
        } else if (block == Blocks.ICE)
            return Stalactite.ice_stalactite;
        else if (block == Blocks.PACKED_ICE)
            return Stalactite.packed_ice_stalactite;
        else if (block == MoreStoneBlocks.newStoneVariants[0])
            return Stalactite.basalt_stalactite;
        else if (block == MoreStoneBlocks.newStoneVariants[5])
            return Stalactite.limestone_stalactite;
        else if (block == MoreStoneBlocks.newStoneVariants[17])
            return Stalactite.marble_stalactite;

        return null;
    }

}
