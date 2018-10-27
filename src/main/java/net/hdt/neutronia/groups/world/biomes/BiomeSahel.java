package net.hdt.neutronia.groups.world.biomes;

import net.hdt.neutronia.groups.world.world.gen.features.WorldGenPatches;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenTreeShrubAcacia;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;

import java.util.Iterator;
import java.util.Random;

public class BiomeSahel extends Biome {
    protected static final WorldGenPatches GRASS_PATCHES = new WorldGenPatches(Blocks.GRASS.getDefaultState(), 5);
    protected static final WorldGenAbstractTree SHRUB_ACACIA = new WorldGenTreeShrubAcacia();
    private static final WorldGenAbstractTree SAVANNA_TREE = new WorldGenSavannaTree(false);

    public BiomeSahel(BiomeProperties properties) {
        super(properties);

        this.decorator.treesPerChunk = 3;
        this.decorator.flowersPerChunk = 3;
        this.decorator.grassPerChunk = 4;
        this.decorator.deadBushPerChunk = 5;
        this.decorator.reedsPerChunk = 25;
        this.decorator.cactiPerChunk = 10;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        Iterator<Biome.SpawnListEntry> iterator = this.spawnableMonsterList.iterator();

        while (iterator.hasNext()) {
            Biome.SpawnListEntry biome$spawnlistentry = iterator.next();

            if (biome$spawnlistentry.entityClass == EntityZombie.class || biome$spawnlistentry.entityClass == EntityZombieVillager.class) {
                iterator.remove();
            }
        }

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 19, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 1, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHusk.class, 80, 4, 4));
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (WorldGenAbstractTree) (rand.nextInt(4) > 0 ? SAVANNA_TREE : SHRUB_ACACIA);
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal > 2D) {
            this.topBlock = Blocks.GRASS.getDefaultState();
            this.fillerBlock = Blocks.DIRT.getDefaultState();
        } else {

            this.topBlock = Blocks.SAND.getDefaultState();
            this.fillerBlock = Blocks.SAND.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);

    }

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
        WorldGenerator gold = new GoldGenerator();
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, gold, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD))
            gold.generate(worldIn, rand, pos);

        int grasspatchChance = rand.nextInt(4);
        if (grasspatchChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
            GRASS_PATCHES.generate(worldIn, rand, blockpos);
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DESERT_WELL))
            if (rand.nextInt(1000) == 0) {
                int i = rand.nextInt(16) + 8;
                int j = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
                (new WorldGenDesertWells()).generate(worldIn, rand, blockpos);
            }

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
            if (rand.nextInt(64) == 0) {
                (new WorldGenFossils()).generate(worldIn, rand, pos);
            }
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0x92B25C);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0x92B25C);
    }

    public static class GoldGenerator extends WorldGenerator {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos pos) {
            int count = 5 + rand.nextInt(6);
            for (int i = 0; i < count; i++) {
                int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
                BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

                net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE))) {
                    worldIn.setBlockState(blockpos, Blocks.GOLD_ORE.getDefaultState(), 16 | 2);
                }
            }
            return true;
        }
    }
}
