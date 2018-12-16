package team.hdt.neutronia_legacy.groups.world.world.caves;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.groups.GroupLoader;

public class CaveBiomeIcy extends BasicCaveBiome {

    int stalagmiteChance;
    boolean usePackedIce;

    public CaveBiomeIcy() {
        super(Blocks.ICE.getDefaultState(), null, null, true);
    }

    @Override
    public void fillWall(World world, BlockPos pos, IBlockState state) {
        fillCeiling(world, pos, state);
    }

    @Override
    public void fillFloor(World world, BlockPos pos, IBlockState state) {
        IBlockState placeState = floorState;
        if (usePackedIce)
            placeState = Blocks.PACKED_ICE.getDefaultState();

        BlockPos placePos = pos;
        world.setBlockState(pos, placeState, 2);

        if (stalagmiteChance > 0 && world.rand.nextInt(stalagmiteChance) == 0) {
            int height = 3 + world.rand.nextInt(3);
            for (int i = 0; i < height; i++) {
                placePos = placePos.up();
                IBlockState stateAt = world.getBlockState(placePos);

                if (world.getBlockState(placePos).getBlock().isAir(stateAt, world, placePos))
                    world.setBlockState(placePos, placeState, 2);
                else break;
            }
        }
    }

    @Override
    public void setupConfig(String category) {
        stalagmiteChance = GroupLoader.config.getInt("Stalagmite Chance", category, 60, 0, Integer.MAX_VALUE, "The higher, the less stalagmites will spawn");
        usePackedIce = GroupLoader.config.getBoolean("Use Packed Ice", category, true, "");
    }

}
