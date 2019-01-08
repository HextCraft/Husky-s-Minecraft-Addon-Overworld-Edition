package team.abnormals.neutronia.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenUtils {

    public static int getGroundFromAbove(World world, int x, int z, Block spawnBlock) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 31) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt == spawnBlock;
        }

        return y;
    }

}
