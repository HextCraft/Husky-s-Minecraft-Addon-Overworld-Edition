package net.hdt.neutronia.groups.building;

import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.groups.building.features.*;

public class Building extends Group {

    @Override
    public void addFeatures() {
        addFeatures(
                new LogBlocks(),
                new MoreStoneBlocks(),
                new SoulStone(),
                new VanillaStairsAndSlabs(),
                new VanillaWalls(),
                new WoodBlocks(),
                new WorldStoneBricks()
        );
    }

    @Override
    protected boolean canEnable() {
        return true;
    }
}
