package team.abnormal.neutronia.world.biomes;

import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.Random;

public class TropicalMesaBiome extends NBiome {

    public final boolean isHilly;
    public final boolean brycePillars;
    public final boolean hasForest;
    private IBlockState[] clayBands;
    private long worldSeed;
    private NoiseGeneratorPerlin pillarNoise;
    private NoiseGeneratorPerlin pillarRoofNoise;
    private NoiseGeneratorPerlin clayBandsOffsetNoise;

    protected static final IBlockState ORANGE_STAINED_HARDENED_CLAY = STAINED_HARDENED_CLAY.withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);

    public TropicalMesaBiome(boolean isHillyIn, boolean brycePillarsIn, boolean hasForestIn, BiomeProperties properties) {
        super(properties);
        isHilly = isHillyIn;
        brycePillars = brycePillarsIn;
        hasForest = hasForestIn;
        decorator.deadBushPerChunk = 20;
        decorator.reedsPerChunk = 3;
        decorator.cactiPerChunk = 5;
        decorator.flowersPerChunk = 0;
        if (topBlock == Blocks.GRASS.getDefaultState()) {
            spawnableCreatureList.add(new SpawnListEntry(EntityParrot.class, 40, 1, 2));
            spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
            spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 1, 1, 1));
            spawnableMonsterList.remove(new SpawnListEntry(EntityHusk.class, 10, 1, 4));
            spawnableCreatureList.remove(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        }
        if (topBlock == RED_SAND) {
            spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 10, 1, 4));
            spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
            spawnableCreatureList.remove(new SpawnListEntry(EntityParrot.class, 40, 1, 2));
            spawnableCreatureList.remove(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
            spawnableMonsterList.remove(new SpawnListEntry(EntityOcelot.class, 1, 1, 1));
        }
        if (topBlock == RED_SAND || topBlock == HARDENED_CLAY) {
            decorator.treesPerChunk = -999;
        }
        if (topBlock == Blocks.GRASS.getDefaultState()) {
            decorator.treesPerChunk = 4;
        }
    }

    public BiomeDecorator createBiomeDecorator()
    {
        return new Decorator();
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(10) == 0)
        {
            return BIG_TREE_FEATURE;
        }
        else if (rand.nextInt(2) == 0) {
            return new WorldGenShrub(JUNGLE_LOG, OAK_LEAVES);
        }
        else
        {
            return (rand.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAVES) : new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAVES, true));
        }
    }

    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        int i = rand.nextInt(16) + 8;
        int j = rand.nextInt(16) + 8;
        int height = worldIn.getHeight(pos.add(i, 0, j)).getY() * 2; // could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int k = rand.nextInt(height);
        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN))
            (new WorldGenMelon()).generate(worldIn, rand, pos.add(i, k, j));
        WorldGenVines worldgenvines = new WorldGenVines();

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
            for (int j1 = 0; j1 < 50; ++j1)
            {
                k = rand.nextInt(16) + 8;
                int i1 = rand.nextInt(16) + 8;
                worldgenvines.generate(worldIn, rand, pos.add(k, 128, i1));
            }
        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
            if (rand.nextInt(64) == 0) {
                (new WorldGenFossils()).generate(worldIn, rand, pos);
            }
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        if (clayBands == null || worldSeed != worldIn.getSeed())
        {
            generateBands(worldIn.getSeed());
        }

        if (pillarNoise == null || pillarRoofNoise == null || worldSeed != worldIn.getSeed())
        {
            Random random = new Random(worldSeed);
            pillarNoise = new NoiseGeneratorPerlin(random, 4);
            pillarRoofNoise = new NoiseGeneratorPerlin(random, 1);
        }

        worldSeed = worldIn.getSeed();
        double d4 = 0.0D;

        if (this.brycePillars)
        {
            int i = (x & -16) + (z & 15);
            int j = (z & -16) + (x & 15);
            double d0 = Math.min(Math.abs(noiseVal), pillarNoise.getValue((double)i * 0.25D, (double)j * 0.25D));

            if (d0 > 0.0D)
            {
                double d1 = 0.001953125D;
                double d2 = Math.abs(pillarRoofNoise.getValue((double)i * 0.001953125D, (double)j * 0.001953125D));
                d4 = d0 * d0 * 2.5D;
                double d3 = Math.ceil(d2 * 50.0D) + 14.0D;

                if (d4 > d3)
                {
                    d4 = d3;
                }

                d4 = d4 + 64.0D;
            }
        }

        int k1 = x & 15;
        int l1 = z & 15;
        int i2 = worldIn.getSeaLevel();
        IBlockState iblockstate = STAINED_HARDENED_CLAY;
        IBlockState iblockstate3 = fillerBlock;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        boolean flag = Math.cos(noiseVal / 3.0D * Math.PI) > 0.0D;
        int l = -1;
        boolean flag1 = false;
        int i1 = 0;

        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (chunkPrimerIn.getBlockState(l1, j1, k1).getMaterial() == Material.AIR && j1 < (int)d4)
            {
                chunkPrimerIn.setBlockState(l1, j1, k1, STONE);
            }

            if (j1 <= rand.nextInt(5))
            {
                chunkPrimerIn.setBlockState(l1, j1, k1, BEDROCK);
            }
            else if (i1 < 15 || brycePillars)
            {
                IBlockState iblockstate1 = chunkPrimerIn.getBlockState(l1, j1, k1);

                if (iblockstate1.getMaterial() == Material.AIR)
                {
                    l = -1;
                }
                else if (iblockstate1.getBlock() == Blocks.STONE)
                {
                    if (l == -1)
                    {
                        flag1 = false;

                        if (k <= 0)
                        {
                            iblockstate = AIR;
                            iblockstate3 = STONE;
                        }
                        else if (j1 >= i2 - 4 && j1 <= i2 + 1)
                        {
                            iblockstate = STAINED_HARDENED_CLAY;
                            iblockstate3 = fillerBlock;
                        }

                        if (j1 < i2 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                        {
                            iblockstate = WATER;
                        }

                        l = k + Math.max(0, j1 - i2);

                        if (j1 >= i2 - 1)
                        {
                            if (this.hasForest && j1 > 86 + k * 2)
                            {
                                if (flag)
                                {
                                    chunkPrimerIn.setBlockState(l1, j1, k1, COARSE_DIRT);
                                }
                                else
                                {
                                    chunkPrimerIn.setBlockState(l1, j1, k1, GRASS);
                                }
                            }
                            else if (j1 > i2 + 3 + k)
                            {
                                IBlockState iblockstate2;

                                if (j1 >= 64 && j1 <= 127)
                                {
                                    if (flag)
                                    {
                                        iblockstate2 = HARDENED_CLAY;
                                    }
                                    else
                                    {
                                        iblockstate2 = getBand(x, j1, z);
                                    }
                                }
                                else
                                {
                                    iblockstate2 = ORANGE_STAINED_HARDENED_CLAY;
                                }

                                chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate2);
                            }
                            else
                            {
                                chunkPrimerIn.setBlockState(l1, j1, k1, topBlock);
                                flag1 = true;
                            }
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate3);

                            if (iblockstate3.getBlock() == Blocks.STAINED_HARDENED_CLAY)
                            {
                                chunkPrimerIn.setBlockState(l1, j1, k1, ORANGE_STAINED_HARDENED_CLAY);
                            }
                        }
                    }
                    else if (l > 0)
                    {
                        --l;

                        if (flag1)
                        {
                            chunkPrimerIn.setBlockState(l1, j1, k1, ORANGE_STAINED_HARDENED_CLAY);
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(l1, j1, k1, getBand(x, j1, z));
                        }
                    }

                    ++i1;
                }
            }
        }
        if (noiseVal < 0.75 && rand.nextInt(10) < 9) {
            topBlock = Blocks.GRASS.getDefaultState();
            fillerBlock = Blocks.DIRT.getDefaultState();
        }
        else if (noiseVal < 1 && rand.nextInt(10) < 9) {
            topBlock = RED_SAND;
            fillerBlock = RED_SAND;
        }
    }

    public void generateBands(long p_150619_1_)
    {
        clayBands = new IBlockState[64];
        Arrays.fill(clayBands, HARDENED_CLAY);
        Random random = new Random(p_150619_1_);
        clayBandsOffsetNoise = new NoiseGeneratorPerlin(random, 1);

        for (int l1 = 0; l1 < 64; ++l1)
        {
            l1 += random.nextInt(5) + 1;

            if (l1 < 64)
            {
                clayBands[l1] = ORANGE_STAINED_HARDENED_CLAY;
            }
        }

        int i2 = random.nextInt(4) + 2;

        for (int i = 0; i < i2; ++i)
        {
            int j = random.nextInt(3) + 1;
            int k = random.nextInt(64);

            for (int l = 0; k + l < 64 && l < j; ++l)
            {
                clayBands[k + l] = STAINED_HARDENED_CLAY.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
            }
        }

        int j2 = random.nextInt(4) + 2;

        for (int k2 = 0; k2 < j2; ++k2)
        {
            int i3 = random.nextInt(3) + 2;
            int l3 = random.nextInt(64);

            for (int i1 = 0; l3 + i1 < 64 && i1 < i3; ++i1)
            {
                clayBands[l3 + i1] = STAINED_HARDENED_CLAY.withProperty(BlockColored.COLOR, EnumDyeColor.BROWN);
            }
        }

        int l2 = random.nextInt(4) + 2;

        for (int j3 = 0; j3 < l2; ++j3)
        {
            int i4 = random.nextInt(3) + 1;
            int k4 = random.nextInt(64);

            for (int j1 = 0; k4 + j1 < 64 && j1 < i4; ++j1)
            {
                clayBands[k4 + j1] = STAINED_HARDENED_CLAY.withProperty(BlockColored.COLOR, EnumDyeColor.RED);
            }
        }

        int k3 = random.nextInt(3) + 3;
        int j4 = 0;

        for (int l4 = 0; l4 < k3; ++l4)
        {
            int i5 = 1;
            j4 += random.nextInt(16) + 4;

            for (int k1 = 0; j4 + k1 < 64 && k1 < 1; ++k1)
            {
                clayBands[j4 + k1] = STAINED_HARDENED_CLAY.withProperty(BlockColored.COLOR, EnumDyeColor.WHITE);

                if (j4 + k1 > 1 && random.nextBoolean())
                {
                    clayBands[j4 + k1 - 1] = STAINED_HARDENED_CLAY.withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
                }

                if (j4 + k1 < 63 && random.nextBoolean())
                {
                    clayBands[j4 + k1 + 1] = STAINED_HARDENED_CLAY.withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
                }
            }
        }
    }

    public IBlockState getBand(int p_180629_1_, int p_180629_2_, int p_180629_3_)
    {
        int i = (int)Math.round(this.clayBandsOffsetNoise.getValue((double)p_180629_1_ / 512.0D, (double)p_180629_1_ / 512.0D) * 2.0D);
        return clayBands[(p_180629_2_ + i + 64) % 64];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 2211330;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 2211330;
    }

    class Decorator extends BiomeDecorator
    {
        private Decorator()
        {
        }

        protected void generateOres(World worldIn, Random random)
        {
            super.generateOres(worldIn, random);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, goldGen, chunkPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD))
                genStandardOre1(worldIn, random, 20, goldGen, 32, 80);
        }
    }
}