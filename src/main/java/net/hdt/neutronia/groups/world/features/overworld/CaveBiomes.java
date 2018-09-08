package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.huskylib2.recipe.RecipeHandler;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GlobalConfig;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.handler.server.BiomeTypeConfigHandler;
import net.hdt.neutronia.base.handler.server.DimensionConfig;
import net.hdt.neutronia.groups.world.blocks.BlockGlowcelium;
import net.hdt.neutronia.groups.world.blocks.BlockGlowshroom;
import net.hdt.neutronia.groups.world.world.CaveBiomeGenerator;
import net.hdt.neutronia.groups.world.world.caves.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class CaveBiomes extends Component {

    public static List<CaveBiomeGenerator> biomes;

    public static BlockMod glowcelium;
    public static Block glowshroom;

    public static int glowshroomGrowthRate;

    public static boolean glowceliumEnabled;
    boolean enableStairsAndSlabs, enableWalls;

    @Override
    public void setupConfig() {
        biomes = new ArrayList<>();

        glowceliumEnabled = loadPropBool("Enable Glowcelium and Glowshrooms", "", false);
        enableStairsAndSlabs = loadPropBool("Enable stairs and slabs", "", true) && GlobalConfig.enableVariants;
        enableWalls = loadPropBool("Enable walls", "", true) && GlobalConfig.enableVariants;

        glowshroomGrowthRate = loadPropInt("Glowshroom Growth Rate", "The smaller, the faster glowshrooms will spread. Vanilla mushroom speed is 25.", 20);

        biomes.add(loadUndergrondBiomeInfo("Lush", new CaveBiomeLush(), 160, Type.JUNGLE));
        biomes.add(loadUndergrondBiomeInfo("Sandstone", new CaveBiomeSandstone(), 160, Type.SANDY));
        biomes.add(loadUndergrondBiomeInfo("Slime", new CaveBiomeSlime(), 240, Type.SWAMP));
        biomes.add(loadUndergrondBiomeInfo("Prismarine", new CaveBiomePrismarine(), 200, Type.OCEAN));
        biomes.add(loadUndergrondBiomeInfo("Spider", new CaveBiomeSpiderNest(), 160, Type.PLAINS));
        biomes.add(loadUndergrondBiomeInfo("Overgrown", new CaveBiomeOvergrown(), 160, Type.FOREST));
        biomes.add(loadUndergrondBiomeInfo("Icy", new CaveBiomeIcy(), 160, Type.COLD));
        biomes.add(loadUndergrondBiomeInfo("Lava", new CaveBiomeLava(), 160, Type.MESA));
        biomes.add(loadUndergrondBiomeInfo("Glowshroom", new CaveBiomeGlowshroom(), 160, Type.MOUNTAIN, Type.MUSHROOM));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (glowceliumEnabled) {
            glowcelium = new BlockGlowcelium();
            glowshroom = new BlockGlowshroom();

            RecipeHandler.addShapelessOreDictRecipe(new ItemStack(Items.MUSHROOM_STEW), "mushroomAny", "mushroomAny", new ItemStack(Items.BOWL));
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (glowceliumEnabled) {
            OreDictionary.registerOre("mushroomAny", Blocks.RED_MUSHROOM);
            OreDictionary.registerOre("mushroomAny", Blocks.BROWN_MUSHROOM);
            OreDictionary.registerOre("mushroomAny", glowshroom);
        }
    }

    @SubscribeEvent
    public void onOreGenerate(OreGenEvent.GenerateMinable event) {
        if (event.getType() == EventType.DIRT) {
            World world = event.getWorld();
            BlockPos pos = event.getPos();

            Chunk chunk = world.getChunk(pos);

            for (CaveBiomeGenerator gen : biomes)
                gen.generate(chunk.x, chunk.z, world);
        }
    }

    @Override
    public boolean hasOreGenSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    private CaveBiomeGenerator loadUndergrondBiomeInfo(String name, CaveBiome biome, int rarity, Type... biomes) {
        String category = configCategory + "." + name;
        UndergroundBiomeInfo info = new UndergroundBiomeInfo(category, biome, rarity, biomes);

        return new CaveBiomeGenerator(info);
    }

    public static class UndergroundBiomeInfo {

        public final boolean enabled;
        public final CaveBiome biome;
        public final DimensionConfig dims;
        public final List<Type> types;
        public final int rarity;
        public final int minXSize, minYSize, minZSize;
        public final int xVariation, yVariation, zVariation;
        public final int minY, maxY;

        private UndergroundBiomeInfo(String category, CaveBiome biome, int rarity, Type... biomes) {
            this.enabled = GroupLoader.config.getBoolean("Enabled", category, true, "");
            this.biome = biome;
            this.types = BiomeTypeConfigHandler.parseBiomeTypeArrayConfig("Allowed Biome Types", category, biomes);
            this.rarity = GroupLoader.config.getInt("Rarity", category, rarity, 0, Integer.MAX_VALUE, "This biome will spawn in 1 of X valid chunks");

            dims = new DimensionConfig(category);

            minY = GroupLoader.config.getInt("Minimum Y Level", category, 10, 0, 255, "");
            maxY = GroupLoader.config.getInt("Maximum Y Level", category, 40, 0, 255, "");

            minXSize = GroupLoader.config.getInt("X Minimum", category, 26, 0, Integer.MAX_VALUE, "");
            minYSize = GroupLoader.config.getInt("Y Minimum", category, 12, 0, Integer.MAX_VALUE, "");
            minZSize = GroupLoader.config.getInt("Z Minimum", category, 26, 0, Integer.MAX_VALUE, "");

            xVariation = GroupLoader.config.getInt("X Variation", category, 14, 0, Integer.MAX_VALUE, "");
            yVariation = GroupLoader.config.getInt("Y Variation", category, 6, 0, Integer.MAX_VALUE, "");
            zVariation = GroupLoader.config.getInt("Z Variation", category, 14, 0, Integer.MAX_VALUE, "");

            biome.setupBaseConfig(category);
        }

    }

}
