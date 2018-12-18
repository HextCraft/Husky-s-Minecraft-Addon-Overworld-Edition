/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.biome.BiomeProvider
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.IChunkGenerator
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraft.world.storage.WorldInfo
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *//*

package team.hdt.neutronia_revamped.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import team.hdt.neutronia_revamped.NeutroniaRevamped;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WorldGenCustomStructures
implements IWorldGenerator {
    private static List<Biome> biomeList = ForgeRegistries.BIOMES.getValues();

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.getWorldInfo().isMapFeaturesEnabled()) {
            int blockX = chunkX * 16 + random.nextInt(16) + 8;
            int blockZ = chunkZ * 16 + random.nextInt(16) + 8;
            switch (world.provider.getDimension()) {
                case 1: {
                    if (!NeutroniaRevamped.activateEndGeneration) break;
                    this.generateStructure(StructureList.BONE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBones, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.BONE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBones, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_ALTAR_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpawnAltars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_ALTAR_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpawnAltars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_HOUSE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceEndBuildings, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_VILLA, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceEndBuildings, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_PILLARS, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceStonePillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_TOWER_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceEndTowers, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_TOWER_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceEndTowers, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_TOWER_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceEndTowers, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_TOWER_4, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceEndTowers, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.END_WALL_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWallRuins, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.END_WALL_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWallRuins, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.END_WALL_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWallRuins, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.END_CRYSTAL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceEndCrystals, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.FOSSIL_ENDERDRAGON_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFossils, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.FOSSIL_ENDERDRAGON_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFossils, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.FOSSIL_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFossils, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.FOSSIL_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFossils, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateStructure(StructureList.SKULL_WITHER, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSkulls, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.OBSIDIAN_PILLAR_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceObsidianPillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.OBSIDIAN_PILLAR_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceObsidianPillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.OBSIDIAN_PILLAR_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceObsidianPillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.OBSIDIAN_PILLAR_BROKEN_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceObsidianPillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.OBSIDIAN_PILLAR_BROKEN_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceObsidianPillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.NETHER_PORTAL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceNetherPortals, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateUndergroundStructure(StructureList.END_DUNGEON, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTombs, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.END_RUIN, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWallRuins, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.ENDBRICK_PILLAR_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceStonePillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    this.generateDownsetStructure(StructureList.ENDBRICK_PILLAR_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceStonePillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
                    break;
                }
                case 0: {
                    if (!NeutroniaRevamped.activateOverworldGeneration) break;
                    this.generateStructure(StructureList.DEAD_TREE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.DEAD_TREE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.DEAD_TREE_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.BIG_CACTUS, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBiggerCacti, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.HUGE_CACTUS, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBiggerCacti, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.SMALL_PYRAMIDE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchancePyramides, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.PYRAMIDE_TEMPLE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTemple, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.ACACIA_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.SKULL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSkulls, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.ROCK_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.ROCK_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.ROCK_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.SAND_ROCK_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.SAND_ROCK_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.SAND_ROCK_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateDownsetStructure(StructureList.DESERT_PILLARS_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDesertPillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateDownsetStructure(StructureList.DESERT_PILLARS_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDesertPillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateDownsetStructure(StructureList.SAND_TOTEM_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTotems, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.SAND_TOTEM_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTotems, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateDownsetStructure(StructureList.SAND_TOTEM_BIG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTotems, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateBuryStructure(StructureList.OASE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceOasis, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateDownsetStructure(StructureList.OASE_SMALL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceOasis, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.PALM_BIG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchancePalms, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.PALM_SMALL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchancePalms, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.PALM_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchancePalms, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.PALM_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchancePalms, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateUndergroundStructure(StructureList.DESERT_TOMB, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTombs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateBuryStructure(StructureList.PYRAMIDE_DUNGEON, world, random, blockX, blockZ, NeutroniaRevamped.spawnchancePyramideDungeon, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateDownsetStructure(StructureList.SPHINX, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSphinx, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateDownsetStructure(StructureList.PYRAMIDE_BIG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchancePyramides, BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
                    this.generateStructure(StructureList.SNOWMAN, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSnowmen, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.SNOWMAN_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSnowmen, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.DEAD_SPRUCE_TREE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.SPRUCE_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.FALLEN_SPRUCE_TREE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFallenTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.FALLEN_SPRUCE_TREE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFallenTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.SNOW_PILE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSnowPiles, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.SNOW_PILE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSnowPiles, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.SNOW_PILE_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSnowPiles, BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
                    this.generateStructure(StructureList.WITCH_BREWING_STAND, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpawnAltars, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateStructure(StructureList.SKULL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSkulls, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateStructure(StructureList.SCARECROW, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceScarecrow, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateStructure(StructureList.ILLAGER_HOUSE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceIllagerHouses, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateDownsetStructure(StructureList.ALTAR_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpawnAltars, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateDownsetStructure(StructureList.ALTAR_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpawnAltars, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateDownsetStructure(StructureList.NETHER_PORTAL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceNetherPortals, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateStructure(StructureList.OAK_LOG_SHORT, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateUndergroundStructure(StructureList.TOMB, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTombs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateBuryStructure(StructureList.HORCRUX, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpecials, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    this.generateStructure(StructureList.TREE_HOLE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
                    this.generateStructure(StructureList.DEAD_JUNGLE_TREE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
                    this.generateStructure(StructureList.JUNGLE_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
                    this.generateStructure(StructureList.BIG_JUNGLE_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
                    this.generateStructure(StructureList.FALLEN_JUNGLE_TREE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFallenTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
                    this.generateStructure(StructureList.OAK_LOG_SHORT, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
                    this.generateBuryStructure(StructureList.MAYA_TEMPLE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceMayaTemple, BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
                    this.generateStructure(StructureList.OAK_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.OAK_LOG_SHORT, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.LOG_BUNDLE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogBundle, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.DEAD_OAK_TREE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.BUSH_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBushes, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.BUSH_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBushes, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.BUSH_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBushes, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.SCARECROW, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceScarecrow, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ROCK_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ROCK_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ROCK_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ROCK_COBBLE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ROCK_COBBLE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ROCK_COBBLE_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.FALLEN_OAK_TREE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFallenTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.FALLEN_OAK_TREE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFallenTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateDownsetStructure(StructureList.ALTAR_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpawnAltars, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateDownsetStructure(StructureList.ALTAR_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpawnAltars, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateBuryStructure(StructureList.GRAVE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceGrave, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ILLAGER_HOUSE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceIllagerHouses, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateDownsetStructure(StructureList.NETHER_PORTAL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceNetherPortals, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.VILLAGER_HOUSE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceVillagerHouses, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.VILLAGER_HOUSE_SMALL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceVillagerHouses, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateBuryStructure(StructureList.WELL_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWell, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateBuryStructure(StructureList.WELL_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWell, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.VILLAGER_HOUSE_BROCKEN, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceVillagerHouses + 500, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateUndergroundStructure(StructureList.SPIDER_NEST_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpiderNests, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateUndergroundStructure(StructureList.SPIDER_NEST_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpiderNests, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateUndergroundStructure(StructureList.SPIDER_NEST_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpiderNests, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
                    this.generateStructure(StructureList.ROCK_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.ROCK_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.ROCK_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateDownsetStructure(StructureList.CAMP, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceCamps, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.SCARECROW, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceScarecrow, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.BUSH_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBushes, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.BUSH_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBushes, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.BUSH_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBushes, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateBuryStructure(StructureList.GRAVE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceGrave, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateBuryStructure(StructureList.MINE_ENTRY, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceMineEntry, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.OAK_LOG_SHORT, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.STONE_PILLAR, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceStonePillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateUndergroundStructure(StructureList.TOMB, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTombs, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.VILLAGER_HOUSE, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceVillagerHouses, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.VILLAGER_HOUSE_SMALL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceVillagerHouses, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateBuryStructure(StructureList.WALL_BIG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWallRuins, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.WALL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWallRuins, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.WHEAT_BALL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWheatBalls, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateBuryStructure(StructureList.WELL_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWell, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateBuryStructure(StructureList.WELL_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceWell, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateDownsetStructure(StructureList.FARM, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceFarm, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
                    this.generateStructure(StructureList.ROCK_COBBLE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateStructure(StructureList.ROCK_COBBLE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateStructure(StructureList.ROCK_COBBLE_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceRocks, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateStructure(StructureList.STONE_PILLAR, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceStonePillars, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateBuryStructure(StructureList.LAVA_FOUNTAIN, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLavaFountain, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateBuryStructure(StructureList.METEOR, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceMeteor, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateBuryStructure(StructureList.MINE_ENTRY, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceMineEntry, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateUndergroundStructure(StructureList.TOMB, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTombs, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateBuryStructure(StructureList.FLAG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceStonePillars + 500, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateUndergroundStructure(StructureList.SPIDER_NEST_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpiderNests, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateUndergroundStructure(StructureList.SPIDER_NEST_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpiderNests, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateUndergroundStructure(StructureList.SPIDER_NEST_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpiderNests, BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                    this.generateStructure(StructureList.ACACIA_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateStructure(StructureList.SKULL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSkulls, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateStructure(StructureList.DEAD_TREE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateStructure(StructureList.DEAD_TREE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateStructure(StructureList.DEAD_TREE_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateBuryStructure(StructureList.LAVA_FOUNTAIN, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLavaFountain, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateStructure(StructureList.SAND_TOTEM_RED, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTotems, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateDownsetStructure(StructureList.SAND_TOTEM_RED_BIG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceTotems, BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                    this.generateDownsetStructure(StructureList.BOAT, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBoats, BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
                    this.generateDownsetStructure(StructureList.RAFT, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceBoats, BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
                    this.generateDownsetStructure(StructureList.OAK_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs + 3000, BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
                    this.generateStructure(StructureList.ACACIA_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA));
                    this.generateStructure(StructureList.SKULL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSkulls, BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA));
                    this.generateStructure(StructureList.DEAD_TREE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA));
                    this.generateStructure(StructureList.DEAD_TREE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA));
                    this.generateStructure(StructureList.DEAD_TREE_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA));
                    this.generateStructure(StructureList.ACACIA_LOG, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceLogs, BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
                    this.generateStructure(StructureList.SKULL, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSkulls, BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
                    this.generateStructure(StructureList.DEAD_TREE_1, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
                    this.generateStructure(StructureList.DEAD_TREE_2, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
                    this.generateStructure(StructureList.DEAD_TREE_3, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceDeadTrees, BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
                    this.generateBuryStructure(StructureList.HORCRUX, world, random, blockX, blockZ, NeutroniaRevamped.spawnchanceSpecials, BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
                    break;
                }
                case -1: {
                    if (NeutroniaRevamped.activateNetherGeneration) {
                        // empty if block
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int blockX, int blockZ, int chance, Set<Biome> set) {
        float chanceModified = NeutroniaRevamped.generationModifier * (float)chance;
        int blockY = StructureGenerator.getGroundFromAbove(world, blockX, blockZ);
        BlockPos pos = new BlockPos(blockX, blockY + 1, blockZ);
        Biome biome = world.getChunk(pos).getBiome(pos, world.getBiomeProvider());
        if (set.contains(biome) && (int)(Math.random() * (double)chanceModified) == 0) {
            generator.generate(world, random, pos);

            System.out.println("Generating a structure");
        }
    }

    private void generateDownsetStructure(WorldGenerator generator, World world, Random random, int blockX, int blockZ, int chance, Set<Biome> set) {
        float chanceModified = NeutroniaRevamped.generationModifier * (float)chance;
        int blockY = StructureGenerator.getGroundFromAbove(world, blockX, blockZ);
        BlockPos pos = new BlockPos(blockX, blockY, blockZ);
        Biome biome = world.getChunk(pos).getBiome(pos, world.getBiomeProvider());
        if (set.contains(biome) && (int)(Math.random() * (double)chanceModified) == 0) {
            generator.generate(world, random, pos);

            System.out.println("Generating a downset structure");
        }
    }

    private void generateBuryStructure(WorldGenerator generator, World world, Random random, int blockX, int blockZ, int chance, Set<Biome> set) {
        float chanceModified = NeutroniaRevamped.generationModifier * (float)chance;
        int blockY = StructureGenerator.getGroundFromAbove(world, blockX, blockZ);
        BlockPos pos = new BlockPos(blockX, blockY - 3, blockZ);
        Biome biome = world.getChunk(pos).getBiome(pos, world.getBiomeProvider());
        if (set.contains(biome) && (int)(Math.random() * (double)chanceModified) == 0) {
            generator.generate(world, random, pos);

            System.out.println("Generating a burried structure");
        }
    }

    private void generateUndergroundStructure(WorldGenerator generator, World world, Random random, int blockX, int blockZ, int chance, Set<Biome> set) {
        float chanceModified = NeutroniaRevamped.generationModifier * (float)chance;
        Random rand = new Random();
        int blockY = StructureGenerator.getGroundFromAbove(world, blockX, blockZ);
        BlockPos pos = new BlockPos(blockX, blockY - 35, blockZ);
        Biome biome = world.getChunk(pos).getBiome(pos, world.getBiomeProvider());
        if (set.contains(biome) && (int)(Math.random() * (double)chanceModified) == 0) {
            generator.generate(world, random, pos);

            System.out.println("Generating a underground structure");
        }
    }

    private void generateFlyingStructure(WorldGenerator generator, World world, Random random, int blockX, int blockZ, int chance, Set<Biome> set) {
        float chanceModified = NeutroniaRevamped.generationModifier * (float)chance;
        Random rand = new Random();
        int blockY = StructureGenerator.getGroundFromAbove(world, blockX, blockZ);
        BlockPos pos = new BlockPos(blockX, blockY + 35, blockZ);
        Biome biome = world.getChunk(pos).getBiome(pos, world.getBiomeProvider());
        if (set.contains(biome) && (int)(Math.random() * (double)chanceModified) == 0) {
            generator.generate(world, random, pos);

            System.out.println("Generating a flying structure");
        }
    }

    private static List<?> getBiomeList() {
        ArrayList<Class> biomeClassList = new ArrayList<Class>();
        for (Biome biome : biomeList) {
            if (biome == null) continue;
            biomeClassList.add(biome.getBiomeClass());
        }
        return biomeClassList;
    }
}

*/
