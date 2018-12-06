package team.hdt.neutronia.groups.world.world.caves;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia.base.groups.GroupLoader;

public class CaveBiomeWet extends BasicCaveBiome {

    int seaLanternChance, waterChance;

    public CaveBiomeWet() {
        super(Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState());
    }

    @Override
    public void fillWall(World world, BlockPos pos, IBlockState state) {
        super.fillWall(world, pos, state);
    }

    @Override
    public void fillFloor(World world, BlockPos pos, IBlockState state) {
        if (waterChance > 0 && !isBorder(world, pos, state) && world.rand.nextInt(waterChance) == 0)
            world.setBlockState(pos, Blocks.WATER.getDefaultState());
        else super.fillFloor(world, pos, state);
    }

    @Override
    public void setupConfig(String category) {
        seaLanternChance = GroupLoader.config.getInt("Sea Lantern Chance", category, 120, 0, Integer.MAX_VALUE, "The higher, the less sea lanterns will spawn");
        waterChance = GroupLoader.config.getInt("Water Chance", category, 4, 0, Integer.MAX_VALUE, "The higher, the less water will spawn");
    }


}
