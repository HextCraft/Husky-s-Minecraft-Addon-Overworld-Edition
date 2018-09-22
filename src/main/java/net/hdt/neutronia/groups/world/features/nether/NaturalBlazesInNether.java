package net.hdt.neutronia.groups.world.features.nether;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class NaturalBlazesInNether extends Component {

    int weight, min, max;
    boolean restrictToNetherrack;

    List<String> allowedBlocks;

    @Override
    public void setupConfig() {
        weight = loadProperty("Spawn Weight", 5).setComment("The higher, the more will spawn").get();
        min = loadProperty("Smallest spawn group", 1).get();
        max = loadProperty("Largest spawn group", 2).get();

        restrictToNetherrack = loadProperty("Block restrictions", true).setComment("Make naturally spawned blazes only spawn in allowed blocks").get();
        allowedBlocks = Arrays.asList(loadProperty("Allowed spawn blocks", new String[]{
                Blocks.NETHERRACK.getRegistryName().toString(),
                Blocks.SOUL_SAND.getRegistryName().toString(),
                Blocks.MAGMA.getRegistryName().toString(),
                "neutronia:basalt"
        }).setComment("Only used if \" Block restrictions\" is enabled.").get());
    }

    @Override
    public String getDescription() {
        return "Makes blazes spawn naturally";
    }

    @Override
    public void init(FMLInitializationEvent event) {
        SpawnListEntry blazeEntry = new SpawnListEntry(EntityBlaze.class, weight, min, max);
        BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).forEach(biome -> biome.getSpawnableList(EnumCreatureType.MONSTER).add(blazeEntry));
    }

    @SubscribeEvent
    public void onSpawn(LivingSpawnEvent.CheckSpawn event) {
        if (restrictToNetherrack && !event.isSpawner() && event.getEntityLiving() instanceof EntityBlaze && event.getResult() != Result.DENY && event.getEntityLiving().world instanceof WorldServer) {
            EntityBlaze blaze = (EntityBlaze) event.getEntityLiving();
            WorldServer world = (WorldServer) blaze.world;
            BlockPos pos = blaze.getPosition();
            Block block = world.getBlockState(pos.down()).getBlock();
            ResourceLocation res = block.getRegistryName();
            if (res != null) {
                boolean allowedBlock = allowedBlocks.contains(res.toString());
                boolean fortress = world.getChunkProvider().isInsideStructure(world, "Fortress", pos);
                if (!fortress && !allowedBlock)
                    event.setResult(Result.DENY);
            }
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public String getName() {
        return "Naturally Spawning Blazes";
    }

}
