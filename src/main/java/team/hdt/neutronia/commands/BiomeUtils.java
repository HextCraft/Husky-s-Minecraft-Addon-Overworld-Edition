package team.hdt.neutronia.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeUtils {

    static BlockPos spiralOutwardsLookingForBiome(ICommandSender sender, World world, Biome biomeToFind, double startX, double startZ, int timeout) {
        double a = 16 / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x;
        double z;
        double dist = 0;
        int n;
        long start = System.currentTimeMillis();
        BlockPos.PooledMutableBlockPos pos = BlockPos.PooledMutableBlockPos.retain();
        int previous = 0;
        int i = 0;
        for (n = 0; dist < Integer.MAX_VALUE; ++n) {
            if ((System.currentTimeMillis() - start) > timeout) {
                return null;
            }
            double rootN = Math.sqrt(n);
            dist = a * rootN;
            x = startX + (dist * Math.sin(b * rootN));
            z = startZ + (dist * Math.cos(b * rootN));
            pos.setPos(x, 0, z);
            if (sender instanceof EntityPlayer) {
                if (previous == 3)
                    previous = 0;
                String s = (previous == 0 ? "." : previous == 1 ? ".." : "...");
                ((EntityPlayer) sender).sendStatusMessage(new TextComponentString("Scanning" + s), true);
                if (i == 1501) {
                    previous++;
                    i = 0;
                }
                i++;
            }
            if (world.getBiome(pos).equals(biomeToFind)) {
                pos.release();
                if (sender instanceof EntityPlayer)
                    ((EntityPlayer) sender).sendStatusMessage(new TextComponentString("Found Biome"), true);
                return new BlockPos((int) x, 0, (int) z);
            }
        }
        return null;
    }

    private static String getBiomeName(Biome biome) {
        if (biome != null) {
            final String original = biome.getBiomeName();
            StringBuilder fixed = new StringBuilder();
            char pre = ' ';
            for (int i = 0; i < original.length(); i++) {
                final char c = original.charAt(i);
                if (Character.isUpperCase(c) && Character.isLowerCase(pre) && Character.isAlphabetic(pre)) {
                    fixed.append(" ");
                }
                fixed.append(String.valueOf(c));
                pre = c;
            }

            return fixed.toString();

        }

        return "";
    }

}