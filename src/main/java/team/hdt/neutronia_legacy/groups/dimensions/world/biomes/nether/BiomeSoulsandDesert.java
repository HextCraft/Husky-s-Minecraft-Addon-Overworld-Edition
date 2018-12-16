package team.hdt.neutronia_legacy.groups.dimensions.world.biomes.nether;

import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeSoulsandDesert extends BiomeNether {

    public BiomeSoulsandDesert() {
        super(new BiomeProperties("Soulsand Desert").setTemperature(4.0F).setRainfall(0.0F).setRainDisabled(), "soulsand_desert");
        topBlock = Blocks.SOUL_SAND.getDefaultState();
        fillerBlock = Blocks.SOUL_SAND.getDefaultState();
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMagmaCube.class, 25, 1, 4));
    }

}
