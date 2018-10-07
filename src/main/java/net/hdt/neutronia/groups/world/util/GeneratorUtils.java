package net.hdt.neutronia.groups.world.util;

import net.hdt.neutronia.groups.world.features.overworld.Corals;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorUtils {
    public static final IBlockState[] CORAL_BLOCK_TYPES = {
            Corals.coralBlock[0].getDefaultState(),
            Corals.coralBlock[1].getDefaultState(),
            Corals.coralBlock[2].getDefaultState(),
            Corals.coralBlock[3].getDefaultState(),
            Corals.coralBlock[4].getDefaultState(),
            Corals.coralBlock[5].getDefaultState(),
            Corals.coralBlock[6].getDefaultState(),
            Corals.coralBlock[7].getDefaultState(),
            Corals.coralBlock[8].getDefaultState(),
            Corals.coralBlock[9].getDefaultState(),
            Corals.coralBlock[10].getDefaultState()
    };
    public static final IBlockState[] CORAL_PLANT_TYPES = {
            Corals.coral[0].getDefaultState(),
            Corals.coral[1].getDefaultState(),
            Corals.coral[2].getDefaultState(),
            Corals.coral[3].getDefaultState(),
            Corals.coral[4].getDefaultState(),
            Corals.coral[5].getDefaultState(),
            Corals.coral[6].getDefaultState(),
            Corals.coral[7].getDefaultState(),
            Corals.coral[8].getDefaultState(),
            Corals.coral[9].getDefaultState(),
            Corals.coral[10].getDefaultState()
    };
    public static final IBlockState[] CORAL_FAN_TYPES = {
            Corals.coralFan[0].getDefaultState(),
            Corals.coralFan[1].getDefaultState(),
            Corals.coralFan[2].getDefaultState(),
            Corals.coralFan[3].getDefaultState(),
            Corals.coralFan[4].getDefaultState(),
            Corals.coralFan[5].getDefaultState(),
            Corals.coralFan[6].getDefaultState(),
            Corals.coralFan[7].getDefaultState(),
            Corals.coralFan[8].getDefaultState(),
            Corals.coralFan[9].getDefaultState(),
            Corals.coralFan[10].getDefaultState()
    };
    public static final IBlockState[] CORAL_WALL_FAN_TYPES = {
            Corals.coralFanWall[0].getDefaultState(),
            Corals.coralFanWall[1].getDefaultState(),
            Corals.coralFanWall[2].getDefaultState(),
            Corals.coralFanWall[3].getDefaultState(),
            Corals.coralFanWall[4].getDefaultState(),
            Corals.coralFanWall[5].getDefaultState(),
            Corals.coralFanWall[6].getDefaultState(),
            Corals.coralFanWall[7].getDefaultState(),
            Corals.coralFanWall[8].getDefaultState(),
            Corals.coralFanWall[9].getDefaultState(),
            Corals.coralFanWall[10].getDefaultState()
    };
    private static final IBlockState WATER = Blocks.WATER.getDefaultState();

    //returns the top block that isn't water
    public static int getOceanSurfaceHeight(World worldIn, int x, int z) {
        int y = worldIn.getSeaLevel();
        IBlockState blocky;
        do {
            y--;
            blocky = worldIn.getBlockState(new BlockPos(x, y, z));
        } while (blocky == WATER && y > 0);

        return y + 1;
    }

    //Get an array of values that represent a line from point A to point B
    public static BlockPos[] getBresehnamArrays(BlockPos src, BlockPos dest) {
        return getBresehnamArrays(src.getX(), src.getY(), src.getZ(), dest.getX(), dest.getY(), dest.getZ());
    }

    //Get an array of values that represent a line from point A to point B
    public static BlockPos[] getBresehnamArrays(int x1, int y1, int z1, int x2, int y2, int z2) {
        int i, dx, dy, dz, absDx, absDy, absDz, x_inc, y_inc, z_inc, err_1, err_2, doubleAbsDx, doubleAbsDy, doubleAbsDz;

        BlockPos pixel = new BlockPos(x1, y1, z1);
        BlockPos lineArray[];

        dx = x2 - x1;
        dy = y2 - y1;
        dz = z2 - z1;
        x_inc = (dx < 0) ? -1 : 1;
        absDx = Math.abs(dx);
        y_inc = (dy < 0) ? -1 : 1;
        absDy = Math.abs(dy);
        z_inc = (dz < 0) ? -1 : 1;
        absDz = Math.abs(dz);
        doubleAbsDx = absDx << 1;
        doubleAbsDy = absDy << 1;
        doubleAbsDz = absDz << 1;

        if ((absDx >= absDy) && (absDx >= absDz)) {
            err_1 = doubleAbsDy - absDx;
            err_2 = doubleAbsDz - absDx;
            lineArray = new BlockPos[absDx + 1];
            for (i = 0; i < absDx; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.up(y_inc);
                    err_1 -= doubleAbsDx;
                }
                if (err_2 > 0) {
                    pixel = pixel.south(z_inc);
                    err_2 -= doubleAbsDx;
                }
                err_1 += doubleAbsDy;
                err_2 += doubleAbsDz;
                pixel = pixel.east(x_inc);
            }
        } else if ((absDy >= absDx) && (absDy >= absDz)) {
            err_1 = doubleAbsDx - absDy;
            err_2 = doubleAbsDz - absDy;
            lineArray = new BlockPos[absDy + 1];
            for (i = 0; i < absDy; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.east(x_inc);
                    err_1 -= doubleAbsDy;
                }
                if (err_2 > 0) {
                    pixel = pixel.south(z_inc);
                    err_2 -= doubleAbsDy;
                }
                err_1 += doubleAbsDx;
                err_2 += doubleAbsDz;
                pixel = pixel.up(y_inc);
            }
        } else {
            err_1 = doubleAbsDy - absDz;
            err_2 = doubleAbsDx - absDz;
            lineArray = new BlockPos[absDz + 1];
            for (i = 0; i < absDz; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.up(y_inc);
                    err_1 -= doubleAbsDz;
                }
                if (err_2 > 0) {
                    pixel = pixel.east(x_inc);
                    err_2 -= doubleAbsDz;
                }
                err_1 += doubleAbsDy;
                err_2 += doubleAbsDx;
                pixel = pixel.south(z_inc);
            }
        }
        lineArray[lineArray.length - 1] = pixel;

        return lineArray;
    }

}