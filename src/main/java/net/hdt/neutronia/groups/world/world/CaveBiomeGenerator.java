package net.hdt.neutronia.groups.world.world;

import net.hdt.neutronia.base.handler.server.BiomeTypeConfigHandler;
import net.hdt.neutronia.groups.world.features.overworld.CaveBiomes.UndergroundBiomeInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CaveBiomeGenerator extends MultiChunkFeatureGenerator {

    public final UndergroundBiomeInfo info;

    long seedXor;

    public CaveBiomeGenerator(UndergroundBiomeInfo info) {
        this.info = info;

        seedXor = info.biome.getClass().toString().hashCode();
    }

    @Override
    public boolean canGenerate(World world, int chunkX, int chunkZ) {
        return info.dims.canSpawnHere(world);
    }

    @Override
    public int getFeatureRadius() {
        return (int) Math.ceil(Math.max(info.minXSize + info.xVariation, info.minZSize + info.zVariation));
    }

    @Override
    public void generateChunkPart(BlockPos src, Random random, int chunkX, int chunkZ, World world) {
        int radiusX = info.minXSize + random.nextInt(info.xVariation);
        int radiusY = info.minYSize + random.nextInt(info.yVariation);
        int radiusZ = info.minZSize + random.nextInt(info.zVariation);
        apply(world, src, random, chunkX, chunkZ, radiusX, radiusY, radiusZ);
    }

    @Override
    public BlockPos[] getSourcesInChunk(Random random, int chunkX, int chunkZ, World world) {
        if (random.nextInt(info.rarity) == 0) {
            return new BlockPos[]{
                    new BlockPos(chunkX * 16 + random.nextInt(16), info.minY + random.nextInt(info.maxY - info.minY), chunkZ * 16 + random.nextInt(16))
            };
        }

        return new BlockPos[0];
    }

    @Override
    public long modifyWorldSeed(long seed) {
        return seed ^ seedXor;
    }

    @Override
    public boolean isSourceValid(World world, BlockPos pos) {
        Biome biome = world.getBiome(pos);
        return BiomeTypeConfigHandler.biomeTypeIntersectCheck(info.types, biome) && info.biome.isValidBiome(biome);
    }

    public void apply(World world, BlockPos center, Random random, int chunkX, int chunkZ, int radiusX, int radiusY, int radiusZ) {
        int centerX = center.getX();
        int centerY = center.getY();
        int centerZ = center.getZ();

        double radiusX2 = radiusX * radiusX;
        double radiusY2 = radiusY * radiusY;
        double radiusZ2 = radiusZ * radiusZ;

        info.biome.floorList = new ArrayList();
        info.biome.ceilingList = new ArrayList();
        info.biome.insideList = new ArrayList();
        info.biome.wallMap = new HashMap();

        forEachChunkBlock(chunkX, chunkZ, center.getY() - radiusY, center.getY() + radiusY, (pos) -> {
            int x = pos.getX() - center.getX();
            int y = pos.getY() - center.getY();
            int z = pos.getZ() - center.getZ();

            double distX = x * x;
            double distY = y * y;
            double distZ = z * z;
            boolean inside = distX / radiusX2 + distY / radiusY2 + distZ / radiusZ2 <= 1;

            if (inside)
                info.biome.fill(world, center.add(x, y, z));
        });

        info.biome.floorList.forEach(pos -> info.biome.finalFloorPass(world, pos));
        info.biome.ceilingList.forEach(pos -> info.biome.finalCeilingPass(world, pos));
        info.biome.wallMap.keySet().forEach(pos -> info.biome.finalWallPass(world, pos));
        info.biome.insideList.forEach(pos -> info.biome.finalInsidePass(world, pos));

        if (info.biome.hasDungeon() && world instanceof WorldServer && random.nextFloat() < info.biome.dungeonChance) {
            List<BlockPos> candidates = new ArrayList(info.biome.wallMap.keySet());
            candidates.removeIf(pos -> {
                BlockPos down = pos.down();
                IBlockState state = world.getBlockState(down);
                return info.biome.isWall(world, down, state) || state.getBlock().isAir(state, world, down);
            });

            if (!candidates.isEmpty()) {
                BlockPos pos = candidates.get(world.rand.nextInt(candidates.size()));

                EnumFacing border = info.biome.wallMap.get(pos);
                if (border != null)
                    info.biome.spawnDungeon((WorldServer) world, pos, border);
            }
        }
    }
}
