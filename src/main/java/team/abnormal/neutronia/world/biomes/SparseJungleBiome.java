package team.abnormal.neutronia.world.biomes;

import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SparseJungleBiome extends BiomeJungle {

    public SparseJungleBiome(BiomeProperties properties) {
        super(true, properties);
        spawnableCreatureList.add(new SpawnListEntry(EntityParrot.class, 40, 1, 2));
        spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 1, 1, 1));
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
}