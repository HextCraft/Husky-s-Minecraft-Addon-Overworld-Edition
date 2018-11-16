package net.hdt.neutronia.groups.building.features;

import net.hdt.neutronia.base.blocks.BlockNeutroniaFence;
import net.hdt.neutronia.base.blocks.BlockNeutroniaFenceGate;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class VanillaFencesAndFenceGates extends Component {

    public static void add(String name, Block block, ItemStack materialTwo, int meta, boolean fence, boolean fenceGate, boolean doIt) {
        if (!doIt)
            return;

        String wallName;
        IBlockState state = block.getStateFromMeta(meta);

        if (fence) {
            wallName = name + "_fence";
            BlockNeutroniaFence.initFence(block, materialTwo, meta, new BlockNeutroniaFence(wallName, state));
        }

        if (fenceGate) {
            wallName = name + "_fence_gate";
            BlockNeutroniaFenceGate.initFenceGate(block, materialTwo, meta, new BlockNeutroniaFenceGate(wallName));
        }
    }

    public static void add(String name, Block block, ItemStack materialTwo, int meta, boolean fence, boolean fenceGate, boolean doIt, CreativeTabs creativeTabs) {
        if (!doIt)
            return;

        String wallName;
        IBlockState state = block.getStateFromMeta(meta);

        if (fence) {
            wallName = name + "_fence";
            BlockNeutroniaFence neutroniaWall = new BlockNeutroniaFence(wallName, state);
            neutroniaWall.setCreativeTab(creativeTabs);
            BlockNeutroniaFence.initFence(block, materialTwo, meta, neutroniaWall);
        }

        if (fenceGate) {
            wallName = name + "_fence_gate";
            BlockNeutroniaFenceGate neutroniaFenceGate = new BlockNeutroniaFenceGate(wallName);
            neutroniaFenceGate.setCreativeTab(creativeTabs);
            BlockNeutroniaFenceGate.initFenceGate(block, materialTwo, meta, neutroniaFenceGate);
        }
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
