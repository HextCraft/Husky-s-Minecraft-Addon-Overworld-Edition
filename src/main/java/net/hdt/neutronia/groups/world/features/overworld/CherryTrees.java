package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenTreeCherry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CherryTrees extends Component {

    public static BlockPVJLog cherryLog;
    public static BlockPVJLeaves cherryLeaves;
    public static BlockPVJSapling cherrySapling;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        cherryLog = new BlockPVJLog("cherry_log");
        cherryLeaves = new BlockPVJLeaves("cherry_leaves");
        cherrySapling = new BlockPVJSapling("cherry_sapling", new WorldGenTreeCherry());
        cherryLeaves.setSapling(cherrySapling);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}