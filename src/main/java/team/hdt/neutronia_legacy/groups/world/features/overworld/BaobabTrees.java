package team.hdt.neutronia_legacy.groups.world.features.overworld;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockPVJLeaves;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockPVJLog;
import team.hdt.neutronia_legacy.groups.world.blocks.BlockPVJSapling;
import team.hdt.neutronia_legacy.groups.world.world.gen.features.tree.WorldGenBaobabTree;

public class BaobabTrees extends Component {

    public static BlockPVJLog baobabLog;
    public static BlockPVJLeaves baobabLeaves;
    public static BlockPVJSapling baobabSapling;
    public static BlockNeutroniaBase baobabPlanks;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        baobabLog = new BlockPVJLog("baobab_log");
        baobabPlanks = new BlockNeutroniaBase(Material.WOOD, "baobab_planks");
        baobabLeaves = new BlockPVJLeaves("baobab_leaves");
        baobabSapling = new BlockPVJSapling("baobab_sapling", new WorldGenBaobabTree());
        baobabLeaves.setSapling(baobabSapling);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}