package net.hdt.neutronia.groups.dimensions.world.biomes.end;

import net.hdt.neutronia.groups.dimensions.world.utils.EndFoliageColorizer;
import net.hdt.neutronia.groups.dimensions.world.utils.EndGrassColorizer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EndBiomeColorHelper {
    private static final EndBiomeColorHelper.ColorResolver GRASS_COLOR = (biome, blockPosition) ->
    {
        double temperature = (double) MathHelper.clamp(biome.getTemperature(blockPosition), 0.0F, 1.0F);
        double rainfall = (double) MathHelper.clamp(biome.getRainfall(), 0.0F, 1.0F);
        return biome.getModdedBiomeGrassColor(EndGrassColorizer.getGrassColor(temperature, rainfall));
    };

    private static final EndBiomeColorHelper.ColorResolver FOLIAGE_COLOR = (biome, blockPosition) ->
    {
        double temperature = (double) MathHelper.clamp(biome.getTemperature(blockPosition), 0.0F, 1.0F);
        double rainfall = (double) MathHelper.clamp(biome.getRainfall(), 0.0F, 1.0F);
        return biome.getModdedBiomeFoliageColor(EndFoliageColorizer.getFoliageColor(temperature, rainfall));
    };

    private static final EndBiomeColorHelper.ColorResolver WATER_COLOR = (biome, blockPosition) -> biome.getWaterColor();

    private static int getColorAtPos(IBlockAccess blockAccess, BlockPos pos, EndBiomeColorHelper.ColorResolver colorResolver) {
        int i = 0;
        int j = 0;
        int k = 0;

        for (BlockPos.MutableBlockPos mutableBlockPos : BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1), pos.add(1, 0, 1))) {
            int l = colorResolver.getColorAtPos(blockAccess.getBiome(mutableBlockPos), mutableBlockPos);
            i += (l & 16711680) >> 16;
            j += (l & 65280) >> 8;
            k += l & 255;
        }

        return (i / 9 & 255) << 16 | (j / 9 & 255) << 8 | k / 9 & 255;
    }

    public static int getGrassColorAtPos(IBlockAccess blockAccess, BlockPos pos) {
        return getColorAtPos(blockAccess, pos, GRASS_COLOR);
    }

    public static int getFoliageColorAtPos(IBlockAccess blockAccess, BlockPos pos) {
        return getColorAtPos(blockAccess, pos, FOLIAGE_COLOR);
    }

    public static int getWaterColorAtPos(IBlockAccess blockAccess, BlockPos pos) {
        return getColorAtPos(blockAccess, pos, WATER_COLOR);
    }

    @SideOnly(Side.CLIENT)
    interface ColorResolver {
        int getColorAtPos(Biome biome, BlockPos blockPosition);
    }
}