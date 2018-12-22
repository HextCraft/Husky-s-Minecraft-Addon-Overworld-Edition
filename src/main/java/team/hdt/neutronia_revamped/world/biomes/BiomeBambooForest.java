package team.hdt.neutronia_revamped.world.biomes;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeBambooForest extends Biome {

    public BiomeBambooForest() {
        super(new BiomeProperties("Bamboo Forest").setBaseBiome(Biomes.JUNGLE.getBiomeName()).setTemperature(0.95F).setRainfall(0.9F));

        this.decorator.grassPerChunk = 25;
        this.decorator.flowersPerChunk = 4;

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityParrot.class, 40, 1, 2));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }

}
