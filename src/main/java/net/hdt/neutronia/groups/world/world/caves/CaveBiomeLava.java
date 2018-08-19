package net.hdt.neutronia.groups.world.world.caves;

import net.hdt.neutronia.base.groups.GroupLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CaveBiomeLava extends BasicCaveBiome {

    int lavaChance, obsidianChance;
    boolean usePackedIce;

    public CaveBiomeLava() {
        super(Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), true);
    }

    @Override
    public void fillCeiling(World world, BlockPos pos, IBlockState state) {
        world.setBlockState(pos, Blocks.MAGMA.getDefaultState(), 2);
    }

    @Override
    public void fillWall(World world, BlockPos pos, IBlockState state) {
        world.setBlockState(pos, Blocks.MAGMA.getDefaultState(), 2);
    }

    @Override
    public void fillFloor(World world, BlockPos pos, IBlockState state) {
        if (lavaChance > 0 && !isBorder(world, pos, state) && world.rand.nextInt(lavaChance) == 0)
            world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        else if (obsidianChance > 0 && world.rand.nextInt(obsidianChance) == 0)
            world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState(), 2);
        else
            world.setBlockState(pos, Blocks.MAGMA.getDefaultState(), 2);
    }

    @Override
    public void setupConfig(String category) {
        lavaChance = GroupLoader.config.getInt("Lava Chance", category, 4, 0, Integer.MAX_VALUE, "The higher, the less lava will spawn");
        obsidianChance = GroupLoader.config.getInt("Obsidian Chance", category, 16, 0, Integer.MAX_VALUE, "The higher, the less obsidian will spawn");
    }
}
