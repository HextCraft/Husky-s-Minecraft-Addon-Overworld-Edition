package net.hdt.neutronia.groups.world.world;

import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.groups.world.blocks.BlockStalagmite;
import net.hdt.neutronia.groups.world.features.overworld.Stalagmite;
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

public class StalagmiteGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!Stalagmite.dimensionConfig.canSpawnHere(world))
            return;

        int x = chunkX * 16 + 8;
        int z = chunkZ * 16 + 8;

        int spread = 10;
        int tries = Stalagmite.tries;
        int innerSpread = 6;
        int innerTries = Stalagmite.clusterCount;
        int upperBound = Stalagmite.maxHeight;
        int offset = 6;

        if (world.provider.isNether()) {
            upperBound = 128;
            offset = 0;
            tries = Stalagmite.netherTries;
            innerTries = Stalagmite.netherClusterCount;
        }

        for (int i = 0; i < tries; i++) {
            BlockPos target = new BlockPos(x + random.nextInt(spread), random.nextInt(upperBound) + offset, z + random.nextInt(spread));
            if (placeStalagmiteCluster(random, world, target, innerSpread, innerTries))
                i++;
        }
    }

    private boolean placeStalagmiteCluster(Random random, World world, BlockPos pos, int spread, int tries) {
        if (!findAndPlaceStalagmite(random, world, pos))
            return false;

        for (int i = 0; i < tries; i++) {
            BlockPos target = pos.add(random.nextInt(spread * 2 + 1) - spread, random.nextInt(spread + 1) - spread, random.nextInt(spread * 2 + 1) - spread);
            findAndPlaceStalagmite(random, world, target);
        }

        return true;
    }

    private boolean findAndPlaceStalagmite(Random random, World world, BlockPos pos) {
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

        Block type = getStalagmiteType(stateAt);
        placeStalagmite(random, world, pos, type);

        return true;
    }

    private void placeStalagmite(Random random, World world, BlockPos pos, Block type) {
        if (type == null)
            return;

        EnumFacing diff = EnumFacing.UP;
        int size = random.nextInt(3) == 0 ? 2 : 3;

        for (int i = 0; i < size; i++) {
            pos = pos.offset(diff);
            if (!world.isAirBlock(pos))
                return;

            BlockStalagmite.EnumSize sizeType = BlockStalagmite.EnumSize.values()[size - i - 1];
            IBlockState targetBlock = type.getDefaultState().withProperty(BlockStalagmite.SIZE, sizeType);
            world.setBlockState(pos, targetBlock);
        }
    }

    @SuppressWarnings("incomplete-switch")
    private Block getStalagmiteType(IBlockState state) {
        Block block = state.getBlock();

        if (block == Blocks.NETHERRACK)
            return Stalagmite.netherrack_stalagmite;
        else if (block == Blocks.STONE) {
            switch (state.getValue(BlockStone.VARIANT)) {
                case STONE:
                    return Stalagmite.stone_stalagmite;
                case ANDESITE:
                    return Stalagmite.andesite_stalagmite;
                case GRANITE:
                    return Stalagmite.granite_stalagmite;
                case DIORITE:
                    return Stalagmite.diorite_stalagmite;
            }
        } else if (block == Blocks.ICE)
            return Stalagmite.ice_stalagmite;
        else if (block == Blocks.PACKED_ICE)
            return Stalagmite.packed_ice_stalagmite;
        else if (block == MoreStoneBlocks.newStoneVariants[0])
            return Stalagmite.basalt_stalagmite;
        else if (block == MoreStoneBlocks.newStoneVariants[5])
            return Stalagmite.limestone_stalagmite;
        else if (block == MoreStoneBlocks.newStoneVariants[17])
            return Stalagmite.marble_stalagmite;

        return null;
    }

}
