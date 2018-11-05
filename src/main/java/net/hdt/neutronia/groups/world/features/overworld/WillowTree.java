package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.features.tree.WorldGenWillowTree;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WillowTree extends Component {

    public static BlockPVJLog willowLog;
    public static BlockPVJLeaves willowLeaves;
    public static BlockPVJSapling willowSapling;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        willowLog = new BlockPVJLog("willow_log");
        willowLeaves = new BlockPVJLeaves("willow_leaves");
        willowSapling = new BlockPVJSapling("willow_sapling", new WorldGenWillowTree());
        willowLeaves.setSapling(willowSapling);
    }

    /*@SubscribeEvent
    public void onChunkDecorate(DecorateBiomeEvent.Decorate event) {
        if(event.getWorld().getBiome(Objects.requireNonNull(event.getPlacementPos())) == Biomes.SWAMPLAND) {
            if(event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE) {
                event.setResult(Event.Result.DENY);
                new WorldGenWillowTree().generate(event.getWorld(), event.getRand(), event.getPlacementPos());
            }
        }
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }*/

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}