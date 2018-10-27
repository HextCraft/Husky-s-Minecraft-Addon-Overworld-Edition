package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenBaobabTree;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BaobabTrees extends Component {

    public static BlockPVJLog baobabLog;
    public static BlockPVJLeaves baobabLeaves;
    public static BlockPVJSapling baobabSapling;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        baobabLog = new BlockPVJLog("baobab_log");
        baobabLeaves = new BlockPVJLeaves("baobab_leaves");
        baobabSapling = new BlockPVJSapling("baobab_sapling", new WorldGenBaobabTree());
        baobabLeaves.setSapling(baobabSapling);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}