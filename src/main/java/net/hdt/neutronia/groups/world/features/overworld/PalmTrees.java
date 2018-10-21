package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.world.blocks.BlockCoconut;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLeaves;
import net.hdt.neutronia.groups.world.blocks.BlockPVJLog;
import net.hdt.neutronia.groups.world.blocks.BlockPVJSapling;
import net.hdt.neutronia.groups.world.world.gen.tree.WorldGenPalmTree;
import net.hdt.neutronia.items.ItemCoconut;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class PalmTrees extends Component {

    public static Block coconut;
    public static Item crackedCoconut;
    public static Block palmLog, palmLeaf, palmSapling;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        coconut = new BlockCoconut();
        crackedCoconut = new ItemCoconut(coconut);
        palmLog = new BlockPVJLog("palm_log");
        palmSapling = new BlockPVJSapling("palm_sapling", new WorldGenPalmTree());
        palmLeaf = new BlockPVJLeaves("palm_leaves", palmSapling);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}