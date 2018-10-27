package net.hdt.neutronia.groups.world.biomes;

import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenTreeShrubSpruce;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenTreeTallSpruce;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomePinelands extends Biome {

    protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
    protected static final WorldGenAbstractTree SHRUB_SPRUCE = new WorldGenTreeShrubSpruce();
    private final WorldGenTreeTallSpruce spruceGenerator = new WorldGenTreeTallSpruce(true);

    public BiomePinelands() {
        super(new BiomeProperties("Pinelands").setBaseHeight(0.91F).setHeightVariation(0.5F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));

        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator.treesPerChunk = 2;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 8;
        this.decorator.gravelPatchesPerChunk = 2;
        this.decorator.generateFalls = true;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 5, 2, 6));

    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(3) > 0) {
            return this.spruceGenerator;
        } else {
            return (WorldGenAbstractTree) (rand.nextInt(1) == 0 ? SHRUB_SPRUCE : SHRUB_SPRUCE);
        }

    }

    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }


    public void decorate(World worldIn, Random rand, BlockPos pos) {
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
        WorldGenerator diamonds = new DiamondGenerator();
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
            diamonds.generate(worldIn, rand, pos);

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_WATER)) {
            int boulderChance = rand.nextInt(12);
            if (boulderChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                LAKE.generate(worldIn, rand, blockpos);
            }

        }

        super.decorate(worldIn, rand, pos);
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0x6CB867;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0x6CB867;

    }

    public static class DiamondGenerator extends WorldGenerator {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos pos) {
            int count = 5 + rand.nextInt(6);
            for (int i = 0; i < count; i++) {
                int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
                BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

                net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE))) {
                    worldIn.setBlockState(blockpos, Blocks.DIAMOND_ORE.getDefaultState(), 16 | 2);
                }
            }
            return true;
        }
    }
}