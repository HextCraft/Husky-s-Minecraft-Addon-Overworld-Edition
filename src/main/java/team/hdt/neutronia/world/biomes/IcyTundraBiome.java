package team.hdt.neutronia.world.biomes;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import team.hdt.neutronia.world.gen.features.PatchFeature;
import team.hdt.neutronia.world.gen.features.tree.WorldGenTreeShrubSpruce;

import java.util.Iterator;
import java.util.Random;

public class IcyTundraBiome extends Biome {

    protected static final PatchFeature GRAVEL_PATCHES = new PatchFeature(Blocks.GRAVEL.getDefaultState(), 5);
    protected static final PatchFeature SNOW_PATCHES = new PatchFeature(Blocks.SNOW.getDefaultState(), 5);
    protected static final WorldGenAbstractTree SHRUB_SPRUCE = new WorldGenTreeShrubSpruce();
    private final WorldGenIceSpike iceSpike = new WorldGenIceSpike();
    private final WorldGenIcePath icePatch = new WorldGenIcePath(4);

    public IcyTundraBiome(BiomeProperties properties) {
        super(properties);

        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator.extraTreeChance = 1;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 2;
        this.decorator.generateFalls = true;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPolarBear.class, 1, 1, 2));
        Iterator<SpawnListEntry> iterator = this.spawnableMonsterList.iterator();

        while (iterator.hasNext()) {
            SpawnListEntry biome$spawnlistentry = iterator.next();

            if (biome$spawnlistentry.entityClass == EntitySkeleton.class) {
                iterator.remove();
            }
        }

        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 20, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityStray.class, 80, 4, 4));
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (WorldGenAbstractTree) (rand.nextInt(1) == 0 ? SHRUB_SPRUCE : SHRUB_SPRUCE);
    }

    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
        WorldGenerator lapis = new LapisGenerator();
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, lapis, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS))
            lapis.generate(worldIn, rand, pos);

        int stonepatchChance = rand.nextInt(4);
        if (stonepatchChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
            GRAVEL_PATCHES.generate(worldIn, rand, blockpos);
        }

        int snowpatchChance = rand.nextInt(4);
        if (snowpatchChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
            SNOW_PATCHES.generate(worldIn, rand, blockpos);
        }
        for (int i = 0; i < 3; ++i) {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            this.iceSpike.generate(worldIn, rand, worldIn.getHeight(pos.add(j, 0, k)));
        }

        for (int l = 0; l < 2; ++l) {
            int i1 = rand.nextInt(16) + 8;
            int j1 = rand.nextInt(16) + 8;
            this.icePatch.generate(worldIn, rand, worldIn.getHeight(pos.add(i1, 0, j1)));
        }
        super.decorate(worldIn, rand, pos);
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        double d0 = GRASS_COLOR_NOISE.getValue((double) pos.getX() * 0.0225D, (double) pos.getZ() * 0.0225D);
        return d0 < -0.1D ? super.getModdedBiomeGrassColor(0xAB853E) : super.getModdedBiomeGrassColor(0xB46438);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xB2893A);
    }

    public static class LapisGenerator extends WorldGenerator {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos pos) {
            int count = 5 + rand.nextInt(6);
            for (int i = 0; i < count; i++) {
                int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
                BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

                net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE))) {
                    worldIn.setBlockState(blockpos, Blocks.LAPIS_ORE.getDefaultState(), 16 | 2);
                }
                net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
            }
            return true;
        }
    }
}