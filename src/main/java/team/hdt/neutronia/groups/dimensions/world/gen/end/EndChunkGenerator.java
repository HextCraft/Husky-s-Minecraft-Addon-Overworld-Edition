package team.hdt.neutronia.groups.dimensions.world.gen.end;

import net.minecraft.block.BlockChorusFlower;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.WorldGenEndGateway;
import net.minecraft.world.gen.feature.WorldGenEndIsland;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenEndCity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.*;
import team.hdt.neutronia.base.util.BlockHelper;
import team.hdt.neutronia.base.world.biome.BiomeWrapper;
import team.hdt.neutronia.groups.dimensions.world.biomes.end.NEBiomeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EndChunkGenerator extends ChunkGeneratorEnd {
    private World world;
    private Random rand;

    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves scaleNoiseGen;
    private NoiseGeneratorOctaves depthNoiseGen;
    private NoiseGeneratorSimplex islandNoiseGen;

    private Biome[] biomesForGen;

    private double[] buffer;
    private double[] noiseData1;
    private double[] noiseData2;
    private double[] noiseData3;

    private boolean mapFeaturesEnabled;
    private BlockPos spawnPoint;

    private MapGenEndCity endCityGen = new MapGenEndCity(this);
    private WorldGenEndIsland endIslands = new WorldGenEndIsland();

    private int chunkX = 0;
    private int chunkZ = 0;

    public EndChunkGenerator(World worldIn, BlockPos pos) {
        super(worldIn, worldIn.getWorldInfo().isMapFeaturesEnabled(), worldIn.getSeed(), pos);
        this.world = worldIn;
        this.mapFeaturesEnabled = worldIn.getWorldInfo().isMapFeaturesEnabled();
        this.spawnPoint = pos;
        this.rand = new Random(worldIn.getSeed());
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.scaleNoiseGen = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoiseGen = new NoiseGeneratorOctaves(this.rand, 16);
        this.islandNoiseGen = new NoiseGeneratorSimplex(this.rand);
        InitNoiseGensEvent.ContextEnd context = new InitNoiseGensEvent.ContextEnd(this.noiseGen1, this.noiseGen2, this.noiseGen3, this.scaleNoiseGen, this.depthNoiseGen, this.islandNoiseGen);
        context = TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, context);
        this.noiseGen1 = context.getLPerlin1();
        this.noiseGen2 = context.getLPerlin2();
        this.noiseGen3 = context.getPerlin();
        this.scaleNoiseGen = context.getDepth();
        this.depthNoiseGen = context.getScale();
        this.islandNoiseGen = context.getIsland();
        this.endCityGen = (MapGenEndCity) TerrainGen.getModdedMapGen(this.endCityGen, InitMapGenEvent.EventType.END_CITY);
    }

    @Override
    public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
        buffer = getHeights(buffer, chunkX * 2, 0, chunkZ * 2, 3, 33, 3);

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 32; z++) {
                    double d1 = buffer[(x * 3 + y) * 33 + z];
                    double d2 = buffer[(x * 3 + y + 1) * 33 + z];
                    double d3 = buffer[((x + 1) * 3 + y) * 33 + z];
                    double d4 = buffer[((x + 1) * 3 + y + 1) * 33 + z];
                    double d5 = (buffer[(x * 3 + y) * 33 + z + 1] - d1) * 0.25D;
                    double d6 = (buffer[(x * 3 + y + 1) * 33 + z + 1] - d2) * 0.25D;
                    double d7 = (buffer[((x + 1) * 3 + y) * 33 + z + 1] - d3) * 0.25D;
                    double d8 = (buffer[((x + 1) * 3 + y + 1) * 33 + z + 1] - d4) * 0.25D;

                    for (int x2 = 0; x2 < 4; x2++) {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.125D;
                        double d13 = (d4 - d2) * 0.125D;

                        for (int y2 = 0; y2 < 8; y2++) {
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.125D;

                            for (int z2 = 0; z2 < 8; z2++) {
                                IBlockState state = Blocks.AIR.getDefaultState();

                                if (d15 > 0.0D) {
                                    state = Blocks.END_STONE.getDefaultState();
                                }

                                int posX = y2 + x * 8;
                                int posY = x2 + z * 4;
                                int posZ = z2 + y * 8;
                                primer.setBlockState(posX, posY, posZ, state);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    @Override
    public void buildSurfaces(ChunkPrimer primer) {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, world)) {
            return;
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int l = -1;
                IBlockState topBlock = Blocks.END_STONE.getDefaultState();
                IBlockState fillerBlock = Blocks.END_STONE.getDefaultState();

                for (int y = 127; y >= 0; y--) {
                    IBlockState checkBlock = primer.getBlockState(x, y, z);

                    if (checkBlock.getMaterial() == Material.AIR) {
                        l = -1;
                    } else if (checkBlock.getBlock() == Blocks.STONE) {
                        if (l == -1) {
                            l = 1;

                            if (y >= 0) {
                                primer.setBlockState(x, y, z, topBlock);
                            } else {
                                primer.setBlockState(x, y, z, fillerBlock);
                            }
                        } else if (l > 0) {
                            l--;
                            primer.setBlockState(x, y, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Chunk generateChunk(int chunkXIn, int chunkZIn) {
        ChunkPrimer primer = new ChunkPrimer();
        biomesForGen = world.getBiomeProvider().getBiomes(biomesForGen, chunkXIn * 16, chunkZIn * 16, 16, 16);
        rand.setSeed((long) chunkXIn * 341873128712L + (long) chunkZIn * 132897987541L);
        chunkX = chunkXIn;
        chunkZ = chunkZIn;
        setBlocksInChunk(chunkXIn, chunkZIn, primer);
        buildSurfaces(primer);
        replaceBiomeBlocks(chunkX, chunkZ, primer, biomesForGen);

        if (mapFeaturesEnabled) {
            endCityGen.generate(world, chunkXIn, chunkZIn, primer);
        }

        Chunk chunk = new Chunk(world, primer, chunkXIn, chunkZIn);
        byte[] biomeArray = chunk.getBiomeArray();

        for (int i = 0; i < biomeArray.length; ++i) {
            biomeArray[i] = (byte) Biome.getIdForBiome(biomesForGen[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private float getIslandHeightValue(int chunkX, int chunkZ, int posX, int posZ) {
        float f = (float) (chunkX * 2 + posX);
        float f1 = (float) (chunkZ * 2 + posZ);
        float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;

        if (f2 > 80.0F) {
            f2 = 80.0F;
        }

        if (f2 < -100.0F) {
            f2 = -100.0F;
        }

        for (int x = -12; x <= 12; x++) {
            for (int z = -12; z <= 12; z++) {
                long k = (long) (chunkX + x);
                long l = (long) (chunkZ + z);

                if (k * k + l * l > 4096L && islandNoiseGen.getValue((double) k, (double) l) < -0.8999999761581421D) {
                    float f3 = (MathHelper.abs((float) k) * 3439.0F + MathHelper.abs((float) l) * 147.0F) % 13.0F + 9.0F;
                    f = (float) (posX - x * 2);
                    f1 = (float) (posZ - z * 2);
                    float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;

                    if (f4 > 80.0F) {
                        f4 = 80.0F;
                    }

                    if (f4 < -100.0F) {
                        f4 = -100.0F;
                    }

                    if (f4 > f2) {
                        f2 = f4;
                    }
                }
            }
        }

        return f2;
    }

    public boolean isIslandChunk(int chunkX, int chunkZ) {
        return (long) chunkX * (long) chunkX + (long) chunkZ * (long) chunkZ > 4096L && getIslandHeightValue(chunkX, chunkZ, 1, 1) >= 0.0F;
    }

    private double[] getHeights(double[] heightMap, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ) {
        ChunkGeneratorEvent.InitNoiseField event = new ChunkGeneratorEvent.InitNoiseField(this, heightMap, posX, posY, posZ, sizeX, sizeY, sizeZ);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) {
            return event.getNoisefield();
        }

        if (heightMap == null) {
            heightMap = new double[sizeX * sizeY * sizeZ];
        }

        double scaleXZ = 684.412D;
        scaleXZ = scaleXZ * 2.0D;
        noiseData1 = noiseGen3.generateNoiseOctaves(noiseData1, posX, posY, posZ, sizeX, sizeY, sizeZ, scaleXZ / 80.0D, 4.277575000000001D, scaleXZ / 80.0D);
        noiseData2 = noiseGen1.generateNoiseOctaves(noiseData2, posX, posY, posZ, sizeX, sizeY, sizeZ, scaleXZ, 684.412D, scaleXZ);
        noiseData3 = noiseGen2.generateNoiseOctaves(noiseData3, posX, posY, posZ, sizeX, sizeY, sizeZ, scaleXZ, 684.412D, scaleXZ);
        int centerX = posX / 2;
        int centerZ = posZ / 2;
        int height = 0;

        for (int x = 0; x < sizeX; x++) {
            for (int z = 0; z < sizeZ; z++) {
                float islandHeight = getIslandHeightValue(centerX, centerZ, x, z);

                for (int y = 0; y < sizeY; y++) {
                    double d2 = noiseData2[height] / 512.0D;
                    double d3 = noiseData3[height] / 512.0D;
                    double d5 = (noiseData1[height] / 10.0D + 1.0D) / 2.0D;
                    double d4;

                    if (d5 < 0.0D) {
                        d4 = d2;
                    } else if (d5 > 1.0D) {
                        d4 = d3;
                    } else {
                        d4 = d2 + (d3 - d2) * d5;
                    }

                    d4 = d4 - 8.0D;
                    d4 = d4 + (double) islandHeight;
                    int k1 = 2;

                    if (y > sizeY / 2 - k1) {
                        double d6 = (double) ((float) (y - (sizeY / 2 - k1)) / 64.0F);
                        d6 = MathHelper.clamp(d6, 0.0D, 1.0D);
                        d4 = d4 * (1.0D - d6) + -3000.0D * d6;
                    }

                    k1 = 8;

                    if (y < k1) {
                        double d7 = (double) ((float) (k1 - y) / ((float) k1 - 1.0F));
                        d4 = d4 * (1.0D - d7) + -30.0D * d7;
                    }

                    heightMap[height] = d4;
                    height++;
                }
            }
        }

        return heightMap;
    }

    @Override
    public void populate(int chunkX, int chunkZ) {
        ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        BlockPos blockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        BiomeWrapper wrapper = NEBiomeManager.getBiomeWrapper(world.getBiome(blockPos.add(16, 0, 16)));

        BlockFalling.fallInstantly = true;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, world, rand, chunkX, chunkZ, false));
        endCityGen.generateStructure(world, rand, chunkPos);
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Populate(this, world, rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.CUSTOM));

        if (chunkX * chunkX + chunkZ * chunkZ > 4096L) {
            if (getIslandHeightValue(chunkX, chunkZ, 1, 1) < -20.0F && rand.nextInt(14) == 0) {
                endIslands.generate(world, rand, blockPos.add(rand.nextInt(16) + 8, 55 + rand.nextInt(16), rand.nextInt(16) + 8));

                if (rand.nextInt(4) == 0) {
                    endIslands.generate(world, rand, blockPos.add(rand.nextInt(16) + 8, 55 + rand.nextInt(16), rand.nextInt(16) + 8));
                }
            }

            if (getIslandHeightValue(chunkX, chunkZ, 1, 1) > 40.0F) {
                int chorusGenAmount = rand.nextInt(5);

                for (int genIndex = 0; genIndex < chorusGenAmount; genIndex++) {
                    int posX = rand.nextInt(16) + 8;
                    int posZ = rand.nextInt(16) + 8;
                    int posY = world.getHeight(blockPos.add(posX, 0, posZ)).getY();

                    if (posY > 0) {
                        int groundPos = posY - 1;

                        if (world.isAirBlock(blockPos.add(posX, groundPos + 1, posZ)) && BlockHelper.isOreDict("endstone", world.getBlockState(blockPos.add(posX, groundPos, posZ)).getBlock())) {
                            BlockChorusFlower.generatePlant(world, blockPos.add(posX, groundPos + 1, posZ), rand, 8);
                        }
                    }
                }

                if (rand.nextInt(700) == 0) {
                    int posX = rand.nextInt(16) + 8;
                    int posZ = rand.nextInt(16) + 8;
                    int worldHeightAtPos = world.getHeight(blockPos.add(posX, 0, posZ)).getY();

                    if (worldHeightAtPos > 0) {
                        int posY = worldHeightAtPos + 3 + rand.nextInt(7);
                        BlockPos newPos = blockPos.add(posX, posY, posZ);

                        new WorldGenEndGateway().generate(world, rand, newPos);
                        TileEntity tileEntity = world.getTileEntity(newPos);

                        if (tileEntity instanceof TileEntityEndGateway) {
                            TileEntityEndGateway endGateway = (TileEntityEndGateway) tileEntity;
                            endGateway.setExactPosition(spawnPoint);
                        }
                    }
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this, world, rand, chunkX, chunkZ, false));

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, rand, chunkPos));
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Decorate(world, rand, chunkPos, blockPos, DecorateBiomeEvent.Decorate.EventType.CUSTOM));

        if (wrapper != null && wrapper.shouldGenDefaultFeatures()) {
            wrapper.getBiome().decorate(world, rand, blockPos);
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world, rand, chunkPos));

        MinecraftForge.EVENT_BUS.post(new OreGenEvent.Pre(world, rand, blockPos));
        MinecraftForge.EVENT_BUS.post(new OreGenEvent.GenerateMinable(world, rand, new WorldGenMinable(Blocks.AIR.getDefaultState(), 0, BlockMatcher.forBlock(Blocks.AIR)), blockPos, OreGenEvent.GenerateMinable.EventType.CUSTOM));
        MinecraftForge.EVENT_BUS.post(new OreGenEvent.Post(world, rand, blockPos));

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        BiomeWrapper wrapper = NEBiomeManager.getBiomeWrapper(world.getBiome(pos));
        return creatureType == null || wrapper == null ? new ArrayList<>() : wrapper.getSpawnableMobs(creatureType);
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return "EndCity".equalsIgnoreCase(structureName) && endCityGen != null ? endCityGen.getNearestStructurePos(worldIn, position, findUnexplored) : null;
    }

    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return ("EndCity".equalsIgnoreCase(structureName) && endCityGen != null) && endCityGen.isInsideStructure(pos);
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    /**
     * A method that replaces the default Nether blocks with ones designated for each biome
     * <p>
     * Written by the Biomes O' Plenty team here:
     * https://github.com/Glitchfiend/BiomesOPlenty/blob/9873b7ad56ab8f32e6073dea060c4b67aad8b77e/src/main/java/biomesoplenty/common/biome/nether/BOPHellBiome.java#L84
     *
     * @author Biomes O' Plenty team
     */
    private void replaceBiomeBlocks(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomes) {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, world)) {
            return;
        }

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                BiomeWrapper wrapper = NEBiomeManager.getBiomeWrapper(biomes[x + z * 16]);

                if (wrapper != null) {
                    Biome biome = wrapper.getBiome();

                    IBlockState topBlock = wrapper.getBlock("topBlock", biome.topBlock);
                    IBlockState upperFillerBlock = wrapper.getBlock("upperFillerBlock", biome.fillerBlock);
                    IBlockState middleFillerBlock = wrapper.getBlock("middleFillerBlock", biome.fillerBlock);
                    IBlockState lowerFillerBlock = wrapper.getBlock("lowerFillerBlock", biome.fillerBlock);
                    IBlockState bottomBlock = wrapper.getBlock("bottomBlock", biome.fillerBlock);

                    int localX = ((chunkX * 16) + x) & 15;
                    int localZ = ((chunkZ * 16) + z) & 15;

                    boolean wasLastBlockSolid = true;

                    for (int localY = 255; localY >= 0; localY--) {
                        int blocksToSkip = 0;

                        IBlockState checkState = primer.getBlockState(localX, localY, localZ);

                        if (checkState.getBlock() == Blocks.END_STONE) {
                            primer.setBlockState(localX, localY, localZ, middleFillerBlock);

                            if (!wasLastBlockSolid) {
                                primer.setBlockState(localX, localY, localZ, topBlock);

                                for (int floorOffset = 1; floorOffset <= 4 - 1 && localY - floorOffset >= 0; floorOffset++) {
                                    IBlockState state = primer.getBlockState(localX, localY - floorOffset, localZ);
                                    blocksToSkip = floorOffset;

                                    if (state.getBlock() == Blocks.END_STONE) {
                                        primer.setBlockState(localX, localY - floorOffset, localZ, upperFillerBlock);
                                    } else {
                                        break;
                                    }
                                }
                            }

                            wasLastBlockSolid = true;
                        } else if (checkState.getBlock() == Blocks.AIR) {
                            if (wasLastBlockSolid) {
                                if (localY + 1 < 256) {
                                    primer.setBlockState(localX, localY + 1, localZ, lowerFillerBlock);
                                }

                                for (int roofOffset = 2; roofOffset <= 4 && localY + roofOffset <= 127; roofOffset++) {
                                    IBlockState state = primer.getBlockState(localX, localY + roofOffset, localZ);

                                    if (state.getBlock() == Blocks.END_STONE || state == middleFillerBlock) {
                                        primer.setBlockState(localX, localY + roofOffset, localZ, bottomBlock);
                                    } else {
                                        break;
                                    }
                                }
                            }

                            wasLastBlockSolid = false;
                        }

                        localY -= blocksToSkip;
                    }
                }
            }
        }
    }
}