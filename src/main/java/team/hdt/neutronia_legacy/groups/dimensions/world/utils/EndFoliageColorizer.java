package team.hdt.neutronia_legacy.groups.dimensions.world.utils;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EndFoliageColorizer {
    private static int[] foliageBuffer = new int[65536];

    public static void setFoliageBiomeColorizer(int[] foliageBufferIn) {
        foliageBuffer = foliageBufferIn;
    }

    public static int getFoliageColor(double temperature, double humidity) {
        humidity = humidity * temperature;
        int i = (int) ((1.0D - temperature) * 255.0D);
        int j = (int) ((1.0D - humidity) * 255.0D);
        return foliageBuffer[j << 8 | i];
    }
}