package net.hdt.neutronia.groups.world.world.gen.tree;

import net.hdt.neutronia.groups.world.WorldGenConstants;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class NeutroniaTreeGenerator extends WorldGenTrees implements WorldGenConstants {

    public boolean isWorldGen = true;

    public NeutroniaTreeGenerator(boolean isWorldGen) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
    }

    public NeutroniaTreeGenerator(boolean isWorldGen, int minTreeHeight, IBlockState logState, IBlockState leavesState, boolean vinesGrow) {
        super(!isWorldGen, minTreeHeight, logState, leavesState, vinesGrow);
        this.isWorldGen = isWorldGen;
    }

    public NeutroniaTreeGenerator(boolean isWorldGen, int minTreeHeight, IBlockState logState, IBlockState leavesState) {
        super(!isWorldGen, minTreeHeight, logState, leavesState, false);
        this.isWorldGen = isWorldGen;
    }

    public NeutroniaTreeGenerator(boolean isWorldGen, IBlockState logState, IBlockState leavesState) {
        super(!isWorldGen, 4, logState, leavesState, false);
        this.isWorldGen = isWorldGen;
    }

}